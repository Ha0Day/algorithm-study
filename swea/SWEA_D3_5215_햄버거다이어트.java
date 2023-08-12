import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_5215_햄버거다이어트 {

    static int T;
    static int N, L;
    static int ing[][];
    static int max;
    static boolean[] isSelected;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            max = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 재료 수
            L = Integer.parseInt(st.nextToken()); // 제한 칼로리
            isSelected = new boolean[N];
            ing = new int[N][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ing[i][0] = Integer.parseInt(st.nextToken()); // 맛 점수
                ing[i][1] = Integer.parseInt(st.nextToken()); // 칼로리

            }

            // -----------입력 완료

            subset(0, 0);

            sb.append("#" + t + " " + max + "\n");
        }

        System.out.println(sb);

    }

    static void subset(int cnt, int tot) {
        if (tot > L) {
            return;
        }
        if (cnt == N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (isSelected[i]) {
                    sum += ing[i][0];
                }
            }
            if (sum > max) {
                max = sum;
            }
            return;

        }

        isSelected[cnt] = true;
        subset(cnt + 1, tot + ing[cnt][1]);
        isSelected[cnt] = false;
        subset(cnt + 1, tot);

    }

}
