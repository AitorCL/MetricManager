package FileStatStorageTest;

import JavaAnalyzer.ClassAnalyzer.ClassAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.FileStatsStorage;
import JavaAnalyzer.JavaAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

public class Class_FileStatStorageTest {

    private String sorucePath = "src/CodeForTest";
    private String resultPath = "c:/ParseTest";
    private FileStatsStorage fileStatsStorage = new FileStatsStorage();

    @Test
    public void className() throws FileNotFoundException, IOException {
        ClassStats classStats = new ClassStats();
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileStatsStorage);
        classAnalyzer.scanForClasses(initTest());
        classStats = fileStatsStorage.getClassStat();
        assertEquals("class FileForTest ", classStats.getClassName());
    }

    @Test
    public void classAtributes() throws FileNotFoundException, IOException {
        ClassStats classStats = new ClassStats();
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileStatsStorage);
        classAnalyzer.scanForClasses(initTest());
        classStats = fileStatsStorage.getClassStat();
        assertEquals(0, classStats.getAtributeNumber());
    }

    @Test
    public void classLineNumber() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(29, fileStatsStorage.getClassList().get(0).getClassLineNumber());
    }

    @Test
    public void classMethodNumber() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(2, fileStatsStorage.getClassList().get(0).getMethodNumber());
    }

    @Test
    public void classCommentNumber() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(6, fileStatsStorage.getClassList().get(0).getCommentLinesNumber());
    }

    public BufferedReader initTest() throws FileNotFoundException {
        BufferedReader bufferedFile = new BufferedReader(new FileReader("src/CodeForTest/FileForTest.java"));
        return bufferedFile;
    }
}
