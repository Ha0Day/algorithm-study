import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q1974_스도쿠검증 {
    static int T;
    static int N = 9;
    static int[][] arr = new int[N][N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int answer = 0;

        for (int t = 1; t <= T; t++) {

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //----입력 끝

            answer = check(arr) ? 1 : 0;

            //출력
            sb.append("#" + t + " " + answer + "\n");
        }
        System.out.println(sb);
    }

    public static boolean check(int[][] arr) {
        for (int k = 0; k < N; k++) {
            HashSet<Integer> set = new HashSet();
            //행 검증
            for (int i = 0; i < N; i++) {
                set.add(arr[k][i]);
            }
            if (set.size() != N) {
                return false;
            }

            set = new HashSet();
            //열 검증
            for (int i = 0; i < N; i++) {
                set.add(arr[i][k]);
            }
            if (set.size() != N) {
                return false;
            }

            set = new HashSet();
            //박스 검증
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    set.add(arr[(k / 3) * 3 + i][(k % 3) * 3 + j]);
                }
            }
            if (set.size() != N) {
                return false;
            }
        }
        return true;
    }
}
