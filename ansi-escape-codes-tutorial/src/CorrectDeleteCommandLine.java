import java.io.IOException;

public class CorrectDeleteCommandLine {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 设置命令行为raw模式，否则会自动解析方向键以及后退键，并且直到按下回车read方法才会返回
        String[] cmd = { "/bin/sh", "-c", "stty raw </dev/tty" };
        Runtime.getRuntime()
               .exec(cmd)
               .waitFor();
        while (true) {
            String input = "";
            int index = 0;
            while (true) {
                char ch = (char) System.in.read();
                if (ch == 3) {
                    // CTRL-C
                    return;
                }
                else if (ch >= 32 && ch <= 126) {
                    // 普通字符
                    input = input.substring(0, index) + ch + input.substring(index, input.length());
                    index++;
                }
                else if (ch == 10 || ch == 13) {
                    // 回车
                    System.out.println();
                    System.out.print("\u001b[1000D");
                    System.out.println("echo: " + input);
                    input = "";
                    index = 0;
                }
                else if (ch == 27) {
                    // 左右方向键
                    char next1 = (char) System.in.read();
                    char next2 = (char) System.in.read();
                    if (next1 == 91) {
                        if (next2 == 68) {
                            // 左方向键
                            index = Math.max(0, index - 1);
                        }
                        else if (next2 == 67) {
                            // 右方向键
                            index = Math.min(input.length(), index + 1);
                        }
                    }
                }
                else if (ch == 127) {
                    // 删除
                    if (index > 0) {
                        input = input.substring(0, index - 1) + input.substring(index, input.length());
                        index -= 1;
                    }
                }
                System.out.print("\u001b[1000D"); // 将光标移动到最左侧
                System.out.print("\u001b[0K"); // 清除光标所在行的全部内容
                System.out.print(input);
                System.out.print("\u001b[1000D"); // 再次将光标移动到最左侧
                if (index > 0) {
                    System.out.print("\u001b[" + index + "C"); // 将光标移动到index处
                }
                System.out.flush();
            }
        }
    }
}
