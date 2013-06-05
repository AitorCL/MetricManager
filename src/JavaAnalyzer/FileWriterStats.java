package JavaAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.MethodAnalyzer.MethodStats;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class FileWriterStats {

    private String resultPath;

    public FileWriterStats(String path) {
        this.resultPath = path;
    }

    public PrintWriter openLogFile(String path) throws IOException {
        FileWriter fileLog = new FileWriter(path, true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        return printWriter;
    }

    public void writePackages(ArrayList<PackageStats> packageList) throws IOException {
        try (PrintWriter printWriterPackage = openLogFile(resultPath + "/Package_log.txt")) {
            Date fecha = new Date();
            for (PackageStats packageClass : packageList) {
                printWriterPackage.append("[" + fecha + "]");
                printWriterPackage.append("Name: " + packageClass.getPackageName() + ",");
                printWriterPackage.append("Classes: " + packageClass.getPrackageClassNumber() + ",");
                printWriterPackage.append("Lines: " + packageClass.getPackageLineNumber() + "\r\n");
            }
            printWriterPackage.close();
        }
    }

    public void writeClasses(ArrayList<ClassStats> classList) throws IOException {
        try (PrintWriter printWriterClases = openLogFile(resultPath + "/Class_log.txt")) {
            Date fecha = new Date();
            for (ClassStats actualClass : classList) {
                printWriterClases.append("[" + fecha + "]");
                printWriterClases.append("Name: " + actualClass.getClassName() + ",");
                printWriterClases.append("Atributes: " + actualClass.getAtributeNumber() + ",");
                printWriterClases.append("Methods: " + actualClass.getMethodNumber() + ",");
                printWriterClases.append("Lines: " + actualClass.getClassLineNumber() + ",");
                printWriterClases.append("Comment: " + actualClass.getCommentLinesNumber() + "\r\n");
            }
            printWriterClases.close();
        }
    }

    public void writeMethod(ArrayList<MethodStats> methodList) throws IOException {
        try (PrintWriter printWriterMethods = openLogFile(resultPath + "/Method_log.txt")) {
            Date fecha = new Date();
            printWriterMethods.append("[" + fecha + "]" + "\r\n");
            for (MethodStats actualMethod : methodList) {
                printWriterMethods.append("     class: " + actualMethod.getClassWhereIBelong() + ", ");
                printWriterMethods.append("Method: " + actualMethod.getMethodName() + ", ");
                printWriterMethods.append("Params: " + actualMethod.getParamNumber() + ", ");
                printWriterMethods.append("Lines: " + actualMethod.getLineNumber() + ", ");
                printWriterMethods.append("CC: " + actualMethod.getCyclomaticComplexity() + "\r\n");
            }
            printWriterMethods.close();
        }
    }
}
