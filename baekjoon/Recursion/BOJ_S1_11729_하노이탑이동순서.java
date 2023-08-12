package Recursion;

import java.util.Scanner;

public class BOJ_S1_11729_하노이탑이동순서 {

    static int N;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sb = new StringBuilder();
        hanoi(N, 1, 2, 3);

        System.out.println((1 << N) - 1);
        System.out.println(sb.toString());
    }

    static void hanoi(int n, int start, int temp, int des) {

        if (n == 1) {
            sb.append(start + " " + des + "\n");
            return;
        }
        hanoi(n - 1, start, des, temp);
        hanoi(1, start, temp, des);
        hanoi(n - 1, temp, start, des);
    }

}
