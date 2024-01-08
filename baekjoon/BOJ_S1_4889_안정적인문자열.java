import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_S1_4889_안정적인문자열 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int num = 1;
        while (input.charAt(0) != '-') {
            Stack<Character> stack = new Stack();
            int cnt = 0;

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '{') {
                    stack.push('{');
                } else {
                    if (stack.isEmpty()) {
                        cnt++;
                        stack.push('{');
                    } else {
                        stack.pop();
                    }
                }
            }
            if (!stack.isEmpty()) {
                cnt += stack.size() / 2;
            }
            System.out.println(num + ". " + cnt);
            num++;
            input = br.readLine();
        }
    }

}
