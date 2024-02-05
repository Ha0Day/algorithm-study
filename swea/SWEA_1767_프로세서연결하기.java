import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//Sol1
//백트래킹
//dfs 후 상태 원상복구해야 함
//deep copy 사용
//배열 복사본 만들어서 매개변수로 넘김 - 추천 x (메모리 사용을 많이 함)
public class SWEA_1767_프로세서연결하기 {
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
    static int length;
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
            boolean[][] visited = new boolean[N][N];
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

            dfs(0, 0, 0, visited);

            sb.append("#" + t + " " + minWireLen + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(int cnt, int coreCnt, int wireLen, boolean[][] visited) {
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

        //4방 중 하나를 선택한 다음 coreList의 코어 끝까지 감
        //그 다음 돌아와서 다음 방향 선택해서 또 끝까지 들어감
        for (int d = 0; d < 4; d++) {
            //visited 배열 deep copy. visited 값 원위치로 돌리지 않기 위해
            boolean[][] visitedCopy = new boolean[visited.length][visited[0].length];
            for (int i = 0; i < visitedCopy.length; i++) {
                System.arraycopy(visited[i], 0, visitedCopy[i], 0, visited[0].length);
            }
            //가는 길에 아무것도 없으면 coreCnt++, 전선 길이도 증가
            length = 0; //증가하는 전선 길이
            if (isPossible(cur.x, cur.y, d, visitedCopy)) {
                dfs(cnt + 1, coreCnt + 1, wireLen + length, visitedCopy);
            }
        }
        //"전원에 연결할 수 있는 상황이더라도" 현재 전원에 연결하지 않는 것이 결론적으로 가장 많은 코어를 연결하게 될 수도 있음
        //따라서 전원에 연결되지 않는 경우도 들어가야 함
        //그래서 for문 밖에 위치해야 함 (4방으로 전원 연결 가능 여부와 상관없이 수행돼야 하므로)
        dfs(cnt + 1, coreCnt, wireLen, visited);
        //여기서 visitedCopy를 넘겨 버리면 isPossible에서 false가 되는 과정에서 바뀐 값들이 원상복구되지 않고 넘어가게 됨
    }

    //전원이 연결되는지를 리턴하는 함수
    static boolean isPossible(int r, int c, int d, boolean[][] visited) {

        int nr = r + dr[d];
        int nc = c + dc[d];
        while (canGo(nr, nc, visited)) { //전선이 뻗어나갈 수 있으면
            visited[nr][nc] = true; //전선이 있다고 표시
            nr = nr + dr[d]; //이동
            nc = nc + dc[d]; //이동
            length++; //한 칸씩 이동할 때마다 전선길이도 증가
        }

        //전원이 흐르는 가장자리까지 도달했으면 true, 도달 못했으면 false 리턴
        if (d == 0 && nc == N) { //우
            return true;
        }
        if (d == 1 && nr == N) { //하
            return true;
        }
        if (d == 2 && nc == -1) { //좌
            return true;
        }
        if (d == 3 && nr == -1) { //상
            return true;
        }
        return false;
    }

    //전선이 뻗어나갈 수 있는지 체크하는 함수
    //범위를 벗어나거나, 전선이 있거나, 코어가 있으면 false를, 그게 아니면 true를 리턴하는 함수
    static boolean canGo(int r, int c, boolean[][] visited) {
        return r >= 0 && r < N && c >= 0 && c < N && !visited[r][c] && map[r][c] == 0;
    }
}
