package Ch4_Trees_Graphs;

//4.6 Successor
// 이진 탐색 트리에서 주어진 노드의 '다음' 노드(중위 후속자)를 찾는 알고리즘을 작성하라.
// 각 노드에는 부모 노드를 가리키는 링크가 존재한다고 가정하자.
public class Successor {
    public static void main(String[] args) {
        Node n20 = new Node(20);
        Node n5 = new Node(5);
        Node n3 = new Node(3);
        Node n15 = new Node(15);
        Node n10 = new Node(10);
        Node n17 = new Node(17);
        Node n50 = new Node(50);
        Node n30 = new Node(30);
        Node n40 = new Node(40);

        n20.left = n5;
        n20.right = n50;
        n5.left = n3;
        n5.right = n15;
        n15.left = n10;
        n15.right = n17;
        n50.left = n30;
        n30.right = n40;

        n3.parent = n5;
        n10.parent = n15;
        n17.parent = n15;
        n15.parent = n5;
        n5.parent = n20;
        n40.parent = n30;
        n30.parent = n50;
        n50.parent = n20;

        System.out.println(findSuccessor(n15).data);
    }

    //Draft
    //시간복잡도 O(N)
    static Node findSuccessor(Node node) {
        if (node.right != null) {
            Queue<Node> q = new Queue<>();
            inorder(node.right, q);
            return q.dequeue();
        }
        if (node.parent == null) {
            return null;
        }
        //left child
        if (node.parent.left == node) return node.parent;

        //right child
        while (node.parent.right == node) {
            node = node.parent;
        }
        if (node == null) return null;

        return node.parent;
    }

    static void inorder(Node node, Queue<Node> q) {
        if (node != null) {
            inorder(node.left, q);
            q.enqueue(node);
        }
    }
}

