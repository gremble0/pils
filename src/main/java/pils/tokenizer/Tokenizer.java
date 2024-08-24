package pils.tokenizer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.Optional;

public class Tokenizer {
  private final LineNumberReader reader;
  private Optional<PrintStream> logStream;
  private String line;
  private int lineCursor;
  private Token token;

  public Tokenizer(String path) throws FileNotFoundException {
    this(path, null);
  }

  public Tokenizer(String path, PrintStream logStream) throws FileNotFoundException {
    this.reader = new LineNumberReader(new FileReader(path));
    this.setLogStream(logStream);
    this.readLine();
  }

  public void setLogStream(PrintStream logStream) {
    this.logStream = Optional.ofNullable(logStream);
  }

  public boolean eof() {
    return this.token == Token.EOF;
  }

  public void advance() {
    if (this.lineCursor >= this.line.length())
      this.readLine();

    if (this.token == Token.EOF)
      return;

    char curChar = this.line.charAt(this.lineCursor);
    switch (curChar) {
      case '(':
        this.setToken(Token.LPAREN);
        ++this.lineCursor;
        break;

      case ')':
        this.setToken(Token.RPAREN);
        ++this.lineCursor;
        break;

      default: {
        this.setToken(Token.SYMBOL);
        ++this.lineCursor;
        break;
      }
    }
  }

  private void readLine() {
    this.lineCursor = 0;

    try {
      this.line = this.reader.readLine();
      if (this.line == null) {
        this.reader.close();
        this.setToken(Token.EOF);
      }
    } catch (IOException exception) {
      // TODO: better error - what does this even mean?
      throw new TokenizerException("IO error");
    }
  }

  private void setToken(Token token) {
    if (this.logStream.isPresent())
      this.logStream.get().println("Line " + this.reader.getLineNumber() + ": " + token);
    this.token = token;
  }
}
