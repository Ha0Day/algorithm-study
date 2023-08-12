package Ch3_Stacks_Queues;

import java.util.Stack;

//3.4 Queue via Stacks
//스택 두 개로 큐 하나를 구현한 MyQueue 클래스를 구현하라
public class QueueViaStacks {
    public static void main(String[] args) {

        //MyQueue queue = new MyQueue();    //for Draft code
        sol_MyQueue queue = new sol_MyQueue();  //for Solution code

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(4);
        System.out.println("peek: " + queue.peek());
        queue.dequeue();
        queue.dequeue();
        System.out.println("peek: " + queue.peek());
        queue.dequeue();
        System.out.println(queue.isEmpty());

    }

    //Draft code
    private static class MyQueue {
        Stack<Integer> stack = new Stack();
        Stack<Integer> reverseStack = new Stack();


        void printQueue() {
            while (!stack.isEmpty()) {
                reverseStack.push(stack.pop());
            }

            while (!reverseStack.isEmpty()) {
                int item = reverseStack.pop();
                System.out.print(item + " ");
                stack.push(item);
            }
            System.out.println();
        }

        void enqueue(int item) {
            stack.push(item);
        }

        int dequeue() {
            while (!stack.isEmpty()) {
                reverseStack.push(stack.pop());
            }
            int item = reverseStack.pop();

            while (!reverseStack.isEmpty()) {
                stack.push(reverseStack.pop());
            }

            return item;
        }

        int peek() {
            while (!stack.isEmpty()) {
                reverseStack.push(stack.pop());
            }
            int item = reverseStack.peek();

            while (!reverseStack.isEmpty()) {
                stack.push(reverseStack.pop());
            }
            return item;
        }

        boolean isEmpty() {
            return stack.isEmpty();
        }
    }


    //Solution code
    //Draft code와 아이디어는 동일하나
    //Draft code의 경우 pop/peek 연산이 연이어 호출될 때 쓸데없이 원소를 오락가락하게 옮기게 됨
    //여기서는 원소들의 순서를 반드시 뒤집어야 할 경우가 아니면 두 번째 스택에 그대로 두는 게으른(lazy) 접근법을 사용
    //stack
    private static class sol_MyQueue {
        Stack<Integer> stackNewest; //새 원소가 맨 위에 있는 스택
        Stack<Integer> stackOldest; //마지막 원소가 맨 위에 있는 스택

        //stackOldest가 비어 있을 때만 stackNewest의 모든 원소의 순서를 뒤집어서 stackOldest에 넣음
        //원소 삭제는 stackOldest에서, 원소 추가는 stackNewest에 함

        sol_MyQueue() {
            stackNewest = new Stack<>();
            stackOldest = new Stack<>();
        }

        public int size() {
            return stackNewest.size() + stackOldest.size();
        }

        void enqueue(int item) {
            stackNewest.push(item);
        }

        private void shiftStacks() {
            if (stackOldest.isEmpty()) {
                while (!stackNewest.isEmpty()) {
                    stackOldest.push(stackNewest.pop());
                }
            }
        }

        int peek() {
            shiftStacks();
            return stackOldest.peek();
        }

        int dequeue() {
            shiftStacks();
            return stackOldest.pop();
        }

        boolean isEmpty() {
            return stackOldest.isEmpty() && stackNewest.isEmpty();
        }
    }
}
