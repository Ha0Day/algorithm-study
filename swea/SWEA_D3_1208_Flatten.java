import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D3_1208_Flatten {
    static int N;
    static int arr[];

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine()); // 덤프 횟수
            st = new StringTokenizer(br.readLine());
            arr = new int[100];

            for (int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            // -----------입력 완료

            for (int i = 0; i < N; i++) {
                Arrays.sort(arr);
                if (arr[arr.length - 1] - arr[0] <= 1) {
                    break;
                } else {
                    arr[arr.length - 1]--;
                    arr[0]++;
                }
            }
            Arrays.sort(arr);

            sb.append("#" + t + " " + (arr[arr.length - 1] - arr[0]) + "\n");
        }
        System.out.println(sb);

    }

}
