package Ch3_Stacks_Queues;

import java.util.Stack;

//3.5 Sort Stack
//가장 작은 값이 위로 오도록 스택을 정렬하는 프로그램을 작성하라.
//추가적으로 하나 정도의 스택은 사용해도 괜찮지만, 스택에 보관된 요소를 배열 등의 다른 자료구조로 복사할 수는 없다.
//스택은 push, pop, peek, isEmpty의 네 가지 연산을 제공해야 한다.
public class SortStack {

    public static void main(String[] args) {

        //for Draft code
        /*MyStack stack = new MyStack();

        stack.push(4);
        System.out.println(stack.peek());
        stack.push(5);
        System.out.println(stack.peek());
        stack.push(1);
        System.out.println(stack.peek());
        stack.push(9);
        System.out.println(stack.peek());
        stack.push(11);
        System.out.println(stack.peek());
        stack.push(8);
        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());

        stack.push(0);
        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());

        System.out.println(stack.isEmpty());

        stack.pop();

        System.out.println(stack.isEmpty());*/


        //for Solution code
        Stack s = new Stack();
        s.push(4);
        s.push(5);
        s.push(1);
        s.push(9);
        s.push(11);
        s.push(8);

        sort(s);

        while (!s.isEmpty()) {
            System.out.println(s.pop() + " ");
        }
    }


    //Draft code
    //시간 복잡도 O(N)
    //공간 복잡도 O(N)
    //문제 이해를 다르게 해서 완전 다른 코드 작성..
    public static class MyStack {
        Stack<Integer> sortedStack = new Stack<>();
        Stack<Integer> tmpStack = new Stack<>();

        void push(int item) {
            //스택이 비어 있는 경우
            if (sortedStack.isEmpty()) {
                sortedStack.push(item);
            }
            //추가하려는 값이 최솟값인 경우
            else if (item <= sortedStack.peek()) {
                sortedStack.push(item);
            }
            //추가하려는 값이 최솟값이 아닌 경우
            else {
                boolean itemPushed = false; //추가하려는 값이 추가되었는지 확인하는 변수
                while (!sortedStack.isEmpty()) {
                    int element = sortedStack.peek();
                    //추가하려는 값보다 스택의 원소가 작은 경우, 혹은 이미 추가하려는 값이 추가된 경우
                    if (element <= item || itemPushed) {
                        sortedStack.pop();
                        tmpStack.push(element);
                    }
                    //추가하려는 값보다 스택의 원소가 큰 경우, item 먼저 추가
                    else {
                        tmpStack.push(item);
                        itemPushed = true;
                    }
                }
                //추가하려는 값이 가장 커서 아직 추가가 안 된 경우
                if (!itemPushed) {
                    tmpStack.push(item);
                }

                //최솟값이 가장 위에 오도록 다시 원래 스택으로 이동
                while (!tmpStack.isEmpty()) {
                    sortedStack.push(tmpStack.pop());
                }
            }
        }

        int pop() {
            return sortedStack.pop();
        }

        int peek() {
            return sortedStack.peek();
        }

        boolean isEmpty() {
            return sortedStack.isEmpty();
        }
    }


    //Solution code
    //시간 복잡도 O(N^2)
    //공간 복잡도 O(N)
    static void sort(Stack s) {
        Stack<Integer> originalStack = s;
        Stack<Integer> sortedStack = new Stack();

        while (!originalStack.isEmpty()) {
            int tmp = originalStack.pop();

            while (!sortedStack.isEmpty() && sortedStack.peek() > tmp) {
                originalStack.push(sortedStack.pop());
            }
            sortedStack.push(tmp);
        }

        //sortedStack은 역순으로 정렬되어 있으므로 마지막에 다시 originalStack으로 옮겨줌으로써 정렬 완료
        while (!sortedStack.isEmpty()) {
            originalStack.push(sortedStack.pop());
        }
    }
}
