package BackTracking;

import java.io.*;
import java.util.StringTokenizer;

public class Q15652 {

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

        getSequence(0, 1);
        System.out.print(sb);

    }

    static void getSequence(int index, int at) {

        if (index == M) {
            for (int j = 0; j < arr.length; j++) {
                sb.append(arr[j]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = at; i <= N; i++) {
            arr[index] = i;
            getSequence(index + 1, i);
        }
    }
}
