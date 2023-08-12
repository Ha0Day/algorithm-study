package Ch8_DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//8.13 Stack of Boxes
// 너비 w, 높이 h, 깊이 d인 박스 n개가 있다.
// 상자는 회전시킬 수 없으며, 다른 상자 위에 올려 놓을 수 있다.
// 단, 아래 놓인 상자의 너비, 높이, 깊이가 위에 놓인 상자의 너비, 높이, 깊이보다 더 클 때에만 가능하다.
// 이 상자들로 쌓을 수 있는 가장 높은 탑을 구하는 메서드를 작성하라.
// 탑의 높이는 탑을 구성하는 모든 상자 높이의 합이다.
public class CR_Ch8_Q13_StackOfBoxes {
    public static void main(String[] args) {
        ArrayList<Box> boxes = new ArrayList<>();

        Box b1 = new Box(2, 5, 3);
        Box b2 = new Box(3, 4, 8);
        Box b3 = new Box(2, 3, 7);
        Box b4 = new Box(4, 4, 10);
        Box b5 = new Box(1, 2, 4);
        Box b6 = new Box(1, 10, 9);

        boxes.add(b1);
        boxes.add(b2);
        boxes.add(b3);
        boxes.add(b4);
        boxes.add(b5);
        boxes.add(b6);

        System.out.println(sol_createStack2(boxes));

    }

    //Solution1
    static int sol_createStack(ArrayList<Box> boxes) {
        //높이를 기준으로 내림차순으로 정렬하기
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        int[] stackMap = new int[boxes.size()]; // 캐시. stackMap[i]는 상자 i를 바닥에 놓았을 때 가장 높게 쌓을 수 있는 높이를 의미
        for (int i = 0; i < boxes.size(); i++) {
            int height = sol_createStack(boxes, i, stackMap);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }

    static int sol_createStack(ArrayList<Box> boxes, int bottomIndex, int[] stackMap) {
        if (bottomIndex < boxes.size() && stackMap[bottomIndex] > 0) {
            return stackMap[bottomIndex];
        }
        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = sol_createStack(boxes, i, stackMap);
                maxHeight = Math.max(height, maxHeight);
            }
        }
        maxHeight += bottom.h;
        stackMap[bottomIndex] = maxHeight;
        return maxHeight;
    }


    //Solution2
    // 재귀 알고리즘의 각 단계에서 실제 박스를 스택에 쌓는 것이 아니라 선택만 하게 함
    // i번 상자를 바닥에 놓고 시작하는 재귀와 i번 상자를 바닥에 놓지 않고 시작하는 재귀를 수행
    // 둘 중 더 높이가 높은 경우를 반환
    static int sol_createStack2(ArrayList<Box> boxes) {
        //높이를 기준으로 상자를 내림차순 정렬
        Collections.sort(boxes, new BoxComparator());
        int[] stackMap = new int[boxes.size()];
        return sol_createStack2(boxes, null, 0, stackMap);
    }

    static int sol_createStack2(ArrayList<Box> boxes, Box bottom, int offset, int[] stackMap) {
        if (offset >= boxes.size()) return 0;  //초기 사례
        //현재 상자가 바닥일 때의 높이
        Box newBottom = boxes.get(offset);
        int heightWithBottom = 0;
        if (bottom == null || newBottom.canBeAbove(bottom)) {
            if (stackMap[offset] == 0) {
                stackMap[offset] = sol_createStack2(boxes, newBottom, offset + 1, stackMap);
                stackMap[offset] += newBottom.h;
            }
            heightWithBottom = stackMap[offset];
        }

        //바닥이 아닐 때
        int heightWithoutBottom = sol_createStack2(boxes, bottom, offset + 1, stackMap);
        //둘 중 더 나은 것을 반환
        return Math.max(heightWithoutBottom, heightWithBottom);
    }
}


class Box {
    int w, h, d;

    Box(int w, int h, int d) {
        this.w = w;
        this.h = h;
        this.d = d;
    }

    boolean canBeAbove(Box box) {
        if (box.w > this.w && box.h > this.h && box.d > this.d) {
            return true;
        }
        return false;
    }
}

class BoxComparator implements Comparator<Box> {
    public int compare(Box x, Box y) {
        return y.h - x.h;
    }
}