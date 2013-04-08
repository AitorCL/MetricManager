package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Analyzer {

    private PackageAnalyzer packageParse = new PackageAnalyzer(0,0,0);
    public Analyzer() {
    }

    public void startParse() throws FileNotFoundException, IOException {
        packageParse.AnalyzePackage(new File("c:/ParseTest/TestDirectory4"));
    }
}
