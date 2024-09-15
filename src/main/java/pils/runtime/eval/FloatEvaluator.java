package pils.runtime.eval;

import pils.runtime.objects.PilsFloat;
import pils.tokenizer.Token;
import pils.tokenizer.Tokenizer;

abstract public class FloatEvaluator {
  public static PilsFloat eval(Tokenizer tokenizer) {
    Token token = tokenizer.getToken();
    if (token.type() != Token.Type.FLOAT_LITERAL)
      throw new AssertionError();

    tokenizer.advance();
    return new PilsFloat(token.getFloat());
  }
}
