package ssafy.dfs;

public class live_CompleteBinaryTreeTest_dfs {
    public static void main(String[] args) {

        int size = 9;
        live_CompleteBinaryTree_dfs<Character> tree = new live_CompleteBinaryTree_dfs<>(size);

        for (int i = 0; i < size; i++) {
            tree.add((char) (65 + i));
        }

        //루트노드부터
        tree.dfsByPreOrder(1); //전위순회
        System.out.println();
        tree.dfsByInOrder(1); //중위순회
        System.out.println();
        tree.dfsByPostOrder(1); //후위순회
        System.out.println();

        tree.dfs(); // 스택을 사용한 dfs. 전위순회인데 자식들의 순서만 뒤집어짐
    }
}