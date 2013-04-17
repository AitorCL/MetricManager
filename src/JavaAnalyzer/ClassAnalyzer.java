package JavaAnalyzer;

import java.io.BufferedReader;
import java.io.IOException;

public class ClassAnalyzer {

    private FileParameters fileParameters;

    public ClassAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
    }

    public boolean scanForClasses(BufferedReader bufferedFile) throws IOException {
        String sCadena;
        if ((sCadena = bufferedFile.readLine()) != null) {
            if (sCadena.indexOf("public class") != -1) {
                fileParameters.increaseClassNumber();
                fileParameters.increaseLineNumber();
                return true;
            }
        }
        return false;
    }
}
