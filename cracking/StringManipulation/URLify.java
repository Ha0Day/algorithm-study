package StringManipulation;

//1.3 URLify - 문자열의 모든 공백을 '%20'으로 바꾸는 함수
//입력 : 문자열, 문자열의 길이 (ex. "Mr John Smith", 13)
public class URLify {

    public static void main(String[] args) {
        String s = "Mr John Smith";
        int len = 13;

        System.out.println(urlify(s, len));
        System.out.println(urlify2(s, len));

    }

    //Draft - O(N)
    private static String urlify(String s, int len) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    //Draft2 - O(N)
    private static String urlify2(String s, int len) {
        return s.replaceAll(" ", "%20");
    }

}
