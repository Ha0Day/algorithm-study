import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_S3_1463_1로만들기 {

    static int N;
    static boolean visited[];
    static int count;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];

        bfs();

        System.out.println(count);

    }

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.offer(N);
        visited[N] = true;

        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int x = q.poll();
                if (x == 1) {
                    return;
                }
                if (x % 3 == 0 && !visited[x / 3]) {
                    q.offer(x / 3);
                    visited[x / 3] = true;
                }
                if (x % 2 == 0 && !visited[x / 2]) {
                    q.offer(x / 2);
                    visited[x / 2] = true;
                }
                if (!visited[x - 1]) {
                    q.offer(x - 1);
                    visited[x - 1] = true;
                }
            }
            count++;
        }
    }
}
