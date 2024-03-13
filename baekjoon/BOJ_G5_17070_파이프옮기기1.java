import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17070_파이프옮기기1 {
    static int N;
    static int[][] map;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 2); //가로, (1,2)에 끝이 위치한 상태에서 시작
        System.out.println(count);
    }

    static void dfs(int type, int x, int y) {
        if (x == N && y == N) { //(N,N)에 도착한 경우
            count++;
            return;
        }
        if (type == 0) { //가로이면
            horizontal(x, y);
        }
        if (type == 1) { //세로이면
            vertical(x, y);
        }
        if (type == 2) { //대각선이면
            horizontal(x, y);
            vertical(x, y);
        }
        diagonal(x, y); //대각선으로는 어떤 경우든 갈 수 있음

    }

    //가로
    private static void horizontal(int x, int y) {
        if (check(x, y + 1)) {
            dfs(0, x, y + 1);
        }
    }

    //세로
    private static void vertical(int x, int y) {
        if (check(x + 1, y)) {
            dfs(1, x + 1, y);

        }
    }

    //대각선
    private static void diagonal(int x, int y) {
        if (check(x + 1, y + 1)) {
            if (map[x + 1][y] == 0 && map[x][y + 1] == 0) {
                dfs(2, x + 1, y + 1);
            }

        }
    }

    //map 범위를 벗어나지 않고, 그 칸에 벽이 없는지 확인하는 함수
    static boolean check(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N && map[x][y] == 0;
    }
}
