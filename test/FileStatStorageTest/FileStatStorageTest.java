package FileStatStorageTest;

import JavaAnalyzer.ClassAnalyzer.ClassAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.FileStatsStorage;
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

public class FileStatStorageTest {

    private String filePath = "test/CodeForTest/FileForTest.java";

    @Test
    public void howManyClasses() throws FileNotFoundException, IOException {
        BufferedReader bufferedFile = initTest();
        ClassStats classStats = new ClassStats();
        FileStatsStorage fileStatsStorage = new FileStatsStorage();
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileStatsStorage);
        classAnalyzer.scanForClasses(initTest());
        classStats = fileStatsStorage.getClassStat();
        assertEquals("class FileForTest ", classStats.getClassName());
        bufferedFile.close();
    }

    public BufferedReader initTest() throws FileNotFoundException {

        File file = new File(filePath);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        return bufferedFile;
    }
}
