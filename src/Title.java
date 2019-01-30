import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Title {

    public List<Entry> entries;

    public Title() {
        entries = new ArrayList<>();
    }

    public static final class Entry {
        public String name;
        public String text;
        public Font font;

        public Entry(String name, Font font) {
            this.name = name;
            this.text = null;
            this.font = font;
        }

    }
}
