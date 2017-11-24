import java.io.IOException;

public class LineMovementTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("111");
        System.out.println("222");
        System.out.println("333");
        System.out.println("444");
        System.out.println("555");
        Thread.sleep(2000);
        System.out.println("\u001b[3Faaa"); // 光标向上移动3行并且移至行首
        Thread.sleep(2000);
        System.out.println("\u001b[1Ebbb"); // 光标向下移动1行并且移至行首
        Thread.sleep(2000);
    }
}
