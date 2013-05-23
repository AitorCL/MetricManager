
package JavaAnalyzerTest;

import JavaAnalyzer.JavaAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class JavaAnalyzerTest {
    
    @Test
    public void fileTest() throws FileNotFoundException, IOException{
        File file;
        String path = "test/CodeForTest/FileForTest.java";
        file = new File(path);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        JavaAnalyzer jA = new JavaAnalyzer();
        jA.scanFile(file);
        assertTrue(true);
        bufferedFile.close();
    } 
}
