package AtributerAnalyzerTest;

import JavaAnalyzer.AtributeAnalyzer.AtributeStats;
import static org.junit.Assert.*;
import org.junit.Test;

public class AtributeStatTest {

    @Test
    public void increasePrivateAtributeTest() {
        AtributeStats atributeStats = new AtributeStats();
        atributeStats.increasePrivateAtributes();
        assertEquals(1, atributeStats.getPrivateAtributes(), 0.001);
    }

    @Test
    public void increaseProtectedAtributeTest() {
        AtributeStats atributeStats = new AtributeStats();
        atributeStats.increaseProtectedAtributes();
        assertEquals(1, atributeStats.getProtectedAtributes(), 0.001);
    }

    @Test
    public void increasePublicAtributeTest() {
        AtributeStats atributeStats = new AtributeStats();
        atributeStats.increasePublicAtributes();
        assertEquals(1, atributeStats.getPublicAtributes(), 0.001);
    }
}
