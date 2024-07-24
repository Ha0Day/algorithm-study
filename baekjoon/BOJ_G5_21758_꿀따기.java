import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_21758_꿀따기 {
    public static int N;
    public static int[] arr;
    public static int maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i == 0 || i == N - 1) {
                continue;
            }
            if (arr[i] > max) {
                max = arr[i];
            }
            sum += arr[i];
        }

        //벌1-벌2-벌통
        int sum1 = sum + arr[N - 1];
        int tempSum = 0;
        for (int i = 1; i < N; i++) {
            int bee2 = arr[i];
            int bee1Sum = sum1 - bee2;
            int bee2Sum = sum1 - (tempSum + bee2);
            if (bee1Sum + bee2Sum > maxSum) {
                maxSum = bee1Sum + bee2Sum;
            }
            tempSum += bee2;
        }

        //벌통-벌2-벌1
        int sum2 = sum + arr[0];
        int tempSum2 = 0;
        for (int i = N - 2; i >= 0; i--) {
            int bee2 = arr[i];
            int bee1Sum = sum2 - bee2;
            int bee2Sum = sum2 - (tempSum2 + bee2);
            if (bee1Sum + bee2Sum > maxSum) {
                maxSum = bee1Sum + bee2Sum;
            }
            tempSum2 += bee2;
        }

        //벌1-벌통-벌2
        if (sum + max > maxSum) {
            maxSum = sum + max;
        }

        System.out.println(maxSum);
    }
}