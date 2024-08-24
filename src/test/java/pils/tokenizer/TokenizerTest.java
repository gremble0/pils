package pils.tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

public class TokenizerTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(FileNotFoundException.class, () -> new Tokenizer(""));
    Assertions.assertDoesNotThrow(() -> new Tokenizer("src/test/tokenizer/source/empty.pls"));
  }

  @Test
  public void testBasicTokenizer() throws FileNotFoundException {
    // Initialize tokenizer and logging
    Tokenizer tokenizer = new Tokenizer("src/test/tokenizer/source/basic.pls");
    PrintStream logStream = new PrintStream(new FileOutputStream("src/test/tokenizer/logs/basic.log"));
    tokenizer.setLogStream(logStream);

    // Tokenizer the file
    while (!tokenizer.eof())
      tokenizer.advance();

    // Assert successful tokenization
    BufferedReader expectedReader = new BufferedReader(new FileReader("src/test/tokenizer/expected/basic.log"));
    BufferedReader actualReader = new BufferedReader(new FileReader("src/test/tokenizer/logs/basic.log"));
    List<String> expectedLines = expectedReader.lines().toList();
    List<String> actualLines = actualReader.lines().toList();
    Assertions.assertEquals(expectedLines, actualLines);
  }
}
