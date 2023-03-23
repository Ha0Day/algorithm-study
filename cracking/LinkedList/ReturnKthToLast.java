package LinkedList;

//2.2 Return Kth to Last
//단방향 연결리스트가 주어졌을 때 뒤에서 k번째 원소를 찾는 알고리즘을 구현하라
public class ReturnKthToLast {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(4);
        Node node4 = new Node(2);
        Node node5 = new Node(10);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(returnKthToLast(node1, 3)); // expected : 4

    }

    //Draft
    //두 개의 포인터를 사용
    //current 포인터는 연결리스트 순회, runner 포인터는 k 만큼 뒤에서 따라옴
    //Time Complexity - O(N)
    //Space Complexity - O(1)
    //N: 노드 개수
    private static int returnKthToLast(Node head, int k) {
        Node current = head;
        Node runner = current;
        int index = 0;
        while (current != null) {
            index = index + 1;
            if (index >= k + 1) {
                runner = runner.next;
            }
            current = current.next;
        }
        if (k > index) return -1;
        return runner.data;
    }
}
