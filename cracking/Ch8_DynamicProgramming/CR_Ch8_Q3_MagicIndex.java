package Ch8_DynamicProgramming;

//8.3 Magic Index
// 배열 A[0...n-1]에서 A[i] = i인 인덱스를 마술 인덱스라 정의한다.
// 정렬된 상태의 배열이 주어졌을 때, 마술 인덱스가 존재한다면 그 값을 찾는 메서드를 작성하라.
// 배열 안에 중복된 값은 없다.
// 연관 문제) 중복된 값을 허용한다면 어떻게 풀겠는가?
public class CR_Ch8_Q3_MagicIndex {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, 4, 7, 10};

        System.out.println(getMagicIndex(arr));
        System.out.println(getMagicIndex2(arr));

        int[] arr2 = {-1, 0, 1, 2, 5, 5, 10};
        System.out.println(sol_getMagicIndex_NotDistinct(arr2));

    }

    //Draft code
    //Solution1
    //시간복잡도 O(N)
    static int getMagicIndex(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) {
                return i;
            }
        }
        return -1;
    }


    //Solution2
    //이진탐색 활용
    //시간복잡도 O(logN)
    static int getMagicIndex2(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        return getMagicIndex2(low, high, arr);
    }

    static int getMagicIndex2(int low, int high, int[] arr) {
        int mid = (low + high) / 2;

        if (low > high) {
            return -1;
        }

        if (arr[mid] == mid) {
            return mid;
        }
        if (arr[mid] > mid) {
            return getMagicIndex2(low, mid - 1, arr);
        }
        return getMagicIndex2(mid + 1, high, arr);
    }

    //Solution - 연관문제 (중복 허용)
    // 시간복잡도 O(N)
    static int sol_getMagicIndex_NotDistinct(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        return getMagicIndex_NotDistinct(arr, start, end);
    }

    static int getMagicIndex_NotDistinct(int[] arr, int start, int end) {
        if (start > end) return -1;

        int midIndex = (start + end) / 2;
        int midValue = arr[midIndex];

        if (midIndex == midValue) {
            return midIndex;
        }

        //왼쪽 탐색
        int leftIndex = Math.min(midIndex - 1, midValue);
        int left = getMagicIndex_NotDistinct(arr, start, leftIndex);
        if (left >= 0) {
            return left;
        }

        //오른쪽 탐색
        int rightIndex = Math.max(midIndex + 1, midValue);
        int right = getMagicIndex_NotDistinct(arr, rightIndex, end);
        return right;

    }
}
