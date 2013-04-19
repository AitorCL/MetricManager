
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
        if (sCadena.indexOf("/*")!=-1){
            openCommentary = true;
            fileparameters.increasecommentLinesNumber();    
            return;
        }
        if (sCadena.indexOf("*/")!=-1){
            openCommentary = false;
        }
    }
    /***
     123123*324234 / / 
     * 
     */ 
     
}
