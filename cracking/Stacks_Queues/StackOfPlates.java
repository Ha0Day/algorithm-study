package Stacks_Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//3.3 Stack of Plates
//접시를 쌓다가 무더기 높이가 어느 정도 높아지면 새로운 무더기를 만든다. 이것을 흉내 내는 자료구조 SetOfStacks를 구현하라
//SetOfStacks는 여러 개의 스택으로 구성되어 있으며, 이전 스택이 지정된 용량을 초과하는 경우 새로운 스택을 생성한다.
//SetOfStacks.push()와 SetOfStacks.pop()은 스택이 하나인 경우와 동일하게 동작해야 한다.
//다시 말해, pop()은 정확히 하나의 스택이 있을 때와 동일한 값을 반환해야 한다.
//연관문제) 특정한 하위 스택에 대해서 pop을 수행하는 popAt(int index) 함수를 구현하라.
public class StackOfPlates {

    public static void main(String[] args) {

        //for Draft code
        SetOfStacks stacks = new SetOfStacks(3);

        stacks.push(1);
        System.out.println("push 1");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(2);
        System.out.println("push 2");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(3);
        System.out.println("push 3");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(4);
        System.out.println("push 4");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(5);
        System.out.println("push 5");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(6);
        System.out.println("push 6");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(7);
        System.out.println("push 7");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(8);
        System.out.println("push 8");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.push(9);
        System.out.println("push 8");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.pop();
        System.out.println("pop");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.popAt(1);
        System.out.println("popAt 1");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());

        stacks.printAllStacks();

    }

    //Draft code
    private static class SetOfStacks {
        int stackCapacity;
        int indexOfAllStacks = -1;
        List<Stack> setOfStacks = new ArrayList<>();

        SetOfStacks(int stackCapacity) {
            this.stackCapacity = stackCapacity;
        }

        void printAllStacks() {
            for (int i = 0; i < setOfStacks.size(); i++) {
                while (!setOfStacks.get(i).isEmpty()) {
                    System.out.print(setOfStacks.get(i).pop() + " ");
                }
                System.out.println();
            }
        }

        void push(int item) {

            if (endOfStack()) {
                Stack<Integer> stack = new Stack();
                setOfStacks.add(stack);
                stack.push(item);
            } else {
                Stack lastStack = setOfStacks.get(indexOfLastStack());
                lastStack.push(item);
            }
            indexOfAllStacks = indexOfAllStacks + 1;
        }

        private boolean endOfStack() {
            if ((indexOfAllStacks + 1) % stackCapacity == 0) {
                return true;
            }
            return false;
        }

        private int indexOfLastStack() {
            return indexOfAllStacks / stackCapacity;
        }

        int pop() {
            Stack lastStack = setOfStacks.get(indexOfLastStack());
            int item = (int) lastStack.pop();
            indexOfAllStacks = indexOfAllStacks - 1;
            if (lastStack.isEmpty()) setOfStacks.remove(indexOfLastStack() + 1);
            return item;
        }

        int popAt(int index) {
            //마지막 스택인 경우
            if (index == indexOfLastStack()) {
                return pop();
            }

            //마지막 스택이 아닌 경우
            return shiftData(index);
        }

        int shiftData(int index) {
            Stack lastStack = setOfStacks.get(indexOfLastStack());

            //pop하는 값까지 저장하기 위해 마지막에 +1
            int arrSize = stackCapacity * (setOfStacks.size() - index - 1 - 1) + lastStack.size() + 1;
            int[] arr = new int[arrSize];

            //배열에 뒤 스택의 값 모두 저장
            for (int i = 0; i < arrSize; i++) {
                arr[i] = pop();
            }

            //스택에 값 저장
            for (int i = arrSize - 2; i >= 0; i--) {
                push(arr[i]);
            }

            return arr[arrSize - 1];
        }
    }
}
