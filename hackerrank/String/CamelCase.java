package String;

import java.io.IOException;

public class CamelCase {

    public static void main(String[] args) throws IOException {
        System.out.println(camelcase("iAmHaYoung"));
    }

    public static int camelcase(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                count++;
            }
        }
        return count + 1;
    }

}
