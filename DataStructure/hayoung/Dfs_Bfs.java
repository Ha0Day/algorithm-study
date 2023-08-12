package hayoung;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

class Queue<T> {
    class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> first;
    private Node<T> last;

    public void enqueue(T item) {
        Node<T> t = new Node<T>(item);

        if (last != null) {
            last.next = t;
        }
        last = t;
        if (first == null) {
            first = last;
        }
    }

    public T dequeue() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        T data = first.data;
        first = first.next;

        if (first == null) {
            last = null;
        }
        return data;
    }

    public T peek() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

}

class Graph {
    class Node {
        int data;
        LinkedList<Node> adjacent;
        boolean marked; //방문했는지 마킹

        Node(int data) {
            this.data = data;
            this.marked = false;
            adjacent = new LinkedList<Node>();
        }
    }

    Node[] nodes;   //노드를 저장할 배열

    Graph(int size) {
        nodes = new Node[size]; //간단한 구현을 위해 그래프 노드 수는 고정
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i); //편의를 위해 배열 인덱스와 data 일치시킴
        }
    }

    //두 노드의 관계를 저장하는 함수
    void addEdge(int i1, int i2) {
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        //인접 노드를 저장하는 linkedlist에 서로가 있는지 확인 후 저장
        if (!n1.adjacent.contains(n2)) {
            n1.adjacent.add(n2);
        }
        if (!n2.adjacent.contains(n1)) {
            n2.adjacent.add(n1);
        }
    }

    //스택으로 dfs 구현
    void dfs() {
        dfs(0); //dfs 그냥 호출하면 index 0 부터 시작하는 것으로 함
    }

    void dfs(int index) {
        Node root = nodes[index];
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.marked = true;

        while (!stack.isEmpty()) {
            Node r = stack.pop();
            for (Node n : r.adjacent) {
                if (n.marked == false) {
                    n.marked = true;
                    stack.push(n);
                }
            }
            visit(r);   //꺼낸 노드 출력
        }
        System.out.println();
    }

    //큐로 bfs 구현
    void bfs() {
        bfs(0);
    }

    void bfs(int index) {
        Node root = nodes[index];
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        root.marked = true;

        while (!queue.isEmpty()) {
            Node r = queue.dequeue();
            for (Node n : r.adjacent) {
                if (n.marked == false) {
                    n.marked = true;
                    queue.enqueue(n);
                }
            }
            visit(r);
        }
        System.out.println();
    }

    //재귀 호출을 이용한 dfs 구현
    //linkedlist가 노드의 주소를 갖기 때문에 재귀 호출을 할 때는 매개변수로 Node를 받아야 함
    void dfsR(Node r) {
        if (r == null) return;
        r.marked = true;
        //인접 노드 호출 전에 현재 노드 우선 출력
        visit(r);
        //호출된 적 없는 인접 노드 호출
        for (Node n : r.adjacent) {
            if (n.marked == false) {
                dfsR(n);
            }
        }
    }

    //시작 노드를 다양하게 test하기 위해 배열의 index를 받는 형태도 하나 선언
    //index를 받으면 해당 index의 노드를 시작으로 재귀 호출 진행
    void dfsR(int index) {
        Node r = nodes[index];
        dfsR(r);
    }

    void dfsR() {
        dfsR(0);
    }

    private void visit(Node n) {
        System.out.print(n.data + " ");
    }
}


/*

  0
 /
1 -- 3    7
|  / | \ /
| /  |  5
2 -- 4   \
          6 - 8

*/
public class Dfs_Bfs {
    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(4, 3);
        g.addEdge(3, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 8);

        Graph g2 = new Graph(9);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(1, 3);
        g2.addEdge(2, 4);
        g2.addEdge(2, 3);
        g2.addEdge(4, 3);
        g2.addEdge(3, 5);
        g2.addEdge(5, 6);
        g2.addEdge(5, 7);
        g2.addEdge(6, 8);

        Graph g3 = new Graph(9);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(1, 3);
        g3.addEdge(2, 4);
        g3.addEdge(2, 3);
        g3.addEdge(4, 3);
        g3.addEdge(3, 5);
        g3.addEdge(5, 6);
        g3.addEdge(5, 7);
        g3.addEdge(6, 8);

        g.dfs();
        g2.bfs();
        g3.dfsR();

    }
}
