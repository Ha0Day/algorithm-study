package StringManipulation;

public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress("aabcccccaaaaaaaaaaaaaaa"));
        System.out.println(compress("abbbbbc"));
        System.out.println(compress("abbbc"));

        System.out.println();

        System.out.println(sol_compress("aabcccccaaaaaaaaaaaaaaa"));
        System.out.println(sol_compress("abbbbbc"));
        System.out.println(sol_compress("abbbc"));
    }

    //Draft - O(N)
    public static String compress(String s) {

        //우선 거를 수 있는 것부터 거르기
        int num = 0;
        int compressedlength = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            num++;
            if (s.charAt(i) != s.charAt(i + 1)) {
                compressedlength += 1 + String.valueOf(num).length();
                num = 0;
            }
        }

        if (compressedlength >= s.length()) return s;

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

    //Solution - O(N)
    private static String sol_compress(String str) {
        int finalLength = countCompression(str);
        if (finalLength >= str.length()) return str;

        StringBuilder compressed = new StringBuilder(finalLength);
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            //다음 문자와 현재 문자가 같지 않다면 현재 문자를 결과 문자열에 추가한다.
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.toString();
    }

    private static int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            //다음 문자와 현재 문자가 같지 않다면 길이를 증가시킨다.
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }
}
