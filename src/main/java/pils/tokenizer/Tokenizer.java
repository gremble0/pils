package pils.tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Tokenizer {
    private BufferedReader reader;

    public Tokenizer(String path) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(path));
    }
}
