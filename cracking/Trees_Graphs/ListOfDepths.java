package Trees_Graphs;

import java.sql.Array;
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

        System.out.println();

        ArrayList<LinkedList<Node>> lists = sol_createLevelLinkedList(n1);
        LinkedList<Node> list2;

        for (int i = 0; i < lists.size(); i++) {
            list2 = lists.get(i);
            for (int j = 0; j < list2.size(); j++) {
                System.out.print(list2.get(j).data + " ");
            }
            System.out.println();
        }
    }

    //Draft code
    //BFS 활용하여 풀이
    //시간 복잡도 O(N)
    //공간 복잡도 O(N)
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

    //Solution1 - 깊이 우선 탐색
    //어떤 방법으로 순회하든 상관 없음. 현재 탐색 중인 노드의 깊이만 알 수 있으면 됨
    //여기서는 전위 순회 알고리즘을 살짝 변형하여 풀이
    //재귀 함수 호출 시 level+1을 인자로 넘김
    //시간 복잡도 O(N)
    //공간 복잡도 O(N)
    //   - (균형 잡힌 트리의 경우) O(logN)만큼의 재귀 호출. 새로운 깊이를 탐색할 때마다 스택 사용.
    //   - 그러나 결국 O(N)만큼의 데이터 반환해야 함.
    static void createLevelLinkedList(Node root, ArrayList<LinkedList<Node>> lists, int level) {
        if (root == null) return;    //base condition

        LinkedList list = null;
        if (lists.size() == level) {    //리스트에 해당 레벨이 없다.
            list = new LinkedList();
            /*
            깊이가 증가하는 순서로 순회했다는 사실에 유의하자.
            따라서 #i를 처음 마주쳤다면, 0부터 i-1번째까지는 이전에 이미 lists에 추가되어야 한다.
            따라서 새로운 깊이 #i를 lists의 끝에 추가해도 안전하다.
            */
            lists.add(list);
        } else {
            list = lists.get(level);
        }

        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    static ArrayList<LinkedList<Node>> sol_createLevelLinkedList(Node root) {
        ArrayList<LinkedList<Node>> lists = new ArrayList<>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    //Solution2 - 너비 우선 탐색
    //i번째 깊이에 어떤 노드들이 있는지 알아내려면, i-1번째 깊이에 있는 노드의 모든 자식 노드를 검사
    //시간 복잡도 O(N)
    //공간 복잡도 O(N)
    //   - 순환적으로 구현하여 추가 공간 요구하지 않음.
    //   - 그러나 결국 O(N)만큼의 데이터 반환해야 함.
    ArrayList<LinkedList<Node>> sol_createLevelLinkedList2(Node root) {
        ArrayList<LinkedList<Node>> result = new ArrayList<>();
        //루트 방문
        LinkedList current = new LinkedList();
        if (root != null) {
            current.add(root);
        }

        while (current.size() > 0) {
            result.add(current);    //이전 깊이 추가
            LinkedList<Node> parents = current; //다음 깊이로 진행
            current = new LinkedList();
            for (Node parent : parents) {
                //자식 노드들 방문
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
    }
}
