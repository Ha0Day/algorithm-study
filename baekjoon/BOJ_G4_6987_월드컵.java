import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_6987_월드컵 {

    static int[][] score;
    static int[][] match;
    static boolean possible;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= 4; t++) {
            score = new int[6][3];
            possible = false;

            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 6; k++) {
                for (int i = 0; i < 3; i++) {
                    score[k][i] = Integer.parseInt(st.nextToken());
                    sum += score[k][i];
                }
            }

            // ------------------입력 완료

            match = new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4},
                    {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}};
            // 1 : A-B
            // 2 : A-C
            // 3 : A-D
            // 4 : A-E
            // 5 : A-F
            // 6 : B-C
            // 7 : B-D
            // 8 : B-E
            // 9 : B-F
            // 10 : C-D
            // 11 : C-E
            // 12 : C-F
            // 13 : D-E
            // 14 : D-F
            // 15 : E-F

            if (sum != 30) {
                possible = false;

            } else {
                dfs(0);

            }
            if (possible) {
                sb.append(1 + " ");
            } else {
                sb.append(0 + " ");
            }
        }

        System.out.println(sb);

    }

    static void dfs(int matchNum) {

        if (matchNum == 15) {
            possible = true;
            return;
        }

        if (score[match[matchNum][0]][0] > 0 && score[match[matchNum][1]][2] > 0) {
            score[match[matchNum][0]][0]--;
            score[match[matchNum][1]][2]--;
            dfs(matchNum + 1);
            score[match[matchNum][0]][0]++;
            score[match[matchNum][1]][2]++;

        }
        if (score[match[matchNum][0]][2] > 0 && score[match[matchNum][1]][0] > 0) {
            score[match[matchNum][0]][2]--;
            score[match[matchNum][1]][0]--;
            dfs(matchNum + 1);
            score[match[matchNum][0]][2]++;
            score[match[matchNum][1]][0]++;

        }
        if (score[match[matchNum][0]][1] > 0 && score[match[matchNum][1]][1] > 0) {
            score[match[matchNum][0]][1]--;
            score[match[matchNum][1]][1]--;
            dfs(matchNum + 1);
            score[match[matchNum][0]][1]++;
            score[match[matchNum][1]][1]++;

        }

    }

}
