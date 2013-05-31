package JavaAnalyzer.MethodAnalyzer;

import java.io.PrintWriter;

public class MethodStats {

    private String methodName;
    private String classWhereIBelong;
    private int paramNumber;
    private int lineNumber;
    private int commentLines;
    private int cyclomaticComplexity;
    private int methodBracers;

    public MethodStats() {
        this.methodName = "";
        this.classWhereIBelong = "";
        this.paramNumber = 0;
        this.lineNumber = 0;
        this.cyclomaticComplexity = 0;
        this.methodBracers = 0;
    }

    public String getClassWhereIBelong() {
        return classWhereIBelong;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getParamNumber() {
        return paramNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getCommentLines() {
        return commentLines;
    }

    public int getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public int getMethodBracers() {
        return methodBracers;
    }

    public void increaseCommentLines() {
        this.commentLines++;
    }

    public void setMethodName(String methodName) {
        this.methodName = extractMethodName(methodName);
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
        if (containReserveWord(line)) {
            increaseCyclomaticComplexity();
            searchForComplexityOperators(line);
        }
    }

    public boolean containReserveWord(String line) {
        return line.contains("if")
                || line.contains("while")
                || line.contains("for")
                || line.contains("foreach")
                || line.contains("case")
                || line.contains("default")
                || line.contains("continue")
                || line.contains("catch");
    }

    public void searchForComplexityOperators(String line) {
        findLogicOperatorForComplexity(line, "||");
        findLogicOperatorForComplexity(line, "&&");
        findLogicOperatorForComplexity(line, "?");
    }

    private void findLogicOperatorForComplexity(String line, String sKey) {
        while (line.indexOf(sKey) > -1) {
            line = line.substring(line.indexOf(sKey) + 1, line.length());
            increaseCyclomaticComplexity();
        }
    }

    public void writeStats(PrintWriter printWriter) {
        printWriter.append("     Method: " + methodName + ", ");
        printWriter.append("Params: " + paramNumber + ", ");
        printWriter.append("Lines: " + lineNumber + ", ");
        printWriter.append("CC: " + cyclomaticComplexity + "\r\n");
    }

    public String extractMethodName(String methodName) {
        return methodName.substring(methodName.indexOf("p"), methodName.indexOf("("));
    }

    public void setClassWhereMethodBelong(String line) {
        this.classWhereIBelong = line.substring(line.indexOf("class"), line.indexOf("{"));
    }
}
