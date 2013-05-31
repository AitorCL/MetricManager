package JavaAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.MethodAnalyzer.MethodStats;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class FileStatsStorage {

    private ClassStats classStat;
    private MethodStats MethodStas;
    private ArrayList<ClassStats> classList;
    private ArrayList<MethodStats> methodList;

    public FileStatsStorage() {
        this.classStat = new ClassStats();
        this.MethodStas = new MethodStats();
        this.classList = new ArrayList<>();
        this.methodList = new ArrayList<>();
    }

    public ClassStats getClassStat() {
        return classStat;
    }

    public MethodStats getMethodStas() {
        return MethodStas;
    }

    public void setClassStat(ClassStats classStat) {
        this.classStat = classStat;
    }

    public void setMethodStas(MethodStats MethodStas) {
        this.MethodStas = MethodStas;
    }

    public void addClass() {
        classList.add(this.classStat);
        this.classStat = new ClassStats();
    }

    public void addMethod() {
        methodList.add(this.MethodStas);
        this.MethodStas = new MethodStats();
    }

    void writeStats() throws IOException {
        writeClasses();
        writeMethod();
    }

    public PrintWriter openLogFile(String path) throws IOException {
        FileWriter fileLog = new FileWriter(path, true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        return printWriter;
    }

    public void writeClasses() throws IOException {
        PrintWriter printWriter = openLogFile("c:/ParseTest/Class_logII.txt");
        for (ClassStats actualClass : classList) {
            Date fecha = new Date();
            printWriter.append("[" + fecha + "]");
            printWriter.append("Name: " + actualClass.getClassName() + ",");
            printWriter.append("Atributes: " + actualClass.getAtributeNumber() + ",");
            printWriter.append("Methods: " + actualClass.getMethodNumber() + ",");
            printWriter.append("Lines: " + actualClass.getClassLineNumber() + ",");
            printWriter.append("Comment: " + actualClass.getCommentLinesNumber() + "\r\n");
        }
        printWriter.close();
    }

    public void writeMethod() throws IOException {
        PrintWriter printWriter = openLogFile("c:/ParseTest/Method_logII.txt");
        Date fecha = new Date();
        printWriter.append("[" + fecha + "]" + "\r\n");
        for (MethodStats actualMethod : methodList) {
            if (!"".equals(actualMethod.getMethodName())) {
                printWriter.append("     class: " + actualMethod.getClassWhereIBelong() + ", ");
                printWriter.append("Method: " + actualMethod.getMethodName() + ", ");
                printWriter.append("Params: " + actualMethod.getParamNumber() + ", ");
                printWriter.append("Lines: " + actualMethod.getLineNumber() + ", ");
                printWriter.append("CC: " + actualMethod.getCyclomaticComplexity() + "\r\n");
            }
        }
        printWriter.close();
    }
}
