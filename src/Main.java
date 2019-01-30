import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        TitleParser parser = new TitleParser(new File(args[0]));
        Title title = parser.parse();

        TitleFiller filler = new TitleFiller(title);
        filler.fill();

        TitlePainter painter = new TitlePainter(title);
        if (args.length > 1) {
            painter.addBackground(ImageIO.read(new File(args[1])));
        }
        BufferedImage image = painter.paint();

        File output = new File("title.png");
        ImageIO.write(image, "png", output);
    }

}
