package StringManipulation;

import java.util.Arrays;

public class PalindromePermutation {

    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("ab aba"));
    }

    private static boolean isPalindromePermutation(String s) {

        char[] chArr = s.toLowerCase().toCharArray();
        char[] chArr2 = new char[chArr.length];

        Arrays.sort(chArr);

        int j = 0;
        for (int i = 0; i < chArr.length; i++) {
            if (chArr[i] == ' ') {
                continue;
            }
            chArr2[j++] = chArr[i];
        }

        boolean checked = false;

        int i = 0;
        while (i < chArr2.length - 1) {
            if (chArr2[i] != chArr2[i + 1]) {
                if (checked) {
                    return false;
                }
                checked = true;
                i = i + 1;
            }
            i = i + 2;
        }
        return true;
    }
}
