package JavaAnalyzer;

import Analyzers.Analyzer;
import JavaAnalyzer.AtributeAnalyzer.AtributeAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassAnalyzer;
import JavaAnalyzer.ClassAnalyzer.ClassStats;
import JavaAnalyzer.ImportAnalyzer.ImportAnalyzer;
import JavaAnalyzer.ImportAnalyzer.ImportStats;
import JavaAnalyzer.MethodAnalyzer.MethodAnalyzer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JavaAnalyzer extends Analyzer {

    private ArrayList<PackageStats> packageList;
    private FileStatsStorage fileStatsStorage;
    private FileWriterStats fileWriterStats;
    private File directory;

    public JavaAnalyzer(String sourcePath, String resultPath) {
        this.packageList = new ArrayList<>();
        this.fileStatsStorage = new FileStatsStorage();
        this.fileWriterStats = new FileWriterStats(resultPath);
        this.directory = new File(sourcePath);
    }

    public void fullScan() throws FileNotFoundException, IOException {
        AnalyzePackage(directory);
        updatePackageStats();
        writeScanResults();
    }

    @Override
    public void AnalyzePackage(File directory) throws FileNotFoundException, IOException {
        for (File actualFile : directory.listFiles()) {
            if (actualFile.isDirectory()) {
                packageList.add(new PackageStats(actualFile.getName()));
                AnalyzePackage(actualFile);
            } else {
                scanFile(actualFile);
            }
        }
    }

    public void updatePackageStats() {
        for (PackageStats actualPackage : packageList) {
            for (ClassStats actualClass : fileStatsStorage.getClassList()) {
                if (actualClass.getPackageWhereIBelong().contains(actualPackage.getPackageName())) {
                    actualPackage.increaseClassNumber();
                    actualPackage.increasePackageLineNumber(actualClass.getClassLineNumber());
                }
            }
        }
    }

    @Override
    public void scanFile(File file) throws FileNotFoundException, IOException {
        BufferedReader bufferedFile = getFileBuffer(file);
        startScan(bufferedFile, file);
    }

    public FileStatsStorage getFileStatsStorage() {
        return fileStatsStorage;
    }

    private BufferedReader getFileBuffer(File file) throws FileNotFoundException, IOException {
        return new BufferedReader(new FileReader(file));
    }

    private void startScan(BufferedReader bufferedFile, File file) throws IOException {
        if (!findImports(bufferedFile)) {
            bufferedFile = getFileBuffer(file);
        }
        findClasses(bufferedFile);
    }

    private boolean findImports(BufferedReader bufferedFile) throws IOException {
        ImportAnalyzer importAnalyzer = new ImportAnalyzer(fileStatsStorage);
        importAnalyzer.scanForImports(bufferedFile);
        return moveBufferToMark(bufferedFile, importAnalyzer.getImportStats());
    }

    private void findClasses(BufferedReader bufferedFile) throws IOException {
        ClassAnalyzer classAnalyzer = new ClassAnalyzer(fileStatsStorage);
        if (classAnalyzer.scanForClasses(bufferedFile)) {
            findAtributes(bufferedFile, fileStatsStorage.getClassStat());
            findMethods(bufferedFile);
            fileStatsStorage.addClass();
            findClasses(bufferedFile);
        }
    }

    private void findAtributes(BufferedReader bufferedFile, ClassStats classStats) throws IOException {
        AtributeAnalyzer atributeAnalyzer = new AtributeAnalyzer(classStats);
        moveBufferToMark(atributeAnalyzer.scanForAtributes(bufferedFile), bufferedFile);
    }

    private void findMethods(BufferedReader bufferedFile) throws IOException {
        MethodAnalyzer methodAnalyzer = new MethodAnalyzer(fileStatsStorage);
        moveBufferToMark(methodAnalyzer.scanForMethods(bufferedFile), bufferedFile);
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

    public void writeScanResults() throws IOException {
        fileWriterStats.writePackages(packageList);
        fileWriterStats.writeClasses(fileStatsStorage.getClassList());
        fileWriterStats.writeMethod(fileStatsStorage.getMethodList());
    }
}