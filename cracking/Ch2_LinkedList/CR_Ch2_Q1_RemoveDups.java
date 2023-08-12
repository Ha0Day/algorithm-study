package LinkedList;

import java.util.*;

//2.1 Remove Dups
//정렬되어 있지 않은 연결리스트가 주어졌을 때 이 리스트에서 중복되는 원소를 제거하는 코드를 작성하라.
//연관문제) 임시 버퍼를 사용할 수 없다면 어떻게 풀까?
public class RemoveDups {
    public static void main(String[] args) {
        //For Draft code
        /*
        List list = new LinkedList();

        list.add(1);
        list.add(2);
        list.add(2);
        list.add(0);
        list.add(-1);
        list.add(-1);

        removeDuplicates(list);
        System.out.println(list);
        */


        //For Solution code
        //Create Nodes
        Node node1 = new Node(1); // Expected result [1,2,3]
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(3);
        Node node6 = new Node(3);

        //Link Nodes
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        //sol_removeDups(node1);
        sol_removeDups2(node1);

        //Print Result
        while (node1 != null) {
            System.out.print(node1.data + " ");
            node1 = node1.next;
        }
    }

    //Draft
    private static void removeDuplicates(List list) {
        Set set = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }

        list.removeAll(set);


        Iterator it = set.iterator();

        for (int i = 0; i < set.size(); i++) {
            list.add(it.next());
        }
    }

    //Solution1
    //Time Complexity - O(N)
    //Space Complexity - O(N)
    //N: 노드 개수
    public static void sol_removeDups(Node node) {
        HashSet hs = new HashSet();
        Node previous = null; //리스트에서 노드 삭제 시 사용 예정
        while (node != null) {
            if (!hs.contains(node.data)) {
                hs.add(node.data);
                previous = node; //지금 방문했기 때문에 현재 노드를 previos에 저장함으로써 다음 노드로 이동
            } else { //앞 서 있었던 값이면 리스트에서 제거
                previous.next = node.next;
            }
            node = node.next;
        }
    }

    //Solution2 - 연관문제 (버퍼 사용x)
    //두 개의 포인터를 사용하는 방법
    //current 포인터는 연결리스트 순회, runner 포인터는 그 뒤에 중복되는 원소가 있는지 확인
    //Time Complexity - O(N^2)
    //Space Complexity - O(1)
    //N: 노드 개수
    public static void sol_removeDups2(Node head) {
        Node current = head;
        while (current != null) {
            //값이 같은 다음 노드들을 모두 제거한다.
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }
}


