package JavaAnalyzer.ClassAnalyzer;

import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ClassAnalyzer {

    private ClassStats classStats;

    public ClassAnalyzer() {
        this.classStats = new ClassStats();
    }

    public ClassStats getClassStats() {
        return classStats;
    }

    public boolean scanForClasses(BufferedReader bufferedFile) throws IOException {
        String line;
        CommentAnalyzer commentAnalyzer = new CommentAnalyzer(classStats);
        while ((line = bufferedFile.readLine()) != null) {
            if (line.contains("public class ")) {
                updateClassStats(line);
                return true;
            } else {
                commentAnalyzer.searchComment(line);
            }
        }
        return false;
    }

    public void writeClassStats() throws IOException {
        PrintWriter printWriter = openLogFile("c:/ParseTest/Class_log.txt");
        if (classStats != null) {
            classStats.writeStats(printWriter);
        }
        printWriter.close();
    }

    public void prepareMethodStatsFile() throws IOException {
        PrintWriter printWriter = openLogFile("c:/ParseTest/Method_log.txt");
        Date fecha = new Date();
        printWriter.append("[" + fecha + "]" + "\r\n");
        printWriter.append("Name: " + classStats.getClassName() + "\r\n");
        printWriter.close();
    }

    public PrintWriter openLogFile(String path) throws IOException {
        FileWriter fileLog = new FileWriter(path, true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        return printWriter;
    }

    public void updateClassStats(String line) {
        classStats.setClassName(line);
        classStats.increaseClassLines();
    }
}
