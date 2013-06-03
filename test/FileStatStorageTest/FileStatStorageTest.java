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

public class FileStatStorageTest {

    private String filePath = "src/CodeForTest/FileForTest.java";

    @Test
    public void className() throws FileNotFoundException, IOException {
        ClassStats classStats = new ClassStats();
        FileStatsStorage fileStatsStorage = new FileStatsStorage();
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileStatsStorage);
        classAnalyzer.scanForClasses(initTest());
        classStats = fileStatsStorage.getClassStat();
        assertEquals("class FileForTest ", classStats.getClassName());
    }
    
    @Test
    public void classAtributes() throws FileNotFoundException, IOException {
        ClassStats classStats = new ClassStats();
        FileStatsStorage fileStatsStorage = new FileStatsStorage();
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileStatsStorage);
        classAnalyzer.scanForClasses(initTest());
        classStats = fileStatsStorage.getClassStat();
        assertEquals(0, classStats.getAtributeNumber());
    }
    
    @Test
    public void classLineNumber() throws FileNotFoundException, IOException {
        FileStatsStorage fileStatStatsStorage = new FileStatsStorage();
        File file = new File(filePath);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(29, fileStatStatsStorage.getClassList().get(0).getClassLineNumber());
        bufferedFile.close();        
    }
 
    @Test
    public void classMethodNumber() throws FileNotFoundException, IOException {
        FileStatsStorage fileStatStatsStorage = new FileStatsStorage();
        File file = new File(filePath);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(2, fileStatStatsStorage.getClassList().get(0).getMethodNumber());
        bufferedFile.close();        
    }    
    
    @Test
    public void classCommentNumber() throws FileNotFoundException, IOException {
        FileStatsStorage fileStatStatsStorage = new FileStatsStorage();
        File file = new File(filePath);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(6, fileStatStatsStorage.getClassList().get(0).getCommentLinesNumber());
        bufferedFile.close();        
    }    
    
    
    public BufferedReader initTest() throws FileNotFoundException {
        File file = new File(filePath);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        return bufferedFile;
    }
}
