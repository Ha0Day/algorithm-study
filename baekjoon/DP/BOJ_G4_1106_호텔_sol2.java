package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_1106_호텔_sol2 {
    static int C, N;
    static int cost[], people[];
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cost = new int[N + 1];
        people = new int[N + 1];

        dp = new int[C + 100]; //dp[i] : i명의 고객을 늘리기 위한 최소 비용
        //한 도시에서 얻을 수 있는 최대 명수가 100명이므로 C+100

        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            people[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = people[i]; j < dp.length; j++) {
                if (dp[j - people[i]] != Integer.MAX_VALUE) {
                    dp[j] = Integer.min(dp[j], dp[j - people[i]] + cost[i]);
                }
            }
        }
        int min = Integer.MAX_VALUE;

        for (int i = C; i < dp.length; i++) {
            if (dp[i] < min) {
                min = dp[i];
            }
        }
        System.out.println(min);
    }
}
