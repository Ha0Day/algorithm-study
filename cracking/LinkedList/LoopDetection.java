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
        Node node6 = node3;

        System.out.println(node3);
        System.out.println(node6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node6.next = node6;

        //System.out.println(loopDetection(node1));
        System.out.println(sol_FindBeginning(node1));
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

    //Solution
    /*
    속도가 다른 포인터 2개 사용 (루프가 있다면 2개 포인터는 반드시 충돌)
    FastPointer를 SlowPointer보다 두 배 빠르게 이동시킴
    SlowPointer가 k만큼 움직인 후에 루프 시작점에 도달한다고 하면, FastPointer는 루프 내에서 k번째 노드에 있을 것 (2k만큼 갔으므로)
    즉, FastPointer와 SlowPointer는 LOOP_SIZE - k 노드만큼 뒤쳐져 있음 (여기서 k는 정확히는 mod(k,LOOP_SIZE))

    SlowPointer가 한 번 움직일 때마다 FastPointer가 두 번 움직이니까 이 둘은 한 번 움직일 때마다 노드 한 개만큼 가까워짐
    따라서 LOOP_SIZE-k번 이동하면 두 포인터는 만나게 되고, 이 둘은 모두 루프 시작 지점에서 k만큼 뒤쳐져 있음

    연결리스트의 시작 지점 또한 루프의 시작 지점에서 k만큼 떨어져 있음
    그러므로, 한 포인터는 현재 위치를 가리키고 다른 포인터는 연결리스트의 시작 지점에 놓은 뒤 이 둘을 함께 움직인다면, 그들은 루프 시작 지점에서 만날 것

    -최종 알고리즘-
    1. 두 포인터 FastPointer와 SlowPointer를 만든다.
    2. FastPointer는 한 번에 두 노드, SlowPointer는 한 번에 한 노드씩 움직인다.
    3. 두 포인터가 만나면, SlowPointer는 LinkedListHead로 옮기고, FastPointer는 그 자리에 그대로 둔다.
    4. SlowPointer와 FastPointer를 한 번에 한 노드씩 움직인다. 이 둘이 만나는 지점을 반환한다.
     */
    //시간복잡도
    static Node sol_FindBeginning(Node head) {
        Node slow = head;
        Node fast = head;

        //만나는 지점을 찾는다. 연결리스트 안에서 LOOP_SIZE-k만큼 들어간 상태이다.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next; //2개 노드씩 이동

            if (slow == fast) { //충돌
                break;
            }
        }

        //에러 체크. 만나는 지점이 없다면, 루프도 없다.
        if (fast == null || fast.next == null) {
            return null;
        }

        //slow를 head로 옮기고, fast는 그대로 둔다. 이 둘은 루프 시작 지점에서 k만큼 떨어져 있다.
        //이 둘이 같은 속도로 움직인다면 시작 지점에서 만나게 된다.
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        //둘 다 루프의 시작 지점을 가리킨다.
        return fast;
    }
}