package Ch10_Sort;

//10.5 드문드문 탐색
// 빈 문자열이 섞여 있는 정렬된 문자열 배열이 주어졌을 때, 특정 문자열의 위치를 찾는 메서드를 작성하라
// 예) 입력: ball, {"at","","","","ball","","","car","","","dad","",""} / 출력 : 4
public class CR_Ch10_Q5_SparseSearch {
    public static void main(String[] args) {
        String[] data5 = {"apple", "", "", "banana", "", "", "", "cherry", "date", "", "elephant"};
        String target5 = "apple";
        int result5 = findStringIndex(data5, target5);
        System.out.println("Position of '" + target5 + "' in Test Case 5 is: " + result5);
    }

    public static int findStringIndex(String[] arr,String str) {
        int start = 0;
        int last = arr.length - 1;
        return search(str, arr, start, last);

    }

    public static int search(String str, String[] arr, int start, int last) {
        if (start > last) {
            return -1;
        }
        int mid = (start + last) / 2;

        if (arr[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;

            while (true) {
                if (start > last) {
                    return -1;
                } else if (start <= left && !arr[left].isEmpty()) {
                    mid = left;
                    break;
                } else if (right <= last && !arr[right].isEmpty()) {
                    mid = right;
                    break;
                }
                left--;
                right++;
            }
        }

        if (str.equals(arr[mid])) {
            return mid;
        } else if (arr[mid].compareTo(str) < 0) {
            return search(str, arr, mid + 1, last);

        } else {
            return search(str, arr, start, mid - 1);
        }

    }
}
