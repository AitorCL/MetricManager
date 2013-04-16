package Analyzers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JavaAnalyzer extends Analyzer {

    private FileParameters fileParameters;

    @Override
    public void scanFile(File file) throws FileNotFoundException, IOException {
        BufferedReader bufferedFile = getFileBuffer(file);
        startScan(bufferedFile, file);
        fileStats(file);
    }

    private BufferedReader getFileBuffer(File file) throws FileNotFoundException {
        fileParameters = new FileParameters();
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
        String sFichero = ("c:/ParseTest/" + showFileName(file) + "_log.txt");
        File fichero = new File(sFichero);

        if (fichero.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
            bw.write("   Imports: " + fileParameters.getImportNumber() + "\n");
            bw.write("   Class :" + fileParameters.getClassNumber() + "\n");
            bw.write("     Class atributes :" + fileParameters.getAtributeNumber() + "\n");
            bw.write("     Class methods :" + fileParameters.getMethodNumber() + "\n");
            bw.write("   File lines:" + fileParameters.getLineNumber() + "\n");

        }
//        showFileName(file);
//        System.out.println("   Imports: " + fileParameters.getImportNumber());
//        System.out.println("   Class :" + fileParameters.getClassNumber());
//        System.out.println("     Class atributes :" + fileParameters.getAtributeNumber());
//        System.out.println("     Class methods :" + fileParameters.getMethodNumber());
//        System.out.println("   File lines:" + fileParameters.getLineNumber());
    }

    private boolean findImports(BufferedReader bufferedFile) throws IOException {
        ImportAnalyzer importAnalyzer = new ImportAnalyzer(fileParameters);
        importAnalyzer.scanForImports(bufferedFile);
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
}
