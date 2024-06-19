package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_27211_도넛행성 {
    static int N, M;
    static int[][] map;
    static int count = 0; //구역 수
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);

    }

    public static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque();
        visited[i][j] = true;
        q.offer(new int[]{i, j});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                //좌표가 음수가 되거나 최댓값을 넘은 경우 처리
                nr = nr == -1 ? N - 1 : nr;
                nr = nr == N ? 0 : nr;
                nc = nc == -1 ? M - 1 : nc;
                nc = nc == M ? 0 : nc;

                //이미 방문했거나 숲인 경우는 스킵
                if (visited[nr][nc] || map[nr][nc] == 1) {
                    continue;
                }

                q.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        count++; //구역 내에 갈 수 있는 곳을 모두 간 후 구역 수 증가
    }
}
