import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//완탐
//시간복잡도 O(N^2 * M^2)
//시간 초과
public class BOJ_G3_2206_벽부수고이동하기 {

    static int N, M;
    static int map[][];
    static boolean visited[][];
    static Queue<int[]> blockQue;
    static int min;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int count = 1;
    static boolean finished;
    static boolean neverFinished = true;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blockQue = new ArrayDeque<>();
        map = new int[N][M];
        visited = new boolean[N][M];

        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 1) {
                    blockQue.offer(new int[]{i, j}); //벽을 모두 큐에 넣음
                }
            }
        }
        // ---------------------입력 완료

        //벽이 하나도 없는 경우
        if (blockQue.isEmpty()) {

            bfs();
            min = count;
        }
        //벽이 존재하는 경우
        else {
            while (!blockQue.isEmpty()) {
                int[] broken = blockQue.poll(); //벽을 하나씩 꺼내서
                map[broken[0]][broken[1]] = 0; //벽을 부수고
                count = 1;
                finished = false;

                bfs(); //거리를 구하고

                map[broken[0]][broken[1]] = 1; //벽 복원
                visited = new boolean[N][M];

                if (finished && (count < min)) { //벽을 하나씩 부쉈을 때의 거리 비교
                    min = count;
                    if (min == N + M - 1) {
                        break;
                    }
                }
            }
        }

        if (neverFinished) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }

    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!check(nr, nc)) {
                        continue;
                    }
                    if (nr == N - 1 && nc == M - 1) {
                        finished = true;
                        neverFinished = false;
                        count++;
                        return;
                    }
                    if (map[nr][nc] == 1 || visited[nr][nc]) {
                        visited[nr][nc] = true;
                        continue;
                    }
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
            count++;

            if (count > min) {
                return;
            }
        }

    }
    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}

