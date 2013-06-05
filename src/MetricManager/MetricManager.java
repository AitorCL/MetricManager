package MetricManager;

import JavaAnalyzer.JavaAnalyzer;
import UserInterfaces.UserInterface;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MetricManager {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        UserInterface userInterface = new UserInterface();
        userInterface.showStartMessage();
        JavaAnalyzer javaAnalyzer = new JavaAnalyzer(userInterface.getSourcePath(),
                userInterface.getResultsPath());
        javaAnalyzer.fullScan();
        userInterface.showFinishMessage();
    }
}
