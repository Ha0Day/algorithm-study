import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D2_12712_파리퇴치3 {
    static int T;
    static int N;
    static int M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //----입력 끝
            int max_sum = Integer.MIN_VALUE;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int sum = getSum(r, c);
                    if (sum > max_sum) {
                        max_sum = sum;
                    }
                }
            }

            //출력
            sb.append("#" + t + " " + max_sum + "\n");
        }
        System.out.println(sb);
    }

    private static int getSum(int r, int c) {
        int plus_sum = arr[r][c], x_sum = arr[r][c];
        //+
        for (int i = 1; i < M; i++) {
            plus_sum += (c - i < 0 ? 0 : arr[r][c - i]) + (r - i < 0 ? 0 : arr[r - i][c])
                    + (c + i >= N ? 0 : arr[r][c + i]) + (r + i >= N ? 0 : arr[r + i][c]);
        }
        //x
        for (int i = 1; i < M; i++) {
            x_sum += (r - i < 0 || c - i < 0 ? 0 : arr[r - i][c - i]) + (r - i < 0 || c + i >= N ? 0 : arr[r - i][c + i])
                    + (r + i >= N || c - i < 0 ? 0 : arr[r + i][c - i]) + (r + i >= N || c + i >= N ? 0 : arr[r + i][c + i]);
        }

        return Math.max(plus_sum, x_sum);
    }
}
