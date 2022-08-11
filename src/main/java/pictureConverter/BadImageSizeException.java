package pictureConverter;

public class BadImageSizeException extends Throwable {
    public BadImageSizeException(double ratio, double maxRatio) {
        super("Максимальное соотношение сторон изображения " + maxRatio + ", а у этой " + ratio);
    }
}
