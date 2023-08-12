package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S5_2563_색종이 {

    static int[][] map;
    static int N;
    static int pos[][];
    static int count;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pos = new int[N][2];
        int maxX = 0, maxY = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
            if (maxX < pos[i][0]) {
                maxX = pos[i][0];
            }
            if (maxY < pos[i][1]) {
                maxY = pos[i][1];
            }
        }

        map = new int[maxX + 10][maxY + 10];

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[pos[k][0] + i][pos[k][1] + j] = 1;
                }
            }
        }

        for (int i = 0; i < maxX + 10; i++) {
            for (int j = 0; j < maxY + 10; j++) {
                if (map[i][j] == 1) {
                    count++;
                }
            }
        }

        System.out.println(count);

    }

}
