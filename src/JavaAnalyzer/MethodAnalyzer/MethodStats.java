package JavaAnalyzer.MethodAnalyzer;

import java.io.PrintWriter;

public class MethodStats {

    private String methodName;
    private int paramNumber;
    private int lineNumber;
    private int cyclomaticComplexity;

    public MethodStats() {
        this.methodName = "";
        this.paramNumber = 0;
        this.lineNumber = 0;
        this.cyclomaticComplexity = 0;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void increaseParamNumber(){
        this.paramNumber++;
    }
    public void increaseLineNumber() {
        this.lineNumber++;
    }

    public void increaseCyclomaticComplexity() {
        cyclomaticComplexity++;
    }

    public void writeStats(PrintWriter printWriter) {
        printWriter.println("Method : " + methodName);
        printWriter.println("   Parameters              :" + paramNumber);
        printWriter.println("   Lines                   :" + lineNumber);
        printWriter.println("   CyclomaticComplexity    :" + cyclomaticComplexity);
    }
}
