import java.io.IOException;

public class BasicCommandLine {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 设置命令行为raw模式，否则会自动解析方向键以及后退键，并且直到按下回车read方法才会返回
        String[] cmd = { "/bin/sh", "-c", "stty raw </dev/tty" };
        Runtime.getRuntime()
               .exec(cmd)
               .waitFor();
        while (true) {
            String input = "";
            while (true) {
                char ch = (char) System.in.read();
                if (ch == 3) {
                    // CTRL-C
                    return;
                }
                else if (ch >= 32 && ch <= 126) {
                    // 普通字符
                    input += ch;
                }
                else if (ch == 10 || ch == 13) {
                    // 回车
                    System.out.println();
//                    System.out.print("\u001b[1000D");
                    System.out.println("echo: " + input);
                    input = "";
                }

                System.out.print("\u001b[1000D"); // 首先将光标移动到最左侧
                System.out.print(input); // 重新输出input
                System.out.flush();
            }
        }
    }
}
