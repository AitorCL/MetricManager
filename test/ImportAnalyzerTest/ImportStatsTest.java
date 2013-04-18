package ImportAnalyzerTest;

import JavaAnalyzer.ImportAnalyzer.ImportStats;
import static org.junit.Assert.*;
import org.junit.Test;

public class ImportStatsTest {

    @Test
    public void increaseJavaImportTest() {
        ImportStats importStat = new ImportStats();
        importStat.increaseJavaImports();
        assertEquals(1, importStat.getJavaImports(), 0.001);
    }

    @Test
    public void increaseOtherImportTest() {
        ImportStats importStat = new ImportStats();
        importStat.increaseOtherImports();
        assertEquals(1, importStat.getOtherImports(), 0.001);
    }
}