import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4008_숫자만들기 {

    static int T, N;
    static int plus, minus, mul, divide;
    static int[] num;
    static int min, max;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            plus = Integer.parseInt(st.nextToken());
            minus = Integer.parseInt(st.nextToken());
            mul = Integer.parseInt(st.nextToken());
            divide = Integer.parseInt(st.nextToken());
            num = new int[N];
            int diff = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            dfs(0, num[0]);

            diff = max - min;

            sb.append("#" + t + " " + diff + "\n");

        }
        System.out.println(sb);
    }

    static void dfs(int cnt, int res) {
        if (cnt == N - 1) {
            max = Math.max(max, res);
            min = Math.min(min, res);
            return;
        }

        if (plus > 0) {
            plus--;
            dfs(cnt + 1, res + num[cnt + 1]);
            plus++;
        }
        if (minus > 0) {
            minus--;
            dfs(cnt + 1, res - num[cnt + 1]);
            minus++;
        }
        if (mul > 0) {
            mul--;
            dfs(cnt + 1, res * num[cnt + 1]);
            mul++;
        }
        if (divide > 0) {
            divide--;
            dfs(cnt + 1, res / num[cnt + 1]);
            divide++;
        }

    }

}
