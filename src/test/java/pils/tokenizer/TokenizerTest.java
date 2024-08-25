package pils.tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.List;

public class TokenizerTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(FileNotFoundException.class, () -> new Tokenizer(""));
    Assertions.assertDoesNotThrow(() -> new Tokenizer("src/test/tokenizer/source/empty.pils"));
  }

  private void tokenizeFile(String baseName) throws Exception {
    // Initialize tokenizer and logging
    PrintStream logStream = new PrintStream(new FileOutputStream("src/test/tokenizer/logs/" + baseName + ".log"));
    Tokenizer tokenizer = new Tokenizer("src/test/tokenizer/source/" + baseName + ".pils", logStream);

    // Tokenizer the file
    while (!tokenizer.eof())
      tokenizer.advance();

    // Assert successful tokenization
    BufferedReader expectedReader = new BufferedReader(
        new FileReader("src/test/tokenizer/expected/" + baseName + ".log"));
    BufferedReader actualReader = new BufferedReader(new FileReader("src/test/tokenizer/logs/" + baseName + ".log"));

    List<String> expectedLines = expectedReader.lines().toList();
    List<String> actualLines = actualReader.lines().toList();

    Assertions.assertIterableEquals(expectedLines, actualLines);

    expectedReader.close();
    actualReader.close();
  }

  @Test
  public void testTokenizeParens() throws Exception {
    this.tokenizeFile("parens");
  }

  @Test
  public void testBasicTokenizer() throws Exception {
    this.tokenizeFile("basic");
  }
}
