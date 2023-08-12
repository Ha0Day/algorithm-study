package Implementation;

import java.util.Scanner;

public class BOJ_G4_2448_별찍기11 {

    static int N;
    static int[][] star;

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);
        N = scann.nextInt();
        star = new int[N][2 * N - 1];

        star(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(star[i][j] == 1 ? "*" : " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void star(int r, int c, int n) {
        // end condition BC
        if (n == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i == 0 && j == 2) star[r + i][c + j] = 1;
                    if (i == 1 && (j == 1 || j == 3)) star[r + i][c + j] = 1;
                    if (i == 2) star[r + i][c + j] = 1;
                }
            }
            return;
        }
        star(r, c + n / 2, n / 2);
        star(r + n / 2, c, n / 2);
        star(r + n / 2, c + n, n / 2);
    }
}