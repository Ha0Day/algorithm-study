package Stack;

import java.util.Stack;

public class BalancedBrackets {
    public static void main(String[] args) {
        System.out.println(checkBrackets("][(]}})("));
    }

    //Draft code
    public static String checkBrackets(String expression) {
        if (expression.length() % 2 != 0) return "NO";

        Stack<Character> stack = new Stack();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) != ']' && expression.charAt(i) != ')' && expression.charAt(i) != '}') {
                stack.push(expression.charAt(i));
                continue;
            }

            if(stack.isEmpty()) return "NO";

            if (expression.charAt(i) == ']') {
                if (stack.peek() == '[') {
                    stack.pop();
                }
            }
            if (expression.charAt(i) == ')') {
                if (stack.peek() == '(') {
                    stack.pop();
                }
            }
            if (expression.charAt(i) == '}') {
                if (stack.peek() == '{') {
                    stack.pop();
                }
            }
        }

        if(stack.isEmpty()) return "YES";

        return "NO";
    }
}
