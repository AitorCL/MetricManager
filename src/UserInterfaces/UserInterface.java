package UserInterfaces;

import java.util.Scanner;

public class UserInterface {

    private String sourcePath;
    private String resultsPath;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getResultsPath() {
        return resultsPath;
    }

    public void setResultsPath(String resultsPath) {
        this.resultsPath = resultsPath;
    }

    public void showStartMessage() {
        Scanner in = new Scanner(System.in);
        System.out.println("Wellcome to metric manager V 0.1.\n");
        System.out.println("Plaase tell us your source directory.\n");
        sourcePath = in.next();
        System.out.println("And now, please, tell us your result directory.\n");
        resultsPath = in.next();
    }

    public void showFinishMessage() {
        System.out.println("Thanks you for use Metric Manager tool.");
    }
}
