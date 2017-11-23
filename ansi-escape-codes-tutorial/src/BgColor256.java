/**
 * 背景色256位
 */
public class BgColor256 {
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int code = i * 16 + j;
                System.out.printf("\u001b[48;5;%dm%-4d", code, code);
            }
            System.out.println("\u001b[0m");
        }
    }
}
