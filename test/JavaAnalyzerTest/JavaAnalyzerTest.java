package JavaAnalyzerTest;

import JavaAnalyzer.FileStatsStorage;
import JavaAnalyzer.JavaAnalyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

public class JavaAnalyzerTest {

    private String filePath = "src/CodeForTest/FileForTest.java";
    private FileStatsStorage fileStatStatsStorage = new FileStatsStorage();
    private File file = new File(filePath);

    @Test
    public void howManyClasses() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(1, fileStatStatsStorage.getClassList().size());
    }

    @Test
    public void howManyMethods() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer();
        javaAnalyzer.scanFile(file);
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(2, fileStatStatsStorage.getMethodList().size());
    }
}