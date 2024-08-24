package pils.tokenizer;

public enum Token {
  LPAREN("("),
  RPAREN(")"),
  EOF("<eof>"),
  SYMBOL("<symbol>");

  private final String image;

  private Token(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return this.image;
  }
}
