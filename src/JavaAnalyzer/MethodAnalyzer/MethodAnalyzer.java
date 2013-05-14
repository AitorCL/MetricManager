package JavaAnalyzer.MethodAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MethodAnalyzer {

    private FileParameters fileParameters;
    private MethodStats methodStats;

    public MethodAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;

    }

    public FileParameters getFileParameters() {
        return fileParameters;
    }

    public void setFileParameters(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
    }

    public MethodStats getMethodStats() {
        return methodStats;
    }

    public void setMethodStats(MethodStats methodStats) {
        this.methodStats = methodStats;
    }

    public String scanForMethods(BufferedReader bufferedFile) throws IOException {
        String line = bufferedFile.readLine();
        fileParameters.getLineNumber();
        CommentAnalyzer commentAnalyzer = new CommentAnalyzer(fileParameters);
        while ((line) != null && !line.contains("public class ")) {
            isMethod(line);
            commentAnalyzer.searchComment(line);
            line = nextLine(line, bufferedFile);
        }
        return line;
    }

    private void isMethod(String line) throws IOException {
        if (line.contains("public ") || line.contains("private ")
                || line.contains("protected ")) {
            if (line.endsWith(");")) {
                System.out.println(line + ("interface method"));
            } else {
                if (line.contains(") {") || line.contains("){")) {
                    showMethodStats();
                    testAppend();
                    fileParameters.increaseMethodNumber();
                    methodStats = new MethodStats();
                    methodStats.setMethodName(line);
                    methodStats.increaseMethodBracers();
                    methodStats.increaseLineNumber();
                    searchParameters(line);
                }
            }
        }
    }

    private String nextLine(String line, BufferedReader bufferedFile) throws IOException {
        line = bufferedFile.readLine();
        if (line != null) {
            bufferedFile.mark(line.length());
            fileParameters.increaseLineNumber();
            methodStats.cyclomaticComplexitySearch(line);
            if (line.contains("{") && !line.contains("public ")) {
                methodStats.increaseMethodBracers();
            }
            if (methodStats.getMethodBracers() != 0) {
                methodStats.increaseLineNumber();
                if (line.contains("}")) {
                    methodStats.decreaseMethodBracers();
                }
            }
        }
        return line;
    }

    public void showMethodStats() {
        if (methodStats != null) {
            methodStats.writeStats(fileParameters.getPrintWriter());
        }
    }

    public void testAppend() throws IOException {
        String sFichero = ("c:/ParseTest/Method_log.txt");
        FileWriter fileLog = new FileWriter(sFichero,true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        if (methodStats != null) {
            methodStats.testAppendWrite(printWriter);
        }
        printWriter.close();
        
    }

    private void searchParameters(String line) {
        if (line.contains("()")) {
            return;
        }
        while (line.indexOf(",") > -1) {
            line = line.substring(line.indexOf(",") + 1, line.length());
            methodStats.increaseParamNumber();
        }
        methodStats.increaseParamNumber();
    }
}
