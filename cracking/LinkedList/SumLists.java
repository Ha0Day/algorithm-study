package LinkedList;

//2.5 Sum Lists
//연결리스트로 숫자를 표현할 때 각 노드가 자릿수 하나를 가리키는 방식으로 표현할 수 있다.
//각 숫자는 역순으로 배열되어 있다.
//즉, 첫 번째 자릿수가 리스트의 맨 앞에 위치하도록 배열
//이런 숫자 2개가 있을 때. 이 두 수를 더하여 그 합을 연결리스트로 반환하는 함수 작성
//예) 입력 : (7-1-6) + (5-9-2) / 출력 : 2-1-9
//연관문제) 각 자릿수가 정상적으로 배열된다고 가정하고 같은 문제 풀어보기
public class SumLists {
    public static void main(String[] args) {
        Node first1 = new Node(7);
        Node first2 = new Node(1);
        Node first3 = new Node(6);

        Node second1 = new Node(5);
        Node second2 = new Node(9);
        Node second3 = new Node(2);

        first1.next = first2;
        first2.next = first3;

        second1.next = second2;
        second2.next = second3;


        //Node node = sol_addLists(first1, second1, 0);
        Node node = sol_addLists2(first1, second1);

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    //Solution
    //Time Complexity - O(N)
    //Space Complexity - O(N)
    private static Node sol_addLists(Node l1, Node l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        Node result = new Node();
        int value = carry;
        if (l1 != null) {
            value += l1.data;
        }
        if (l2 != null) {
            value += l2.data;
        }

        result.data = value % 10;

        //재귀
        if (l1 != null || l2 != null) {
            Node more = sol_addLists(l1 == null ? null : l1.next,
                    l2 == null ? null : l2.next,
                    value >= 10 ? 1 : 0);

            result.next = more;
        }
        return result;
    }

    //Solution - 연관문제
    //point1) 두 리스트의 길이를 비교해 짧은 리스트 앞에 0을 메꿔 넣어야 함
    //point2) 재귀 함수의 반환 값을 tail에 붙였던 앞 문제와 다르게 이번엔 head에 붙여야 함
    //부분합이라는 래퍼 클래스 사용하여 carry를 넘김
    static class PartialSum {
        public Node sum = null;
        public int carry = 0;
    }

    static int length(Node node) {
        int index = 0;
        while (node != null) {
            index = index + 1;
            node = node.next;
        }
        return index;
    }

    static Node sol_addLists2(Node l1, Node l2) {
        int len1 = length(l1);
        int len2 = length(l2);

        //짧은 리스트에 0을 붙인다.
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        //두 리스트를 더한다.
        PartialSum sum = addListsHelper(l1, l2);

        //넘김수(carry)가 존재한다면 리스트의 앞쪽에 삽입한다. 그렇지 않다면 연결리스트만 반환
        if (sum.carry == 0) {
            return sum.sum;
        } else {
            Node result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    static PartialSum addListsHelper(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
        //작은 자릿수를 재귀적으로 더한다.
        PartialSum sum = addListsHelper(l1.next, l2.next);

        //현재 값에 넘김수를 더한다.
        int val = sum.carry + l1.data + l2.data;

        //현재 자릿수를 합한 결과를 삽입한다.
        Node full_result = insertBefore(sum.sum, val % 10);

        //지금까지의 합과 넘김수를 반환한다.
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }

    //리스트 앞에 0을 추가한다.
    static Node padList(Node l, int padding) {
        Node head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    //연결리스트 앞에 노드를 삽입하기 위한 도움 함수
    static Node insertBefore(Node list, int data) {
        Node node = new Node(data);
        if (list != null) {
            node.next = list;
        }
        return node;
    }
}
