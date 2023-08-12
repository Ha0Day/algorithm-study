package Ch2_LinkedList;

//2.4 Partition
//값 x가 주어졌을 때 x보다 작은 노드들을 x보다 크거나 같은 노드들보다 앞에 오도록 하는 코드를 작성하라
//만약 x가 리스트에 있다면 x는 그보다 작은 원소들보다 뒤에 나오기만 하면 된다.
//즉, 원소 x는 '오른쪽 그룹' 어딘가에만 존재하면 된다. 왼쪽과 오른쪽 그룹 사이에 있을 필요는 없다.
//예) 입력 : 3-5-8-5-10-2-1(분할값 x=5) / 출력 : 3-1-2-10-5-5-8
public class CR_Ch2_Q4_Partition {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(23);
        Node node3 = new Node(52);
        Node node4 = new Node(71);
        Node node5 = new Node(41);
        Node node6 = new Node(31);
        Node node7 = new Node(22);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Node node = sol_partition2(node1, 5); //함수명 바꾸며 테스트

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

        if (firstSmallHead == null) return firstBigHead;

        smallHead.next = firstBigHead;

        if (firstBigHead == null) {
            smallHead.next = null;
        } else {
            bigHead.next = null;
        }

        return firstSmallHead;
    }

    //Solution1
    //Draft와 비슷
    //원소 순서 보장
    //서로 다른 연결리스트 2개를 만들고,
    //하나에는 x보다 작은 원소들 보관, 하나에는 x보다 크거나 같은 원소들 보관
    //마지막에 before list 뒤에 after list 연결
    public static Node sol_partition(Node node, int x) {
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        while (node != null) {
            Node next = node.next; //리스트의 다음 노드 저장해 두고,
            node.next = null; //현재 노드는 리스트에서 떼는 것
            if (node.data < x) {
                //before 리스트가 현재 비어 있는 경우
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = node;
                } else {
                    beforeEnd.next = node; //before 리스트의 끝에 원소 삽입
                    beforeEnd = node; //포인터 이동
                }
            } else {
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = node;
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        }

        beforeEnd.next = afterStart;
        return beforeStart;
    }

    //Solution2
    //원소 순서 보장 x
    //리스트의 head와 tail에서 원소를 재배치
    //이미 존재하는 노드를 사용해 새로운 리스트 생성
    //피벗보다 큰 원소들은 tail에, 피벗보다 작은 원소들은 head에 붙임
    private static Node sol_partition2(Node node, int x) {
        Node head = node;
        Node tail = node;

        while (node != null) {
            Node next = node.next;
            if (node.data < x) {
                //head에 노드 삽입
                node.next = head; //head 앞에 node 붙이고
                head = node; //head를 앞으로 이동
            } else {
                //tail에 노드 삽입
                tail.next = node; //tail 뒤에 node 붙이고
                tail = node; //tail을 뒤로 이동
            }
            node = next;
        }
        tail.next = null;

        //head가 바뀌었기 때문에 새로운 head를 반환해야 함
        return head;
    }
}
