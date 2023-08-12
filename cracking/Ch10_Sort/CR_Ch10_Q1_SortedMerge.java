package Sort;

//10.1 Sorted Merge
// 정렬된 배열 A와 B가 주어진다. A의 끝에는 B를 전부 넣을 수 있을 만큼 충분한 여유 공간이 있다.
// B와 A를 정렬된 상태로 병합하는 메서드를 작성하라.
public class SortedMerge {
    public static void main(String[] args) {
        int a[] = new int[8];
        a[0] = 1;
        a[1] = 3;
        a[2] = 5;
        a[3] = 7;
        int b[] = {2, 6, 10, 11};

        //sort(a, b);
        sol_merge(a, b, check(a), b.length-1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static void sort(int[] a, int[] b) {
        int aindex = 0, bindex = 0;

        int amax = check(a);
        while (bindex < b.length) {
            if (aindex > amax) {
                for (int i = bindex; i < b.length; i++) {
                    a[aindex++] = b[i];
                }
                break;
            }

            if (a[aindex] > b[bindex]) {
                move(a, aindex, amax);
                a[aindex] = b[bindex];
                bindex++;
                amax++;
            }
            aindex++;
        }
    }

    static void move(int[] a, int index, int amax) {
        for (int i = amax; i >= index; i--) {
            a[i + 1] = a[i];
        }
    }

    static int check(int[] a) {
        int index = 0;

        while (a[index] != 0) {
            index++;
        }
        return index - 1;
    }

    //Solution
    //여유 공간이 있는 배열 뒤쪽부터 삽입
    static void sol_merge(int[] a, int[] b, int indexA, int indexB) {
        //indexA는 배열 a의 마지막 원소 인덱스(indexB도 같은 맥락)
        int indexMerged = indexA + indexB + 1;  //병합된 배열의 마지막 위치

        while (indexB >= 0) {
            // a의 마지막 원소 > b의 마지막 원소
            if (indexA >= 0 && a[indexA] > b[indexB]) {
                a[indexMerged] = a[indexA];
                indexA--;
            } else {
                a[indexMerged] = b[indexB];
                indexB--;
            }
            indexMerged--;
        }
    }
}
