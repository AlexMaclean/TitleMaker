import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TitleFiller {

    private final Title title;

    public TitleFiller(Title title) {
        this.title = title;
    }

    public void fill() {

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
