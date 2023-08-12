package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B3_10990_별찍기15 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                sb.append(" ");
            }
            sb.append("*");
            for (int j = 0; j < 2 * i - 1; j++) {
                sb.append(" ");
            }
            if (i != 0) {
                sb.append("*");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
