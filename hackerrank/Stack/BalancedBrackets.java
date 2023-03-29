package Stack;

import java.util.Stack;

public class BalancedBrackets {
    public static void main(String[] args) {
        System.out.println(checkBrackets("][(]}})("));
    }

    //Draft code
    //Refactored
    public static String checkBrackets(String expression) {
        if (expression.length() % 2 != 0) return "NO";

        Stack<Character> stack = new Stack();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c != ']' && c != ')' && c != '}') {
                stack.push(c);
            }

            if (stack.isEmpty()) return "NO";

            char top = stack.peek();
            if (c == ']' && top == '[') {
                stack.pop();
            }
            if (c == ')' && top == '(') {
                stack.pop();
            }
            if (c == '}' && top == '{') {
                stack.pop();
            }
        }

        if (stack.isEmpty()) return "YES";

        return "NO";
    }

    //Another Solution
    public static String checkBrackets2(String expression){
        Stack<Character> stack = new Stack();

        for(char c : expression.toCharArray())
        {
            if(c == '(')
                stack.push(')');
            else if(c == '{')
                stack.push('}');
            else if(c == '[')
                stack.push(']');

            else{
                if( stack.isEmpty() || c != stack.peek()){
                    return "NO";
                }
                else{
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty())
            return "YES";
        else
            return "NO";
    }
}
