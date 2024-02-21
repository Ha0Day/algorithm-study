import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_11501_주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            long profit = 0; //이익
            int days = Integer.parseInt(br.readLine()); //날
            int[] price = new int[days]; //날 별 주가
            int max = Integer.MIN_VALUE; //현재 최대 주가
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < days; i++) {
                price[i] = Integer.parseInt(st.nextToken()); //입력 받기
            }
            for (int i = days - 1; i >= 0; i--) { //뒤에서부터 확인
                if (price[i] > max) {
                    max = price[i]; //최댓값 갱신
                } else {
                    profit += (max - price[i]);
                }
            }
            System.out.println(profit);
        }
    }
}