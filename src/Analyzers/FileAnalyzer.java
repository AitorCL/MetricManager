package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileAnalyzer {

    public String showFileName(File file) {
        return file.getName();
    }

    public abstract void scanFile(File file) throws FileNotFoundException, IOException;
}
