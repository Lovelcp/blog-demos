public class SimpleProgressBar {
    public static void main(String[] args) throws InterruptedException {
        new SimpleProgressBar().loading();
    }

    void loading() throws InterruptedException {
        System.out.println("Loading...");
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(100);
            System.out.print("\u001b[1000D" + i + "%");
        }
    }
}
