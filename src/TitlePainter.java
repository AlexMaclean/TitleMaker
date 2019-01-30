import java.awt.*;
import java.awt.image.BufferedImage;

public class TitlePainter {

    private final Title title;
    private BufferedImage background;

    private static final int PADDING = 8;
    private static final int MARGIN = 40;

    public TitlePainter(Title title) {
        this.title = title;
        this.background = null;
    }

    public void addBackground(BufferedImage image) {
        this.background = image;
    }

    public BufferedImage paint() {
        Dimension size = getSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.black);
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int offset = MARGIN;

        for (Title.Entry entry : title.entries) {
            g.setFont(entry.font);
            FontMetrics metrics = g.getFontMetrics(entry.font);

            int centered_x = size.width / 2 - metrics.stringWidth(entry.text) / 2;
            int height = (int) trueHeight(metrics);

            offset += height + PADDING;

            g.drawString(entry.text, centered_x, offset - PADDING / 2);

//            g.drawRect(centered_x, offset - PADDING / 2 - height, metrics.stringWidth(entry.text), height);
        }

        if (background != null) {
            maskBackground(image);
        }

        return image;
    }

    private Dimension getSize() {
        BufferedImage dummy = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dummy.createGraphics();

        Dimension size = new Dimension(MARGIN * 2, MARGIN * 2);

        for (Title.Entry entry : title.entries) {
            FontMetrics metrics = g.getFontMetrics(entry.font);
            size.width = Math.max(size.width, metrics.stringWidth(entry.text) + MARGIN * 2);
            size.height += trueHeight(metrics) + PADDING;
        }

        return size;
    }

    private void maskBackground(BufferedImage image) {

        BufferedImage back_scaled = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = back_scaled.createGraphics();
        g.drawImage(background,
                0, 0, back_scaled.getWidth(), back_scaled.getHeight(),
                0, 0, Math.min(background.getWidth(), back_scaled.getWidth()),
                Math.min(background.getHeight(), back_scaled.getHeight()),
                null);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color alpha = new Color(image.getRGB(x, y), true);
                Color rgb = new Color(back_scaled.getRGB(x, y), true);
                Color newColor = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha.getAlpha());
                image.setRGB(x,y,  newColor.getRGB());
            }
        }
    }


    private static double trueHeight(FontMetrics fm) {
        return fm.getAscent() * 0.92;
    }
}
