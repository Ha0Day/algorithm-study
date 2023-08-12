package Ch1_StringManipulation;

import java.util.HashSet;
import java.util.Set;

//1.1 Is Unique - 문자열에 중복이 있는지 체크
public class IsUnique {
    public static void main(String[] args) {
        String s = "adbcfwak";
        System.out.println(checkDuplicates(s));
        System.out.println(checkDuplicates2(s));
        System.out.println(checkDuplicates3(s));

        //위 3개 함수와 반대 결과 나와야 함
        System.out.println(sol_isUniqueChar(s));
        System.out.println(sol_isUniqueChars(s));
    }

    //Draft1 - O(N^2)
    public static boolean checkDuplicates(String s) {
        char[] charArr = s.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            int tmp = charArr[i];
            for (int j = i + 1; j < charArr.length; j++) {
                if (tmp == charArr[j]) return true;
            }
        }
        return false;
    }

    //Draft2 - O(N)
    public static boolean checkDuplicates2(String s) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }

        return set.size() != s.length();
    }

    //Draft3 - O(N^2)
    public static boolean checkDuplicates3(String s) {

        for (int i = 0; i < s.length(); i++) {
            int tmp = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (tmp == s.charAt(j)) return true;
            }
        }
        return false;
    }

    //Solution1 - O(N)
    //256개보다 많은 문자를 순회하지 않으므로 O(1)이라고도 할 수 있다.
    //문자열이 ASCII 문자열이라는 가정 하에,
    public static boolean sol_isUniqueChar(String str) {
        if (str.length() > 128) return false;
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    //Solution2 - O(N)
    //비트 벡터 사용
    //Solution1보다 필요한 공간 1/8로 줄일 수 있음
    public static boolean sol_isUniqueChars(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a'; //현재 문자에 해당하는 이진수 자리 확인
            if ((checker & (1 << val)) > 0) { //현재 문자에 해당하는 이진수 자리가 1인지 체크. 1이면 이미 사용된 문자
                return false;
            }
            checker |= (1 << val); //현재 문자에 해당하는 이진수 자리를 1로 바꿈
        }
        return true;
    }

}