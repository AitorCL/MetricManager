package JavaAnalyzer;

import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.IOException;

public class AtributeAnalyzer {

    private FileParameters fileParameters;

    public AtributeAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
    }

    public String scanForAtributes(BufferedReader bufferedFile) throws IOException {
        String sCadena;
        while ((sCadena = bufferedFile.readLine()) != null && sCadena.indexOf("{") == -1) {
            bufferedFile.mark(sCadena.length());
            isAtribute(sCadena);
            fileParameters.increaseLineNumber();
        }
        return sCadena;
    }

    private void isAtribute(String sCadena) {
        if ((sCadena.indexOf("public ") != -1
                || sCadena.indexOf("protected ") != -1
                || sCadena.indexOf("private ") != -1)
                && (sCadena.endsWith(";")) && (!sCadena.endsWith(");"))) {
            fileParameters.increaseAtributeNumber();
        }
    }
}
