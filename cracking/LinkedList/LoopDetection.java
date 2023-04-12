package LinkedList;

//2.8 Loop Detection
// 순환 연결리스트(circular linked list)가 주어졌을 때,
// 순환되는 부분의 첫째 노드를 반환하는 알고리즘을 작성하라
// 순환 연결리스트란 노드의 next 포인터가 앞선 노드들 가운데 어느 하나를 가리키도록 설정되어 있는, 엄밀히 말해서는 변질된 방식의 연결리스트이다
//예) 입력: A->B->C->D->E->C(앞의 C와 동일) / 출력 : C
public class LoopDetection {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = node1;

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node6.next = node6;

        System.out.println(loopDetection(node1));
    }

    //Draft code

    static Node loopDetection(Node head) {
        Node original_head = head;
        Node compNode; //비교 대상이되는 노드

        if (head == null) return null;
        head = head.next;

        int index = 1;

        while (head != null) {
            compNode = original_head;

            for (int i = 0; i < index; i++) {
                if (compNode == head) {
                    return head;
                }
                compNode = compNode.next;
            }
            head = head.next;
            index++;
        }
        return null; //리스트에 끝이 있다는 것은 loop가 없다는 의미
    }
}
