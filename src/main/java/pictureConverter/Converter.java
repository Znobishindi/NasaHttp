package pictureConverter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;


public class Converter implements TextGraphicsConverter {
    private final Picture picture = new Picture();
    private TextColorSchema schema = new Schema();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        double ratio = (double) img.getWidth() / (double) img.getHeight();
        if (ratio > picture.getMaxRatio() && picture.getMaxRatio() != 0) {
            throw new BadImageSizeException(ratio, picture.getMaxRatio());
        }
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();

        if (picture.getMaxWidth() != 0 && picture.getMaxHeight() == 0) {
            double widthRatio = setNewWidthRatio(newWidth);
            newHeight = (int) Math.round(newHeight * widthRatio);
            newWidth = (int) Math.round(newWidth * widthRatio);
        }
        if (picture.getMaxHeight() != 0 && picture.getMaxWidth() == 0) {
            double heightRatio = setNewHeightRatio(newHeight);
            newHeight = (int) Math.round(newHeight * heightRatio);
            newWidth = (int) Math.round(newWidth * heightRatio);
        }
        if (picture.getMaxHeight() != 0 && picture.getMaxWidth() != 0) {
            double widthAndHeightRatio = setNewWidthAndHeightRatio(newHeight, newWidth);
            newHeight = (int) Math.round(newHeight * widthAndHeightRatio);
            newWidth = (int) Math.round(newWidth * widthAndHeightRatio);
        }
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        Character[][] picture = new Character[newHeight][newWidth];
        StringBuilder imgTxt = new StringBuilder();
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                int color = bwRaster.getPixel(j, i, new int[3])[0];
                char c = schema.convert(color);
                picture[i][j] = c;
                imgTxt.append(picture[i][j]);
                imgTxt.append(picture[i][j]);
            }
            imgTxt.append("\n");
        }

        return imgTxt.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        picture.setMaxWidth(width);
    }

    @Override
    public void setMaxHeight(int height) {
        picture.setMaxHeight(height);
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        picture.setMaxRatio(maxRatio);
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

    public double setNewWidthRatio(int newWidth) {
        return (double) newWidth / picture.getMaxWidth();
    }

    public double setNewHeightRatio(int newHeight) {
        return (double) newHeight / picture.getMaxHeight();
    }

    public double setNewWidthAndHeightRatio(int newHeight, int newWidth) {
        double ratioW = (double) picture.getMaxWidth() / newWidth;
        double ratioH = (double) picture.getMaxHeight() / newHeight;
        return Math.min(ratioH, ratioW);
    }
}
