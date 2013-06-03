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

    public ArrayList<ClassStats> getClassList() {
        return classList;
    }

    public ArrayList<MethodStats> getMethodList() {
        return methodList;
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
        if (!"".equals(this.MethodStas.getMethodName())) {
            methodList.add(this.MethodStas);
        }
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
        try (PrintWriter printWriterClases = openLogFile("c:/ParseTest/Class_logII.txt")) {
            Date fecha = new Date();
            for (ClassStats actualClass : classList) {
                printWriterClases.append("[" + fecha + "]");
                printWriterClases.append("Name: " + actualClass.getClassName() + ",");
                printWriterClases.append("Atributes: " + actualClass.getAtributeNumber() + ",");
                printWriterClases.append("Methods: " + actualClass.getMethodNumber() + ",");
                printWriterClases.append("Lines: " + actualClass.getClassLineNumber() + ",");
                printWriterClases.append("Comment: " + actualClass.getCommentLinesNumber() + "\r\n");
            }
        }
    }

    public void writeMethod() throws IOException {
        try (PrintWriter printWriterMethods = openLogFile("c:/ParseTest/Method_logII.txt")) {
            Date fecha = new Date();
            for (MethodStats actualMethod : methodList) {
                printWriterMethods.append("[" + fecha + "]" + "\r\n");
                printWriterMethods.append("     class: " + actualMethod.getClassWhereIBelong() + ", ");
                printWriterMethods.append("Method: " + actualMethod.getMethodName() + ", ");
                printWriterMethods.append("Params: " + actualMethod.getParamNumber() + ", ");
                printWriterMethods.append("Lines: " + actualMethod.getLineNumber() + ", ");
                printWriterMethods.append("CC: " + actualMethod.getCyclomaticComplexity() + "\r\n");
            }
        }
    }
}