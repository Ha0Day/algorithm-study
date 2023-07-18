package Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st=new StringTokenizer(br.readLine(),"+|-");
        String str = br.readLine();
        int[] nums = new int[str.length() / 2 + 1];
        char[] ops = new char[str.length() / 2];

        int numIndex = 0;
        int opIndex = 0;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-') {
                nums[numIndex++] = Integer.parseInt(sb.toString());
                sb = new StringBuilder();
                ops[opIndex++] = ch;
            } else {
                sb.append(ch);
            }
        }
        nums[numIndex] = Integer.parseInt(sb.toString());

        for (int i = 0; i < ops.length - 1; i++) {
            if (ops[i] == '-' && ops[i + 1] == '+') {
                ops[i + 1] = '-';
            }
        }

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (ops[i-1] == '+') {
                result = result + nums[i];
            } else if (ops[i-1] == '-') {
                result = result - nums[i];
            }
        }
        System.out.println(result);
    }
}
