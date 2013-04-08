package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Analyzer {

    private PackageAnalyzer packageAnalyzer = new PackageAnalyzer();

    public Analyzer() {
    }

    public void startParse() throws FileNotFoundException, IOException {
        packageAnalyzer.AnalyzePackage(new File("c:/ParseTest/TestDirectory4"));
        packageAnalyzer.showStats();
        
    }
}
