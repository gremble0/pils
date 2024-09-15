package pils.runtime.eval;

import pils.runtime.objects.PilsInt;
import pils.tokenizer.Token;
import pils.tokenizer.Tokenizer;

abstract public class IntEvaluator {
  public static PilsInt eval(Tokenizer tokenizer) {
    Token token = tokenizer.getToken();
    if (token.type() != Token.Type.INT_LITERAL)
      throw new AssertionError();

    tokenizer.advance();
    return new PilsInt(token.getInt());
  }
}
