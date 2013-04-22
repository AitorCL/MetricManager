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
        String sCadena;
        while ((sCadena = bufferedFile.readLine()) != null && !sCadena.contains("public ")) {
            bufferedFile.mark(sCadena.length());
            isImport(sCadena);
            fileParameters.increaseLineNumber();
        }
    }

    public void isImport(String String) {
        if (String.contains("import ")) {
            fileParameters.increaseImportNumber();
            if (String.contains("java.")) {
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
