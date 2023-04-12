package LinkedList;

//2.7 Intersection
// 단방향 연결리스트 두 개 주어짐
// 이 두 리스트의 교집합 노드를 찾은 뒤 반환하는 코드를 작성하라
// 여기서 교집합이란 노드의 값이 아니라 노드의 주소가 완전히 같은 경우를 말함
public class Intersection {

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
        Node bnode4 = new Node(4);
        Node bnode5 = new Node(4);

        System.out.println(bnode2);

        anode1.next = anode2;
        anode2.next = anode3;
        anode3.next = anode4;
        anode4.next = anode5;

        bnode1.next = bnode2;
        bnode2.next = bnode3;
        bnode3.next = bnode4;
        bnode4.next = bnode5;

        System.out.println(intersection(anode1, bnode1));
    }

    //Draft code
    //시간복잡도 O(N*M)
    static Node intersection(Node anode, Node bnode) {

        Node tmp = bnode;

        while (anode != null) {
            bnode = tmp;
            while (bnode != null) {
                if (anode.equals(bnode)) {
                    return anode;
                }
                bnode = bnode.next;
            }
            anode = anode.next;
        }
        return null;
    }
}
