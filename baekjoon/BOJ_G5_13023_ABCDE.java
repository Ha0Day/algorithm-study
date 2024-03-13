import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_G5_13023_ABCDE {
    static int N, M;
    static ArrayList<Integer>[] adjacent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjacent = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjacent[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjacent[a].add(b);
            adjacent[b].add(a);
        }
        int answer = 0;
        HashSet<Integer> set;
        for (int i = 0; i < N; i++) {
            set = new HashSet<>();
            set.add(i);
            if (dfs( i, 0, set)) {
                answer = 1;
                break;
            }
        }
        System.out.println(answer);
    }

    static boolean dfs(int cur, int cnt, HashSet<Integer> set) {
        if (cnt == 4) {
            return true;
        }

        set.add(cur);

        for (int adj : adjacent[cur]) {
            if (set.contains(adj)) {
                continue;
            }
            if (dfs(adj,cnt + 1, set)) {
                return true;
            }
        }
        set.remove(cur);
        return false;
    }
}
