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
        if (isOneLineComentary(line)) {
            return;
        }
        if (isCommentaryBlock(line)) {
            return;
        }        
    }

    public boolean isOneLineComentary(String line) {
        if (line.contains("//")||(line.contains("/*") && line.contains("*/"))) {
            classStats.increaseCommentLines();
            return true;
        }
        return false;
    }

    public boolean isCommentaryBlock(String line) {
        if (startCommentBlock(line)) return true;
        if (isOpenBlock()) return true;
        finishCommentBlock(line);
        return false;
    }

    public boolean startCommentBlock(String line) {
        if (line.contains("/*")) {
            classStats.increaseCommentLines();
            openCommentary = true;
            return true;
        }
        return false;
    }

    public boolean isOpenBlock() {
        if (openCommentary) {
            classStats.increaseCommentLines();
            return true;
        }
        return false;
    }

    public void finishCommentBlock(String line) {
        if (line.contains("*/")) {
            classStats.increaseCommentLines();
            openCommentary = false;
        }
    }
}
