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

    public boolean scanForClasses(BufferedReader bufferedFile) throws IOException {
        String line;
         CommentAnalyzer commentAnalyzer = new CommentAnalyzer(fileParameters);
           while ((line = bufferedFile.readLine()) != null) {
                if (line.contains("public class ")) {
                    classStats.setClassName(line);
                    fileParameters.increaseClassNumber();
                    fileParameters.increaseLineNumber();
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
    
    public void testAppend() throws IOException {
        String sFichero = ("c:/ParseTest/Class_log.txt");
        FileWriter fileLog = new FileWriter(sFichero, true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        if (classStats != null) {
            classStats.testAppendWrite(printWriter);
        }
        printWriter.close();
        prepareMethodStatsFile();
    }
    private void prepareMethodStatsFile() throws IOException{
        String sFichero = ("c:/ParseTest/Method_log.txt");
        FileWriter fileLog = new FileWriter(sFichero,true);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        printWriter.append("Name: "+classStats.getClassName()+"\r\n");
        printWriter.append("Method Name,Parameters,Lines,CC"+"\r\n");
        printWriter.close();
    }
}
