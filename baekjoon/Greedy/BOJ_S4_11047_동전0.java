package Greedy;

import java.io.*;
import java.util.*;

public class BOJ_S4_11047_동전0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(minCoin(arr, K));


    }

    private static int minCoin(int[] arr, int K) {
        int count = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= K) {
                count += K / arr[i];
                K = K % arr[i];

            }

        }
        if (K != 0) {
            return -1;
        }
        return count;
    }


}
