import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Sol1
//백트래킹
//dfs 후 상태 원상복구해야 함
//deep copy 사용
//배열 복사본 만들어서 매개변수로 넘김 - 추천 x (메모리 사용을 많이 함)
public class BOJ_G4_15683_감시 {
    static int N, M;
    static int[][] map;
    static ArrayList<CCTV> cameras;
    static int min = Integer.MAX_VALUE;
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

        boolean[][] visited = new boolean[N][M];

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

        dfs(0, visited);

        System.out.println(min);

    }

    static void dfs(int cnt, boolean[][] visited) {
        if (cnt == cameras.size()) {
            checkMin(visited);
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

        int d = 0;
        while (d < num) { //회전 횟수만큼 반복

            //visited 배열 복사
            //백트래킹 코드 따로 쓰지 않기 위해
            boolean visitedCopy[][] = new boolean[visited.length][visited[0].length];

            for (int i = 0; i < visitedCopy.length; i++) {
                System.arraycopy(visited[i], 0, visitedCopy[i], 0, visited[0].length);
            }

            //종류별 방향 갯수만큼 탐색
            for (int dirIndex : typeDirections[camera.type]) {
                int dir = (dirIndex + d) % 4;
                int r = camera.r;
                int c = camera.c;

                move(r, c, dir, visitedCopy); //감시 가능 구역의 visited를 true로 바꿈

            }
            dfs(cnt + 1, visitedCopy); //다음 카메라
            d++;
        }
    }

    private static void checkMin(boolean[][] visited) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == false && map[i][j] == 0) { //사각지대
                    count++;
                }
            }
        }
        if (count < min) {
            min = count;
        }
    }

    static void move(int r, int c, int dir, boolean[][] visited) {
        if (dir == 0) { //우
            while (canGo(r, c + 1)) {
                c++;
                visited[r][c] = true;
            }
        } else if (dir == 1) { //상
            while (canGo(r - 1, c)) {
                r--;
                visited[r][c] = true;
            }
        } else if (dir == 2) { //좌
            while (canGo(r, c - 1)) {
                c--;
                visited[r][c] = true;
            }
        } else if (dir == 3) { //하
            while (canGo(r + 1, c)) {
                r++;
                visited[r][c] = true;
            }
        }
    }

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
