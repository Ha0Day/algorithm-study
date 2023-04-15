package LinkedList;

public class Node {
    public int data;
    public Node next;
    public boolean valid;

    public Node(){
        //this.data = -1;
        //this.next = null;
        //this.valid = true;
    }

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.valid = true;
    }
}
