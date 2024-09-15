package pils.runtime.eval;

import pils.runtime.objects.PilsString;
import pils.tokenizer.Token;
import pils.tokenizer.Tokenizer;

abstract public class StringEvaluator {
  public static PilsString eval(Tokenizer tokenizer) {
    Token token = tokenizer.getToken();
    if (token.type() != Token.Type.STRING_LITERAL)
      throw new AssertionError();

    tokenizer.advance();
    return new PilsString(token.getString());
  }
}
