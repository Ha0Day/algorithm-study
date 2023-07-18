import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Q1868_파핑파핑지뢰찾기 {
    static int T, N;
    static int res;
    static char map[][];
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] mCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            res=0;
            map = new char[N][N];
            mCnt = new int[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }
            //주변 지뢰 세기
            checkNum();

            //주변에 지뢰 없는 곳부터 누르기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mCnt[i][j] == 0 && map[i][j] != '*') {
                        click(i, j);
                        res++;
                    }
                }
            }
            //아직 눌리지 않은 곳 누르기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mCnt[i][j] > 0 && map[i][j] != '*') {
                        res++;
                    }
                }
            }
            System.out.println("#" + t + " " + res);
        }
    }

    private static void click(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));

        //방문체크
        mCnt[x][y] = -1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            //주변 탐색
            for (int d = 0; d < 8; d++) {
                int xx = now.x + dx[d];
                int yy = now.y + dy[d];
                //범위를 벗어나거나 이미 눌린 곳이거나 지뢰면 pass
                if (xx < 0 || xx >= N || yy < 0 || yy >= N || mCnt[xx][yy] == -1 || map[xx][yy] == '*') continue;
                //주변 좌표도 지뢰 count가 0이라면 queue에 추가
                if (mCnt[xx][yy] == 0) {
                    q.add(new Point(xx, yy));
                }
                //방문 체크
                mCnt[xx][yy] = -1;
            }
        }
    }

    public static void checkNum() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                //클릭할 수 있는 곳이라면 주변에 지뢰가 몇 개 있는지 세보자
                if (map[x][y] == '.') {
                    int count = 0;
                    for (int d = 0; d < 8; d++) {
                        int xx = x + dx[d];
                        int yy = y + dy[d];
                        //범위를 벗어나거나 지뢰가 아니면 pass
                        if (xx < 0 || xx >= N || yy < 0 || yy >= N || map[xx][yy] != '*') continue;
                        count++;
                    }
                    mCnt[x][y] = count;
                }
            }

        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}