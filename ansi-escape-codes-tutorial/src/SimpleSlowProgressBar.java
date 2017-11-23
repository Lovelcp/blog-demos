public class SimpleSlowProgressBar {
    public static void main(String[] args) throws InterruptedException {
        new SimpleProgressBar().loading();
    }

    void loading() throws InterruptedException {
        System.out.println("Loading...");
        for (int i = 1; i <= 100; i++) {
            System.out.print("\u001b[1000D");
            Thread.sleep(1000);
            System.out.print(i + "%");
            Thread.sleep(1000);
        }
    }
}
