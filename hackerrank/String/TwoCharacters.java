package String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TwoCharacters {
    public static void main(String[] args) {
        System.out.println(alternate("beabeefeab"));
    }

    public static int alternate(String s) {
        Set set = new HashSet<Character>();

        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
            }
        }
        int numOfChars = set.size();

        if (numOfChars < 2) return 0;
        if (numOfChars == 2) {
            if (checkAlternating(new StringBuilder(s))) {
                return s.length();
            } else return 0;
        }

        ArrayList list = new ArrayList(set);
        int maxLength = 0;

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                char first = (Character) list.get(i);
                char second = (Character) list.get(j);
                int length = lengthOfChangedString(first, second, new StringBuilder(s));
                if (length > maxLength) maxLength = length;
            }
        }
        return maxLength;
    }

    private static int lengthOfChangedString(char first, char second, StringBuilder sb) {
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) != first && sb.charAt(i) != second) {
                sb.deleteCharAt(i);
            }
        }
        if (checkAlternating(sb)) {
            return sb.length();
        }
        return 0;
    }

    private static boolean checkAlternating(StringBuilder sb) {
        int length = sb.length();
        for (int i = 0; i < length - 2; i++) {
            if (sb.charAt(i) != sb.charAt(i + 2)) {
                return false;
            }
        }
        return true;
    }

}

