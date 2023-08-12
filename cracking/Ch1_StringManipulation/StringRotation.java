package Ch1_StringManipulation;

//1.9 String Rotation
//한 단어가 다른 문자열에 포함되어 있는지 판별하는 isSubstring이라는 메서드 있음
//s1과 s2의 두 문자열이 주어짐
//s2가 s1을 회전시킨 결과인지 판별하는 함수 작성
//예: 'waterbottle'은 'erbottlewat'을 회전 시킨 게 맞음
//단 isSubString 메서드는 한 번만 호출 가능
public class StringRotation {
    public static void main(String[] args) {
        String s1 = "aabbcyza";
        String s2 = "bcyzaaab";

        System.out.println(isRotatedString(s1, s2));
        System.out.println(sol_isRotation(s1, s2));
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


    //Solution - O(N)
    //쪼개지는 부분 기준으로 x, y로 나뉜다고 했을 때
    //xy=s1, yx=s2
    //yx는 언제나 xyxy=s1s1의 부분 문자열
    //isSubstring 함수의 수행 시간에 따라 이 함수의 시간복잡도가 달라짐
    public static boolean sol_isRotation(String s1, String s2) {
        int len = s1.length();
        //s1와 s2의 길이가 같고 빈 문자열이 아닌지 확인
        if (len == s2.length() && len > 0) {
            String s1s1 = s1 + s1;
            return isSubString(s1s1, s2);
        }
        return false;
    }

    //예시로 가져온 코드
    private static boolean isSubString(String s1s1, String s2) {
        return s1s1.contains(s2);
    }
}
