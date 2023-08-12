package Ch3_Stacks_Queues;

import java.util.Stack;

//3.2 Stack Min
//기본적인 push, pop 기능이 구현된 스택에서 최솟값을 반환하는 min 함수를 추가하려고 한다.
//push, pop, min 연산은 모두 O(1)시간에 동작해야 한다.
//min 함수를 어떻게 설계할 수 있는가?

//Solution
//스택을 뒤져 최솟값을 찾는 경우 O(1)이 될 수 없다.
//시간복잡도를 O(1)로 하는 대신 공간 복잡도를 늘린다.
//min 값을 기록하는 스택을 하나 더 둔다.
//+ java에서 정의한 stack을 상속 받아 활용한다.
public class StackMin {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(5);
        stack.push(6);
        stack.push(3);
        stack.push(7);
        stack.push(2);

        System.out.println("Minimum value: " + stack.min());  // should print "Minimum value: 2"

        stack.pop();
        stack.pop();

        System.out.println("Minimum value: " + stack.min());  // should print "Minimum value: 3"

        stack.push(1);

        System.out.println("Minimum value: " + stack.min());  // should print "Minimum value: 1"

    }

    public static class MyStack extends Stack<Integer> {
        private Stack<Integer> minStack;

        public MyStack() {
            minStack = new Stack();
        }

        public void push(int num) {
            super.push(num);
            if (min() > num) {
                minStack.push(num);
            }
        }

        public Integer pop() {
            if (super.peek() == min()) {
                minStack.pop();
            }
            return super.pop();
        }

        int min() {
            if (minStack.isEmpty()) {
                return Integer.MAX_VALUE;
            }
            return minStack.peek();
        }
    }
}
