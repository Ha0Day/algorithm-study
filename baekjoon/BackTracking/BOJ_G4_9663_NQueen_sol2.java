package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_9663_NQueen_sol2 {

    static int N;
    static int[] cols, dia, undia;
    static int count;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cols = new int[N + 1];
        dia = new int[2 * N + 1];
        undia = new int[2 * N + 1];

        dfs(1);
        System.out.println(count);

    }

    static void dfs(int row) {
        if (row == N + 1) {
            count++;
            return;
        }

        for (int col = 1; col <= N; col++) {
            // 퀸이 같은 선 위에 놓여 있지 않다면
            if (!(cols[col] == 1 || dia[row + col] == 1 || undia[N + (row - col) + 1] == 1)) {
                //놓기!!
                cols[col] = 1;
                dia[row + col] = 1;
                undia[N + (row - col) + 1] = 1;

                dfs(row + 1);

                //다시 비우기
                cols[col] = 0;
                dia[row + col] = 0;
                undia[N + (row - col) + 1] = 0;
            }
        }

    }

}
