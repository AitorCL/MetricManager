package ImportAnalyzerTest;

import JavaAnalyzer.ImportAnalyzer.ImportAnalyzer;
import JavaAnalyzer.ImportAnalyzer.ImportStats;
import JavaAnalyzer.JavaAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImportAnalyzerTest {

    @Test
    public void totalImportsTest() throws FileNotFoundException, IOException {
        File file;
        String path = "test/CodeForTest/FileForTest.java";
        file = new File(path);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        ImportAnalyzer importAnalyzer = new ImportAnalyzer();
        importAnalyzer.scanForImports(bufferedFile);
        ImportStats importStats = new ImportStats();
        importStats = importAnalyzer.getImportStats();
        assertEquals(5, importStats.getTotalImports());
        bufferedFile.close();
    }
}
