package Analyzers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class PackageAnalyzer extends FileAnalyzer {

    private int packageNumbers;
    private int fileNumber;

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

    public abstract void AnalyzePackage(File directory) throws FileNotFoundException, IOException;
}
