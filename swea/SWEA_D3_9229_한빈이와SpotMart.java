import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_9229_한빈이와SpotMart {

    static int N, M, T;
    static int[] snack;
    static int[] selected;
    static int max = -1;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            snack = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                snack[i] = Integer.parseInt(st.nextToken());
            }
            max = -1;

            combi(0, 0, 0);

            sb.append("#" + t + " " + max + "\n");

        }
        System.out.println(sb);

    }

    static void combi(int cnt, int start, int tot) {

        if (tot > M) { // 가지치기
            return;
        }
        if (cnt == 2) {
            max = Math.max(max, tot);
            return;
        }

        for (int i = start; i < N; i++) {
            combi(cnt + 1, i + 1, tot + snack[i]);
        }
    }

}
