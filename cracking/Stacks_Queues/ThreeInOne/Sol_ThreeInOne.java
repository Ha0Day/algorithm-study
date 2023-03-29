package Stacks_Queues.ThreeInOne;

import java.util.EmptyStackException;

public class ThreeInOne {

    public static void main(String[] args) throws Exception {

        FixedMultiStack stack = new FixedMultiStack(5);

        stack.push(0, 1);
        stack.push(0, 2);
        stack.push(0, 3);
        stack.push(1, 4);
        stack.push(1, 5);
        stack.push(1, 6);
        stack.push(2, 7);
        stack.push(2, 8);
        stack.push(2, 9);
        stack.push(2, 10);
        stack.push(2, 11);

        stack.printAllStacks();

        stack.pop(1);
        stack.pop(1);
        stack.pop(1);
        stack.pop(1);
        stack.pop(3);
        stack.pop(3);

        stack.printAllStacks();

        System.out.println(stack.isEmpty(1));
        System.out.println(stack.isEmpty(2));
        System.out.println(stack.isEmpty(3));

        System.out.println(stack.peek(3));
    }

}

class FixedMultiStack {

    private static int numberOfStacks = 3;
    private static int stackCapacity;
    private static int[] values;
    private static int[] sizes;


    public static void printAllStacks() {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }
    }

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public static void push(int stackNum, int value) throws Exception {
        if (isFull(stackNum)) {
            throw new Exception();
        }

        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public static int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }

        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex];
        values[topIndex] = 0;
        sizes[stackNum]--;
        return value;
    }

    public static int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    public static boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public static boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    private static int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }
}
