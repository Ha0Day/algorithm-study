import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_4963_섬의개수 {
    static int w, h;
    static int map[][];
    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
    static boolean[][] visited;
    static int unionNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            map = new int[h][w];
            visited = new boolean[h][w];

            unionNum = 0;

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //=========입력 완

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    //방문하지 않았고, 땅인 경우
                    if (!visited[i][j] && map[i][j] == 1) {
                        bfs(i, j);
                        unionNum++; //섬 개수 증가
                    }
                }
            }
            System.out.println(unionNum);
        }
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] cur = q.poll();
                for (int d = 0; d < 8; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if (!check(nr, nc)) {
                        continue;
                    }
                    if (visited[nr][nc]) {
                        continue;
                    }
                    if (map[nr][nc] == 1) {
                        q.offer(new int[]{nr, nc});
                    }
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < h && c >= 0 && c < w;
    }
}
