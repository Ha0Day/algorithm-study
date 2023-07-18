package String;

import java.io.*;

public class SuperReducedString {
    public static void main(String[] args) throws IOException {
        System.out.println(superReducedString("baab"));
    }


    public static String superReducedString(String s) {
        // Write your code here
        StringBuffer sb = new StringBuffer(s);

        int i = 0;
        while (i < sb.length() - 1) {
            if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.deleteCharAt(i + 1);
                sb.deleteCharAt(i);
                i = 0;
                continue;
            }
            i++;
        }

        if (sb.length() == 0) return "Empty String";

        return sb.toString();

    }
}
