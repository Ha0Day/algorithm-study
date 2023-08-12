package Ch8_DynamicProgramming;

import java.util.ArrayList;

//8.7 Permutations without Dups
// 문자열이 주어졌을 때 모든 경우의 순열을 계산하는 메서드를 작성하라.
// 단, 문자는 중복되어 나타날 수 없다.
public class CR_Ch8_Q7_PermutationsWithoutDups {
    public static void main(String[] args) {

        ArrayList<String> list = getPerms("abcd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    static ArrayList<String> getPerms(String str) {
        //탈출 조건
        if (str.length() == 1) {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(str);
            return tmp;
        }
        ArrayList<String> newList = new ArrayList<>();
        ArrayList<String> oldList = getPerms(str.substring(0, str.length() - 1));

        for (int i = 0; i < oldList.size(); i++) {
            for (int j = 0; j < oldList.get(i).length(); j++) {
                newList.add(addChar(oldList.get(i), j, str.charAt(str.length() - 1)));
            }
            newList.add(oldList.get(i) + str.charAt(str.length() - 1));
        }
        return newList;
    }

    static String addChar(String s, int index, char ch) {

        if (index == 0) {
            return ch + s;
        }

        return s.substring(0, index) + ch + s.substring(index);

    }
}
