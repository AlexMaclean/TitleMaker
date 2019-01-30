import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TitleParser {

    private final File inputFile;
    private final Title title;

    public TitleParser(File inputFile) {
        this.inputFile = inputFile;
        this.title = new Title();
    }

    public Title parse() {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            while (true) {
                String line = reader.readLine();
                if (line == null || line.trim().equals("")) {
                    break;
                }
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return title;
    }

    private void processLine(String line) {
        String[] args = line.split(",");
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }
        Font font = new Font(args[1], Font.PLAIN, Integer.parseInt(args[2]));
        Title.Entry e = new Title.Entry(args[0], font);
        if (args.length == 4) {
            e.text = args[3].toUpperCase();
        }
        title.entries.add(e);
    }
}
