package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//8.1 Triple Step
// 한번에 1 또는 2 또는 3계단을 오를 수 있다.
// n개의 계단을 오르는 방법이 몇 가지 있는지 계산하는 메서드를 구현하라.
public class TripleStep {

    static HashMap<Integer, Integer> numsTD = new HashMap<>();
    static HashMap<Integer, Integer> numsBU = new HashMap<>();

    public static void main(String[] args) {

        int n = 7;

        System.out.println(stepR(n));
        System.out.println(stepTD(n));
        System.out.println(stepBU(n));

        System.out.println(sol_stepR(n));
        System.out.println(sol_stepTD(n));

    }

    //Refactored - n=0 케이스 추가
    // 재귀
    // 시간 복잡도 O(3^n)
    static int stepR(int n) {
        //탈출 조건
        if (n == 0) {
            return 1;
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
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }

        numsTD.put(n, stepTD(n - 3) + stepTD(n - 2) + stepTD(n - 1));

        return numsTD.get(n);
    }

    //DP (Bottom-Up)
    //시간 복잡도 O(N)
    static int stepBU(int n) {
        numsBU.put(0, 1);
        numsBU.put(1, 1);
        numsBU.put(2, 2);

        for (int i = 3; i <= n; i++) {
            numsBU.put(i, numsBU.get(i - 3) + numsBU.get(i - 2) + numsBU.get(i - 1));
        }

        return numsBU.get(n);
    }


    //Solution1 - 재귀
    // Draft - 재귀와 거의 흡사
    // 시간 복잡도 O(3^n)
    static int sol_stepR(int n) {
        //탈출 조건
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        return sol_stepR(n - 3) + sol_stepR(n - 2) + sol_stepR(n - 1);
    }

    //Solution2 - DP (Top-Down)
    //Draft - DP (Top-Down)와 거의 흡사
    //일반적으로 캐시를 사용할 때 HashMap을 사용하지만
    //여기서는 키 값이 정확하게 1부터 n까지의 정수이므로 정수 배열을 사용하는 것이 공간 면에서 더 효율적
    //시간 복잡도 O(N)
    static int sol_stepTD(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return sol_stepTD(n, memo);
    }

    static int sol_stepTD(int n, int[] memo) {
        //탈출 조건
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        //중복 없앰
        if (memo[n] < -1) {
            return memo[n];
        }

        memo[n] = sol_stepTD(n - 3, memo) + sol_stepTD(n - 2, memo) + sol_stepTD(n - 1, memo);

        return memo[n];
    }
}

/* 참고사항
    n=37만 돼도 overflow 발생되므로 int 대신 long을 사용할 수 있지만 문제 발생 시점만 늦출 뿐 완벽히 해결하지는 못함.
    BigInteger 클래스를 사용해 해결할 수 있음.
 */
