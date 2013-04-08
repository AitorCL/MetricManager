package Analyzers;

public class FileParameters {

    private int classNumber;
    private int importNumber;
    private int atributeNumber;
    private int methodNumber;
    private int lineNumber;

    public FileParameters() {
        this.classNumber = 0;
        this.importNumber = 0;
        this.atributeNumber = 0;
        this.methodNumber = 0;
        this.lineNumber = 0;
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
