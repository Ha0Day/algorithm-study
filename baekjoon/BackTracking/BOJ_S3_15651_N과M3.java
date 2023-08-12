package BackTracking;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_S3_15651_N과M3 {

    static int N;
    static int M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        getSequence(0);
        System.out.print(sb);
    }

    static void getSequence(int index) {

        if (index == M) {
            for (int j = 0; j < arr.length; j++) {
                sb.append(arr[j]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[index] = i;
            getSequence(index + 1);
        }
    }
}
