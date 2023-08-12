package Ch8_DynamicProgramming;

//8.11 Coins
// 쿼터(25센트), 다임(10센트), 니켈(5센트), 페니(1센트)의 네 가지 동전이 무한히 주어졌을 때,
// n센트를 표현하는 모든 방법의 수를 계산하는 코드를 작성하라.
public class CR_Ch8_Q11_Coins {

    public static void main(String[] args) {
        int n = 35;
        System.out.println(makeChange(n));
    }

    //Solution
    private static int makeChange(int n) {
        int[] denoms = {25, 10, 5, 1};
        int[][] map = new int[n + 1][denoms.length]; //이미 계산된 값
        return makeChange(n, denoms, 0, map);
    }

    static int makeChange(int amount, int[] denoms, int index, int[][] map) {
        //계산된 값 반환
        if (map[amount][index] > 0) {
            return map[amount][index];
        }
        //마지막 denom
        if (index >= denoms.length - 1) {
            return 1;
        }
        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            //다음 denom으로 진행한다. denomAmount짜리 동전 i개가 있다고 가정
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, denoms, index + 1, map);
        }
        map[amount][index] = ways;
        return ways;
    }
}
