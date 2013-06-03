package JavaAnalyzer.ClassAnalyzer;

import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import JavaAnalyzer.FileStatsStorage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ClassAnalyzer {

    private FileStatsStorage fileStatsStorage;


    public ClassAnalyzer(FileStatsStorage fileStatsStorage) {
        this.fileStatsStorage = fileStatsStorage;
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

    public void updateClassStats(String line) {
        fileStatsStorage.getClassStat().setClassName(line);
        fileStatsStorage.getMethodStas().setClassWhereMethodBelong(line);
        fileStatsStorage.getClassStat().increaseClassLines();
    }
}
