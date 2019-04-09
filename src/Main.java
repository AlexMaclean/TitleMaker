import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(final String[] args) throws IOException {

        TitleParser parser = new TitleParser(new File(args[0]));
        Title title = parser.parse();

        fillTitle(title);

        TitlePainter painter = new TitlePainter(title);
        if (args.length > 1) {
            painter.addBackground(ImageIO.read(new File(args[1])));
        }
        BufferedImage image = painter.paint();

        File output = new File("title.png");
        ImageIO.write(image, "png", output);
    }

    /**
     * Prompts the user for the entries to the title fields then reads what the
     * user enters, storing them in the given field. The entries are raised to
     * all upper case.
     *
     * @param title The title which will be filled with values from the console
     */
    private static void fillTitle(final Title title) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (Title.Entry entry : title.entries) {

            if (entry.text != null) {
                System.out.println("Preset '" + entry.name + "' : " + entry.text);
            } else {
                System.out.print("Enter '" + entry.name + "' : ");
                try {
                    entry.text = reader.readLine().toUpperCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
