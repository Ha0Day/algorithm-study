import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.Arrays;
import java.util.HashMap;

// Maximum Subarray
/* - 길이가 N인 정수 배열이 주어집니다.
   - 배열에서 가장 큰 연속 부분 배열(sub-array)의 합을 찾아야 합니다.
   - 배열의 원소는 음수일 수도 있고 양수일 수도 있습니다.
   - 부분 배열은 빈 배열이면 안 되며, 연속된 원소들로 구성되어야 합니다.
   - 예1) Input: -2 1 -3 4 -1 2 1 -5 4 / Output: 6
   - 예2) Input: -1 2 3 -5 4 / Output: 5
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = {-1, 2, 3, -5, 4};

        System.out.println(getMaxSubarray_BF(arr));
        System.out.println(getMaxSubarray_BF(arr2));

        System.out.println(getMaxSubarray_BF2(arr));
        System.out.println(getMaxSubarray_BF2(arr2));

        System.out.println(getMaxSubarray_BF3(arr));
        System.out.println(getMaxSubarray_BF3(arr2));

        System.out.println(getMaxSubarray_DP_TD(arr));
        System.out.println(getMaxSubarray_DP_TD(arr2));

        System.out.println(getMaxSubarray_DP_BU(arr));
        System.out.println(getMaxSubarray_DP_BU(arr2));

    }

    //Draft code

    //Brute Force - 중첩 for문
    //시간 복잡도 O(N^2)
    //공간 복잡도 O(1) ?
    static int getMaxSubarray_BF(int[] arr) {
        int max_sum = Integer.MIN_VALUE;
        int sum = 0;

        //중첩 for문
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                sum = sum + arr[j];
                if (sum >= max_sum) {
                    max_sum = sum;
                }
            }
            sum = 0;
        }
        return max_sum;
    }


    //Brute Force2 - for문 + 재귀
    //시간 복잡도 O(N)
    //공간 복잡도 O(N^2) ? n의 공간을 만드는 함수를 n번 호출하므로
    static int getMaxSubarray_BF2(int[] arr) {
        //탈출 조건
        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        }

        //for문
        int max_sum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            sum = sum + arr[i];
            if (sum >= max_sum) {
                max_sum = sum;
            }
        }
        //재귀
        int[] cut_arr = new int[arr.length - 1];
        for (int i = 0; i < cut_arr.length; i++) {
            cut_arr[i] = arr[i];
        }
        return Math.max(max_sum, getMaxSubarray_BF2(cut_arr));
    }


    //Brute Force3 - 재귀 2개
    //시간복잡도 O(N^2)
    //공간복잡도 O(N^2) ?
    static int getMaxSubarray_BF3(int[] arr) {
        //탈출 조건
        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        }

        //재귀
        int[] cut_arr = new int[arr.length - 1];
        for (int i = 0; i < cut_arr.length; i++) {
            cut_arr[i] = arr[i];
        }
        return Math.max(imax(arr), getMaxSubarray_BF3(cut_arr));
    }

    static int imax(int[] arr) {
        //탈출 조건
        if (arr.length == 1) {
            return arr[0];
        }

        int[] cut_arr = new int[arr.length - 1];
        for (int i = 0; i < cut_arr.length; i++) {
            cut_arr[i] = arr[i];
        }

        return Math.max(imax(cut_arr) + arr[arr.length - 1], arr[arr.length - 1]);
    }


    //DP (Top-Down)
    //시간 복잡도 O(N)
    //공간 복잡도 O(N^2) ?
    static int getMaxSubarray_DP_TD(int[] arr) {
        //탈출 조건
        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        }

        //재귀
        int[] cut_arr = new int[arr.length - 1];
        for (int i = 0; i < cut_arr.length; i++) {
            cut_arr[i] = arr[i];
        }
        return Math.max(imax2(arr), getMaxSubarray_DP_TD(cut_arr)); //재귀함수가 2개
    }

    static int imax2(int[] arr) {
        //캐시
        HashMap<Integer, Integer> nums = new HashMap<>();
        return imax2(arr, nums);
    }

    static int imax2(int[] arr, HashMap<Integer, Integer> nums) {
        //캐시에 있는 경우 바로 return
        if (nums.containsKey(arr.length)) {
            return nums.get(arr.length);
        }

        //탈출 조건
        if (arr.length == 0) {
            return 0;
        }

        int[] cut_arr = new int[arr.length - 1];
        for (int i = 0; i < cut_arr.length; i++) {
            cut_arr[i] = arr[i];
        }

        nums.put(arr.length, Math.max(imax2(cut_arr, nums) + arr[arr.length - 1], arr[arr.length - 1]));

        return nums.get(arr.length);
    }


    //DP (Bottom-Up)
    //시간 복잡도 O(N)
    //공간 복잡도 O(1) ?
    static int getMaxSubarray_DP_BU(int[] arr) {

        //n이 가장 작은 케이스 저장
        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return arr[0];
        }

        //캐시 - 직전 값만 저장하면 되므로 int형으로 선언
        int imax = arr[0];
        int result = arr[0];

        for (int i = 1; i < arr.length; i++) {
            imax = Math.max(imax + arr[i], arr[i]);
            result = Math.max(result, imax);
        }
        return result;
    }
}
