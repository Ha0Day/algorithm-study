import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_2573_빙산 {

    static int N, M;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static List<Integer> list;
    static int year; //정답. 빙산이 분리되는 최초의 시간(년)
    static boolean separated; //빙산이 분리되었는지 확인하는 변수
    static int tempC, tempR; //bfs 코드에서 큐에 넣을 초기 좌표
    static boolean[][] visited;

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

        //--------------- 입력 완료

        //시뮬
        while (true) {
            list = new ArrayList<>(); //리스트 초기화

            //인접한 0의 개수 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) { //빙산이 있으면
                        int count = 0;
                        for (int d = 0; d < 4; d++) { //4방의 0개수 확인
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if (!check(nr, nc)) {
                                continue;
                            }
                            if (map[nr][nc] == 0) {
                                count++; //0의 개수
                            }
                        }
                        list.add(count); //0 개수 저장
                    }
                }
            }

            //다 녹았으면
            if (list.size() == 0) {
                year = 0;
                break;
            }

            int index = 0;
            boolean melt = false; //올해 아예 사라진 빙산이 있는지 확인하는 변수

            //저장한 0 개수만큼 빙산 크기 줄이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) {
                        map[i][j] = map[i][j] - list.get(index++); //빙산이 인접한 0의 개수만큼 녹음
                        if (map[i][j] <= 0) {
                            map[i][j] = 0;
                            melt = true; //아예 사라진 빙산이 존재
                        } else {
                            tempR = i; //남아 있는 빙산 좌표 아무거나 저장 (bfs시 큐에 넣기 위함)
                            tempC = j;
                        }
                    }
                }
            }
            year++;
            separated = false; //bfs 돌기 전 초기화

            //올해 아예 사라진 빙산이 있다면 분리 여부 확인
            if (melt) {
                bfs(); //분리되었는지 확인
                if (separated) { //분리되었다면
                    break;
                }
            }
        }

        System.out.println(year);

    }

    static void bfs() {
        visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{tempR, tempC});
        visited[tempR][tempC] = true;

        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!check(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) {
                        continue;
                    }
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        //큐가 비어 있는데 빙산이 있는 곳 중 visited하지 않은 곳이 있다면 분리된 것
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && visited[i][j] == false) {
                    separated = true;
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
