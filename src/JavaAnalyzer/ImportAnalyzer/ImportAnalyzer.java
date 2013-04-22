package JavaAnalyzer.ImportAnalyzer;

import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.IOException;

public class ImportAnalyzer {

    private FileParameters fileParameters;
    private ImportStats importStats;

    public ImportAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
        this.importStats = new ImportStats();
    }

    public void scanForImports(BufferedReader bufferedFile) throws IOException {
        String line;
        while ((line = bufferedFile.readLine()) != null && !line.contains("public ")) {
            bufferedFile.mark(line.length());
            isImport(line);
            fileParameters.increaseLineNumber();
        }
    }

    public void isImport(String line) {
        if (line.contains("import ")) {
            fileParameters.increaseImportNumber();
            if (line.contains("java.")) {
                importStats.increaseJavaImports();
            } else {
                importStats.increaseOtherImports();
            }

        }
    }

    public void showImportStats() {
        importStats.writeStats(fileParameters.getPrintWriter());
    }
}
