
package JavaAnalyzer.ClassAnalyzer;

import java.io.PrintWriter;

public class ClassStats {
    
    private String className;
    private int classLineNumber;

    public ClassStats() {
        this.className = "";
        this.classLineNumber = 0;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public void writeStats(PrintWriter printWriter) {
        printWriter.println("");
        printWriter.println("Class: " + className);
        printWriter.println("");
        
    }
    public void testAppendWrite(PrintWriter printWriter) {
        printWriter.append("Name: "+className+"\r\n");
        
    } 

    public String getClassName() {
        return className;
    }
    
    
}
