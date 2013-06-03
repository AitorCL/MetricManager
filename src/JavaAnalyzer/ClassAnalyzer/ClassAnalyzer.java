package JavaAnalyzer.ClassAnalyzer;

import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import JavaAnalyzer.FileStatsStorage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ClassAnalyzer {

    private ClassStats classStats;
    private FileStatsStorage fileStatsStorage;

    public ClassAnalyzer() {
        this.classStats = new ClassStats();
    }

    public ClassAnalyzer(FileStatsStorage fileStatsStorage) {
        this.fileStatsStorage = fileStatsStorage;
    }

    public ClassStats getClassStats() {
        return classStats;
    }

    public boolean scanForClasses(BufferedReader bufferedFile) throws IOException {
        String line;
        CommentAnalyzer commentAnalyzer = new CommentAnalyzer(fileStatsStorage.getClassStat());
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
        if (fileStatsStorage.getClassStat() != null) {
            fileStatsStorage.getClassStat().writeStats(printWriter);
        }
        printWriter.close();
    }

    public void prepareMethodStatsFile() throws IOException {
        PrintWriter printWriter = openLogFile("c:/ParseTest/Method_log.txt");
        Date fecha = new Date();
        printWriter.append("[" + fecha + "]" + "\r\n");
        printWriter.append("Name: " + fileStatsStorage.getClassStat().getClassName() + "\r\n");
        printWriter.close();
    }

    public PrintWriter openLogFile(String path) throws IOException {
        FileWriter fileLog = new FileWriter(path, true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        return printWriter;
    }

    public void updateClassStats(String line) {
        fileStatsStorage.getClassStat().setClassName(line);
        fileStatsStorage.getMethodStas().setClassWhereMethodBelong(line);
        fileStatsStorage.getClassStat().increaseClassLines();
    }
}
