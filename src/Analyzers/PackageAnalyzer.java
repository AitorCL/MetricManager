package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PackageAnalyzer {

    private int packageNumbers;
    private int fileNumber;
    private int efectiveCodeLines;
    private FileAnalyzer fileAnalizer = new FileAnalyzer();

    public PackageAnalyzer(int packageNumbers, int fileNumber, int efectiveCodeLines) {
        this.packageNumbers = packageNumbers;
        this.fileNumber = fileNumber;
        this.efectiveCodeLines = efectiveCodeLines;
    }

    public void increasePackagesNumber() {
        packageNumbers++;
    }
    private void showDirectoryName(File file) {
        System.out.println(file.getName() + "(Directory)");
    }

    public  void AnalyzePackage(File directory) throws FileNotFoundException, IOException {
        increasePackagesNumber();
        for (File actualFile : directory.listFiles()) {
            if (actualFile.isDirectory()) { 
                showDirectoryName(actualFile);
                AnalyzePackage(actualFile);
            } else {
                fileAnalizer.showFileName(actualFile);
                fileAnalizer.fileAnalyzer(actualFile);
            }
        }
    }
}
