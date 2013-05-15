package JavaAnalyzer.ClassAnalyzer;

import java.io.PrintWriter;

public class ClassStats {

    private String className;
    private int atributeNumber;
    private int methodNumber;
    private int classLineNumber;
    private int commentLinesNumber;

    public ClassStats() {
        this.className = "";
        this.atributeNumber = 0;
        this.methodNumber = 0;
        this.classLineNumber = 0;
        this.commentLinesNumber = 0;
    }

    public void setClassName(String className) {
        this.className = className.substring(className.indexOf("class"), className.indexOf("{"));
    }

    public void increaseAtributes() {
        this.atributeNumber++;
    }

    public void increaseMethods() {
        this.methodNumber++;
    }

    public void increaseClassLines() {
        this.classLineNumber++;
    }

    public void increaseCommentLines() {
        this.commentLinesNumber++;
    }

    public void writeStats(PrintWriter printWriter) {
        printWriter.append("Name: " + className + ",");
        printWriter.append("Atributes: " + atributeNumber + ",");
        printWriter.append("Methods: " + methodNumber + ",");
        printWriter.append("Lines: " + classLineNumber + ",");
        printWriter.append("Comment: " + commentLinesNumber + "\r\n");
    }

    public String getClassName() {
        return className;
    }
}
