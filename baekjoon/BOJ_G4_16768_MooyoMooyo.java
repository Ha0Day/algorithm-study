import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_16768_MooyoMooyo {
    static int N, K, map[][];
    static boolean visited[][];
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean changed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][10];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < 10; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        //------------------ 입력 완료

        do {
            changed = false;
            visited = new boolean[N][10];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 10; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j); //사라진 칸 확인
                    }
                }
            }
            gravity(); //중력 적용
        } while (changed); //사라진 칸이 있으면 반복

        //정답 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void gravity() {

        for (int j = 0; j < 10; j++) {
            int emptyRow = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    if (emptyRow != i) {
                        map[emptyRow][j] = map[i][j];
                        map[i][j] = 0;
                    }
                    emptyRow--;
                }
            }
        }
    }

    public static void bfs(int r, int c) {
        int count = 1;
        Queue<int[]> q = new ArrayDeque();
        List list = new ArrayList<int[]>();
        q.offer(new int[]{r, c});
        list.add(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int cur[] = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (!check(nr, nc) || visited[nr][nc]) {
                    continue;
                }
                if (map[nr][nc] == map[cr][cc]) {
                    count++;
                    list.add(new int[]{nr, nc});
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        if (count >= K) {
            for (int i = 0; i < list.size(); i++) {
                int[] cell = (int[]) list.get(i);
                map[cell[0]][cell[1]] = 0;
            }
            changed = true;
        }
    }

    public static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < 10;
    }
}
