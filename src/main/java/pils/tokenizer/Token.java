package pils.tokenizer;

public enum Token {
    LPAREN("("),
    RPAREN(")"),
    EOF("<eof>"),
    NAME("<name>");

    private final String image;

    private Token(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.image;
    }
}
