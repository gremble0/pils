package pils.runtime.eval;

import pils.tokenizer.Tokenizer;

abstract public class RootEvaluator {
  public static void eval(Tokenizer tokenizer) {
    switch (tokenizer.getToken().type()) {
      case CHAR_LITERAL:
        break;

      case FLOAT_LITERAL:
        break;

      case INT_LITERAL:
        break;

      case LPAREN:
        break;

      case RPAREN:
        break;

      case STRING_LITERAL:
        break;

      case SYMBOL:
        break;

      case EOF:
        break;
    }
  }
}
