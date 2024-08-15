package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//sol 1. 누적합 활용
public class BOJ_G4_1749_점수따먹기 {
    public static int N, M;
    public static int map[][];
    public static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        // 1. dp 상태 배열 정의 : (1,1)~(x,y) 사각형 숫자의 합
        // 2. 초기값 정의 : 인덱스 0인 부분은 0으로 초기화하기 위해 배열의 행과 열 크기를 +1
        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //------------ 입력 완료

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 3-2. 점화식 세우기
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + map[i][j];
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int a = 1; a <= N; a++) {
            for (int b = 1; b <= M; b++) {
                for (int c = a; c <= N; c++) {
                    for (int d = b; d <= M; d++) {
                        // 3-1. 큰 점화식 세우기
                        int val = dp[c][d] - dp[c][b - 1] - dp[a - 1][d] + dp[a - 1][b - 1];
                        if (val > answer) {
                            answer = val;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
