package JavaAnalyzerTest;

import JavaAnalyzer.FileStatsStorage;
import JavaAnalyzer.JavaAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class JavaAnalyzerTest {

    private String filePath = "src/CodeForTest/FileForTest.java";;
    
    @Test
    public void howManyClasses() throws FileNotFoundException, IOException {
        FileStatsStorage fileStatStatsStorage = new FileStatsStorage();
        File file = new File(filePath);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(1, fileStatStatsStorage.getClassList().size());
        bufferedFile.close();
    }
    
    @Test
    public void howManyMethods() throws FileNotFoundException, IOException {
        FileStatsStorage fileStatStatsStorage = new FileStatsStorage();
        File file = new File(filePath);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(2, fileStatStatsStorage.getMethodList().size());
        bufferedFile.close();
    }
}