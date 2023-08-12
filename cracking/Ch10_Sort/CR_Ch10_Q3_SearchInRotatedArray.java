package Ch10_Sort;

//10.3 Search in Rotated Array
// n개의 정수로 구성된 정렬 상태의 배열을 임의의 횟수만큼 회전시켜 얻은 배열이 입력으로 주어진다고 하자.
// 이 배열에서 특정한 원소를 찾는 코드를 작성하라. 회전시키기 전, 원래 배열은 오름차순으로 정렬되어 있었다고 가정한다.
// 입력: {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}에서 5를 찾으라. / 출력: 8(배열에서 5가 위치한 인덱스)
public class CR_Ch10_Q3_SearchInRotatedArray {
    public static void main(String[] args) {
        //int[] arr = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        //int target = 5;

        int[] arr = {7,9, 1,2,3,4,5};
        int target = 3;
        //System.out.println(getTargetIndex(arr, target));
        System.out.println(search(arr, 0, arr.length - 1, target));
    }

    //Draft code
    //최악 O(n)
    private static int getTargetIndex(int[] arr, int target) {
        int newBase = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == target) return i;
            if (arr[i + 1] - arr[i] < 0) {
                newBase = i + 1;
            }
        }

        int start = newBase;
        int end = arr.length - 1;
        int mid = (start + end) / 2;

        while (start <= end) {
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) {
                start = mid + 1;
                mid = (start + end) / 2;
            }
            if (arr[mid] > target) {
                end = mid - 1;
                mid = (start + end) / 2;
            }
        }
        return -1;
    }

    //Solution
    // 중복 원소 없다면 O(logn)
    // 중복 원소 많다면 O(n)
    private static int search(int[] arr, int start, int end, int target) {
        int mid = (start + end) / 2;
        if (target == arr[mid]) {
            return mid;
        }
        if (end < start) return -1;

        if (arr[start] < arr[mid]) {    //왼쪽이 정상 정렬 상태일 때
            if (arr[start] <= target && target < arr[mid]) {
                return search(arr, start, mid - 1, target);    //왼쪽 탐색
            } else {
                return search(arr, mid + 1, end, target);  //오른쪽 탐색
            }
        } else if (arr[mid] < arr[end]) {    //오른쪽이 정상 정렬 상태일 때
            if (arr[mid] < target && target <= arr[end]) {
                return search(arr, mid + 1, end, target);  //오른쪽 탐색
            } else {
                return search(arr, start, mid - 1, target);    //왼쪽 탐색
            }
        } else if (arr[start] == arr[mid]) {
            if (arr[mid] != arr[end]) {
                return search(arr, mid + 1, end, target);  //오른쪽 탐색
            } else {
                int result = search(arr, start, mid - 1, target);    //왼쪽 탐색
                if (result == -1) {
                    return search(arr, mid + 1, end, target);  //오른쪽 탐색
                } else {
                    return result;
                }
            }
        }
        return -1;
    }
}
