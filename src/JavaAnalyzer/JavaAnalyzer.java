package JavaAnalyzer;

import Analyzers.Analyzer;
import JavaAnalyzer.AtributeAnalyzer.AtributeAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.ImportAnalyzer.ImportAnalyzer;
import JavaAnalyzer.ImportAnalyzer.ImportStats;
import JavaAnalyzer.MethodAnalyzer.MethodAnalyzer;
import JavaAnalyzer.MethodAnalyzer.MethodStats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JavaAnalyzer extends Analyzer {

    private ArrayList<PackageStats> packageList;
    private ClassStats classStat;
    private MethodStats methodStat;
    private FileStatsStorage fileStatsStorage;

    public JavaAnalyzer() {
        this.packageList = new ArrayList<>();
        this.fileStatsStorage = new FileStatsStorage();
    }

    @Override
    public void AnalyzePackage(File directory) throws FileNotFoundException, IOException {
        for (File actualFile : directory.listFiles()) {
            if (actualFile.isDirectory()) {
                System.out.println(actualFile.getName());
                AnalyzePackage(actualFile);
            } else {
                System.out.println(actualFile.getName());
                increaseFilesNumber();
                scanFile(actualFile);
            }
        }
    }

    @Override
    public void scanFile(File file) throws FileNotFoundException, IOException {
        BufferedReader bufferedFile = getFileBuffer(file);
        startScan(bufferedFile, file);
    }

    private BufferedReader getFileBuffer(File file) throws FileNotFoundException, IOException {
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        return bufferedFile;
    }

    private void startScan(BufferedReader bufferedFile, File file) throws IOException {
        if (!findImports(bufferedFile)) {
            bufferedFile = getFileBuffer(file);
        }
        findClasses(bufferedFile);
        fileStatsStorage.writeStats();
    }

    private boolean findImports(BufferedReader bufferedFile) throws IOException {
        ImportAnalyzer importAnalyzer = new ImportAnalyzer();
        importAnalyzer.scanForImports(bufferedFile);
        return moveBufferToMark(bufferedFile, importAnalyzer.getImportStats());
    }

    private void findClasses(BufferedReader bufferedFile) throws IOException {
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileStatsStorage);
        if (classAnalyzer.scanForClasses(bufferedFile)) {
            classAnalyzer.prepareMethodStatsFile();
            findAtributes(bufferedFile, fileStatsStorage.getClassStat());
            findMethods(bufferedFile, fileStatsStorage.getClassStat());
            //classAnalyzer.writeClassStats();
            fileStatsStorage.addClass();
            findClasses(bufferedFile);
        }
    }

    private void findAtributes(BufferedReader bufferedFile, ClassStats classStats) throws IOException {
        AtributeAnalyzer atributeAnalyzer = new AtributeAnalyzer(classStats);
        moveBufferToMark(atributeAnalyzer.scanForAtributes(bufferedFile), bufferedFile);
    }

    private void findMethods(BufferedReader bufferedFile, ClassStats classStats) throws IOException {
        MethodAnalyzer methodAnalyzer = new MethodAnalyzer(fileStatsStorage);
        moveBufferToMark(methodAnalyzer.scanForMethods(bufferedFile), bufferedFile);
        //methodAnalyzer.writeMethodStats();
        fileStatsStorage.addMethod();
    }

    private boolean moveBufferToMark(BufferedReader bufferedFile, ImportStats importStat) throws IOException {
        if (importStat.getTotalImports() != 0) {
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
}