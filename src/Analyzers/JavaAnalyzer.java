package Analyzers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JavaAnalyzer extends Analyzer {

    private FileParameters fileParameters;

    @Override
    public void scanFile(File file) throws FileNotFoundException, IOException {
        BufferedReader bufferedFile = getFileBuffer(file);
        startScan(bufferedFile, file);
        fileStats();
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
    public void fileStats() {
        System.out.println("   Imports: " + fileParameters.getImportNumber());
        System.out.println("   Class :" + fileParameters.getClassNumber());
        System.out.println("     Class atributes :" + fileParameters.getAtributeNumber());
        System.out.println("     Class methods :" + fileParameters.getMethodNumber());
        System.out.println("   File lines:" + fileParameters.getLineNumber());
    }

    private boolean findImports(BufferedReader bufferedFile) throws IOException {
        ImportAnalyzer importAnalyzer = new ImportAnalyzer(fileParameters);
        importAnalyzer.scanForImports(bufferedFile);
        return moveBufferToMark(bufferedFile);
    }

    private void findClasses(BufferedReader bufferedFile) throws IOException {
        String sCadena;
        if ((sCadena = bufferedFile.readLine()) != null) {
            if (sCadena.indexOf("public class") != -1) {
                    fileParameters.increaseClassNumber();
                    fileParameters.increaseLineNumber();
                    findAtributes(bufferedFile);
                    findMethods(bufferedFile);
                    findClasses(bufferedFile);
            }
        }
    }

    private void findAtributes(BufferedReader bufferedFile) throws IOException {
        String sCadena="";
        sCadena = scanForAtributes(sCadena, bufferedFile);
        moveBufferToMark(sCadena, bufferedFile);
    }

    //para cada metodo contar el numero de for while try if else
    //ver las dependencias entre paquetes y ficheros
    //referencia de los metodos a los atributos
    private void findMethods(BufferedReader bufferedFile) throws IOException {
        String sCadena = bufferedFile.readLine();
        fileParameters.getLineNumber();
        while ((sCadena) != null && sCadena.indexOf("public class") == -1) {
            if (sCadena.indexOf("public") != -1) {
                if (sCadena.endsWith(");")) {
                    System.out.println(sCadena + ("interface method"));
                } else {
                    if (sCadena.indexOf("){") != -1) {
                        fileParameters.increaseMethodNumber();
                    }
                }
            }
            sCadena = bufferedFile.readLine();
            if (sCadena != null) {
                bufferedFile.mark(sCadena.length());
            }
            fileParameters.increaseLineNumber();
        }
        moveBufferToMark(sCadena, bufferedFile);
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

    private String scanForAtributes(String sCadena, BufferedReader bufferedFile) throws IOException {
        while ((sCadena = bufferedFile.readLine()) != null && sCadena.indexOf("{") == -1) {
            bufferedFile.mark(sCadena.length());
            if ((sCadena.indexOf("public ") != -1
                    || sCadena.indexOf("protected ") != -1
                    || sCadena.indexOf("private ") != -1)
                    && (sCadena.endsWith(";")) && (!sCadena.endsWith(");"))) {
                fileParameters.increaseAtributeNumber();
            }
            fileParameters.increaseLineNumber();
        }
        return sCadena;
    }
}
