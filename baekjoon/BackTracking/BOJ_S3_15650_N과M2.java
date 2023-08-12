package BackTracking;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_S3_15650_N과M2 {

    static int N;
    static int M;
    static int[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M + 1];
        sb = new StringBuilder();
        int at = 1;
        getSequence(1, at);
        System.out.print(sb);

    }

    static void getSequence(int index, int at){

        if (index - 1 == M) {
            for (int j = 1; j < arr.length; j++) {
                sb.append(arr[j]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = at; i <= N; i++) {
            arr[index] = i;
            at = i + 1;
            getSequence(index + 1, at);
        }
    }
}
