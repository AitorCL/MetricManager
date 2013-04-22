package JavaAnalyzer.ClassAnalyzer;

import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.IOException;

public class ClassAnalyzer {

    private FileParameters fileParameters;
    private ClassStats classStats;
    
    public ClassAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
        this.classStats = new ClassStats();
    }

    public boolean scanForClasses(BufferedReader bufferedFile) throws IOException {
        String line;
        if ((line = bufferedFile.readLine()) != null) {
            if (line.contains("public class ")) {
                classStats.setClassName(line);
                fileParameters.increaseClassNumber();
                fileParameters.increaseLineNumber();
                return true;
            }
        }
        return false;
    }
    
    public void showClassStats() {
        classStats.writeStats(fileParameters.getPrintWriter());
    }
}
