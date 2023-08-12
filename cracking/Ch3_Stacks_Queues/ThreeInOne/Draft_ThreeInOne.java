package Ch3_Stacks_Queues.ThreeInOne;

//3.1 Three in One
//배열 한 개로 스택 세 개를 어떻게 구현할지 설명하라

//Draft Code
//스택 3개의 마지막 index 저장
//스택1,2,3순서로 저장
//가변 길이의 스택
//배열 꽉 차면 2배로 늘림
//스택 1 또는 2에 값이 추가되거나 삭제되면 뒤에 있는 data shift
public class Draft_ThreeInOne {
    static int tail1;
    static int tail2;
    static int tail3;
    static int N = 10;
    static int[] arr = new int[N];

    public static void main(String[] args) {

        initialize(arr);
        push(1, 1);
        push(1, 2);
        push(1, 3);
        push(2, 4);
        push(2, 5);
        push(2, 6);
        push(3, 7);
        push(3, 8);
        push(3, 9);
        push(3, 10);
        push(3, 11);

        printAllStacks();

        pop(1);
        pop(1);
        pop(1);
        pop(1);
        pop(3);
        pop(3);

        printAllStacks();

        System.out.println(isEmpty(1));
        System.out.println(isEmpty(2));
        System.out.println(isEmpty(3));

        System.out.println(peek(3));

    }

    private static void printAllStacks() {
        int i = 0;
        while (arr[i] != -1) {
            System.out.print(arr[i] + " ");
            i = i + 1;
        }
        System.out.println();
    }

    private static void initialize(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
        tail1 = -1;
        tail2 = -1;
        tail3 = -1;
    }

    private static void push(int stackNum, int item) {

        if (arr[arr.length - 1] != -1) {
            arr = sizeUp(arr);
        }

        if (stackNum == 1) {
            if (!isEmpty(2) || !isEmpty(3)) {
                shiftDataRight(tail1 + 1);
            }
            arr[tail1 + 1] = item;
            tail1 = tail1 + 1;
            tail2 = tail2 + 1;
            tail3 = tail3 + 1;
        }
        if (stackNum == 2) {
            if (!isEmpty(3)) {
                shiftDataRight(tail2 + 1);
            }
            arr[tail2 + 1] = item;
            tail2 = tail2 + 1;
            tail3 = tail3 + 1;
        }
        if (stackNum == 3) {
            arr[tail3 + 1] = item;
            tail3 = tail3 + 1;
        }
    }

    private static int[] sizeUp(int[] arr) {
        int[] tmpArr = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            tmpArr[i] = arr[i];
        }
        for (int i = arr.length; i < tmpArr.length; i++) {
            tmpArr[i] = -1;
        }
        return tmpArr;
    }

    private static void shiftDataRight(int startIndex) {
        for (int i = tail3 - 1; i >= startIndex; i--) {
            arr[i + 1] = arr[i];
        }
    }

    private static void pop(int stackNum) {
        if (stackNum == 1) {
            if (isEmpty(1)) {
                return;
            } else {
                shiftDataLeft(tail1);
                tail1 = tail1 - 1;
                tail2 = tail2 - 1;
                tail3 = tail3 - 1;
            }
        }
        if (stackNum == 2) {
            if (isEmpty(2)) {
                return;
            } else {
                shiftDataLeft(tail2);
                tail2 = tail2 - 1;
                tail3 = tail3 - 1;
            }
        }
        if (stackNum == 3) {
            if (isEmpty(3)) {
                return;
            } else {
                shiftDataLeft(tail3);
                tail3 = tail3 - 1;
            }
        }
    }

    private static void shiftDataLeft(int startIndex) {
        for (int i = startIndex; i < tail3; i++) {
            arr[i] = arr[i + 1];
        }
        arr[tail3] = -1;
    }

    private static int peek(int stackNum) {
        if (stackNum == 1) {
            if (!isEmpty(1)) {
                return arr[tail1];
            }
        }
        if (stackNum == 2) {
            if (!isEmpty(2)) {
                return arr[tail2];
            }
        }
        if (stackNum == 3) {
            if (!isEmpty(3)) {
                return arr[tail3];
            }
        }
        return -1;
    }

    private static boolean isEmpty(int stackNum) {
        if (stackNum == 1) {
            if (tail1 == -1) return true;
        }
        if (stackNum == 2) {
            if (tail2 == tail1) return true;
        }
        if (stackNum == 3) {
            if (tail3 == tail2) return true;
        }
        return false;
    }
}
