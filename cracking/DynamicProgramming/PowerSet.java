package DynamicProgramming;

import java.util.ArrayList;

//8.4 Power Set
// 어떤 집합의 부분집합을 전부 반환하는 메서드를 작성하라.
public class PowerSet {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        ArrayList<ArrayList<Integer>> lists = getPowerSet(arr);

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).size()==0) {
                System.out.println("Empty set");
                continue;
            }
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.print(lists.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    //Draft code
    //시간 복잡도 O(N*2^N)
    static ArrayList<ArrayList<Integer>> getPowerSet(ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        getPowerSet(arr, lists);
        return lists;
    }

    static void getPowerSet(ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> lists) {
        //탈출조건
        if (arr.isEmpty()) {
            lists.add(new ArrayList<>());
            return;
        }

        int lastElement = arr.get(arr.size() - 1);

        arr.remove(arr.size() - 1);
        getPowerSet(arr, lists);

        int size = lists.size();

        for (int i = 0; i < size; i++) {
            lists.add((ArrayList<Integer>) lists.get(i).clone());
            lists.get(i).add(lastElement);
        }
    }
}
