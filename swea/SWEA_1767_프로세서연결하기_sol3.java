import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;

//Sol3
//백트래킹
//dfs 후 상태 원상복구해야 함
//deep copy 사용하지 않고
//원본 map 자체를 계속 바꿈
public class SWEA_1767_프로세서연결하기_sol3 {

    static int N;

    static int listSize;
    static int maxCoreNum;
    static int minWireLen;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int[][] map;
    static List<int[]> coreList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            maxCoreNum = -1;
            minWireLen = Integer.MAX_VALUE;
            map = new int[N][N];
            coreList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (i > 0 && i < N - 1 && j > 0 && j < N - 1 && map[i][j] == 1) {
                        coreList.add(new int[]{i, j});
                    }
                }
            }
            listSize = coreList.size();
            dfs(0, 0, 0);
            sb.append("#").append(t).append(" ").append(minWireLen).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int cnt, int wireLen, int coreNum) {
        if (maxCoreNum < coreNum) {
            maxCoreNum = coreNum;
            minWireLen = wireLen;
        } else if (maxCoreNum == coreNum) {
            minWireLen = Math.min(minWireLen, wireLen);
        }
        if (cnt == listSize) {
            return;
        }

        int[] p = coreList.get(cnt);

        for (int d = 0; d < 4; d++) {
            boolean check = true; //전원에 연결되었는지 체크하는 변수
            int length = 0; //전선 길이
            int r = p[0] + dr[d]; //좌표 이동
            int c = p[1] + dc[d]; //좌표 이동
            while (r >= 0 && r < N && c >= 0 && c < N) {
                if (map[r][c] != 0) { //빈칸이 아니면 = 코어 또는 다른 전선이 있다면
                    check = false; //전원 연결 안 됨
                    break;
                }
                map[r][c] = -1; //전선으로 바꿈
                length++; //한 칸 이동할 때마다 전선길이 증가
                r += dr[d];
                c += dc[d];
            }
            if (check) { //전원 연결이 되면
                dfs(cnt + 1, wireLen + length, coreNum + 1);
            }

            //dfs 전으로 map 되돌리기
            r = p[0] + dr[d];
            c = p[1] + dc[d];
            while (length > 0) {
                map[r][c] = 0;
                length--;
                r += dr[d];
                c += dc[d];
            }
        }
        //"전원에 연결할 수 있는 상황이더라도" 현재 전원에 연결하지 않는 것이 결론적으로 가장 많은 코어를 연결하게 될 수도 있음
        //따라서 전원에 연결되지 않는 경우도 들어가야 함
        //그래서 for문 밖에 위치해야 함 (4방으로 전원 연결 가능 여부와 상관없이 수행돼야 하므로)
        dfs(cnt + 1, wireLen, coreNum);
    }

}
