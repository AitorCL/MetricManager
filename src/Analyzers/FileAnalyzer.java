package Analyzers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalyzer {

    public void showFileName(File file) {
        System.out.println(file.getName() + "(File)");
    }

    public void fileAnalyzer(File file) throws FileNotFoundException, IOException {
        String sCadena = "";
        int numeroLineas = 0;
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        while ((sCadena = bufferedFile.readLine()) != null) {
            if (sCadena.indexOf("import") != -1) {
                System.out.println(sCadena + ("Import Found"));
            }
            if ((sCadena.indexOf("public") != -1 ||
                 sCadena.indexOf("protected") != -1 ||
                 sCadena.indexOf("private") != -1)&&
                 (sCadena.endsWith(";"))&&(!sCadena.endsWith(")"))) {
                System.out.println(sCadena + ("Atribute Found"));
            }

            if (sCadena.indexOf("public") != -1) {
                if (sCadena.indexOf("class") != -1) {
                    System.out.println(sCadena + ("class Found"));
                } else {
                    if (sCadena.endsWith(");")) {
                        System.out.println(sCadena + ("interface method"));
                    } else {
                        if (sCadena.endsWith("){") || sCadena.endsWith(")")) {
                            System.out.println(sCadena + ("method"));
                        }
                    }
                }
            }
            numeroLineas++;
        }
        System.out.println("El archivo tiene : " + numeroLineas + " Líneas");
    }
}
