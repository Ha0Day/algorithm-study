package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_2223_금화 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        int result = 0;

        int t = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d[] = new int[m];
        int s[] = new int[m];
        int left[] = new int[m];


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            d[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
            if ((d[i] - 1) / s[i] < min) {
                min = (d[i] - 1) / s[i];
            }
        }
        if (min == 0) {
            result = 0;
        } else if (t < min) {
            result = t * x;
        } else {
            result = min * x;
            t = t - min;
            result += (t / 2) * x;
        }
        System.out.println(result);
    }
}
