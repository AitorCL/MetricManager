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
        fileParameters = new FileParameters();
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        if (!findImports(bufferedFile)) {
            bufferedFile = new BufferedReader(new FileReader(file));
        }
        findClasses(bufferedFile);
        fileStats();
    }

    public void fileStats() {
        System.out.println("   Imports: " + fileParameters.getImportNumber());
        System.out.println("   Class :" + fileParameters.getClassNumber());
        System.out.println("     Class atributes :" + fileParameters.getAtributeNumber());
        System.out.println("     Class methods :" + fileParameters.getMethodNumber());
        System.out.println("   File lines:" + fileParameters.getLineNumber());
    }

    private boolean findImports(BufferedReader bufferedFile) throws IOException {
        String sCadena = "";
        while ((sCadena = bufferedFile.readLine()) != null && sCadena.indexOf("public") == -1) {
            bufferedFile.mark(sCadena.length());
            if (sCadena.indexOf("import") != -1) {
                fileParameters.increaseImportNumber();
            }
            fileParameters.getLineNumber();
        }
        if (fileParameters.getImportNumber() != 0) {
            bufferedFile.reset();
            return true;
        } else {
            return false;
        }
    }

    private void findClasses(BufferedReader bufferedFile) throws IOException {
        String sCadena;
        if ((sCadena = bufferedFile.readLine()) != null) {
            if (sCadena.indexOf("public") != -1) {
                if (sCadena.indexOf("class") != -1) {
                    fileParameters.increaseClassNumber();
                    findAtributes(bufferedFile);
                    findMethods(bufferedFile);
                    findClasses(bufferedFile);
                }
            }
        }
    }

    private void findAtributes(BufferedReader bufferedFile) throws IOException {
        String sCadena;
        while ((sCadena = bufferedFile.readLine()) != null && sCadena.indexOf("{") == -1) {
            bufferedFile.mark(sCadena.length());
            if ((sCadena.indexOf("public") != -1
                    || sCadena.indexOf("protected") != -1
                    || sCadena.indexOf("private") != -1)
                    && (sCadena.endsWith(";")) && (!sCadena.endsWith(");"))) {
                fileParameters.increaseAtributeNumber();
            }
        }
       // if (sCadena != null && fileParameters.getAtributeNumber() != 0){
        if(sCadena != null){
            System.out.println("antes" + sCadena);
            bufferedFile.reset();
            System.out.println("despues" + sCadena);}
        //}
    }

    //para cada metodo contar el numero de for while try if else
    //ver las dependencias entre paquetes y ficheros
    //referencia de los metodos a los atributos
    private void findMethods(BufferedReader bufferedFile) throws IOException {
        String sCadena = bufferedFile.readLine();
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
       if(sCadena != null) {
                bufferedFile.mark(sCadena.length());
            }
       }
      // if(sCadena != null && fileParameters.getMethodNumber() != 0){
        if(sCadena != null){
            System.out.println("antes" + sCadena);
            bufferedFile.reset();
            System.out.println("despues" + sCadena);}}

}
