package JavaAnalyzer.CommentAnalyzer;

import JavaAnalyzer.FileParameters;

public class CommentAnalyzer {

    private boolean openCommentary;
    private FileParameters fileParameters;

    public CommentAnalyzer(FileParameters fileparameters) {
        this.openCommentary = false;
        this.fileParameters = fileparameters;
    }

    public void searchComment(String line) {
        if (line.contains("//")) {
            fileParameters.increasecommentLinesNumber();
            return;
        }
        if (line.contains("/*") && line.contains("*/")) {
            fileParameters.increasecommentLinesNumber();
            return;
        }
        if (line.contains("/*")) {
            fileParameters.increasecommentLinesNumber();
            openCommentary = true;
            return;
        }
        if (openCommentary) {
            fileParameters.increasecommentLinesNumber();
            return;
        }
        if (line.contains("*/")) {
            fileParameters.increasecommentLinesNumber();
            openCommentary = false;
        }
    }
}
