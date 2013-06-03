package FileStatStorageTest;

import JavaAnalyzer.FileStatsStorage;
import JavaAnalyzer.JavaAnalyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

public class Method_FileStatStorageTest {

    private String filePath = "src/CodeForTest/FileForTest.java";
    private FileStatsStorage fileStatStatsStorage = new FileStatsStorage();
    private File file = new File(filePath);
    
    @Test
    public void methodName() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals("public static void start", fileStatStatsStorage.getMethodList().get(0).getMethodName());
    }

    @Test
    public void methodLineNumber() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(7, fileStatStatsStorage.getMethodList().get(0).getLineNumber());
    }

    @Test
    public void methodCyclomaticComplexity() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(0, fileStatStatsStorage.getMethodList().get(0).getCyclomaticComplexity());
    }

    @Test
    public void methodCommentLines() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(0, fileStatStatsStorage.getMethodList().get(0).getCommentLines());
    }
    @Test
    public void methodParamNumber() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(1, fileStatStatsStorage.getMethodList().get(0).getParamNumber());
    }
}
