import java.io.IOException;
import java.util.Scanner;

public class SWEA_D3_1289_원재의메모리복구하기 {
    static int T;
    static int count;

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner scann = new Scanner(System.in);
        T = scann.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {

            String s = scann.next();
            count = 0;

            char prev = '0';
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != prev) {
                    count++;
                    if (prev == '0') {
                        prev = '1';
                    } else {
                        prev = '0';
                    }
                }
            }
            sb.append("#" + t + " " + count + "\n");
        }
        System.out.println(sb);
    }
}
