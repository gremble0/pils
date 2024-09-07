package pils.runtime.eval;

import pils.tokenizer.Tokenizer;
import pils.runtime.objects.PilsObject;
import pils.tokenizer.Token;

abstract public class StringEvaluator {
  public static PilsObject eval(Tokenizer tokenizer) {
    if (tokenizer.getToken().type() != Token.Type.STRING_LITERAL)
      throw new AssertionError();

    tokenizer.advance();
    return null;
  }
}
