package Ch8_DynamicProgramming;

//8.9 Parens
// n-쌍의 괄호로 만들 수 있는 모든 합당한(괄호가 적절히 열리고 닫힌) 조합을 출력하는 알고리즘을 구현하라

public class CR_Ch8_Q9_Parens {

    static int n = 3;

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("(");

        printParentheses(sb, 1, 0);
    }

    static void printParentheses(StringBuilder sb, int countOpen, int countClose) {

        //탈출 조건
        if (sb.length() == n * 2) {
            System.out.print(sb);
            System.out.println();
            return;
        }

        if (countOpen < countClose) {
            return;
        }

        if (countOpen < n) {
            sb.append('(');
            printParentheses(sb, countOpen + 1, countClose);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (countClose < n) {
            sb.append(')');
            printParentheses(sb, countOpen, countClose + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
