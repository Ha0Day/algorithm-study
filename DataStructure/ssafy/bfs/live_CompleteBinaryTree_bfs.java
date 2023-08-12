package ssafy.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class live_CompleteBinaryTree_bfs<T> {

    private Object[] nodes;
    private int lastIndex; // 채워진 마지막 노드의 인덱스
    private final int SIZE; // 최대 노드의 개수

    public live_CompleteBinaryTree_bfs(int size) {
        this.SIZE = size;
        nodes = new Object[size + 1]; //인덱스 1부터 시작할 것이므로
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    public boolean isFull() {
        return lastIndex == SIZE;
    }

    public boolean add(T data) {
        if (isFull())
            return false;
        nodes[++lastIndex] = data;
        return true;
    }

    //------------------------------BFS
    public void bfs() {
        if (isEmpty())
            return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Queue<Integer> queue = new ArrayDeque<Integer>();
        // 탐색 시작의 대상부터 큐에 넣기
        queue.offer(1); // 루트노드의 인덱스

        while (!queue.isEmpty()) { // 탐색 대상이 있다면
            int current = queue.poll(); // 탐색 대상 큐에서 꺼내기
            // 탐색대상 방문처리
            System.out.println(nodes[current]);
            // 현재 탐색대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
            if (current * 2 <= lastIndex)
                queue.offer(current * 2); // 왼쪽 자식
            if (current * 2 + 1 <= lastIndex)
                queue.offer(current * 2 + 1); // 오른쪽 자식
        }
    }

    //레벨 확인할 수 있도록 breadth 변수 추가
    public void bfs2() {
        if (isEmpty())
            return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Queue<Integer> queue = new ArrayDeque<Integer>();
        // 탐색 시작의 대상부터 큐에 넣기
        queue.offer(1); // 루트노드의 인덱스

        int breadth = 0;
        while (!queue.isEmpty()) { // 탐색 대상이 있다면
            int size = queue.size();
            while (--size >= 0) {
                int current = queue.poll(); // 탐색 대상 큐에서 꺼내기
                // 탐색대상 방문처리
                System.out.print(nodes[current] + "\t");
                // 현재 탐색대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
                if (current * 2 <= lastIndex)
                    queue.offer(current * 2); // 왼쪽 자식
                if (current * 2 + 1 <= lastIndex)
                    queue.offer(current * 2 + 1); // 오른쪽 자식
            }
            System.out.println();
            System.out.println("=======================================" + breadth + "너비 탐색 완료");
            breadth++;
        }
    }

    //레벨 확인할 수 있도록 int[]을 큐에 삽입
    public void bfs3() {
        if (isEmpty())
            return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Queue<int[]> queue = new ArrayDeque<int[]>(); // int[] : {탐색노드의 인덱스, 레벨}
        // 탐색 시작의 대상부터 큐에 넣기
        queue.offer(new int[]{1, 0}); // 루트노드의 인덱스

        while (!queue.isEmpty()) { // 탐색 대상이 있다면
            int[] info = queue.poll();
            int current = info[0]; // 탐색 대상 큐에서 꺼내기
            // 탐색대상 방문처리
            System.out.println(nodes[current] + "//" + info[1]);
            // 현재 탐색대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
            if (current * 2 <= lastIndex)
                queue.offer(new int[]{current * 2, info[1] + 1}); // 왼쪽 자식
            if (current * 2 + 1 <= lastIndex)
                queue.offer(new int[]{current * 2 + 1, info[1] + 1}); // 오른쪽 자식
        }
    }

}