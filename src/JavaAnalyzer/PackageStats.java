package JavaAnalyzer;

public class PackageStats {

    private String packageName;
    private int packageLineNumber;
    private int prackageClassNumber;

    public PackageStats(String packageName) {
        this.packageName = packageName;
        this.packageLineNumber = 0;
        this.prackageClassNumber = 0;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName.substring(packageName.indexOf("package "), packageName.indexOf(";"));
    }

    public int getPackageLineNumber() {
        return packageLineNumber;
    }

    public void setPackageLineNumber(int packageLineNumber) {
        this.packageLineNumber = packageLineNumber;
    }

    public int getPrackageClassNumber() {
        return prackageClassNumber;
    }

    public void setPrackageClassNumber(int prackageClassNumber) {
        this.prackageClassNumber = prackageClassNumber;
    }

    public void increaseClassNumber() {
        this.prackageClassNumber++;
    }

    public void increasePackageLineNumber(int lines) {
        this.packageLineNumber += lines;
    }
}
