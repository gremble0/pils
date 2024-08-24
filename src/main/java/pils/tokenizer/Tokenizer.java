package pils.tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Optional;

public class Tokenizer {
  private final BufferedReader reader;
  private Optional<PrintStream> logStream;
  private Token curToken;

  public Tokenizer(String path) throws FileNotFoundException {
    this(path, null);
  }

  public Tokenizer(String path, PrintStream logStream) throws FileNotFoundException {
    this.reader = new BufferedReader(new FileReader(path));
    this.logStream = Optional.ofNullable(logStream);
  }

  public void setLogStream(PrintStream logStream) {
    this.logStream = Optional.of(logStream);
  }

  public boolean eof() {
    return this.curToken == Token.EOF;
  }

  public void advance() {
  }
}
