package JavaAnalyzer.CommentAnalyzer;

import JavaAnalyzer.ClassAnalyzer.ClassStats;

public class CommentAnalyzer {

    private boolean openCommentary;
    private ClassStats classStats;

    public CommentAnalyzer(ClassStats classStats) {
        this.openCommentary = false;
        this.classStats = classStats;
    }

    public void searchComment(String line) {
        if (line.contains("//")) {
            classStats.increaseCommentLines();
            return;
        }
        if (line.contains("/*") && line.contains("*/")) {
            classStats.increaseCommentLines();
            return;
        }
        if (line.contains("/*")) {
            classStats.increaseCommentLines();
            openCommentary = true;
            return;
        }
        if (openCommentary) {
            classStats.increaseCommentLines();
            return;
        }
        if (line.contains("*/")) {
            classStats.increaseCommentLines();
            openCommentary = false;
        }
    }
}
