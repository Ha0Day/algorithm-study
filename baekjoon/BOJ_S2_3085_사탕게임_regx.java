import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S2_3085_사탕게임_regx {
    private static int N;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j + 1 < N) {
                    swap(i, j, i, j + 1);
                    answer = Math.max(answer, findMaxCandies());
                    swap(i, j, i, j + 1);
                }
                if (i + 1 < N) {
                    swap(i, j, i + 1, j);
                    answer = Math.max(answer, findMaxCandies());
                    swap(i, j, i + 1, j);
                }
            }
        }

        System.out.println(answer);
    }

    private static void swap(int i1, int j1, int i2, int j2) {
        char temp = board[i1][j1];
        board[i1][j1] = board[i2][j2];
        board[i2][j2] = temp;
    }

    private static int findMaxCandies() {
        int maxCandies = 1;
        for (int i = 0; i < N; i++) {
            int count = 1;
            for (int j = 1; j < N; j++) {
                if (board[i][j] == board[i][j - 1]) count++;
                else count = 1;
                maxCandies = Math.max(maxCandies, count);
            }

            count = 1;
            for (int j = 1; j < N; j++) {
                if (board[j][i] == board[j - 1][i]) count++;
                else count = 1;
                maxCandies = Math.max(maxCandies, count);
            }
        }
        return maxCandies;
    }
}