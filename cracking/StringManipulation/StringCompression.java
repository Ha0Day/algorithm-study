package StringManipulation;

public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress("aabcccccaaa"));
        System.out.println(compress("abbbbbc"));
        System.out.println(compress("abbbc"));
    }

    //Draft - O(N)
    public static String compress(String s) {

        //우선 거를 수 있는 것부터 거르는 것이 시간 복잡도에 좋음
        int num = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                num++;
            }
        }

        if (num * 2 >= s.length()) return s;

        StringBuilder sb = new StringBuilder();

        int count = 1;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                continue;
            }
            sb.append(s.charAt(i)).append(count);
            count = 1;
        }
        sb.append(s.charAt(s.length() - 1)).append(count);

        return sb.toString();
    }
}
