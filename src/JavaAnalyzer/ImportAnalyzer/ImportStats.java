package JavaAnalyzer.ImportAnalyzer;

public class ImportStats {

    private int javaImports;
    private int otherImports;
    private int totalImports;

    public ImportStats() {
        this.javaImports = 0;
        this.otherImports = 0;
        this.totalImports = 0;
    }

    public int getJavaImports() {
        return javaImports;
    }

    public int getOtherImports() {
        return otherImports;
    }

    public int getTotalImports() {
        return totalImports;
    }

    public void increaseJavaImports() {
        this.javaImports++;
        this.totalImports++;
    }

    public void increaseOtherImports() {
        this.otherImports++;
        this.totalImports++;
    }
}
