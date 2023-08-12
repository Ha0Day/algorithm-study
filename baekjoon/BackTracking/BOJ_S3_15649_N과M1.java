package BackTracking;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_S3_15649_N과M1 {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];
        boolean[] visited = new boolean[N + 1];

        getSequence(N, M, 0, arr, visited);

        bw.flush();
        bw.close();

    }

    static void getSequence(int n, int m, int index, int[] arr, boolean[] visited) throws IOException {

        if (index == m) {
            for (int j = 0; j < arr.length; j++) {
                bw.write(arr[j] + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            arr[index] = i;
            visited[i] = true;
            getSequence(n, m, index + 1, arr, visited);
            visited[i] = false;
        }
    }
}
