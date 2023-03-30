package Stacks_Queues;

import java.util.Stack;

//3.4 Queue via Stacks
//스택 두 개로 큐 하나를 구현한 MyQueue 클래스를 구현하라
public class QueueViaStacks {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        queue.enqueue(1);
        queue.printQueue();
        queue.enqueue(2);
        queue.printQueue();
        queue.enqueue(3);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.enqueue(4);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        System.out.println("peek: "+queue.peek());
        queue.dequeue();
        queue.printQueue();
        System.out.println(queue.isEmpty());
        queue.dequeue();
        queue.printQueue();
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
}
