package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_2178_미로탐색 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited; // 방문여부 뿐 아니라 거리까지 구할 때는 boolean 말고 int 배열 사용
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char[] cs = s.toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = cs[j] - '0';
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        count = 1;

        a:
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                if (r == N - 1 && c == M - 1) {
                    break a;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!check(nr, nc)) {
                        continue;
                    }
                    if (map[nr][nc] == 0) {
                        continue;
                    }
                    if (visited[nr][nc] == false) {
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true; // 거리이면서 간 적 있는지 체크
                    }
                }
            }
            count++;
        }
        System.out.println(count);

    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}
