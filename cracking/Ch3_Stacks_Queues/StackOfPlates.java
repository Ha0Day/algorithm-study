package Stacks_Queues;

import java.sql.Array;
import java.util.ArrayList;
import java.util.EmptyStackException;
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
        //SetOfStacks stacks = new SetOfStacks(3);

        //for Solution code
        sol_SetOfStacks stacks = new sol_SetOfStacks(3);


        stacks.push(1);
        /*System.out.println("push 1");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(2);
        /*System.out.println("push 2");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(3);
        /*System.out.println("push 3");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(4);
        /*System.out.println("push 4");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(5);
        /*System.out.println("push 5");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(6);
        /*System.out.println("push 6");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(7);
        /*System.out.println("push 7");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(8);
        /*System.out.println("push 8");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.push(9);
        /*System.out.println("push 8");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.pop();
        /*System.out.println("pop");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

        stacks.popAt(1);
        /*System.out.println("popAt 1");
        System.out.println("current index = " + stacks.indexOfAllStacks);
        System.out.println("current stack = " + stacks.indexOfLastStack());*/

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


    //Solution code
    //stack을 linkedlist로 구현하여
    //popAt 함수의 경우 shift할 때 시간 및 공간 복잡도가 조금 더 나을 것 같음
    private static class sol_SetOfStacks {
        ArrayList<MyStack> stacks = new ArrayList();
        public int capacity;

        public sol_SetOfStacks(int capacity) {
            this.capacity = capacity;
        }

        void printAllStacks() {
            for (int i = 0; i < stacks.size(); i++) {
                while (!stacks.get(i).isEmpty()) {
                    System.out.print(stacks.get(i).pop() + " ");
                }
                System.out.println();
            }
        }

        public MyStack getLastStack() {
            if (stacks.size() == 0) return null;
            return stacks.get(stacks.size() - 1);
        }

        public void push(int v) {
            MyStack last = getLastStack();
            if (last != null && !last.isFull()) { //마지막 스택에 추가한다.
                last.push(v);
            } else {  //스택을 새로 만든다.
                MyStack stack = new MyStack(capacity);
                stack.push(v);
                stacks.add(stack);
            }
        }

        public int pop() {
            MyStack last = getLastStack();
            if (last == null) throw new EmptyStackException();
            int v = last.pop();
            if (last.size == 0) stacks.remove(stacks.size() - 1);
            return v;
        }

        public boolean isEmpty() {
            MyStack last = getLastStack();
            return last == null || last.isEmpty();
        }

        public int popAt(int index) {
            return leftShift(index, true);
        }

        public int leftShift(int index, boolean removeTop) {
            MyStack stack = stacks.get(index);
            int removed_item;
            //제거할 item을 pop
            if (removeTop) removed_item = stack.pop();
                //item 제거 후 뒤 스택의 가장 아래 데이터 제거
            else removed_item = stack.removeBottom();
            if (stack.isEmpty()) {
                stacks.remove(index);
            } else if (stacks.size() > index + 1) { //pop된 스택의 바로 뒤 스택부터 마지막 스택까지
                int v = leftShift(index + 1, false); //재귀
                //앞 스택에 뒤 스택의 가장 아래 데이터를 push
                stack.push(v);
            }
            return removed_item;
        }
    }

    public static class MyStack {
        private int capacity;
        public Node top, bottom;
        public int size = 0;

        public MyStack(int capacity) {
            this.capacity = capacity;
        }

        public boolean isFull() {
            return capacity == size;
        }

        public void join(Node above, Node below) {
            if (below != null) below.above = above;
            if (above != null) above.below = below;
        }

        public boolean push(int v) {
            if (size >= capacity) return false;
            size++;
            Node n = new Node(v);
            if (size == 1) bottom = n;
            join(n, top);
            top = n;
            return true;
        }

        public int pop() {
            Node t = top;
            top = top.below;
            size--;
            return t.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int removeBottom() {
            Node b = bottom;
            bottom = bottom.above;
            if (bottom != null) bottom.below = null;
            size--;
            return b.value;
        }
    }

    public static class Node {
        int value;
        Node below, above;

        Node(int value) {
            this.value = value;
        }
    }
}
