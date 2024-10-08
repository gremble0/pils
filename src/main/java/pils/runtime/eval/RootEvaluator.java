package pils.runtime.eval;

import pils.tokenizer.Tokenizer;

abstract public class RootEvaluator {
  /**
   * Evaluate a tokenizer. The tokenizer has to be unused (never advanced)
   */
  public static void eval(Tokenizer tokenizer) {
    // Tokenizer should not have loaded any tokens yet
    if (tokenizer.getToken() != null)
      throw new AssertionError("Attempt to evaluate already used tokenizer");

    // Load first token
    tokenizer.advance();
    RootEvaluator.evalLoadedRecursively(tokenizer);
  }

  /**
   * Recursively evaluate the program until the tokenizer reaches EOF
   */
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
        // TODO: this is a programmer error, should throw some better exception maybe
        throw new RuntimeException("Unexpected token: " + tokenizer.getToken());

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
