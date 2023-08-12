import java.util.ArrayDeque;
import java.util.Queue;

public class dfs_bfs_0807 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int N, M;
    static int[][] map;
    static int max;
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) {

        map = new int[][]{
                {1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
                {0, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 1, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                {0, 1, 1, 1, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 1, 1, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 1, 1, 1, 0, 0}
        };

        N = map.length;
        M = map[0].length;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    count = 0;
                    bfs(i, j);
                    if (count > max) {
                        max = count;
                    }
                }
            }
        }
        System.out.println(max);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        count++;

        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] pos = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = pos[0] + dr[d];
                    int nc = pos[1] + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
                        continue;
                    }
                    if (map[nr][nc] == 1) {
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                        count++;
                    }
                }
            }
        }
    }
}
