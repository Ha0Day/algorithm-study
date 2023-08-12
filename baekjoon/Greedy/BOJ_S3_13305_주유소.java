package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_13305_주유소 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dist = new long[N - 1];
        long[] price = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < price.length; i++) {
            price[i] = Long.parseLong(st.nextToken());
        }

        long sum = 0;
        long minPrice = price[0];

        for (int i = 0; i < N - 1; i++) {
            sum += minPrice * dist[i];
            if (price[i] > price[i + 1] && price[i + 1] < minPrice) {
                minPrice = price[i + 1];
            }
        }
        System.out.println(sum);

    }
}
