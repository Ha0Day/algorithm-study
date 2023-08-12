package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B3_2522_별찍기12 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 2 * N - 1; i++) {
            for (int j = 0; j < Math.abs(i - N + 1); j++) {
                sb.append(" ");
            }
            for (int j = 0; j < N-Math.abs(i - N + 1); j++) {
                sb.append("*");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }


}
