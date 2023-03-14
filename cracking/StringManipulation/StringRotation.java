package StringManipulation;

import java.util.Arrays;

//1.9 String Rotation
//한 단어가 다른 문자열에 포함되어 있는지 판별하는 isSubstring이라는 메서드 있음
//s1과 s2의 두 문자열이 주어짐
//s2가 s1을 회전시킨 결과인지 판별하는 함수 작성
//예: 'waterbottle'은 'erbottlewat'을 회전 시킨 게 맞음
//단 isSubString 메서드는 한 번만 호출 가능
public class StringRotation {
    public static void main(String[] args) {
        String s1 = "aabbcyaz";
        String s2 = "bcyzaaab";

        System.out.println(isRotatedString(s1, s2));
    }

    //Draft
    public static boolean isRotatedString(String s1, String s2) {

        for (int i = 0; i < s1.length(); i++) {
            if ((s1.substring(i) + s1.substring(0, i)).equals(s2)) {
                return true;
            }
        }
        return false;
    }
}
