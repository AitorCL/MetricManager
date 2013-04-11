package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileAnalyzer {

    public void showFileName(File file) {
        System.out.println(file.getName() + "(File)");
    }

    public abstract void scanFile(File file) throws FileNotFoundException, IOException;
}
