package StringManipulation;

import java.util.HashMap;
import java.util.Set;

//1.2 Check Permutation - 두 문자열이 서로 순열 관계인지 체크
public class CheckPermutation {
    public static void main(String[] args) {
        String s1 = "kbcadaa";
        String s2 = "abbkcad";
        System.out.println(checkPermutation(s1, s2));
    }

    //Draft
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

}
