package Sort;

import java.util.Arrays;

//10.11 Peaks and Valleys
// 정수 배열에서 'peak'란 현재 원소가 인접한 정수보다 크거나 같을 때를 말하고, 'valley'란 현재 원소가 인접한 정수보다 작거나 같을 때를 말한다.
// 예를 들어 {5,8,6,2,3,4,6}이 있으면, {8,6}은 'peak'가 되고, {5,2}는 'valley'가 된다.
// 정수 배열이 주어졌을 때, 'peak'와 'valley'가 번갈아 등장하도록 정렬하는 알고리즘을 작성하라.
// 예) 입력 : {5,3,1,2,3} / 출력 : {5,1,3,2,3}


//Solution1
// 시간복잡도 - O(nlogn)
// 부분 최적 해법
// 정렬 후 2개씩 자리 교환하는 방법
public class PeaksAndValleys {
    static int[] arr;

    public static void main(String[] args) {
        arr = new int[]{5, 3, 1, 2, 3};

        Arrays.sort(arr);

        peakValley();
        System.out.println(Arrays.toString(arr));
    }

    static void peakValley() {
        for (int i = 0; i < arr.length - 1; i = i + 2) {
            swap(i, i + 1);
        }
    }

    static void swap(int a, int b) {
        int temp = 0;
        temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
