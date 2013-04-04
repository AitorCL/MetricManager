
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
            System.out.println(sCadena);
            numeroLineas++;
        }
        System.out.println("El archivo tiene : " + numeroLineas + " Líneas");
    }
}
