import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//Sol3
//백트래킹
//dfs 후 상태 원상복구해야 함
//deep copy 사용하지 않고
//원본 map 자체를 계속 바꿈
public class BOJ_G3_15683_감시_sol3 {
    static int n;
    static int m;
    static int blindSpot;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int[][] map;
    static List<List<Integer>> cctvList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cctvList = new ArrayList<>();
        List<Integer> cctv;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if ((map[i][j] > 0) && (map[i][j] < 6)) {
                    cctv = new ArrayList<>();
                    cctv.add(i);
                    cctv.add(j);
                    cctvList.add(cctv);
                }
            }
        }
        blindSpot = n * m;
        dfs(0);
        System.out.println(blindSpot);
    }

    public static void dfs(int depth) {
        if (depth == cctvList.size()) {
            int num = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0)
                        num++;
                }
            }
            if (num < blindSpot)
                blindSpot = num;
            return;
        }
        List<Integer> cctv = cctvList.get(depth);
        int r = cctv.get(0);
        int c = cctv.get(1);
        switch (map[r][c]) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    observe(r, c, dr[i], dc[i]);
                    dfs(depth + 1);
                    release(r, c, dr[i], dc[i]);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    observe(r, c, dr[i], dc[i]);
                    observe(r, c, dr[i + 2], dc[i + 2]);
                    dfs(depth + 1);
                    release(r, c, dr[i], dc[i]);
                    release(r, c, dr[i + 2], dc[i + 2]);
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    observe(r, c, dr[i], dc[i]);
                    observe(r, c, dr[(i + 1) % 4], dc[(i + 1) % 4]);
                    dfs(depth + 1);
                    release(r, c, dr[i], dc[i]);
                    release(r, c, dr[(i + 1) % 4], dc[(i + 1) % 4]);
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    observe(r, c, dr[i], dc[i]);
                    observe(r, c, dr[(i + 1) % 4], dc[(i + 1) % 4]);
                    observe(r, c, dr[(i + 3) % 4], dc[(i + 3) % 4]);
                    dfs(depth + 1);
                    release(r, c, dr[i], dc[i]);
                    release(r, c, dr[(i + 1) % 4], dc[(i + 1) % 4]);
                    release(r, c, dr[(i + 3) % 4], dc[(i + 3) % 4]);
                }
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    observe(r, c, dr[i], dc[i]);
                }
                dfs(depth + 1);
                for (int i = 0; i < 4; i++) {
                    release(r, c, dr[i], dc[i]);
                }
                break;
        }
    }

    public static void observe(int r, int c, int dRow, int dCol) {
        int row = r;
        int col = c;
        while (row >= 0 && row < n && col >= 0 && col < m && map[row][col] != 6) {
            if (map[row][col] <= 0)
                map[row][col]--;
            row += dRow;
            col += dCol;
        }
    }

    public static void release(int r, int c, int dRow, int dCol) {
        int row = r;
        int col = c;
        while (row >= 0 && row < n && col >= 0 && col < m && map[row][col] != 6) {
            if (map[row][col] < 0)
                map[row][col]++;
            row += dRow;
            col += dCol;
        }
    }

}