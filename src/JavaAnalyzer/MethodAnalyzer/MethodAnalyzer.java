package JavaAnalyzer.MethodAnalyzer;

import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import JavaAnalyzer.FileStatsStorage;
import java.io.BufferedReader;
import java.io.IOException;

public class MethodAnalyzer {

    private FileStatsStorage fileStatsStorage;
    private int bracersNumber;

    public MethodAnalyzer(FileStatsStorage fileStatsStorage) {
        this.fileStatsStorage = fileStatsStorage;
    }

    private void areThereCloseBracer(String line) {
        if (line.contains("}")) {
            decreaseMethodBracer();
        }
    }

    private void increaseBracerNumber() {
        bracersNumber++;
    }

    public String scanForMethods(BufferedReader bufferedFile) throws IOException {
        String line = bufferedFile.readLine();
        fileStatsStorage.getClassStat().increaseClassLines();
        CommentAnalyzer commentAnalyzer = new CommentAnalyzer(fileStatsStorage.getClassStat());
        while ((line) != null && !line.contains("public class ")) {
            isMethod(line);
            commentAnalyzer.searchComment(line);
            line = nextLine(line, bufferedFile);
        }
        return line;
    }

    private void isMethod(String line) throws IOException {
        if (isMethodHead(line)) {
            updateClassStatForNewMethod();
            updateMethodStatsForNewMethod(line);
        }
    }

    private String nextLine(String line, BufferedReader bufferedFile) throws IOException {
        line = bufferedFile.readLine();
        if (line != null) {
            bufferedFile.mark(line.length());
            fileStatsStorage.getClassStat().increaseClassLines();
            fileStatsStorage.getMethodStas().cyclomaticComplexitySearch(line);
            areThereNewOpenBracer(line);
            if (bracersNumber != 0) {
                fileStatsStorage.getMethodStas().increaseLineNumber();
                areThereCloseBracer(line);
            }
        }
        return line;
    }

    private void searchParameters(String line) {
        if (areThereParameters(line)) {
            countParameters(line);
        }
    }

    public boolean areThereParameters(String line) {
        return !line.contains("()");
    }

    public void countParameters(String line) {
        while (line.indexOf(",") > -1) {
            line = line.substring(line.indexOf(",") + 1, line.length());
            fileStatsStorage.getMethodStas().increaseParamNumber();
        }
        fileStatsStorage.getMethodStas().increaseParamNumber();
    }

    public void areThereNewOpenBracer(String line) {
        if (line.contains("{") && !line.contains("public ")) {
            increaseBracerNumber();
        }
    }

    private void updateClassStatForNewMethod() {
        fileStatsStorage.getClassStat().increaseMethods();
        fileStatsStorage.getClassStat().increaseClassLines();
    }

    private void updateMethodStatsForNewMethod(String line) {
        fileStatsStorage.addMethod();
        fileStatsStorage.getMethodStas().setMethodName(line);
        fileStatsStorage.getMethodStas().setClassWhereIBelong(fileStatsStorage.getClassStat().getClassName());
        increaseBracerNumber();
        fileStatsStorage.getMethodStas().increaseLineNumber();
        searchParameters(line);
    }

    private boolean isMethodHead(String line) {
        return (line.contains("public ")
                || line.contains("private ")
                || line.contains("protected ")) && (line.contains(") {") || line.contains("){"));
    }

    public void decreaseMethodBracer() {
        bracersNumber--;
    }
}
