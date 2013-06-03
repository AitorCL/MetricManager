package ImportAnalyzerTest;

import JavaAnalyzer.FileStatsStorage;
import JavaAnalyzer.ImportAnalyzer.ImportAnalyzer;
import JavaAnalyzer.ImportAnalyzer.ImportStats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

public class ImportAnalyzerTest {

    private String filePath = "src/CodeForTest/FileForTest.java";
    private FileStatsStorage fileStatsStorage = new FileStatsStorage();

    @Test
    public void totalImportsTest() throws FileNotFoundException, IOException {
        File file = new File(filePath);
        try (BufferedReader bufferedFile = new BufferedReader(new FileReader(file))) {
            ImportAnalyzer importAnalyzer = new ImportAnalyzer(fileStatsStorage);
            importAnalyzer.scanForImports(bufferedFile);
            ImportStats importStats = new ImportStats();
            importStats = importAnalyzer.getImportStats();
            assertEquals(5, importStats.getTotalImports());
        }
    }
}
