
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
    
    
    public void show(){
        System.out.println("Wellcome to metric manager V 0.1 \n");
        System.out.println("Plaase tell us your source directory\n");
        System.out.println("And now, please, tell us your result directory \ns");
        
        
    }
    
}
