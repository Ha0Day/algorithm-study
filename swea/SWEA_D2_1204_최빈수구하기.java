
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D2_1204_최빈수구하기 {
    static int T;
    static int NUM = 1000;
    static int SCORE = 100;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            br.readLine();
            arr = new int[NUM];
            st = new StringTokenizer(br.readLine());
            int l = 0;
            while (st.hasMoreTokens()) {
                arr[l++] = Integer.parseInt(st.nextToken());
            }

            int[] score_count = new int[SCORE + 1];

            for (int i = 0; i < NUM; i++) {
                score_count[arr[i]]++;
            }

            for (int i = 0; i < NUM; i++) {
                score_count[arr[i]]++;
            }
            int max_count = 0;
            int max_value = 0;
            for (int i = 0; i < score_count.length; i++) {
                if (score_count[i] > max_count) {
                    max_count = score_count[i];
                    max_value = i;
                }
                if (score_count[i] == max_count) {
                    max_value = Math.max(i, max_value);
                }
            }


            //----출력 시작
            sb.append("#" + t + " " + max_value + "\n");
        }
        System.out.println(sb);
    }
}
