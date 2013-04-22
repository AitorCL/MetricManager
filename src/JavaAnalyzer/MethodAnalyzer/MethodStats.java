package JavaAnalyzer.MethodAnalyzer;

import java.io.PrintWriter;

public class MethodStats {

    private String methodName;
    private int paramNumber;
    private int lineNumber;
    private int cyclomaticComplexity;
    private int methodBracers;

    public MethodStats() {
        this.methodName = "";
        this.paramNumber = 0;
        this.lineNumber = 0;
        this.cyclomaticComplexity = 0;
        this.methodBracers = 0;
    }

    public int getMethodBracers() {
        return methodBracers;
    }

    public void increaseMethodBracers() {
        this.methodBracers++;
    }

    public void decreaseMethodBracers() {
        this.methodBracers--;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void increaseParamNumber() {
        this.paramNumber++;
    }

    public void increaseLineNumber() {
        if (!"".equals(this.methodName)) {
            this.lineNumber++;
        }
    }

    private void increaseCyclomaticComplexity() {
        cyclomaticComplexity++;
    }

    public void cyclomaticComplexitySearch(String sCadena) {
        if (sCadena.contains("if")
                || sCadena.contains("while")
                || sCadena.contains("for")
                || sCadena.contains("foreach")
                || sCadena.contains("case")
                || sCadena.contains("default")
                || sCadena.contains("continue")
                || sCadena.contains("catch")) {
            increaseCyclomaticComplexity();
            findLogicOperatorForComplexity(sCadena, "||");
            findLogicOperatorForComplexity(sCadena, "&&");
            findLogicOperatorForComplexity(sCadena, "?");

        }
    }

    private void findLogicOperatorForComplexity(String sCadena, String sKey) {
        while (sCadena.indexOf(sKey) > -1) {
            sCadena = sCadena.substring(sCadena.indexOf(sKey) + 1, sCadena.length());
            increaseCyclomaticComplexity();
        }
    }

    public void writeStats(PrintWriter printWriter) {
        printWriter.println("Method : " + methodName);
        printWriter.println("   Parameters              :" + paramNumber);
        printWriter.println("   Lines                   :" + lineNumber);
        printWriter.println("   CyclomaticComplexity    :" + cyclomaticComplexity);
        printWriter.println("");
    }
}
