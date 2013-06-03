package JavaAnalyzer.ClassAnalyzer;

public class ClassStats {

    private String className;
    private String packageWhereIBelong;
    private int atributeNumber;
    private int methodNumber;
    private int classLineNumber;
    private int commentLinesNumber;

    public ClassStats() {
        this.className = "";
        this.packageWhereIBelong ="";
        this.atributeNumber = 0;
        this.methodNumber = 0;
        this.classLineNumber = 0;
        this.commentLinesNumber = 0;
    }

    public int getAtributeNumber() {
        return atributeNumber;
    }

    public int getMethodNumber() {
        return methodNumber;
    }

    public int getClassLineNumber() {
        return classLineNumber;
    }

    public int getCommentLinesNumber() {
        return commentLinesNumber;
    }

    public void setClassName(String className) {
        this.className = className.substring(className.indexOf("class"), className.indexOf("{"));
    }

    public String getPackageWhereIBelong() {
        return packageWhereIBelong;
    }

    public void setPackageWhereIBelong(String packageWhereIBelong) {
        this.packageWhereIBelong = packageWhereIBelong;
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

    public String getClassName() {
        return className;
    }
}
