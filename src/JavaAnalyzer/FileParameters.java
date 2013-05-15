package JavaAnalyzer;

import java.io.PrintWriter;

public class FileParameters {

    private int importNumber;
    private PrintWriter printWriter;

    public FileParameters(PrintWriter printWriter) {
        this.importNumber = 0;
        this.printWriter = printWriter;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public int getImportNumber() {
        return importNumber;
    }

}
