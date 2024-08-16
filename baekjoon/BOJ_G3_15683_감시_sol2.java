import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Sol2
//백트래킹
//dfs 후 상태 원상복구해야 함
//deep copy 사용
//원본을 복사해뒀다가 dfs 끝나고 돌아올 때 복사해둔 원본으로 되돌림
public class BOJ_G3_15683_감시_sol2 {
    static int N, M;
    static int[][] map;
    static ArrayList<CCTV> cameras;
    static int min = Integer.MAX_VALUE;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] typeDirections = {
            {},
            {0},
            {0, 2},
            {0, 1},
            {0, 1, 2},
            {0, 1, 2, 3}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cameras = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cameras.add(new CCTV(map[i][j], i, j));
                }
            }
        }
        //---------입력 완료

        dfs(0);

        System.out.println(min);

    }

    static void dfs(int cnt) {
        if (cnt == cameras.size()) {
            checkMin();
            return;
        }

        CCTV camera = cameras.get(cnt);

        int num = 0; //회전해야 하는 횟수
        if (camera.type == 1 || camera.type == 3 || camera.type == 4) {
            num = 4;
        } else if (camera.type == 2) {
            num = 2;
        } else {
            num = 1;
        }

        //백트래킹을 위해 map의 복사본을 만들어 두기
        int mapCopy[][] = new int[map.length][map[0].length];
        for (int i = 0; i < mapCopy.length; i++) {
            System.arraycopy(map[i], 0, mapCopy[i], 0, map[0].length);
        }

        int d = 0;
        while (d < num) { //회전 횟수만큼 반복

            //종류별 방향 갯수만큼 탐색
            for (int dirIndex : typeDirections[camera.type]) {
                int dir = (dirIndex + d) % 4;
                int r = camera.r;
                int c = camera.c;

                move(r, c, dir);

            }
            dfs(cnt + 1); //다음 카메라

            //복사해둔 원본으로 다시 교체 (상태 되돌리기)
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(mapCopy[i], 0, map[i], 0, mapCopy[0].length);
            }
            d++;
        }
    }

    private static void checkMin() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { //사각지대
                    count++;
                }
            }
        }
        if (count < min) {
            min = count;
        }
    }

    static void move(int r, int c, int d) {

        int nr = r + dr[d];
        int nc = c + dc[d];

        while (canGo(nr, nc)) {
            map[nr][nc] = -1; //# 표시. 감시 가능-> 연결상황을 map에 반영
            nr += dr[d];
            nc += dc[d];
        }
    }

    //범위를 벗어나거나, 벽을 만나지 않으면 감시 가능
    static boolean canGo(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 6;
    }

    static class CCTV {
        int type;
        int r;
        int c;

        CCTV(int type, int r, int c) {
            this.type = type;
            this.r = r;
            this.c = c;
        }
    }
}
