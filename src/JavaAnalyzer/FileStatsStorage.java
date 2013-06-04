package JavaAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.MethodAnalyzer.MethodStats;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class FileStatsStorage {

    private String packageUnderScan;
    private ClassStats classStat;
    private MethodStats MethodStas;
    private ArrayList<ClassStats> classList;
    private ArrayList<MethodStats> methodList;

    public FileStatsStorage() {
        this.packageUnderScan = "";
        this.classStat = new ClassStats();
        this.MethodStas = new MethodStats();
        this.classList = new ArrayList<>();
        this.methodList = new ArrayList<>();
    }

    public String getPackageUnderScan() {
        return packageUnderScan;
    }

    public void setPackageUnderScan(String packageUnderScan) {
        this.packageUnderScan = packageUnderScan;
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

}