package Sort;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

//10.10 스트림에서의 순위
// 정수 스트림을 읽는다고 하자.
// 주기적으로 어떤 수 x의 랭킹 (x보다 같거나 작은 수의 개수)을 확인하고 싶다.
// 해당 연산을 지원하는 자료구조와 알고리즘을 구현하라.
// 수 하나를 읽을 때마다 호출되는 메서드 track(int x)와
// x보다 같거나 작은 수의 개수(x 자신은 제외)를 반환하는메서드 getRankOfNumber(int x)를 구현하면 된다.
//
// 예) 입력 스트림 : 5, 1, 4, 4, 5, 9, 7, 13, 3
// getRankOfNumber(1)=0
// getRankOfNumber(3)=1
// getRankOfNumber(4)=3

public class RankFromStream {
    static Node root;

    public static void main(String[] args) throws IOException {

        byte[] bytes = {5, 1, 4, 4, 5, 9, 7, 13, 3};

        InputStream is = new ByteArrayInputStream(bytes);
        int num = 0;

        while ((num = is.read()) != -1) {
            track(num); // 다음 노드 저장
        }
        System.out.println(getRankOfNumber(1));
        System.out.println(getRankOfNumber(3));
        System.out.println(getRankOfNumber(4));


    }

    // 자료구조에 element 삽입
    static void track(int x) {
        if (root == null) {
            root = new Node(x);
        } else {
            root.insert(x);
        }
    }

    static int getRankOfNumber(int x) {
        return root.getRank(x);
    }

}

class Node {
    public int left_size = 0;
    public Node left, right;
    public int data;

    public Node(int data) {
        this.data = data;
    }

    public void insert(int data) {
        if (data < this.data) {
            if (left != null) {
                left.insert(data);
            } else {
                left = new Node(data);
                left_size++;
            }
        } else {
            if (right != null) {
                right.insert(data);
            } else {
                right = new Node(data);
            }
        }
    }

    public int getRank(int data) {
        if (data == this.data) {
            return left_size;
        } else if (data < this.data) { //왼쪽
            if (left == null) {
                return -1; //트리 안에 해당 데이터가 존재하지 않음
            } else {
                return left.getRank(data);
            }
        } else { //오른쪽
            int right_rank = right == null ? -1 : right.getRank(data);
            if (right_rank == -1) return -1;
            else return left_size + 1 + right_rank;
        }
    }

}
