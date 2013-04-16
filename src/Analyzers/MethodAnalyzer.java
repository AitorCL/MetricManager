package Analyzers;

import java.io.BufferedReader;
import java.io.IOException;

public class MethodAnalyzer {

    private FileParameters fileParameters;

    public MethodAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
    }

    public String scanForMethods(BufferedReader bufferedFile) throws IOException {
        String sCadena = bufferedFile.readLine();
        fileParameters.getLineNumber();
        while ((sCadena) != null && sCadena.indexOf("public class ") == -1) {
            isMethod(sCadena);
            sCadena = nextLine(sCadena, bufferedFile);
        }
        return sCadena;
    }

    private void isMethod(String sCadena) {
        if (sCadena.indexOf("public ") != -1 || sCadena.indexOf("private ") != -1
                || sCadena.indexOf("protected ") != -1) {
            if (sCadena.endsWith(");")) {
                System.out.println(sCadena + ("interface method"));
            } else {
                if (sCadena.indexOf("){") != -1) {
                    fileParameters.increaseMethodNumber();
                }
            }
        }
    }

    private String nextLine(String sCadena, BufferedReader bufferedFile) throws IOException {
        sCadena = bufferedFile.readLine();
        if (sCadena != null) {
            bufferedFile.mark(sCadena.length());
        }
        fileParameters.increaseLineNumber();
        return sCadena;
    }
}
