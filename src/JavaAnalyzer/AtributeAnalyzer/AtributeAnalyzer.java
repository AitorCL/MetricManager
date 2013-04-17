package JavaAnalyzer.AtributeAnalyzer;

import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.IOException;

public class AtributeAnalyzer {

    private FileParameters fileParameters;
    private AtributeStats atributeStats;

    public AtributeAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
        this.atributeStats = new AtributeStats();
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
        if (isPublicAtribute(sCadena)) {
            atributeStats.increasePublicAtributes();
        }
        if (isProtectedAtribute(sCadena)) {
            atributeStats.increaseProtectedAtributes();
        }
        if (isPrivateAtribute(sCadena)) {
            atributeStats.increasePrivateAtributes();
        }
    }

    private boolean isPrivateAtribute(String sCadena) {
        if ((sCadena.indexOf("private ") != -1)
                && (sCadena.endsWith(";")) && (!sCadena.endsWith(");"))) {
            fileParameters.increaseAtributeNumber();
            return true;
        }
        return false;
    }

    private boolean isProtectedAtribute(String sCadena) {
        if ((sCadena.indexOf("protected ") != -1)
                && (sCadena.endsWith(";")) && (!sCadena.endsWith(");"))) {
            fileParameters.increaseAtributeNumber();
            return true;
        }
        return false;
    }

    private boolean isPublicAtribute(String sCadena) {
        if ((sCadena.indexOf("public ") != -1)
                && (sCadena.endsWith(";")) && (!sCadena.endsWith(");"))) {
            fileParameters.increaseAtributeNumber();
            return true;
        }
        return false;
    }

    public void showAtributetStats() {
        atributeStats.writeStats(fileParameters.getPrintWriter());
    }
}
