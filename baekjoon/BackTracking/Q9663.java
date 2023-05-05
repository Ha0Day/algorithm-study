package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663 {
    static int N;
    static int count;
    static int[][] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        table = new int[N][N];
        count = 0;
        nQueen(0);
        System.out.print(count);
    }

    private static void nQueen(int row) {
        if (row == N) {
            count = count + 1;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!check(row, i)) {
                continue;
            }
            table[row][i] = 1;
            nQueen(row + 1);
            table[row][i] = 0;
        }
    }

    static boolean check(int r, int c) {
        for (int i = 0; i < N; i++) {
            if (table[i][c] == 1) return false;
        }
        int i, j;
        for (i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (table[i][j] == 1) return false;
        }
        for (i = r - 1, j = c + 1; i >= 0 && j < N; i--, j++) {
            if (table[i][j] == 1) return false;
        }
        return true;
    }
}
