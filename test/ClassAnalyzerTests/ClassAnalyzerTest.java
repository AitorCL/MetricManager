package ClassAnalyzerTests;



import JavaAnalyzer.ClassAnalyzer.ClassAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassStats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;


public class ClassAnalyzerTest {
    
    @Test
    public void className() throws FileNotFoundException, IOException{
        String path = "test/CodeForTest/FileForTest.java";
        File file = new File(path);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        ClassStats classStats = new ClassStats();
        ClassAnalyzer classAnalyzer = new ClassAnalyzer();
        classAnalyzer.scanForClasses(bufferedFile);
        classStats = classAnalyzer.getClassStats();
        assertEquals("class FileForTest ", classStats.getClassName());
        bufferedFile.close();
    }
}
