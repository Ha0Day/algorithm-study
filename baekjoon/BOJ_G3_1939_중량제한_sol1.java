import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_1939_중량제한_sol1 {
    public static int N, M;
    public static List<int[]>[] adjacent;
    public static int island1, island2;
    public static int answer;
    public static boolean visited[];
    public static int weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjacent = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            adjacent[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            weight = Math.max(weight, c);
            adjacent[a].add(new int[]{b, c});
            adjacent[b].add(new int[]{a, c});
        }

        st = new StringTokenizer(br.readLine());
        island1 = Integer.parseInt(st.nextToken());
        island2 = Integer.parseInt(st.nextToken());

        int low = 1;
        int high = weight; //입력 받은 중량 중 최댓값으로 초기화

        while (low <= high) {
            visited = new boolean[N + 1];
            int mid = (low + high) / 2;
            if (bfs(mid)) { //해당 중량을 견딜 수 있는 경우
                low = mid + 1;
                answer = mid;
            } else { //해당 중량을 견딜 수 없는 경우
                high = mid - 1;
            }
        }

        System.out.println(answer);

    }

    public static boolean bfs(int mid) {
        Queue q = new ArrayDeque<Integer>();
        visited[island1] = true;
        q.offer(island1);

        while (!q.isEmpty()) {
            int cur = (int) q.poll();
            if (cur == island2) {
                return true;
            }
            for (int[] next : adjacent[cur]) {
                if (!visited[next[0]]) {
                    if (next[1] >= mid) {
                        q.offer(next[0]);
                        visited[next[0]] = true;
                    }
                }
            }
        }
        return false;
    }
}
