package Trees_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

//4.9 BST Sequences
// 배열의 원소를 왼쪽에서부터 차례로 트리에 삽입함으로써 이진 탐색 트리를 생성할 수 있다.
// 이진 탐색 트리 안에서 원소가 중복되지 않는다고 할 때 해당 트리를 만들어 낼 수 있는 가능한 배열을 모두 출력하라.
public class BSTsequences {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n3.left = n1;
        n3.right = n5;
        n5.left = n4;
        n5.right = n6;

        ArrayList<LinkedList<Integer>> arr = sol_allSequences(n3);
        for (int i = 0; i < arr.size(); i++) {

            System.out.println(arr.get(i));
        }
    }

    //Solution
    static ArrayList<LinkedList<Integer>> sol_allSequences(Node node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.data);

        //Recurse on left and right subtrees.
        ArrayList<LinkedList<Integer>> leftSeq = sol_allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = sol_allSequences(node.right);

        //Weave together each list from the left and right sides.
        //오른쪽 서브 트리의 모든 배열과 왼쪽 서브 트리의 모든 배열을 합침
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    //Weave lists together in all possible ways. This algorithm works by removing the head from one list,
    //recursing,
    //and then doing the same thing with the other list.
    static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                           ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        //One list is empty. Add remainder to [a cloned] prefix and store result.
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        //Recurse with head of first added to the prefix. Removing the head will damage first,
        //so we'll need to put it back where wh found it afterwards.
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        //Do the same thing with second, damaging and then restoring the list.
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }
}
