import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_S4_2164_카드2 {
    static int N;
    static Queue<Integer> cards;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            cards.offer(i + 1);
        }

        while (cards.size() != 1) {
            cards.poll();
            int num = cards.poll();
            cards.offer(num);
        }
        System.out.println(cards.poll());
    }

}
