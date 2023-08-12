package LinkedList;

//2.3 Delete Middle Node
//단방향 연결리스트가 주어졌을 때 중간에 있는 노드 하나를 삭제하는 알고리즘 구현
//단, 삭제할 노드에만 접근 가능
public class DeleteMiddleNode {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        sol_deleteMiddleNode(node5);

        while (node1 != null && node1.valid) {
            System.out.print(node1.data + " ");
            node1 = node1.next;
        }
    }

    //Solution
    //삭제할 노드에만 적근할 수 있기 때문에,
    //다음 노드의 데이터를 현재 노드에 복사한 다음에 다음 노드를 지우는 방법

    //삭제할 노드가 리스트의 마지막 노드인 경우 풀 수 없음
    //이 경우 어떻게 처리할지는 다양하지만
    //여기서는 우선 Node 클래스에 valid라는 boolean 변수를 추가하고
    //마지막 노드의 경우 valid하지 않다고 처리하고
    //valid하지 않은 노드는 main함수에서 출력하지 않음
    private static void sol_deleteMiddleNode(Node deleteNode) {
        if (deleteNode == null || deleteNode.next == null) {
            deleteNode.valid = false;
            return;
        }
        //deleteNode는 참조형 변수이므로 메모리 주소를 저장. 값 자체를 바꿀 수 없음
        //따라서 deleteNode의 content를 바꿈
        deleteNode.data = deleteNode.next.data;
        deleteNode.next = deleteNode.next.next;
    }
}
