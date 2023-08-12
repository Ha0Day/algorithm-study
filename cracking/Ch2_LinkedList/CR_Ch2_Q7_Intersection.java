package Ch2_LinkedList;

//2.7 Intersection
// 단방향 연결리스트 두 개 주어짐
// 이 두 리스트의 교집합 노드를 찾은 뒤 반환하는 코드를 작성하라
// 여기서 교집합이란 노드의 값이 아니라 노드의 주소가 완전히 같은 경우를 말함
public class CR_Ch2_Q7_Intersection {

    public static void main(String[] args) {
        Node anode1 = new Node(1);
        Node anode2 = new Node(2);
        Node anode3 = new Node(2);
        Node anode4 = new Node(1);
        Node anode5 = new Node(1);

        System.out.println(anode4);


        Node bnode1 = new Node(1);
        Node bnode2 = anode4;
        Node bnode3 = new Node(5);

        System.out.println(bnode2);

        anode1.next = anode2;
        anode2.next = anode3;
        anode3.next = anode4;
        anode4.next = anode5;

        bnode1.next = bnode2;
        //bnode2.next = bnode3;   //anode4(bnode2)와 anode5의 연결이 끊기게 됨

        System.out.println(sol_findIntersection(anode1, bnode1));
    }

    //Draft code
    //시간복잡도 O(N*M)
    static Node intersection(Node anode, Node bnode) {

        Node tmp = bnode;

        while (anode != null) {
            bnode = tmp;
            while (bnode != null) {
                if (anode.equals(bnode)) {  //equals 대신 == 사용 가능
                    return anode;
                }
                bnode = bnode.next;
            }
            anode = anode.next;
        }
        return null;
    }

    //살짝 별개의 내용이지만 교집합의 존재 유무는 단순히 두 리스트의 마지막 노드가 같은지만 보면 알 수 있다
    //교집합이 생긴 순간 이후부터는 같은 길을 가기 때문

    //Solution
    /*
    1. 각 연결리스트를 순회하면서 길이와 마지막 노드를 구한다.
    2. 마지막 노드를 비교한 뒤, 그 참조값이 다르다면 교집합이 없다는 사실을 바로 반환한다.
    3. 두 연결리스트의 시작점에 포인터를 놓는다.
    4. 길이가 더 긴 리스트의 포인터를 두 길이의 차이만큼 앞으로 움직인다.
    5. 두 포인터가 같아질 때까지 두 리스트를 함께 순회한다.
    */
    //시간복잡도 O(N)

    static int lengthOfList(Node node) {
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    static Node lastNodeOfList(Node node) {
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    static Node sol_findIntersection(Node anode, Node bnode) {
        if (lastNodeOfList(anode) != lastNodeOfList(bnode)) {
            return null;
        }

        int dist;
        Node apoint = anode;
        Node bpoint = bnode;

        if (lengthOfList(anode) > lengthOfList(bnode)) {
            dist = lengthOfList(anode) - lengthOfList(bnode);
            for (int i = 0; i < dist; i++) {
                apoint = apoint.next;
            }
        } else {
            dist = lengthOfList(bnode) - lengthOfList(anode);
            for (int i = 0; i < dist; i++) {
                bpoint = bpoint.next;
            }
        }

        while (apoint != bpoint) {
            apoint = apoint.next;
            bpoint = bpoint.next;
        }
        return apoint;
    }
}
