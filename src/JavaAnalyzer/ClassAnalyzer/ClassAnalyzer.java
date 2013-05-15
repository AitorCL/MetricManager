package JavaAnalyzer.ClassAnalyzer;

import JavaAnalyzer.CommentAnalyzer.CommentAnalyzer;
import JavaAnalyzer.FileParameters;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ClassAnalyzer {

    private FileParameters fileParameters;
    private ClassStats classStats;
    
    public ClassAnalyzer(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
        this.classStats = new ClassStats();
    }

    public ClassStats getClassStats() {
        return classStats;
    }

    
    public boolean scanForClasses(BufferedReader bufferedFile) throws IOException {
        String line;
         CommentAnalyzer commentAnalyzer = new CommentAnalyzer(classStats);
           while ((line = bufferedFile.readLine()) != null) {
                if (line.contains("public class ")) {
                    classStats.setClassName(line);
                    classStats.increaseClassLines();
                    return true;
                }
                else
                {
                    commentAnalyzer.searchComment(line);
                }
            }
        return false;
    }
    
    public void showClassStats() {
        classStats.writeStats(fileParameters.getPrintWriter());
    }
    
    public void writeClassStats() throws IOException {
        String sFichero = ("c:/ParseTest/Class_log.txt");
        FileWriter fileLog = new FileWriter(sFichero, true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        if (classStats != null) {
            classStats.writeStats(printWriter);
        }
        printWriter.close();
    }
    public void prepareMethodStatsFile() throws IOException{
        String sFichero = ("c:/ParseTest/Method_log.txt");
        FileWriter fileLog = new FileWriter(sFichero,true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        printWriter.append("Name: "+classStats.getClassName()+"\r\n");
        printWriter.close();
    }
}
