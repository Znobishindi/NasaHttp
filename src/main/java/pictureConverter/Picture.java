package pictureConverter;

public class Picture {
    private double maxRatio;
    private int maxWidth;
    private int maxHeight;

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }
}
