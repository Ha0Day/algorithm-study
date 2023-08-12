import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_D2_1954_달팽이숫자{
    static int T, N;
    static int map[][];
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };
    static int num;
    static boolean[][] visited;
    static int d;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            num = 1;
            visited = new boolean[N][N];
            d=0;

            snail(0, 0);

            sb.append("#" + t + "\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }

        }
        System.out.println(sb);

    }

    private static void snail(int i, int j) {

        if (num == N * N + 1) {
            return;
        }
        map[i][j] = num++;
        visited[i][j] = true;
        int nr = i + dr[d];
        int nc = j + dc[d];
        if (nr < 0 || nr >= N || nc < 0 | nc >= N || visited[nr][nc]) {
            d = (d + 1) % 4;
            nr = i + dr[d];
            nc = j + dc[d];
        }
        snail(nr, nc);

    }
}
