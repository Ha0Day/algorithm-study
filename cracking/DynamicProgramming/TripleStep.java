package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;

//8.1 Triple Step
// 한번에 1 또는 2 또는 3계단을 오를 수 있다.
// n개의 계단을 오르는 방법이 몇 가지 있는지 계산하는 메서드를 구현하라.
public class TripleStep {

    static HashMap<Integer, Integer> numsTD = new HashMap<>();
    static HashMap<Integer, Integer> numsBU = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(stepR(30));
        System.out.println(stepTD(30));
        System.out.println(stepBU(30));

    }

    //Draft
    // 재귀
    // 시간 복잡도 O(3^n)
    static int stepR(int n) {
        //탈출 조건
        if (n == 3) {
            return 4;
        }
        if (n <= 2) {
            return n;
        }

        return stepR(n - 3) + stepR(n - 2) + stepR(n - 1);
    }

    //DP (Top-Down)
    //시간 복잡도 O(N)
    static int stepTD(int n) {
        //중복 없앰
        if (numsTD.containsKey(n)) {
            return numsTD.get(n);
        }
        //탈출 조건
        if (n == 3) {
            numsTD.put(n, 4);
            return numsTD.get(n);
        }
        if (n <= 2) {
            numsTD.put(n, n);
            return numsTD.get(n);
        }

        numsTD.put(n, stepTD(n - 3) + stepTD(n - 2) + stepTD(n - 1));

        return numsTD.get(n);
    }

    //DP (Bottom-Up)
    //시간 복잡도 O(N)
    static int stepBU(int n) {
        numsBU.put(1, 1);
        numsBU.put(2, 2);
        numsBU.put(3, 4);

        for (int i = 4; i <= n; i++) {
            numsBU.put(i, numsBU.get(i - 3) + numsBU.get(i - 2) + numsBU.get(i - 1));
        }

        return numsBU.get(n);
    }
}
