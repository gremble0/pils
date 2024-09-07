package pils.runtime.eval;

import pils.tokenizer.Tokenizer;
import pils.tokenizer.Token;

abstract public class IntEvaluator {
  public static void eval(Tokenizer tokenizer) {
    if (tokenizer.getToken().type() != Token.Type.INT_LITERAL)
      throw new AssertionError();

  }
}
