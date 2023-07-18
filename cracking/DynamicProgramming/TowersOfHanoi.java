package DynamicProgramming;

import java.util.Stack;

//8.6 하노이 타워
// 전형적인 하노이타원에서는 크기가 서로 다른 N개의 원반을 세 개의 기둥 중 아무 곳으로나 옮길 수 있다
// 스택을 사용하여 모든 원반을 첫 번째 기둥에서 마지막 기둥으로 옮기는 프로그램을 작성하라
public class TowersOfHanoi {
    public static void main(String[] args) {
        int n = 8;

        Tower t1 = new Tower();
        for (int i = n; i >= 1; i--) {
            t1.st.push(i);
        }

        Tower t2 = new Tower();
        Tower t3 = new Tower();

        hanoiTower(n, t1, t2, t3);

        t3.printTower();
    }

    static void hanoiTower(int n, Tower t1, Tower t2, Tower t3) {
        if (n == 0) {
            return;
        }
        hanoiTower(n - 1, t1, t3, t2);
        t1.moveDiscTo(t3);
        hanoiTower(n - 1, t2, t1, t3);
    }
}

class Tower {
    Stack<Integer> st;

    Tower() {
        st = new Stack<>();
    }

    void moveDiscTo(Tower t) {
        t.st.push(st.pop());
    }

    void printTower() {
        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }
    }
}
