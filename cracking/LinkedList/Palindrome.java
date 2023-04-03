package LinkedList;

import java.util.ArrayList;
import java.util.List;

//2.6 Palindrome
//주어진 연결리스트가 회문인지 검사하는 함수를 작성하라
public class Palindrome {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(1);
        Node node4 = new Node(1);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(isPalindrome(node1));

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
}
