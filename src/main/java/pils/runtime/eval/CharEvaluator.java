package pils.runtime.eval;

import pils.runtime.objects.PilsChar;
import pils.tokenizer.Token;
import pils.tokenizer.Tokenizer;

abstract public class CharEvaluator {
  public static PilsChar eval(Tokenizer tokenizer) {
    Token token = tokenizer.getToken();
    if (token.type() != Token.Type.CHAR_LITERAL)
      throw new AssertionError();

    tokenizer.advance();
    return new PilsChar(token.getChar());
  }
}
