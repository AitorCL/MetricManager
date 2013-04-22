
package JavaAnalyzer.CommentAnalyzer;

import JavaAnalyzer.FileParameters;


public class CommentAnalyzer {
    
    private boolean openCommentary;
    private FileParameters fileparameters;

    public CommentAnalyzer(FileParameters fileparameters) {
        this.openCommentary = false;
        this.fileparameters = fileparameters;
    }
    
    
    public void searchComment(String sCadena){
        if (sCadena.contains("/*")){
            openCommentary = true;
            fileparameters.increasecommentLinesNumber();    
            return;
        }
        if (sCadena.contains("*/")){
            openCommentary = false;
        }
    }
    /***
     123123*324234 / / 
     * 
     */ 
     
}
