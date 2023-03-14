package StringManipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

//1.2 Check Permutation - 두 문자열이 서로 순열 관계인지 체크
public class CheckPermutation {
    public static void main(String[] args) {
        String s1 = "kbcadaa";
        String s2 = "aabkcad";
        System.out.println(checkPermutation(s1, s2));

        System.out.println(sol_permutation(s1, s2));
        System.out.println(sol_permutation2(s1, s2));
    }

    //Draft - O(N)
    public static boolean checkPermutation(String s1, String s2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        if (s1.length() != s2.length()) return false;

        int len = s1.length();

        for (int i = 0; i < len; i++) {
            //이미 있으면
            if (map1.containsKey(s1.charAt(i))) {
                int count = map1.get(s1.charAt(i));
                map1.put(s1.charAt(i), count + 1);
            }
            //처음이면
            else {
                map1.put(s1.charAt(i), 1);
            }
        }

        for (int i = 0; i < len; i++) {
            //이미 있으면
            if (map2.containsKey(s2.charAt(i))) {
                int count = map2.get(s2.charAt(i));
                map2.put(s2.charAt(i), count + 1);
            }
            //처음이면
            else {
                map2.put(s2.charAt(i), 1);
            }
        }

        Set set1 = map1.entrySet();
        Set set2 = map2.entrySet();

        if (set1.equals(set2)) return true;
        else return false;
    }

    //Solution1 - O(NlogN)
    //정렬된 두 문자열이 동일한지 체크
    public static String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    public static boolean sol_permutation(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }


    //Solution2 - O(N)
    //문자열에 포함된 문자의 출현 횟수가 같은지 확인
    public static boolean sol_permutation2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[128]; //ASCII라고 가정

        char[] s_array = s.toCharArray();
        for (char c : s_array) {
            letters[c]++;
        }
        for (int i = 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }
        return true;
    }
}
