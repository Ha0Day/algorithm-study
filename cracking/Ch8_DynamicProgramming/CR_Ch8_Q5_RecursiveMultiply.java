package Ch8_DynamicProgramming;

//8.5 Recursive Multiply
// 연산자를 사용하지 않고 양의 정수 두 개를 곱하는 재귀 함수를 작성하라.
// 덧셈, 뺄셈, 비트 시프팅 연산자를 사용할 수 있지만 이들의 사용 횟수를 최소화해야 한다.
public class CR_Ch8_Q5_RecursiveMultiply {
    public static void main(String[] args) {
        System.out.println(multiply(16, 1));
        System.out.println(multiply(1, 2));
        System.out.println(multiply(2, 5));
        System.out.println(multiply(4, 8));
        System.out.println(multiply(3, 8));
        System.out.println(multiply(3, 5));
        System.out.println(multiply(9, 5));
    }

    //Draft code
    static int multiply(int a, int b) {
        int shiftCount = 0;

        return multiplyEven(a, b, shiftCount);
    }

    static int multiplyEven(int a, int b, int shiftCount) {
        //탈출 조건
        if (a % 2 != 0 && b % 2 != 0) {
            return multiplyR(a, b, a, 1) << shiftCount;
        }

        if (a % 2 == 0) {
            shiftCount++;
            a = a / 2;
        }
        if (b % 2 == 0) {
            shiftCount++;
            b = b / 2;
        }

        return multiplyEven(a, b, shiftCount);
    }

    static int multiplyR(int a, int b, int sum, int index) {
        //탈출 조건
        if (index == b) {
            return sum;
        }
        sum = sum + a;
        return multiplyR(a, b, sum, index + 1);
    }
}
