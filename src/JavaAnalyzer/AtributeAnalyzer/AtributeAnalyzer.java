package JavaAnalyzer.AtributeAnalyzer;

import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
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
        String line;
        CommentAnalyzer commentAnalyzer = new CommentAnalyzer(fileParameters);
        while ((line = bufferedFile.readLine()) != null && line.indexOf("{") == -1) {
            bufferedFile.mark(line.length());
            isAtribute(line);
            commentAnalyzer.searchComment(line);
            fileParameters.increaseLineNumber();
        }
        return line;
    }

    private void isAtribute(String line) {
        if (isPublicAtribute(line)) {
            atributeStats.increasePublicAtributes();
        }
        if (isProtectedAtribute(line)) {
            atributeStats.increaseProtectedAtributes();
        }
        if (isPrivateAtribute(line)) {
            atributeStats.increasePrivateAtributes();
        }
    }

    private boolean isPrivateAtribute(String line) {      
        if ((line.contains("private "))
                && (line.endsWith(";")) && (!line.endsWith(");"))) {
            fileParameters.increaseAtributeNumber();
            return true;
        }
        return false;
    }

    private boolean isProtectedAtribute(String line) {
        if ((line.contains("protected "))
                && (line.endsWith(";")) && (!line.endsWith(");"))) {
            fileParameters.increaseAtributeNumber();
            return true;
        }
        return false;
    }

    private boolean isPublicAtribute(String line) {
        if ((line.contains("public "))
                && (line.endsWith(";")) && (!line.endsWith(");"))) {
            fileParameters.increaseAtributeNumber();
            return true;
        }
        return false;
    }

    public void showAtributetStats() {
        atributeStats.writeStats(fileParameters.getPrintWriter());
    }
}
