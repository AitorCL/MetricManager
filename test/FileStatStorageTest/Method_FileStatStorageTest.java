package FileStatStorageTest;

import JavaAnalyzer.FileStatsStorage;
import JavaAnalyzer.JavaAnalyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

public class Method_FileStatStorageTest {

    private String sorucePath = "src/CodeForTest";
    private String resultPath = "c:/ParseTest";
    private FileStatsStorage fileStatStatsStorage = new FileStatsStorage();

    @Test
    public void methodName() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals("public static void start", fileStatStatsStorage.getMethodList().get(0).getMethodName());
    }

    @Test
    public void methodLineNumber() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(7, fileStatStatsStorage.getMethodList().get(0).getLineNumber());
    }

    @Test
    public void methodCyclomaticComplexity() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(0, fileStatStatsStorage.getMethodList().get(0).getCyclomaticComplexity());
    }

    @Test
    public void methodCommentLines() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(0, fileStatStatsStorage.getMethodList().get(0).getCommentLines());
    }

    @Test
    public void methodParamNumber() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals(1, fileStatStatsStorage.getMethodList().get(0).getParamNumber());
    }

    @Test
    public void classWhereMethodBelong() throws FileNotFoundException, IOException {
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sorucePath, resultPath);
        javaAnalyzer.fullScan();
        fileStatStatsStorage = javaAnalyzer.getFileStatsStorage();
        assertEquals("class FileForTest ", fileStatStatsStorage.getMethodList().get(0).getClassWhereIBelong());
    }
}
