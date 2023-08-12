package Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_S5_7568_덩치 {

    static int N;

    static class P implements Comparable<P> {
        int x;
        int y;

        public P(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P p) {

            int rx = this.x - p.x;
            int ry = this.y - p.y;

            if (rx > 0 && ry > 0) {
                return -1; // asc
            } else if (rx < 0 && ry < 0) { //
                return 1; // desc
            } else {
                return 0;
            }
        }

    }

    static List<P> list;

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        N = scann.nextInt();
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new P(scann.nextInt(), scann.nextInt()));
        }

        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;
                if (list.get(i).compareTo(list.get(j)) > 0) { //나보다 덩치가 크면
                    count++;
                }
            }
            System.out.print(count + 1 + " ");
        }
    }

}
