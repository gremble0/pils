package pils.tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TokenizerTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(FileNotFoundException.class, () -> new Tokenizer(""));
    Assertions.assertDoesNotThrow(() -> new Tokenizer("src/test/tokenizer/source/empty.pls"));
  }

  @Test
  public void testBasicTokenizer() throws FileNotFoundException {
    Tokenizer tokenizer = new Tokenizer("src/test/tokenizer/source/basic.pls");
    PrintStream logStream = new PrintStream(new FileOutputStream("src/test/tokenizer/logs/basic.log"));
    PrintStream expectedStream = new PrintStream(new FileOutputStream("src/test/tokenizer/expected/basic.log"));
    tokenizer.setLogStream(logStream);

    while (!tokenizer.eof())
      tokenizer.advance();

    Assertions.assertEquals(logStream, expectedStream);
  }
}
