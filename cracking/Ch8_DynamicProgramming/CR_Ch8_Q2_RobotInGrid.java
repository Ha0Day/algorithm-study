package DynamicProgramming;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;

//8.2 Robot in a Grid
// 행의 개수는 r, 열의 개수는 c인 격자판의 왼쪽 상단 꼭짓점에 로봇이 놓여 있다.
// 이 로봇은 오른쪽 아니면 아래쪽으로만 이동할 수 있다.
// 하지만 어떤 셀은 '금지 구역'으로 지정되어 있어서 로봇이 접근할 수 없다.
// 왼쪽 상단 꼭짓점에서 오른쪽 하단 꼭짓점으로의 경로를 찾는 알고리즘을 설계하라.
public class Robot_in_a_Grid {
    public static void main(String[] args) {
        int c = 4;
        int r = 5;

        boolean[][] limit = {
                {true, true, true, true},
                {true, true, false, true},
                {false, true, true, false},
                {true, true, true, true}
        };

        ArrayList<Point> list = getPath(limit);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("(" + list.get(i).x + ", " + list.get(i).y + ")");
        }
    }

    //Brute Force
    //시간 복잡도 O(2^(r+c))
    //DP (Top-Down) - Brute Force 코드에 캐시만 추가
    //시간 복잡도 O(r*c)
    static ArrayList<Point> getPath(boolean[][] limit) {
        ArrayList<Point> list = new ArrayList<>();
        HashSet<Point> set = new HashSet<>();   //invalid한 path에 포함된 모든 point
        if (pathExists(limit, list, new Point(limit.length - 1, limit[0].length - 1), set)) {
            return list;
        }
        return null;
    }

    static boolean pathExists(boolean[][] limit, ArrayList<Point> list, Point point, HashSet<Point> set) {
        //invalid한 경우
        if (set.contains(point)) {
            return false;
        }

        //경계 밖으로 가거나, 현재 위치가 false인 경우
        if (point.x < 0 || point.y < 0 || (!limit[point.x][point.y])) {
            return false;
        }

        //끝까지 완주한 경우
        if (point.x == 0 && point.y == 0) {
            list.add(new Point(point.x, point.y));
            return true;
        }


        if (pathExists(limit, list, new Point(point.x - 1, point.y), set) || pathExists(limit, list, new Point(point.x, point.y - 1), set)) {
            list.add(new Point(point.x, point.y));
            return true;
        }

        set.add(point); //invalid한 포인트 추가
        return false;
    }
}


class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
