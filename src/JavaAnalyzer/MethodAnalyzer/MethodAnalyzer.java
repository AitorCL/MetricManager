package JavaAnalyzer.MethodAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import JavaAnalyzer.FileStatsStorage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MethodAnalyzer {

    private ClassStats classStats;
    private MethodStats methodStats;
    private FileStatsStorage fileStatsStorage;
    private int bracersNumber;

    public MethodAnalyzer(ClassStats classStats) {
        this.classStats = classStats;
    }

    public MethodAnalyzer(FileStatsStorage fileStatsStorage) {
        this.fileStatsStorage = fileStatsStorage;
    }

    public MethodStats getMethodStats() {
        return methodStats;
    }

    public void setMethodStats(MethodStats methodStats) {
        this.methodStats = methodStats;
    }

    private void areThereCloseBracer(String line) {
        if (line.contains("}")) {
            decreaseMethodBracer();
        }
    }

    private void increaseBracerNumber() {
        bracersNumber++;
    }

    private boolean thereAreStats() {
        return methodStats != null;
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
            writeMethodStats();
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

    public void writeMethodStats() throws IOException {
        String sFichero = ("c:/ParseTest/Method_log.txt");
        FileWriter fileLog = new FileWriter(sFichero, true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        if (thereAreStats()) {
            methodStats.writeStats(printWriter);
        }
        printWriter.close();
    }

    private void searchParameters(String line) {
        if (thereAreParameters(line)) {
            countParameters(line);
        }
    }

    public boolean thereAreParameters(String line) {
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
        //methodStats = new MethodStats();
        fileStatsStorage.addMethod();
        fileStatsStorage.getMethodStas().setMethodName(line);
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
