package Ch1_StringManipulation;

//1.7 Rotate Matrix
//이미지를 표현하는 NxN 행렬
//이미지의 각 픽셀은 4바이트로 표현됨
//이미지를 90도 회전시키는 메서드를 작성
//행렬을 추가로 사용하지 않고
public class RotateMatrix {

    static int[][] matrix = new int[][]{{1, 2, 3, 4, 5}, {4, 5, 6, 7, 8}, {7, 8, 9, 10, 11}, {9, 10, 11, 12, 13}, {11, 12, 13, 14, 15}};

    public static void main(String[] args) {

        //회전 전
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();

        //회전 후
        if (sol_rotate(matrix)) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(matrix[i][j] + "\t");
                }
                System.out.println();
            }
        }

    }

    //No Draft
    //Solution - O(N^2)
    private static boolean sol_rotate(int[][] matrix) {

        if (matrix.length == 0 || matrix.length != matrix[0].length) return false;

        int n = matrix.length;

        //레이어 단위로 회전
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            //해당 레이어의 픽셀 하나하나
            for (int i = first; i < last; i++) {
                int offset = i - first;

                //위쪽 저장
                int top = matrix[first][i];
                //왼쪽 -> 위쪽
                matrix[first][i] = matrix[last - offset][first];
                //아래쪽 -> 왼쪽
                matrix[last - offset][first] = matrix[last][last - offset];
                //오른쪽 -> 아래쪽
                matrix[last][last - offset] = matrix[i][last];
                //위쪽 -> 오른쪽
                matrix[i][last] = top;
            }
        }
        return true;
    }
}
