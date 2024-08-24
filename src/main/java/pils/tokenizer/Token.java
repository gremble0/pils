package pils.tokenizer;

import java.util.Optional;

public record Token(Type type, Optional<Object> value) {
  public enum Type {
    SYMBOL("symbol"),
    INT_LITERAL("int-literal"),
    FLOAT_LITERAL("float-literal"),
    CHAR_LITERAL("char-literal"),
    STRING_LITERAL("string-literal"),

    LPAREN("("),
    RPAREN(")"),
    EOF("<eof>");

    private final String image;

    private Type(String image) {
      this.image = image;
    }

    @Override
    public String toString() {
      return this.image;
    }
  }

  public String getSymbol() {
    return (String) this.value.get();
  }

  public long getInt() {
    return (long) this.value.get();
  }

  public double getFloat() {
    return (double) this.value.get();
  }

  public char getChar() {
    return (char) this.value.get();
  }

  public String getString() {
    return (String) this.value.get();
  }

  @Override
  public String toString() {
    String out = this.type.toString();
    switch (this.type) {
      case SYMBOL:
        out += this.getSymbol();
        break;

      case INT_LITERAL:
        out += this.getInt();
        break;

      case FLOAT_LITERAL:
        out += this.getFloat();
        break;

      case CHAR_LITERAL:
        out += this.getChar();
        break;

      case STRING_LITERAL:
        out += this.getString();
        break;

      case LPAREN:
      case EOF:
      case RPAREN:
        break;
    }

    return out;
  }
}
