package pils;

import java.io.FileNotFoundException;

import pils.runtime.RootEvaluator;
import pils.tokenizer.Tokenizer;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    String file = args[0];
    Tokenizer tokenizer = new Tokenizer(file);
    RootEvaluator.eval(tokenizer);
  }
}
