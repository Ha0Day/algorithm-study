import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D2_1959_두개의숫자열 {
    static int T;
    static int N, M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int sum = 0;
            int max_sum = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int[] a = new int[N];
            int[] b = new int[M];

            st = new StringTokenizer(br.readLine());
            int i = 0;
            while (st.hasMoreTokens()) {
                a[i++] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int j = 0;
            while (st.hasMoreTokens()) {
                b[j++] = Integer.parseInt(st.nextToken());
            }
            //----입력 끝
            int diff = Math.abs(b.length - a.length);

            for (int k = 0; k <= diff; k++) {
                sum = 0;
                for (int l = 0; l < Math.min(a.length, b.length); l++) {
                    if(a.length<b.length){
                        sum += a[l] * b[k + l];
                    }else{
                        sum += b[l] * a[k + l];
                    }
                }
                if (sum > max_sum) {
                    max_sum = sum;
                }
            }

            //----출력 시작
            sb.append("#" + t + " " + max_sum + "\n");
        }
        System.out.println(sb);
    }
}
