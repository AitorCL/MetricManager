package JavaAnalyzer.MethodAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MethodAnalyzer {

    private ClassStats classStats;
    private MethodStats methodStats;
    private int bracersNumber;

    public MethodAnalyzer(ClassStats classStats) {
        this.classStats = classStats;
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
        classStats.increaseClassLines();
        CommentAnalyzer commentAnalyzer = new CommentAnalyzer(classStats);
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
            classStats.increaseClassLines();
            methodStats.cyclomaticComplexitySearch(line);
            areThereNewOpenBracer(line);
            if (bracersNumber != 0) {
                methodStats.increaseLineNumber();
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
            methodStats.increaseParamNumber();
        }
        methodStats.increaseParamNumber();
    }

    public void areThereNewOpenBracer(String line) {
        if (line.contains("{") && !line.contains("public ")) {
            increaseBracerNumber();
        }
    }

    private void updateClassStatForNewMethod() {
        classStats.increaseMethods();
        classStats.increaseClassLines();
    }

    private void updateMethodStatsForNewMethod(String line) {
        methodStats = new MethodStats();
        methodStats.setMethodName(line);
        increaseBracerNumber();
        methodStats.increaseLineNumber();
        searchParameters(line); 
    }

    private boolean isMethodHead(String line) {
        return ( line.contains("public ") ||
                 line.contains("private ")||
                 line.contains("protected "))&&(
                 line.contains(") {") || line.contains("){"));
    }

    public void decreaseMethodBracer() {
        bracersNumber--;
    }
}
