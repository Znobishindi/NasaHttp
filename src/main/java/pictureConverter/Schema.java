package pictureConverter;

public class Schema implements TextColorSchema {

    @Override
    public char convert(int color) {
        String symbols = "#$@%*+!-";
        if (text != null) {
            symbols = text;
        }
        int value = (int) Math.round(symbols.length() / 255.0 * color);
        value = Math.max(value, 0);
        value = Math.min(value, symbols.length() - 1);
        return symbols.charAt(value);
    }

    private String text;


    public void setText(String text) {
        this.text = text;
    }
}
