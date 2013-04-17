
package JavaAnalyzer.ImportAnalyzer;

import java.io.PrintWriter;

public class ImportStats {
    
    private int javaImports;
    private int otherImports;

    public ImportStats() {
        this.javaImports = 0;
        this.otherImports = 0;
    }
    public void  increaseJavaImports(){
        this.javaImports++;
    }
    public void increaseOtherImports (){
        this.otherImports++;
    }
    
    public void writeStats(PrintWriter printWriter)
    {
        printWriter.println("Java  Imports : " + javaImports);
        printWriter.println("Other Imports : " + otherImports);
    }
}