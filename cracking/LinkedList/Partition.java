package LinkedList;

//2.4 Partition
//값 x가 주어졌을 때 x보다 작은 노드들을 x보다 크거나 같은 노드들보다 앞에 오도록 하는 코드를 작성하라
//만약 x가 리스트에 있다면 x는 그보다 작은 원소들보다 뒤에 나오기만 하면 된다.
//즉, 원소 x는 '오른쪽 그룹' 어딘가에만 존재하면 된다. 왼쪽과 오른쪽 그룹 사이에 있을 필요는 없다.
//예) 입력 : 3-5-8-5-10-2-1(분할값 x=5) / 출력 : 3-1-2-10-5-5-8
public class Partition {
    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node2 = new Node(5);
        Node node3 = new Node(8);
        Node node4 = new Node(5);
        Node node5 = new Node(10);
        Node node6 = new Node(2);
        Node node7 = new Node(1);

        //Link Nodes
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Node node = partition(node1, 5);

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    //Draft
    //Time Complexity - O(N)
    //Space Complexity - O(N)
    private static Node partition(Node head, int x) {
        Node bigHead = null;
        Node smallHead = null;
        Node current = head.next;
        Node firstBigHead = null;
        Node firstSmallHead = null;
        boolean noBig = true;
        boolean noSmall = true;

        if (head.data >= x) {
            bigHead = head;
            firstBigHead = bigHead;
            noBig = false;
        } else {
            smallHead = head;
            firstSmallHead = smallHead;
            noSmall = false;
        }

        while (current != null) {
            if (current.data >= x) {
                if (noBig) {
                    bigHead = current;
                    firstBigHead = current;
                    noBig = false;
                    current = current.next;
                    continue;
                }
                bigHead.next = current;
                bigHead = bigHead.next;
            } else {
                if (noSmall) {
                    smallHead = current.next;
                    firstSmallHead = current;
                    noSmall = false;
                    current = current.next;
                    continue;
                }
                smallHead.next = current;
                smallHead = smallHead.next;
            }
            current = current.next;
        }
        smallHead.next = firstBigHead;
        bigHead.next = null;

        return firstSmallHead;
    }
}
