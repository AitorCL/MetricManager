package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class PackageAnalyzer extends FileAnalyzer {

    public abstract void AnalyzePackage(File directory) throws FileNotFoundException, IOException;
}
