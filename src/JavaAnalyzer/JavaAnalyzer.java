package JavaAnalyzer;

import Analyzers.Analyzer;
import JavaAnalyzer.ImportAnalyzer.ImportAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaAnalyzer extends Analyzer {

    private FileParameters fileParameters;

    @Override
    public void scanFile(File file) throws FileNotFoundException, IOException {
        BufferedReader bufferedFile = getFileBuffer(file);
        startScan(bufferedFile, file);
        fileParameters.getPrintWriter().close();
        //fileStats(file);
    }

    private BufferedReader getFileBuffer(File file) throws FileNotFoundException, IOException {
        fileParameters = new FileParameters(createLogFile(file));
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        return bufferedFile;
    }

    private void startScan(BufferedReader bufferedFile, File file) throws IOException {
        if (!findImports(bufferedFile)) {
            bufferedFile = getFileBuffer(file);
        }
        findClasses(bufferedFile);
    }

    public void fileStats(File file) throws IOException {
        try (PrintWriter printWriter = createLogFile(file)) {
            importStats(printWriter);
            classStats(printWriter);
            printWriter.println("   File lines:" + fileParameters.getLineNumber());
            printWriter.close();
        }
    }

    private boolean findImports(BufferedReader bufferedFile) throws IOException {
        ImportAnalyzer importAnalyzer = new ImportAnalyzer(fileParameters);
        importAnalyzer.scanForImports(bufferedFile);
        importAnalyzer.showImportStats();
        return moveBufferToMark(bufferedFile);
    }

    private void findClasses(BufferedReader bufferedFile) throws IOException {
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileParameters);
        if (classAnalyzer.scanForClasses(bufferedFile)) {
            findAtributes(bufferedFile);
            findMethods(bufferedFile);
            findClasses(bufferedFile);
        }
    }

    private void findAtributes(BufferedReader bufferedFile) throws IOException {  
        AtributeAnalyzer atributeAnalyzer = new AtributeAnalyzer(fileParameters);
        moveBufferToMark(atributeAnalyzer.scanForAtributes(bufferedFile), bufferedFile);
        
    }

    //para cada metodo contar el numero de for while try if else
    //ver las dependencias entre paquetes y ficheros
    //referencia de los metodos a los atributos
    private void findMethods(BufferedReader bufferedFile) throws IOException {
        
        MethodAnalyzer methodAnalyzer = new MethodAnalyzer(fileParameters);
        moveBufferToMark(methodAnalyzer.scanForMethods(bufferedFile), bufferedFile);
    }

    private boolean moveBufferToMark(BufferedReader bufferedFile) throws IOException {
        if (fileParameters.getImportNumber() != 0) {
            bufferedFile.reset();
            return true;
        }
        return false;
    }

    private void moveBufferToMark(String sCadena, BufferedReader bufferedFile) throws IOException {
        if (sCadena != null) {
            bufferedFile.reset();
        }
    }

    private void importStats(PrintWriter printWriter) {
        printWriter.println("   Imports: " + fileParameters.getImportNumber());
    }

    private void classStats(PrintWriter printWriter) {
        printWriter.println("   Class :" + fileParameters.getClassNumber());
        atributerStats(printWriter);
        methodStats(printWriter);
    }

    private void atributerStats(PrintWriter printWriter) {
        printWriter.println("     Class atributes :" + fileParameters.getAtributeNumber());
    }

    private void methodStats(PrintWriter printWriter) {
        printWriter.println("     Class methods :" + fileParameters.getMethodNumber());
    }

    private PrintWriter createLogFile(File file) throws IOException {
        String sFichero = ("c:/ParseTest/" + showFileName(file) + "_log.txt");
        FileWriter fileLog = new FileWriter(sFichero);
        PrintWriter printWriter =  new PrintWriter(fileLog,true);
        return printWriter;
    }
}
