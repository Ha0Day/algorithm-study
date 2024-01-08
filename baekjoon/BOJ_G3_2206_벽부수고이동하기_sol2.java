import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//3차원 배열 사용
public class BOJ_G3_2206_벽부수고이동하기_sol2 {

    static int N, M;
    static int map[][];
    static int dist[][][]; //해당 정점까지의 최단거리
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M][2]; // 인덱스0 : x좌표, 인덱스1 : y좌표, 인덱스2 : 0번 또는 1번 정점(각 좌표를 모두 2개의 정점을 나눔)

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        // ---------------------입력 완료

        System.out.println(bfs());
    }

    //목적지까지의 최단 거리를 리턴하는 함수
    static int bfs() {
        if (N == 1 && M == 1) {
            return 1;
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0}); //각 격자를 2개의 정점으로 나눴으므로 (격자 좌표 + 0번/1번 정점) 을 큐에 넣어야 함
        dist[0][0][0] = 1;

        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] cur = q.poll();
                int r = cur[0]; //x좌표
                int c = cur[1]; //y좌표
                int wall = cur[2]; //0번 또는 1번 정점
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!check(nr, nc)) {
                        continue;
                    }

                    //다음 정점의 값이 1인 경우
                    //현재 이미 벽을 부순 상태라면 pass
                    //이미 벽을 부수고 방문한 상태라면 pass
                    if ( map[nr][nc] == 1 && wall == 0 &&dist[nr][nc][1] == 0) {
                        dist[nr][nc][1] = dist[r][c][0] + 1; //무조건 0번 정점에서 왔을 것이므로
                        q.offer(new int[]{nr, nc, 1});
                    }

                    //다음 정점의 값이 0인 경우
                    if (map[nr][nc] == 0) {
                        if (dist[nr][nc][wall] == 0) {
                            dist[nr][nc][wall] = dist[r][c][wall] + 1;
                            if (nr == N - 1 && nc == M - 1) {
                                return dist[nr][nc][wall]; //어차피 0,1번 정점 중 더 작은 값이 최종 답이므로 비교할 필요 없이 먼저 도달한 값 리턴
                            }
                            q.offer(new int[]{nr, nc, wall});
                        }
                    }
                }
            }
        }
        return -1; //도달하지 못한다면 -1 리턴
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}

