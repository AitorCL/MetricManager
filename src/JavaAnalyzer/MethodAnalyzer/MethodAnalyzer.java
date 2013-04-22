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
        String sCadena = bufferedFile.readLine();
        fileParameters.getLineNumber();
        while ((sCadena) != null && !sCadena.contains("public class ")) {
            isMethod(sCadena);
            sCadena = nextLine(sCadena, bufferedFile);
        }
        return sCadena;
    }

    private void isMethod(String sCadena) {
        if (sCadena.contains("public ") || sCadena.contains("private ")
                || sCadena.contains("protected ")) {
            if (sCadena.endsWith(");")) {
                System.out.println(sCadena + ("interface method"));
            } else {
                if (sCadena.contains("){")) {
                    showMethodStats();
                    fileParameters.increaseMethodNumber();
                    methodStats = new MethodStats();
                    methodStats.setMethodName(sCadena);
                    methodStats.increaseMethodBracers();
                    methodStats.increaseLineNumber();
                    searchParameters(sCadena);
                }
            }
        }
    }

    private String nextLine(String sCadena, BufferedReader bufferedFile) throws IOException {
        sCadena = bufferedFile.readLine();
        if (sCadena != null) {
            bufferedFile.mark(sCadena.length());
            fileParameters.increaseLineNumber();
            methodStats.cyclomaticComplexitySearch(sCadena);
            if (sCadena.contains("{") && !sCadena.contains("public ")) {
                methodStats.increaseMethodBracers();
            }
            if (methodStats.getMethodBracers() != 0) {
                methodStats.increaseLineNumber();
                if (sCadena.contains("}")) {
                    methodStats.decreaseMethodBracers();
                }
            }
        }
        return sCadena;
    }

    public void showMethodStats() {
        if (methodStats != null) {
            methodStats.writeStats(fileParameters.getPrintWriter());
        }
    }

    private void searchParameters(String sCadena) {
        if (sCadena.contains("()")) {
            return;
        }
        while (sCadena.indexOf(",") > -1) {
            sCadena = sCadena.substring(sCadena.indexOf(",") + 1, sCadena.length());
            methodStats.increaseParamNumber();
        }
        methodStats.increaseParamNumber();
    }
}
