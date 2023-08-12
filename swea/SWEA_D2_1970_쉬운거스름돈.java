import java.io.*;

public class SWEA_D2_1970_쉬운거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] price = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
        int[] input = new int[T];

        /*for (int i = 0; i < T; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }*/

        for (int i = 0; i < T; i++) {
            int money = Integer.parseInt(br.readLine());
            int[] arr = new int[8];

            for (int j = 0; j < price.length; j++) {
                arr[j] = money / price[j];
                money = money - price[j] * arr[j];
            }

            System.out.println("#" + (i + 1));
            for (int k = 0; k < arr.length; k++) {
                System.out.print(arr[k] + " ");
            }
            System.out.println();
        }
    }
}
