package pils.tokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class TokenizerTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(FileNotFoundException.class, () -> { new Tokenizer(""); });
    Assertions.assertDoesNotThrow(() -> { new Tokenizer("src/test/source/empty.pls"); });
  }
}
