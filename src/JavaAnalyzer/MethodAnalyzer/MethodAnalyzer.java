package JavaAnalyzer.MethodAnalyzer;

import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.IOException;

public class MethodAnalyzer {
    
    private FileParameters fileParameters;
    private MethodStats methodStats;
    
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
                    showMethodStats();
                    fileParameters.increaseMethodNumber();
                    methodStats = new MethodStats();
                    methodStats.setMethodName(sCadena);
                    methodStats.increaseMethodBracers();
                    methodStats.increaseLineNumber();
                    howManyParameters(sCadena);
                }
            }
        }
    }
    
    private String nextLine(String sCadena, BufferedReader bufferedFile) throws IOException {
        sCadena = bufferedFile.readLine();
        if (sCadena != null) {
            bufferedFile.mark(sCadena.length());
            fileParameters.increaseLineNumber();
            methodStats.cyclomaticComplexitySearch(sCadena);
            if (sCadena.indexOf("{") != -1 && sCadena.indexOf("public ") == -1) {
                methodStats.increaseMethodBracers();
            }
            if (methodStats.getMethodBracers() != 0) {
                methodStats.increaseLineNumber();
                if (sCadena.indexOf("}") != -1) {
                    methodStats.decreaseMethodBracers();
                }
            }
        }
        
        return sCadena;
    }
    
    public void showMethodStats() {
        if (methodStats != null) {
            methodStats.writeStats(fileParameters.getPrintWriter());
        }
    }
    
    private void howManyParameters(String sCadena) {
        while (sCadena.indexOf(",") > -1) {
            sCadena = sCadena.substring(sCadena.indexOf(",") + 1, sCadena.length());
            methodStats.increaseParamNumber();
        }
    }
}
