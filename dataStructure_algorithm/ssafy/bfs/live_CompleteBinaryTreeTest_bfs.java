package ssafy.bfs;

public class live_CompleteBinaryTreeTest_bfs {
    public static void main(String[] args) {

        int size = 9;
        live_CompleteBinaryTree_bfs<Character> tree = new live_CompleteBinaryTree_bfs<>(size);

        for (int i = 0; i < size; i++) {
            tree.add((char) (65 + i));
        }

        tree.bfs();
        System.out.println();
        tree.bfs2();
        System.out.println();
        tree.bfs3();
        System.out.println();
    }
}