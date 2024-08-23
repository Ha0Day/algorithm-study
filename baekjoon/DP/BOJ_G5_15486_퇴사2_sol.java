package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ_G5_15486_퇴사2_sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1]; // 상담 기간
        int[] P = new int[N + 1]; // 금액
        int[] dp = new int[N + 2]; // 오늘까지의 최대 수익

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            T[i] = Integer.parseInt(input[0]);
            P[i] = Integer.parseInt(input[1]);
        }

        for (int i = 1; i <= N; i++) {
            // i일에 상담을 하지 않는 경우
            // 상담을 진행하지 않았으므로 dp[i]은 dp[i-1]와 동일하나, dp[i]가 이전에 계산됐을 수 있으므로 비교
            dp[i] = Math.max(dp[i - 1], dp[i]);

            // i일에 상담을 한 경우
            int nextDay = i + T[i] - 1;
            if (nextDay <= N) {
                dp[nextDay] = Math.max(dp[nextDay], dp[i - 1] + P[i]);
            }
        }

        System.out.println(dp[N]);
    }
}