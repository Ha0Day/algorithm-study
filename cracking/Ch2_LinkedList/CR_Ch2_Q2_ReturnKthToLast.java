package Ch2_LinkedList;

//2.2 Return Kth to Last
//단방향 연결리스트가 주어졌을 때 뒤에서 k번째 원소를 찾는 알고리즘을 구현하라
public class CR_Ch2_Q2_ReturnKthToLast {
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

        System.out.println(returnKthToLast(node1, 3).data); // expected : 4
        System.out.println(sol_kthToLast(node1, 3).data);
        System.out.println(sol_nthToLast(node1, 3).data);
    }

    //Draft
    //두 개의 포인터를 사용
    //current 포인터는 연결리스트 순회, runner 포인터는 k 만큼 뒤에서 따라옴
    //Time Complexity - O(N)
    //Space Complexity - O(1)
    //N: 노드 개수
    private static Node returnKthToLast(Node head, int k) {
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
        if (k > index) return null;
        return runner;
    }

    //Solution1 - 재귀적(recursive) 방법
    //노드를 반환하기 위해 index의 Wrapper class 구현
    //Time Complexity - O(N)
    //Space Complexity - O(N)
    static class Index {
        public int value = 0;
    }

    public static Node sol_kthToLast(Node head, int k) {
        Index idx = new Index();
        return kthToLast(head, k, idx);
    }

    //계속 null 반환하다가 idx값과 k값이 일치하면 해당 노드를 끝까지 반환
    public static Node kthToLast(Node head, int k, Index idx) {
        if (head == null) {
            return null;
        }
        Node node = kthToLast(head.next, k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
            return head;
        }
        return node;
    }

    //Solution2 - 순환적(iterative) 방법
    //Draft code와 비슷
    //Time Complexity - O(N)
    //Space Complexity - O(1)
    public static Node sol_nthToLast(Node head, int k) {
        Node p1 = head;
        Node p2 = head;

        //p1을 k노드만큼 이동시킨다.
        for (int i = 0; i < k; i++) {
            if (p1 == null) return null;
            p1 = p1.next;
        }

        //p1과 p2를 함께 움직인다. p1이 끝에 도달하면, p2는 LENGTH-k번째 원소를 가리키게 된다.
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
