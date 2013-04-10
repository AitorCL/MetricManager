
import Analyzers.JavaAnalyzer;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.startParse("c:/ParseTest");
    }
}
