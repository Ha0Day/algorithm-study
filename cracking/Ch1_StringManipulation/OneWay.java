package Ch1_StringManipulation;

//1.5 One Way
//문자열을 편집하는 방법에는 세 가지 종류가 있다 - 문자 삽입, 문자 삭제, 문자 교체
//문자열 두 개가 주어졌을 때 문자열을 같게 만들기 위한 편집 횟수가 1회 이내인지 확인하는 함수 작성
public class OneWay {
    public static void main(String[] args) {

        final String A1 = "hayoung", A2 = "hayyoung";
        final String B1 = "joy", B2 = "oy";
        final String C1 = "truth", C2 = "truth";
        final String D1 = "faith", D2 = "fakth";

        System.out.println(checkOneWay(A1, A2));
        System.out.println(checkOneWay(B1, B2));
        System.out.println(checkOneWay(C1, C2));
        System.out.println(checkOneWay(D1, D2));

        System.out.println();

        System.out.println(sol_oneEditAway(A1, A2));
        System.out.println(sol_oneEditAway(B1, B2));
        System.out.println(sol_oneEditAway(C1, C2));
        System.out.println(sol_oneEditAway(D1, D2));

        System.out.println();

        System.out.println(sol_oneEditAway2(A1, A2));
        System.out.println(sol_oneEditAway2(B1, B2));
        System.out.println(sol_oneEditAway2(C1, C2));
        System.out.println(sol_oneEditAway2(D1, D2));
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


    //Solution1 - O(N)
    static boolean sol_oneEditAway(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() - 1 == second.length()) {
            return oneEditInsert(second, first);
        }
        return false;
    }

    private static boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }
        return true;
    }

    private static boolean oneEditInsert(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;

        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    //Solution2 - O(N)
    //Solution1의 함수를 하나로 합친 것
    //Sol1은 깔끔하고 이해하기 쉬움
    //Sol2는 간결하고 중복된 부분이 없어 유지보수에 좋음
    //각각 장단점이 있음
    public static boolean sol_oneEditAway2(String first, String second) {
        //길이 체크
        if (Math.abs(first.length() - second.length()) > 1) return false;

        //길이가 짧은 문자열과 긴 문자열 찾기
        String s1 = first.length() < second.length() ? first : second; //짧은 무자열
        String s2 = first.length() < second.length() ? second : first; //긴 문자열

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (foundDifference) return false;
                foundDifference = true;
                if (s1.length() == s2.length()) { //교체의 경우 짧은 문자열의 포인터를 증가
                    index1++;
                }
            } else {
                index1++; //동일하다면 짧은 문자열의 포인터 증가
            }
            index2++; //긴 문자열의 포인터는 언제나 증가
        }
        return true;
    }
}
