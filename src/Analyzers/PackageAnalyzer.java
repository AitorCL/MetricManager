package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class PackageAnalyzer extends FileAnalyzer {

    private int packageNumbers;
    private int fileNumber;

    @Override
    public abstract void scanFile(File file) throws FileNotFoundException, IOException;

    public PackageAnalyzer() {
        this.packageNumbers = 0;
        this.fileNumber = 0;
    }

    public void showStats() {
        System.out.println("Directorios Escaneados :" + packageNumbers);
        System.out.println("Archivos Escaneados :" + fileNumber);
    }

    public void increasePackagesNumber() {
        packageNumbers++;
    }

    public void increaseFilesNumber() {
        fileNumber++;
    }

    private void showDirectoryName(File file) {
        System.out.println(file.getName() + "(Directory)");
    }

    public void AnalyzePackage(File directory) throws FileNotFoundException, IOException {
        increasePackagesNumber();
        for (File actualFile : directory.listFiles()) {
            if (actualFile.isDirectory()) {
                showDirectoryName(actualFile);
                AnalyzePackage(actualFile);
            } else {
                increaseFilesNumber();
                scanFile(actualFile);
            }
        }
    }
}
