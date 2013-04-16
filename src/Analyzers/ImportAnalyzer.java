package Analyzers;

import java.io.BufferedReader;
import java.io.IOException;

public class ImportAnalyzer {

    private FileParameters fileParameters;

    public ImportAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
    }

    public void scanForImports(BufferedReader bufferedFile) throws IOException {
        String sCadena;
        while ((sCadena = bufferedFile.readLine()) != null && sCadena.indexOf("public") == -1) {
            bufferedFile.mark(sCadena.length());
            isImport(sCadena);
            fileParameters.increaseLineNumber();
        }
    }

    public void isImport(String String) {
        if (String.indexOf("import ") != -1) {
            fileParameters.increaseImportNumber();
        }
    }
}
