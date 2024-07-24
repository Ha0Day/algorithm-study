import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_1253_좋다 {
    public static int N;
    public static int arr[];
    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); //정렬

        for (int i = 0; i < N; i++) {
            int s = 0; //왼쪽 포인터
            int e = N - 1; //오른쪽 포인터
            int num = arr[i];
            while (s < e) {
                if (s == i) {
                    s++;
                    continue;
                }
                if (e == i) {
                    e--;
                    continue;
                }
                int sum = arr[s] + arr[e];
                if (sum == num) {
                    count++;
                    break;
                } else if (sum < num) {
                    s++;
                } else if (sum > num) {
                    e--;
                }
            }
        }

        System.out.println(count);

    }
}
