package Sort;

//10.2 Group Anagrams
// 철자 순서만 바꾼 문자열(anagram)이 서로 인접하도록 문자열 배열을 정렬하는 메서드를 작성하라.
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;

public class GroupAnagrams {

    public static void main(String[] args) {
        String[] arr = {"abc", "bdd", "bac", "abc", "dbd", "bdd", "ace"};

        groupAna2(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    static void groupAna(String[] arr) {
        Arrays.sort(arr, new Sorting());
    }

    static class Sorting implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return changeStr(o1).compareTo(changeStr(o2));
        }
    }

    static void groupAna2(String[] arr) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(changeStr(arr[i]))) {
                map.get(changeStr(arr[i])).add(arr[i]);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(arr[i]);
                map.put(changeStr(arr[i]), list);
            }
        }

        int index = 0;

        for (String key : map.keySet()) {
            ArrayList<String> list = map.get(key);
            for (int i = 0; i < list.size(); i++) {
                arr[index++] = list.get(i);
            }
        }
    }

    static String changeStr(String str) {
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);

        return new String(charArr);
    }
}
