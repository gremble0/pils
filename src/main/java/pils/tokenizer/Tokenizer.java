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
    return this.token != null && this.token.type() == Token.Type.EOF;
  }

  public void advance() {
    // If we're finished tokenizing the current line go onto the next
    if (this.lineCursor >= this.line.length())
      // If we've reached the end of the file
      if (this.readLine())
        return;

    char curChar = this.line.charAt(this.lineCursor);
    switch (curChar) {
      case '(':
        this.setToken(new Token(Token.Type.LPAREN, Optional.empty()));
        ++this.lineCursor;
        break;

      case ')':
        this.setToken(new Token(Token.Type.RPAREN, Optional.empty()));
        ++this.lineCursor;
        break;

      default: {
        this.setToken(new Token(Token.Type.SYMBOL, Optional.of("hey")));
        ++this.lineCursor;
        break;
      }
    }
  }

  private boolean readLine() {
    this.lineCursor = 0;

    try {
      this.line = this.reader.readLine();
      if (this.line == null) {
        this.reader.close();
        this.setToken(new Token(Token.Type.EOF, Optional.empty()));
        return true;
      }
    } catch (IOException exception) {
      // TODO: better error - what does this even mean?
      throw new TokenizerException("IO error");
    }

    return false;
  }

  private void setToken(Token token) {
    if (this.logStream.isPresent())
      this.logStream.get().println("Line " + this.reader.getLineNumber() + ": " + token);
    this.token = token;
  }
}
