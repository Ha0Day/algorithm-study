import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B2_13300_방배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] table = new int[6][2];
        int sex;
        int year;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            sex = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken()) - 1;
            table[year][sex]++;
        }
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if (table[i][j] == 0) {
                    continue;
                }
                count = count + table[i][j] / K;
                if (table[i][j] % K != 0) {
                    count = count + 1;
                }
            }
        }
        System.out.println(count);
    }
}
