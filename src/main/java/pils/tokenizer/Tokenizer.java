package pils.tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Optional;

public class Tokenizer {
  private final BufferedReader reader;
  private Optional<PrintStream> logStream;
  private String curLine;
  private int linePos;
  private Token curToken;

  public Tokenizer(String path) throws FileNotFoundException {
    this(path, null);
  }

  public Tokenizer(String path, PrintStream logStream) throws FileNotFoundException {
    this.reader = new BufferedReader(new FileReader(path));
    this.setLogStream(logStream);
  }

  public void setLogStream(PrintStream logStream) {
    this.logStream = Optional.ofNullable(logStream);
  }

  public boolean eof() {
    return this.curToken == Token.EOF;
  }

  public void advance() {
    this.readLine();
    if (this.curToken == Token.EOF)
      return;

    char curChar = this.curLine.charAt(this.linePos);
    switch (curChar) {
      case '(':
        this.setCurToken(Token.LPAREN);
        ++this.linePos;
        break;
      case ')':
        this.setCurToken(Token.RPAREN);
        ++this.linePos;
        break;
      default: {
        this.setCurToken(Token.SYMBOL);
        ++this.linePos;
      }
    }
  }

  private void readLine() {
    this.linePos = 0;

    try {
      this.curLine = this.reader.readLine();
      if (this.curLine == null) {
        this.reader.close();
        this.setCurToken(Token.EOF);
      }
    } catch (IOException exception) {
      // TODO: better error - what does this even mean?
      throw new TokenizerException("IO error");
    }
  }

  private void setCurToken(Token token) {
    if (this.logStream.isPresent())
      this.logStream.get().println(token);
    this.curToken = token;
  }
}
