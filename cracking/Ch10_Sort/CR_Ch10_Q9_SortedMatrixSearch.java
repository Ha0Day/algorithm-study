package Ch10_Sort;

//10.9 Sorted Matrix Search
// 각 행과 열이 오름차순으로 정렬된 MxN 행렬이 주어졌을 때, 특정한 원소를 찾는 메서드를 구현하라.
public class CR_Ch10_Q9_SortedMatrixSearch {
    static int N, M;

    public static void main(String[] args) {

        int[][] map = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        N = map.length;
        M = map[0].length;

        Point answer = search(map, 30);
        System.out.println("(" + answer.x + ", " + answer.y + ")");
    }

    //O(N*logM)
    public static Point search(int[][] map, int target) {

        for (int i = 0; i < N; i++) {
            if (map[i][0] > target || map[i][M - 1] < target) {
                continue;
            }

            Point p = binarySearch(i, map, target);
            if (p == null) {
                continue;
            }
            return p;
        }
        return null;
    }

    public static Point binarySearch(int i, int[][] map, int target) {

        int start = 0;
        int end = M - 1;

        int index = binarySearch(start, end, map[i], target);
        if (index != -1) {
            return new Point(i, index);
        }
        return null;
    }

    public static int binarySearch(int start, int end, int[] map, int target) {
        int mid;

        if (start > end) {
            return -1;
        }

        while (start <= end) {
            mid = (start + end) / 2;
            if (map[mid] == target) {
                return mid;
            } else if (map[mid] < target) {
                return binarySearch(mid + 1, end, map, target);
            } else {
                return binarySearch(start, mid - 1, map, target);
            }
        }
        return -1;
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
