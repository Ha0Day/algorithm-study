package Ch10_Sort;

import java.util.ArrayList;

//10.4 Sorted Search, No Size
// 배열과 비슷하지만 size 메서드가 없는 Listy라는 자료구조가 있다.
// 여기에는 i 인덱스에 위치한 원소를 O(1) 시간에 알 수 있는 elemetAt(i) 메서드가 존재한다.
// 만약 i가 배열의 범위를 넘어섰다면 -1을 반환한다. (이 때문에 이 자료구조는 야의 정수만 지원)
// 양의 정수가 정렬된 Listy가 주어졌을 때, 원소 x의 인덱스를 찾는 알고리즘을 작성하라.
// 만약 x가 여러 번 등장한다면 아무거나 하나 반환하면 된다.

public class CR_Ch10_Q4_SortedSearchNoSize {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 8, 10, 13};
        Listy listy = new Listy(arr);

        int x = 8;

        System.out.println(findIndex(listy, x));
        System.out.println(findIndex2(listy, x));
    }

    private static int findIndex2(Listy listy, int x) {
        int size = findSize(listy, x);

        int start = 0;
        int end = size;
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;
            if (listy.element(mid) == x) {
                return mid;
            }
            if (x < listy.element(mid) || listy.element(mid) == -1) {
                end = mid - 1;
            } else if (x > listy.element(mid)) {
                start = mid + 1;
            }
        }
        return -1;
    }

    static int findSize(Listy listy, int x) {
        int index = 1;
        while (listy.element(index) != -1 || listy.element(index) > x) {
            index = index * 2;
        }
        return index;
    }


    private static int findIndex(Listy listy, int x) {
        int i = 0;
        int result = 0;

        while (result != -1) {
            result = listy.element(i);
            if (result == x) {
                return i;
            }
            i++;
        }
        return result;
    }
}

class Listy {
    ArrayList<Integer> list = new ArrayList<>();

    Listy(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
    }

    int element(int index) {
        if (index >= list.size()) {
            return -1;
        }
        return list.get(index);
    }
}
