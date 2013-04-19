package JavaAnalyzer;

import java.io.PrintWriter;

public class FileParameters {

    private int classNumber;
    private int importNumber;
    private int atributeNumber;
    private int methodNumber;
    private int commentLinesNumber;
    private int lineNumber;
    private PrintWriter printWriter;

    public FileParameters(PrintWriter printWriter) {
        this.classNumber = 0;
        this.importNumber = 0;
        this.atributeNumber = 0;
        this.methodNumber = 0;
        this.lineNumber = 0;
        this.commentLinesNumber = 0;
        this.printWriter = printWriter;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public void setImportNumber(int importNumber) {
        this.importNumber = importNumber;
    }

    public void setAtributeNumber(int atributeNumber) {
        this.atributeNumber = atributeNumber;
    }

    public void setMethodNumber(int methodNumber) {
        this.methodNumber = methodNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void resetParameters() {
        this.classNumber = 0;
        this.importNumber = 0;
        this.atributeNumber = 0;
        this.methodNumber = 0;
        this.lineNumber = 0;

    }

    public void increasecommentLinesNumber() {
        commentLinesNumber++;
    }    
    
    public void increaseClassNumber() {
        classNumber++;
    }

    public void increaseImportNumber() {
        importNumber++;
    }

    public void increaseAtributeNumber() {
        atributeNumber++;
    }

    public void increaseMethodNumber() {
        methodNumber++;
    }

    public void increaseLineNumber() {
        lineNumber++;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public int getImportNumber() {
        return importNumber;
    }

    public int getAtributeNumber() {
        return atributeNumber;
    }

    public int getMethodNumber() {
        return methodNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
