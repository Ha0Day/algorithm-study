package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_G4_1744_수묶기 {

    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List positive = new ArrayList<Integer>();
        List negative = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= 0) {
                negative.add(num);
            } else if (num == 1) {
                total++;
            } else {
                positive.add(num);
            }
        }
        //============== 입력 완료

        //절댓값이 커지는 순서로 정렬
        Collections.sort(positive);
        Collections.sort(negative, Collections.reverseOrder());

        calculate(positive);
        calculate(negative);

        System.out.println(total);
    }

    public static void calculate(List<Integer> list) {
        for (int i = list.size() - 1; i > 0; i = i - 2) {
            total += (int) (list.get(i)) * ((int) list.get(i - 1));
        }

        if (list.size() % 2 == 1) {
            total += (int) (list.get(0));
        }
    }
}
