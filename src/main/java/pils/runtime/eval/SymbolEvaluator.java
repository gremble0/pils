package pils.runtime.eval;

import pils.tokenizer.Tokenizer;
import pils.runtime.objects.PilsObject;
import pils.tokenizer.Token;

abstract public class SymbolEvaluator {
  public static PilsObject eval(Tokenizer tokenizer) {
    Token token = tokenizer.getToken();
    if (token.type() != Token.Type.SYMBOL)
      throw new AssertionError();

    tokenizer.advance();
    // TODO: scope lookup (probably from parameter)
    return null;
  }
}
