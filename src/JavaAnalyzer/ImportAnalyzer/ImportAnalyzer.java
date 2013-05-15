package JavaAnalyzer.ImportAnalyzer;

import java.io.BufferedReader;
import java.io.IOException;

public class ImportAnalyzer {

    private ImportStats importStats;

    public ImportAnalyzer() {
        this.importStats = new ImportStats();
    }

    public ImportStats getImportStats() {
        return importStats;
    }

    public void scanForImports(BufferedReader bufferedFile) throws IOException {
        String line;
        while ((line = bufferedFile.readLine()) != null && !line.contains("public ")) {
            bufferedFile.mark(line.length());
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
}
