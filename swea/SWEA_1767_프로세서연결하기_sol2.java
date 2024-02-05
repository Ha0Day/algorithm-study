import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//Sol2
//백트래킹
//dfs 후 상태 원상복구해야 함
//deep copy 사용
//원본을 복사해뒀다가 dfs 끝나고 돌아올 때 복사해둔 원본으로 되돌림
public class SWEA_1767_프로세서연결하기_sol2 {
    static class Core {
        int x;
        int y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int T, N;
    static int map[][];
    static List<Core> coreList; //가장자리에 있지 않은 코어 리스트
    static int maxCoreNum; //코어 개수 최댓값
    static int minWireLen; //코어 개수가 최대일 때, 전선 길이 최소값
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            coreList = new ArrayList<>();
            map = new int[N][N];
            minWireLen = 0;
            maxCoreNum = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) { //가장자리에 있는 코어(이미 전원에 연결되었기 때문)
                            continue; //리스트에 추가하지 않음
                        }
                        coreList.add(new Core(i, j)); //가장자리에 위치하지 않은 코어는 리스트에 추가
                    }
                }
            }
            //--------------입력 완료

            dfs(0, 0, 0);

            sb.append("#" + t + " " + minWireLen + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(int cnt, int coreCnt, int wireLen) {
        //가지치기
        //현재 깊이까지 선택된 코어 개수 + 남은 코어 개수 를 다 더해도 maxCoreNum보다 적으면 더 깊이 들어갈 필요가 없음
        if (coreCnt + (coreList.size() - cnt) < maxCoreNum) {
            return;
        }

        //base condition
        //코어 리스트의 모든 코어를 탐색한 상황이 되면
        if (cnt == coreList.size()) {
            if (coreCnt > maxCoreNum) {//코어 개수가 많은 것이 정답
                maxCoreNum = coreCnt;
                minWireLen = wireLen;
                return;
            }
            if (coreCnt == maxCoreNum) { //코어 개수가 같으면 전선길이가 짧은 것이 정답
                if (minWireLen > wireLen) {
                    minWireLen = wireLen;
                }
            }
            return;
        }

        Core cur = coreList.get(cnt);

        //map의 복사본을 만들어 두기(dfs 끝나고 돌아왔을 때 원래 상태로 되돌리기 위해)
        int[][] mapCopy = new int[map.length][map[0].length];
        for (int i = 0; i < mapCopy.length; i++) {
            System.arraycopy(map[i], 0, mapCopy[i], 0, map[0].length);
        }

        //4방 중 하나를 선택한 다음 coreList의 코어 끝까지 감
        //그 다음 돌아와서 다음 방향 선택해서 또 끝까지 들어감
        for (int d = 0; d < 4; d++) {
            //가는 길에 아무것도 없으면 coreCnt++, 전선 길이도 증가
            if (isPossible(cur.x, cur.y, d)) {
                // 현재위치에서 d방으로 2를 map에 반영
                int len = writeMap(cur.x, cur.y, d);
                //다음 코어로
                dfs(cnt + 1, coreCnt + 1, wireLen + len);
                //dfs 끝나면 되돌려 놓아야 함!
                //복사해뒀던 copyMap -> map 깊은복사
                for (int i = 0; i < N; i++) {
                    System.arraycopy(mapCopy[i], 0, map[i], 0, N);
                }
            }
        }
        //"전원에 연결할 수 있는 상황이더라도" 현재 전원에 연결하지 않는 것이 결론적으로 가장 많은 코어를 연결하게 될 수도 있음
        //따라서 전원에 연결되지 않는 경우도 들어가야 함
        //그래서 for문 밖에 위치해야 함 (4방으로 전원 연결 가능 여부와 상관없이 수행돼야 하므로)
        dfs(cnt + 1, coreCnt, wireLen);
    }

    // 현재의 상태를 map에 표시 -> 연결상황을 map에 반영
    static int writeMap(int r, int c, int d) {
        int len = 0;
        int nr = r;
        int nc = c;

        while (true) {
            nr = nr + dr[d];
            nc = nc + dc[d];
            if (!check(nr, nc)) { //전원이 연결되면 반복문 탈출
                break;
            }
            map[nr][nc] = 2; //전선이 있는 셀 2로 표시
            len++;
        }
        return len;
    }

    //전원이 연결되는지를 리턴하는 함수
    static boolean isPossible(int r, int c, int d) {

        int nr = r;
        int nc = c;
        while (true) { //전선이 뻗어나갈 수 있으면
            nr = nr + dr[d]; //이동
            nc = nc + dc[d]; //이동
            if (!check(nr, nc)) { //범위 밖으로 나가면 전원이 연결된 것
                return true;
            }
            if (map[nr][nc] == 1 || map[nr][nc] == 2) { //다른 전선 또는 다른 코어를 만나면 전원에 연결될 수 없음
                break;
            }
        }
        return false;
    }

    //범위를 벗어나는지 체크하는 함수
    //이 문제에서는 범위를 벗어나면 전원과 연결되는 것!
    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
