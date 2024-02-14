import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S3_1021_회전하는큐 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] num = new int[M];
        int count = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        LinkedList<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        for (int i = 0; i < M; i++) {
            int n = num[i];
            int size = q.size();
            int index = q.indexOf(n);

            if (q.indexOf(n) <= size / 2) { //앞에서 더 가까운 수인 경우
                for (int j = 0; j < index; j++) {
                    int a = q.pollFirst();
                    q.addLast(a);
                    count++;
                }
            } else { //뒤에서 더 가까운 수인 경우
                for (int j = 0; j < size - index; j++) {
                    int a = q.pollLast();
                    q.addFirst(a);
                    count++;
                }
            }
            q.poll();
        }
        System.out.println(count);
    }
}
