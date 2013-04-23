package FileAnalyzerTest;

import JavaAnalyzer.JavaAnalyzer;
import java.io.IOException;
import org.junit.Test;

public class FileAnalyzer {
    
    @Test
    public void JavaAnalyzerTest() throws IOException{
        JavaAnalyzer classAnalyzer = new JavaAnalyzer();
        classAnalyzer.startParse("../MetricManager/test/CodeForTest");
    }
}
