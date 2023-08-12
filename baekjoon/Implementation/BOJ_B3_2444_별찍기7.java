package Implementation;

import java.util.Scanner;

public class BOJ_B3_2444_별찍기7 {

    static int N;

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);
        N = scann.nextInt();
        N = 2 * N - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < Math.abs(i - N / 2); j++) {
                sb.append(" ");
            }
            for (int j = 0; j < N - 2 * Math.abs(i - N / 2); j++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());    // 이뮤터
    }

}
