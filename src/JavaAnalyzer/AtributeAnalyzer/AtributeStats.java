package JavaAnalyzer.AtributeAnalyzer;

import java.io.PrintWriter;


public class AtributeStats {
    
    private int publicAtributes;
    private int protectedAtributes;
    private int privateAtributes;

    public AtributeStats() {
        this.publicAtributes =0;
        this.protectedAtributes = 0;
        this.privateAtributes =0;
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
    }

    public void increaseProtectedAtributes() {
        this.protectedAtributes++;
    }

    public void increasePrivateAtributes() {
        this.privateAtributes++;
    }

    public void writeStats(PrintWriter printWriter)
    {
        printWriter.println("Atributes:");
        printWriter.println("   Public    : " + publicAtributes);
        printWriter.println("   Private   : " + protectedAtributes);
        printWriter.println("   Protected : " + privateAtributes);
    }   
}
