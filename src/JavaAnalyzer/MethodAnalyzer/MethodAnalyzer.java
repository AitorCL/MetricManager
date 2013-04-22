package JavaAnalyzer.MethodAnalyzer;

import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.IOException;

public class MethodAnalyzer {

    private FileParameters fileParameters;
    private MethodStats methodStats;

    public MethodAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
    }

    public String scanForMethods(BufferedReader bufferedFile) throws IOException {
        String line = bufferedFile.readLine();
        fileParameters.getLineNumber();
        while ((line) != null && !line.contains("public class ")) {
            isMethod(line);
            line = nextLine(line, bufferedFile);
        }
        return line;
    }

    private void isMethod(String line) {
        if (line.contains("public ") || line.contains("private ")
                || line.contains("protected ")) {
            if (line.endsWith(");")) {
                System.out.println(line + ("interface method"));
            } else {
                if (line.contains("){")) {
                    showMethodStats();
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
