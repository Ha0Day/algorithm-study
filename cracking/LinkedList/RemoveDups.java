package LinkedList;

import java.util.*;

//2.1 Remove Dups
//정렬되어 있지 않은 연결리스트가 주어졌을 때 이 리스트에서 중복되는 원소를 제거하는 코드를 작성하라.
//연관문제) 임시 버퍼를 사용할 수 없다면 어떻게 풀까?
public class RemoveDups {
    public static void main(String[] args) {
        List list = new LinkedList();

        list.add(1);
        list.add(2);
        list.add(2);
        list.add(0);
        list.add(-1);
        list.add(-1);

        removeDuplicates(list);
        System.out.println(list);
    }

    //Draft
    private static void removeDuplicates(List list) {
        Set set = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }

        list.removeAll(set);


        Iterator it = set.iterator();

        for (int i = 0; i < set.size(); i++) {
            list.add(it.next());
        }
    }
}
