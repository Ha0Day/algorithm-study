package Trees_Graphs;

public class Node {
    int data;
    int level;
    Node left;
    Node right;
    Node next;
    Node parent;

    Node(int data) {
        this.data = data;
    }

    Node(int data, Node parent) {
        this.data = data;
        this.parent = parent;
    }
}
