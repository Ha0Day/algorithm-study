package Trees_Graphs;

//2.2 Minimal Tree
// 오름차순으로 정렬된 배열이 있다.
// 이 배열 안에 들어 있는 원소는 정수이며 중복된 값이 없다고 했을 때 높이가 최소가 되는 이진 탐색 트리를 만드는 알고리즘을 작성하라
public class MinimalTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        inorder(toBST(arr));
    }

    //Solution
    //높이가 최소가 되려면 왼쪽 서브트리와 오른쪽 서브트리의 노드 수를 가능하면 같게 맞춰야 한다.
    //즉, 루트 노드가 배열의 중앙에 오도록 해야 한다. (= 트리 원소 가운데 절반은 루트보다 작고 나머지 절반은 루트보다 커야 한다)
    //배열의 일부가 주어졌을 때, 해당 배열로 만들 수 있는 최소 높이 트리의 루트를 반환하는 메서드를 재귀적으로 사용
    /* 알고리즘
       1. 배열 가운데 원소를 트리에 삽입한다.
       2. 왼쪽 서브트리에 왼쪽 절반 배열 원소들을 삽입한다.
       3. 오른쪽 서브트리에 오른쪽 절반 배열 원소들을 삽입한다.
       4. 재귀 호출을 실행한다.
    */

    static Node toBST(int[] arr) {
        return addNode(0, arr.length - 1, arr);
    }

    static Node addNode(int startIndex, int lastIndex, int[] arr) {
        if (lastIndex < startIndex) {
            return null;
        }
        int middleIndex = (startIndex + lastIndex) / 2;
        Node root = new Node(arr[middleIndex]);
        root.left = addNode(startIndex, middleIndex - 1, arr);
        root.right = addNode(middleIndex + 1, lastIndex, arr);

        return root;
    }

    public static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.data);
            inorder(node.right);
        }
    }
}
