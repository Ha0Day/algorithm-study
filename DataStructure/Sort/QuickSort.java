package Sort;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {3, 6, 9, 1, 2, 10, 4, 8};
        printArray(arr);
        quickSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

    static void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);

        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];

        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;

            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
