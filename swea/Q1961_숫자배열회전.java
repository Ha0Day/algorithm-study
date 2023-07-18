import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1961_숫자배열회전 {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N;
        int[][] arr;


        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //----입력 끝


            //출력
            sb.append("#" + t + "\n");
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    sb.append(arr[N - 1 - i][k]);
                }
                sb.append(" ");
                for (int i = 0; i < N; i++) {
                    sb.append(arr[N - 1 - k][N - 1 - i]);
                }
                sb.append(" ");
                for (int i = 0; i < N; i++) {
                    sb.append(arr[i][N - 1 - k]);
                }
                sb.append("\n");
            }

        }
        System.out.println(sb);
    }
}
