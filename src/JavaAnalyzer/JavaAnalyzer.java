package JavaAnalyzer;

import Analyzers.Analyzer;
import JavaAnalyzer.AtributeAnalyzer.AtributeAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.ImportAnalyzer.ImportAnalyzer;
import JavaAnalyzer.MethodAnalyzer.MethodAnalyzer;
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

    private boolean findImports(BufferedReader bufferedFile) throws IOException {
        ImportAnalyzer importAnalyzer = new ImportAnalyzer(fileParameters);
        importAnalyzer.scanForImports(bufferedFile);
        importAnalyzer.showImportStats();
        return moveBufferToMark(bufferedFile);
    }

    private void findClasses(BufferedReader bufferedFile) throws IOException {
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileParameters);
        if (classAnalyzer.scanForClasses(bufferedFile)) {
            classAnalyzer.prepareMethodStatsFile();
            findAtributes(bufferedFile,classAnalyzer.getClassStats());
            findMethods(bufferedFile,classAnalyzer.getClassStats());
            classAnalyzer.writeClassStats();
            findClasses(bufferedFile);
        }
    }

    private void findAtributes(BufferedReader bufferedFile,ClassStats classStats) throws IOException {
        AtributeAnalyzer atributeAnalyzer = new AtributeAnalyzer(classStats);
        moveBufferToMark(atributeAnalyzer.scanForAtributes(bufferedFile), bufferedFile);
    }

    private void findMethods(BufferedReader bufferedFile,ClassStats classStats) throws IOException {
        MethodAnalyzer methodAnalyzer = new MethodAnalyzer(fileParameters,classStats);
        moveBufferToMark(methodAnalyzer.scanForMethods(bufferedFile), bufferedFile);
        methodAnalyzer.writeMethodStats();
    }

    private boolean moveBufferToMark(BufferedReader bufferedFile) throws IOException {
        if (fileParameters.getImportNumber() != 0) {
            bufferedFile.reset();
            return true;
        }
        return false;
    }

    private void moveBufferToMark(String line, BufferedReader bufferedFile) throws IOException {
        if (line != null) {
            bufferedFile.reset();
        }
    }

    private PrintWriter createLogFile(File file) throws IOException {
        String sFichero = ("c:/ParseTest/" + showFileName(file) + "_log.txt");
        FileWriter fileLog = new FileWriter(sFichero);
        PrintWriter printWriter = new PrintWriter(fileLog, true);
        return printWriter;
    }
}
