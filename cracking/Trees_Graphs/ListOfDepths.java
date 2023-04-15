package Trees_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

//4.3 List of Depths
// 이진 트리가 주어졌을 때 같은 깊이에 있는 노드를 연결리스트로 연결해 주는 알고리즘을 설계하라
// 즉, 트리의 깊이가 D라면 D개의 연결리스트를 만들어야 한다
public class ListOfDepths {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        //n3.left = n6;
        n3.right = n7;
        n5.right = n8;
        //n7.right = n9;

        ArrayList<Node> list = makeListsOfDepths(n1);
        Node node;

        for (int i = 0; i < list.size(); i++) {
            node = list.get(i);
            while (node != null) {
                System.out.print(node.data + " ");
                node = node.next;
            }
            System.out.println();
        }
    }

    //Draft code
    //BFS 활용하여 풀이

    static ArrayList<Node> makeListsOfDepths(Node root) {

        if (root == null) {
            return null;
        }

        ArrayList<Node> lists = new ArrayList();

        Queue<Node> q = new Queue();
        Node head = root;
        Node current;
        int current_level = -1;

        q.enqueue(root);    //루트 노드 삽입

        while (!q.isEmpty()) {
            current = q.dequeue();  //노드 제거
            if (current.level > current_level) { //새로운 레벨이라면
                head = current;
                lists.add(head);
                current_level++;
            } else { //연결리스트에 연결
                head.next = current;
                head = current;
            }

            //자손 노드 큐에 삽입
            if (current.left != null) {
                q.enqueue(current.left);
                current.left.level = current_level + 1;
            }
            if (current.right != null) {
                q.enqueue(current.right);
                current.right.level = current_level + 1;
            }
        }
        return lists;
    }
}
