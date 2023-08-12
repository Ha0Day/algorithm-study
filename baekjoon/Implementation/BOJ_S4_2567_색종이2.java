package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S4_2567_색종이2 {

    static int[][] map;
    static int N;
    static int pos[][];
    static int count;
    static int line;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pos = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());

        }

        map = new int[102][102];

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[pos[k][0] + i][pos[k][1] + j] = 1;
                }
            }
        }

        for (int i = 1; i < 102; i++) {
            for (int j = 1; j < 102; j++) {
                if (map[i][j] != map[i - 1][j]) { // 0, 1 값이 서로 다르면 테두리이다!
                    line++;    //가로 테두리 추가
                }
                if (map[i][j] != map[i][j - 1]) {
                    line++;    //세로 테두리 추가
                }
            }
        }

        System.out.println(line);

    }

}
