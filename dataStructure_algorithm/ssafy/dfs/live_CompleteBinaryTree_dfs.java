package ssafy.dfs;

import java.util.Stack;

public class live_CompleteBinaryTree_dfs<T> {

    private Object[] nodes;
    private int lastIndex; // 채워진 마지막 노드의 인덱스
    private final int SIZE; // 최대 노드의 개수

    public live_CompleteBinaryTree_dfs(int size) {
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

    //------------------------------DFS
    // 전위순회
    public void dfsByPreOrder(int current) { // 현재 노드를 전위순회로 탐색!!
        // 탐색대상 방문처리
        System.out.print(nodes[current]);
        // 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색시키기
        if (current * 2 <= lastIndex) // 오른쪽 자식
            dfsByPreOrder(current * 2);
        if (current * 2 + 1 <= lastIndex) // 왼쪽 자식
            dfsByPreOrder(current * 2 + 1);

    }

    // 중위순회
    public void dfsByInOrder(int current) { // 현재 노드를 전위순회로 탐색!!
        // 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색시키기
        if (current * 2 <= lastIndex) // 오른쪽 자식
            dfsByInOrder(current * 2);
        // 탐색대상 방문처리
        System.out.print(nodes[current]);
        if (current * 2 + 1 <= lastIndex) // 왼쪽 자식
            dfsByInOrder(current * 2 + 1);

    }

    // 후위순회
    public void dfsByPostOrder(int current) { // 현재 노드를 전위순회로 탐색!!
        // 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 재귀 호출로 탐색시키기
        if (current * 2 <= lastIndex) // 오른쪽 자식
            dfsByPostOrder(current * 2);
        if (current * 2 + 1 <= lastIndex) // 왼쪽 자식
            dfsByPostOrder(current * 2 + 1);
        // 탐색대상 방문처리
        System.out.print(nodes[current]);

    }

    public void dfs() {
        if (isEmpty())
            return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Stack<Integer> stack = new Stack<Integer>();
        // 탐색 시작의 대상부터 스택에 넣기
        stack.push(1); // 루트노드의 인덱스

        while (!stack.isEmpty()) { // 탐색 대상이 있다면
            int current = stack.pop(); // 탐색 대상 스택에서 꺼내기
            // 탐색대상 방문처리
            System.out.print(nodes[current]);
            // 현재 탐색대상을 통해 탐색해야 할 새로운 대상을 스택에 넣기
            if (current * 2 <= lastIndex)
                stack.push(current * 2); // 왼쪽 자식
            if (current * 2 + 1 <= lastIndex)
                stack.push(current * 2 + 1); // 오른쪽 자식
        }
    }
}