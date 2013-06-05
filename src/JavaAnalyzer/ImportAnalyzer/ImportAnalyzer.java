package JavaAnalyzer.ImportAnalyzer;

import JavaAnalyzer.FileStatsStorage;
import java.io.BufferedReader;
import java.io.IOException;

public class ImportAnalyzer {

    private ImportStats importStats;
    private FileStatsStorage fileStatsStorage;

    public ImportAnalyzer(FileStatsStorage fileStatsStorage) {
        this.importStats = new ImportStats();
        this.fileStatsStorage = fileStatsStorage;
    }

    public ImportStats getImportStats() {
        return importStats;
    }

    public void scanForImports(BufferedReader bufferedFile) throws IOException {
        String line;String condition = "public ";
        while ((line = bufferedFile.readLine()) != null && !line.contains(condition)) {
            bufferedFile.mark(line.length());
            isPackage(line);
            isImport(line);
        }
    }

    public void isImport(String line) {
        if (line.contains("import ")) {
            if (line.contains("java.")) {
                importStats.increaseJavaImports();
            } else {
                importStats.increaseOtherImports();
            }
        }
    }

    private void isPackage(String line) {
        if (line.contains("package ")) {
            fileStatsStorage.setPackageUnderScan(line);
        }
    }
}
