import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_G4_9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bombStr = br.readLine(); //폭발 문자열
        int strLen = str.length();
        int bombLen = bombStr.length(); //폭발 문자열 길이
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < strLen; i++) {
            stack.push(str.charAt(i));

            //스택 크기가 폭발 문자열의 길이와 같거나 크고 + 마지막 글자가 폭발 문자열의 마지막 글자와 일치하는 경우 -> 문자열 비교
            if (stack.size() >= bombLen && str.charAt(i) == bombStr.charAt(bombLen - 1)) {
                boolean hit = true;
                //문자열 비교
                for (int j = 0; j < bombLen; j++) {
                    if (stack.get(stack.size() - bombLen + j) != bombStr.charAt(j)) { //문자열 불일치
                        hit = false;
                        break;
                    }
                }
                if (hit) { //문자열 일치하는 경우
                    for (int j = 0; j < bombLen; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) System.out.println("FRULA");
        else {
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse());
        }
    }
}
