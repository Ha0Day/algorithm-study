package Trees_Graphs;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

//4.1 Route Between Nodes
// 방향 그래프가 주어졌을 때 두 노드 사이에 경로가 존재하는지 확인하는 알고리즘을 작성하라
public class RouteBetweenNodes {

    public static void main(String[] args) {
        Graph g = new Graph(10);
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

        //marked 되기 때문에 하나씩 test해야 올바른 결과가 출력됨
        //System.out.println(g.pathExistsByDfs(0, 9)); //false
        //System.out.println(g.pathExistsByDfsR(3, 1)); //false
        //System.out.println(g.pathExistsByBfs(0, 8)); //true
        //System.out.println(g.pathExistsByBfs2(2, 7)); //true
    }
}

class Graph {

    class Node {
        private int data;
        boolean marked;
        public LinkedList<Node> adjacent;

        Node(int data) {
            this.data = data;
            marked = false;
            adjacent = new LinkedList<>();
        }
    }

    Node[] nodes;

    Graph(int size) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
    }

    void addEdge(int index1, int index2) {
        nodes[index1].adjacent.add(nodes[index2]);
    }

    /*
    <DFS - Stack을 이용한 구현>
    1) stack을 만든다
    2) 시작할 노드를 우선 stack에 넣는다 (그래프이므로 루트 노드 없음. 정하기 나름. 시작 노드에 따라 결과는 다름)
    3) stack에서 노드를 꺼내고 꺼낸 노드의 자식 노드들을 stack에 넣는다 (여기서 자식 노드는 그래프 상에서 간선으로 연결된 노드들)
    4) 한번 stack에 들어갔던 노드는 다시 넣지 않는다
    */
    boolean pathExistsByDfs(int startIndex, int lastIndex) {
        Node start = nodes[startIndex];
        Node end = nodes[lastIndex];
        Stack<Node> stack = new Stack();

        start.marked = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            Node r = stack.pop();
            if (r.equals(end)) return true;
            for (Node n : r.adjacent) {
                if (n.marked == false) {
                    n.marked = true;
                    stack.push(n);
                }
            }
        }
        return false;
    }


    /*
    <DFS - 재귀 호출을 이용한 구현>
    1) 노드에 방문하면 data를 출력
    2) 자식 노드를 순서대로 재귀 호출
    3) 자식 노드가 호출을 받으면 마찬가지로 1) - 2) 수행
    */
    boolean pathExistsByDfsR(int startIndex, int lastIndex) {
        Node start = nodes[startIndex];
        Node end = nodes[lastIndex];

        start.marked = true;

        if (start.equals(end)) return true;
        for (Node n : start.adjacent) {
            if (n.marked == false && pathExistsByDfsR(n.data, lastIndex)) {
                return true;
            }
        }
        return false;
    }

    /*
    <BFS - Queue을 이용한 구현>
    1) queue를 만든다
    2) 시작할 노드를 우선 queue에 넣는다 (그래프이므로 루트 노드 없음. 정하기 나름. 시작 노드에 따라 결과는 다름)
    3) queue에서 노드를 꺼내고 꺼낸 노드의 자식 노드들을 queue에 넣는다 (여기서 자식 노드는 그래프 상에서 간선으로 연결된 노드들)
    4) 한번 queue에 들어갔던 노드는 다시 넣지 않는다
    */
    boolean pathExistsByBfs(int startIndex, int lastIndex) {
        Node start = nodes[startIndex];
        Node end = nodes[lastIndex];
        Queue<Node> queue = new Queue();

        start.marked = true;
        queue.enqueue(start);

        //if문을 for문 밖에 위치. 큐에서 제거할 때 확인 절차
        while (!queue.isEmpty()) {
            Node r = queue.dequeue();
            if (r.equals(end)) return true;
            for (Node n : r.adjacent) {
                if (n.marked == false) {
                    n.marked = true;
                    queue.enqueue(n);
                }
            }
        }
        return false;
    }

    //Solution
    //위 bfs와 거의 동일
    boolean pathExistsByBfs2(int startIndex, int lastIndex) {

        //두 노드가 같으면 바로 true 반환
        if (startIndex == lastIndex) {
            return true;
        }

        Node start = nodes[startIndex];
        Node end = nodes[lastIndex];
        Queue<Node> queue = new Queue();

        start.marked = true;
        queue.enqueue(start);

        //if문을 for문 안에 위치. 인접 노드 큐에 삽입 전에 확인 절차
        while (!queue.isEmpty()) {
            Node r = queue.dequeue();
            for (Node n : r.adjacent) {
                if (n.marked == false) {
                    if (n.equals(end)) {
                        return true;
                    } else {
                        n.marked = true;
                        queue.enqueue(n);
                    }
                }
            }
        }
        return false;
    }
}

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

