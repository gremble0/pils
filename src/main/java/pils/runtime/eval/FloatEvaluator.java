package pils.runtime.eval;

import pils.tokenizer.Tokenizer;
import pils.tokenizer.Token;

abstract public class FloatEvaluator {
  public static void eval(Tokenizer tokenizer) {
    if (tokenizer.getToken().type() != Token.Type.FLOAT_LITERAL)
      throw new AssertionError();

    tokenizer.advance();
  }
}
