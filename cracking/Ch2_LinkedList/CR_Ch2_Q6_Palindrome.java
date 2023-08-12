package LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//2.6 Palindrome
//주어진 연결리스트가 회문인지 검사하는 함수를 작성하라
public class Palindrome {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(1);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(sol_isPalindrome3(node1));

    }

    //Draft code
    //시간 복잡도 O(N)
    //공간 복잡도 O(N)
    static boolean isPalindrome(Node node) {
        List<Node> list = new ArrayList<>();

        while (node != null) {
            list.add(node);
            node = node.next;
        }

        for (int i = 0; i < list.size() / 2; i++) {
            if (list.get(i).data != list.get(list.size() - 1 - i).data) {
                return false;
            }
        }

        return true;
    }

    //Solution1 - 뒤집어서 비교
    //시간 복잡도 O(N)
    //공간 복잡도 O(N)
    static boolean sol_isPalindrome(Node head) {
        Node reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    private static boolean isEqual(Node one, Node two) {
        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

    private static Node reverseAndClone(Node node) {
        Node head = null;
        while (node != null) {
            Node n = new Node(node.data);   //데이터 복사
            n.next = head;  //새로운 원소가 맨 앞으로 오도록 위치
            head = n;   //head가 맨 앞 원소를 가리키도록 함
            node = node.next;   //원래 리스트의 다음 node를 가져오기 위해
        }
        return head;
    }

    //Solution2 - 순환적 접근법
    //스택을 사용하여 절반을 뒤집음
    //시간 복잡도 O(N)
    //공간 복잡도 O(N) 이긴 하나
    //원소의 절반을 stack에 넣고 비교하므로 복잡도가 더 나을 것 같음
    static boolean sol_isPalindrome2(Node head) {
        Node fast = head;
        Node slow = head;

        Stack<Integer> stack = new Stack();

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        //원소의 개수가 홀수 개라면 가운데 원소는 넘긴다.
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop();
            //값이 다르면 회문이 될 수 없다.
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    //Solution3 - 재귀적 접근법
    //함수를 재귀적으로 호출할 때 length 인자에 length-2를 전달하면 언제 중간 지점에 도달하는지 알 수 있음
    //매 호출마다 length가 2씩 줄어들게 되므로 N/2번 호출한 후엔 length가 0이 됨
    //각 호출마다 리스트의 헤드와 returned_node를 비교한 다음에 returned_node.next를 상위 스택에 반환하는 방식

    //노드와 boolean 값을 같이 반환해야 하므로 Result 클래스 사용
    static class Result {
        public Node node;
        public boolean result;

        public Result(Node node, boolean result) {
            this.node = node;
            this.result = result;
        }
    }

    static boolean sol_isPalindrome3(Node head) {
        int length = lengthOfList(head);
        Result p = isPalindromeRecurse(head, length);
        return p.result;
    }

    static Result isPalindromeRecurse(Node head, int length) {
        if (head == null || length <= 0) { //노드의 개수가 짝수일 때
            return new Result(head, true);
        } else if (length == 1) {   //노드의 개수가 홀수일 때
            return new Result(head.next, true);
        }

        //부분 리스트를 재귀적으로 호출
        Result res = isPalindromeRecurse(head.next, length - 2);

        //위 호출 결과 회문이 아니라는 사실이 밝혀지면, 그 결과값을 반환한다.
        if (!res.result || res.node == null) {
            return res;
        }

        //두 노드의 값이 같은지 확인
        res.result = (head.data == res.node.data);

        //그 다음에 비교할 노드 반환
        res.node = res.node.next;

        return res;
    }

    private static int lengthOfList(Node n) {
        int size = 0;
        while (n != null) {
            size++;
            n = n.next;
        }
        return size;
    }
}
