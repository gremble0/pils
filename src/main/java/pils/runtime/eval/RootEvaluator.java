package pils.runtime.eval;

import pils.tokenizer.Tokenizer;

abstract public class RootEvaluator {
  public static void eval(Tokenizer tokenizer) {
    if (tokenizer.getToken() != null)
      throw new AssertionError("Attempt to evaluate already used tokenizer");

    tokenizer.advance();
    RootEvaluator.evalLoadedRecursively(tokenizer);
  }

  private static void evalLoadedRecursively(Tokenizer tokenizer) {
    switch (tokenizer.getToken().type()) {
      case CHAR_LITERAL:
        CharEvaluator.eval(tokenizer);
        break;

      case FLOAT_LITERAL:
        FloatEvaluator.eval(tokenizer);
        break;

      case INT_LITERAL:
        IntEvaluator.eval(tokenizer);
        break;

      case LPAREN:
        FunctionCallEvaluator.eval(tokenizer);
        break;

      case RPAREN:
        // TODO: this is a programmer error, should throw some exception maybe
        throw new AssertionError("Unexpected token: " + tokenizer.getToken());
        break;

      case STRING_LITERAL:
        StringEvaluator.eval(tokenizer);
        break;

      case SYMBOL:
        SymbolEvaluator.eval(tokenizer);
        break;

      case EOF:
        return;
    }

    RootEvaluator.evalLoadedRecursively(tokenizer);
  }
}
