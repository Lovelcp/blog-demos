import java.util.Collections;

public class AsciiProgressBar {
    public static void main(String[] args) throws InterruptedException {
        new AsciiProgressBar().loading();
    }

    void loading() throws InterruptedException {
        System.out.println("Loading...");
        for (int i = 1; i <= 100; i++) {
            int width = i / 4;
            String left = "[" + String.join("", Collections.nCopies(width, "#"));
            String right = String.join("", Collections.nCopies(25 - width, " ")) + "]";
            System.out.print("\u001b[1000D" + left + right);
            Thread.sleep(100);
        }
    }
}
