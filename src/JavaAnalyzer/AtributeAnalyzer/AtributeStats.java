package JavaAnalyzer.AtributeAnalyzer;

public class AtributeStats {

    private int publicAtributes;
    private int protectedAtributes;
    private int privateAtributes;
    private int totalAtributes;

    public AtributeStats() {
        this.publicAtributes = 0;
        this.protectedAtributes = 0;
        this.privateAtributes = 0;
    }

    public int getPublicAtributes() {
        return publicAtributes;
    }

    public int getProtectedAtributes() {
        return protectedAtributes;
    }

    public int getPrivateAtributes() {
        return privateAtributes;
    }

    public void increasePublicAtributes() {
        this.publicAtributes++;
        this.totalAtributes++;
    }

    public void increaseProtectedAtributes() {
        this.protectedAtributes++;
        this.totalAtributes++;
    }

    public void increasePrivateAtributes() {
        this.privateAtributes++;
        this.totalAtributes++;
    }

    public void increaseTotalAtributes() {
        this.totalAtributes++;
    }
}
