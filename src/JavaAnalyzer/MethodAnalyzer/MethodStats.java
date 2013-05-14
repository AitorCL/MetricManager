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
        this.methodName = methodName.substring(methodName.indexOf("p"),methodName.indexOf("("));
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

    public void cyclomaticComplexitySearch(String line) {
        if (line.contains("if")
                || line.contains("while")
                || line.contains("for")
                || line.contains("foreach")
                || line.contains("case")
                || line.contains("default")
                || line.contains("continue")
                || line.contains("catch")) {
            increaseCyclomaticComplexity();
            findLogicOperatorForComplexity(line, "||");
            findLogicOperatorForComplexity(line, "&&");
            findLogicOperatorForComplexity(line, "?");

        }
    }

    private void findLogicOperatorForComplexity(String line, String sKey) {
        while (line.indexOf(sKey) > -1) {
            line = line.substring(line.indexOf(sKey) + 1, line.length());
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
    public void testAppendWrite(PrintWriter printWriter) {
        printWriter.append("     Method: " +methodName +", ");
        printWriter.append("Params: " +paramNumber+", ");
        printWriter.append("Lines: " +lineNumber+", ");
        printWriter.append("CC: " +cyclomaticComplexity+"\r\n");
        printWriter.append("");    
    }    
    
}
