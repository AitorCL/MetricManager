package JavaAnalyzer.AtributeAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import java.io.BufferedReader;
import java.io.IOException;

public class AtributeAnalyzer {

    private ClassStats classStats;

    public AtributeAnalyzer(ClassStats classStats) {
        this.classStats = classStats;
    }

    public String scanForAtributes(BufferedReader bufferedFile) throws IOException {
        String line;
        CommentAnalyzer commentAnalyzer = new CommentAnalyzer(classStats);
        while ((line = bufferedFile.readLine()) != null && line.indexOf("{") == -1) {
            bufferedFile.mark(line.length());
            isAtribute(line);
            commentAnalyzer.searchComment(line);
            classStats.increaseClassLines();
        }
        return line;
    }

    private void isAtribute(String line) {
        isPublicAtribute(line);
        isProtectedAtribute(line);
        isPrivateAtribute(line);
    }

    private boolean isPrivateAtribute(String line) {
        if ((line.contains("private "))
                && (line.endsWith(";")) && (!line.endsWith(");"))) {
            classStats.increaseAtributes();
            return true;
        }
        return false;
    }

    private boolean isProtectedAtribute(String line) {
        if ((line.contains("protected "))
                && (line.endsWith(";")) && (!line.endsWith(");"))) {
            classStats.increaseAtributes();
            return true;
        }
        return false;
    }

    private boolean isPublicAtribute(String line) {
        if ((line.contains("public "))
                && (line.endsWith(";")) && (!line.endsWith(");"))) {
            classStats.increaseAtributes();
            return true;
        }
        return false;
    }
}
