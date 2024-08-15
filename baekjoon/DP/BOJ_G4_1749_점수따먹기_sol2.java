package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//sol 2. 카데인 알고리즘 활용
public class BOJ_G4_1749_점수따먹기_sol2 {
    public static int N, M;
    public static int map[][], sum[][];
    public static int rowSum[];
    public static int curSum, maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        sum = new int[N][M]; //각 행의 처음부터 자신까지의 합
        rowSum = new int[N]; //현재 상태에서 각 행 별 합


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            sum[i][0] = map[i][0];
            for (int j = 1; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i][j - 1] + map[i][j];
            }
        }
        //------------ 입력 완료

        maxSum = Integer.MIN_VALUE;

        for (int L = 0; L < M; L++) { //왼쪽 열 이동
            for (int R = L; R < M; R++) { //오른쪽 열 이동
                for (int row = 0; row < N; row++) {
                    //rowSum 배열에 각 행의 L~R 열의 합을 저장
                    if (L == 0) {
                        rowSum[row] = sum[row][R];
                    } else {
                        rowSum[row] = sum[row][R] - sum[row][L - 1];
                    }
                }
                //카데인 알고리즘을 활용하여 가장 큰 합을 갖는 행의 범위를 찾음
                kadane();

                //최댓값과 비교
                if (curSum > maxSum) {
                    maxSum = curSum;
                }
            }
        }
        System.out.println(maxSum);
    }

    //rowSum의 부분배열 중 합이 가장 큰 값을 curSum에 저장 (카데인 알고리즘)
    private static void kadane() {

        // 1. dp 상태 배열 정의
        int[] subMaxSum = new int[N]; // 현재 인덱스까지의 부분배열 중 합이 가장 큰 값
        int[] mySum = new int[N]; // 현재 인덱스를 포함한 부분배열 중 합이 가장 큰 값

        // 2. 초기값 정의
        subMaxSum[0] = rowSum[0];
        mySum[0] = rowSum[0];

        for (int i = 1; i < N; i++) {
            // 3-2. 점화식
            mySum[i] = Integer.max(mySum[i - 1] + rowSum[i], rowSum[i]);
            // 3-1. 큰 점화식
            subMaxSum[i] = Integer.max(subMaxSum[i - 1], mySum[i]);
        }

        curSum = subMaxSum[N - 1];
    }
}
