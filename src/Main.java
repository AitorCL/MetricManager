
import Analyzers.Analyzer;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Analyzer Analyzer = new Analyzer();
        Analyzer.startParse();
    }
}
