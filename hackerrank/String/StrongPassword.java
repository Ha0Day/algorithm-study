package String;

import java.util.regex.*;

public class StrongPassword {

    public static void main(String[] args) {
        System.out.println(minimumNumber(11, "#HackerRank"));
    }

    public static int minimumNumber(int n, String password) {
        /*
        numbers = "0123456789"
        lower_case = "abcdefghijklmnopqrstuvwxyz"
        upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        special_characters = "!@#$%^&*()-+"
        */
        int count = 0;

        //Pattern p = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()-+])[A-Za-z\\d!@#$%^&*()-+]{6,}$");


        Pattern p = Pattern.compile(".*[0-9]+.*");
        Matcher m = p.matcher(password);
        if (!m.matches()) count++;

        Pattern p2 = Pattern.compile(".*[a-z]+.*");
        Matcher m2 = p2.matcher(password);
        if (!m2.matches()) count++;

        Pattern p3 = Pattern.compile(".*[A-Z]+.*");
        Matcher m3 = p3.matcher(password);
        if (!m3.matches()) count++;

        Pattern p4 = Pattern.compile(".*[!@#$%^&*()+-]+.*");
        Matcher m4 = p4.matcher(password);
        if (!m4.matches()) count++;

        if (count == 0) {
            if (n >= 6) return 0;
            else return 6 - n;
        } else {
            if (n + count >= 6) {
                return count;
            } else return 6 - n;
        }

    }
}
