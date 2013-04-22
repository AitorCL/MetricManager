
package JavaAnalyzer.CommentAnalyzer;

import JavaAnalyzer.FileParameters;


public class CommentAnalyzer {
    
    private boolean openCommentary;
    private FileParameters fileparameters;

    public CommentAnalyzer(FileParameters fileparameters) {
        this.openCommentary = false;
        this.fileparameters = fileparameters;
    }
    
    
    public void searchComment(String line){
        if (line.contains("/*")){
            openCommentary = true;
            fileparameters.increasecommentLinesNumber();    
            return;
        }
        if (line.contains("*/")){
            openCommentary = false;
        }
    }
    /***
     123123*324234 / / 
     * 
     */ 
     
}
