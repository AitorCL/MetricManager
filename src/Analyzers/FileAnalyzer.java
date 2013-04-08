package Analyzers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalyzer {

    private FileParameters fileParameters;

    public FileAnalyzer() {
        this.fileParameters = new FileParameters();
    }

    public void showFileName(File file) {
        System.out.println(file.getName() + "(File)");
    }

    public void fileAnalyzer(File file) throws FileNotFoundException, IOException {
        String sCadena = "";
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        while ((sCadena = bufferedFile.readLine()) != null) {
            findImports(sCadena);
            findClass(sCadena);
            findAtributes(sCadena);
            findMethod(sCadena);
            fileParameters.increaseLineNumber();
        }
    }

    public void fileStats() {
        System.out.println("Imports: " + fileParameters.getImportNumber());
        System.out.println("Class :" + fileParameters.getClassNumber());
        System.out.println("Class atributes :" + fileParameters.getAtributeNumber());
        System.out.println("Class methods :" + fileParameters.getMethodNumber());
        System.out.println("File lines:" + fileParameters.getLineNumber());
    }

    private void findAtributes(String sCadena) {
        if ((sCadena.indexOf("public") != -1
                || sCadena.indexOf("protected") != -1
                || sCadena.indexOf("private") != -1)
                && (sCadena.endsWith(";")) && (!sCadena.endsWith(")"))) {
            fileParameters.increaseAtributeNumber();
        }
    }

    private void findImports(String sCadena) {
        if (sCadena.indexOf("import") != -1) {
            fileParameters.increaseImportNumber();
        }
    }

    private void findClass(String sCadena) {
        if (sCadena.indexOf("public") != -1) {
            if (sCadena.indexOf("class") != -1) {
                fileParameters.increaseClassNumber();
            }
        }
    }

    private void findMethod(String sCadena) {
        if (sCadena.endsWith(");")) {
            System.out.println(sCadena + ("interface method"));
        } else {
            if (sCadena.endsWith("){") || sCadena.endsWith(")")) {
                fileParameters.increaseMethodNumber();
            }
        }
    }
}
