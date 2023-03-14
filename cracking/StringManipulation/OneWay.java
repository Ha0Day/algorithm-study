package StringManipulation;

//1.5 One Way
//문자열을 편집하는 방법에는 세 가지 종류가 있다 - 문자 삽입, 문자 삭제, 문자 교체
//문자열 두 개가 주어졌을 때 문자열을 같게 만들기 위한 편집 횟수가 1회 이내인지 확인하는 함수 작성
public class OneWay {
    public static void main(String[] args) {
        System.out.println(checkOneWay("hayoung", "hayyoungg"));
        System.out.println(checkOneWay("joy", "oyy"));
        System.out.println(checkOneWay("truth", "truth"));
        System.out.println(checkOneWay("faith", "fbkth"));
    }

    //Draft - O(N)
    private static boolean checkOneWay(String s1, String s2) {

        StringBuffer sb1 = new StringBuffer(s1);
        StringBuffer sb2 = new StringBuffer(s2);

        // 교체의 경우 글자 수 동일
        if (s1.length() == s2.length()) {
            return checkReplace(sb1, sb2);
        }

        //삽입과 삭제는 결국 같은 상황
        //편집 횟수 1회 이내인지 확인
        if (Math.abs(sb1.length() - sb2.length()) != 1) return false;

        if (sb1.length() > sb2.length()) {
            return checkInsertOrDelete(sb1, sb2);
        } else {
            return checkInsertOrDelete(sb2, sb1);
        }
    }

    private static boolean checkInsertOrDelete(StringBuffer sb1, StringBuffer sb2) {

        boolean deleted = false;
        for (int i = 0; i < sb2.length(); i++) {
            if (sb1.charAt(i) != sb2.charAt(i)) {
                sb1.deleteCharAt(i);
                deleted = true;
                break;
            }
        }
        if (!deleted) sb1.deleteCharAt(sb1.length() - 1);

        if (sb1.toString().equals(sb2.toString())) return true;
        else return false;
    }

    private static boolean checkReplace(StringBuffer sb1, StringBuffer sb2) {
        int count = 0;
        for (int i = 0; i < sb1.length(); i++) {
            if (sb1.charAt(i) != sb2.charAt(i)) count++;
            if (count > 1) return false;
        }
        return true;
    }
}
