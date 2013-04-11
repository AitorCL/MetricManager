package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Analyzer extends PackageAnalyzer {

    public void startParse(String path) throws FileNotFoundException, IOException {
        AnalyzePackage(new File(path));
        showStats();
    }

    @Override
    public abstract void scanFile(File file) throws FileNotFoundException, IOException;
}
