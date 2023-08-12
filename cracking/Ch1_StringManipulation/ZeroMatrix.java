package Ch1_StringManipulation;

import java.util.HashSet;
import java.util.Set;

//1.8 Zero Matrix
//MxN행렬의 한 원소가 0일 경우 해당 원소가 속한 행과 열의 모든 월소를 0으로 설정하는 알고리즘 작성
public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 2, 0, 4}, {8, 89, 2, 7}, {7, 8, 9, 10}, {9, 10, 11, 12}, {11, 1, 0, 0}};

        printMatrix(matrix);

        //changeMatrix(matrix);
        sol_setZeros2(matrix);
        System.out.println();

        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Draft
    //Time Complexity : O(NM)
    //Space Complexity : O(M+N)
    private static int[][] changeMatrix(int[][] matrix) {
        Set iSet = new HashSet();
        Set jSet = new HashSet();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    iSet.add(i);
                    jSet.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (!iSet.contains(i)) continue;
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            if (!jSet.contains(j)) continue;
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }


    //Solution1
    //Time Complexity : O(NM)
    //Space Complexity : O(M+N)
    private static void sol_setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];
        //값이 0인 행과 열의 인덱스를 저장한다.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        //행의 원소를 전부 0으로 바꾼다.
        for (int i = 0; i < matrix.length; i++) {
            if (row[i]) nullifyRow(matrix, i);
        }

        //열의 원소를 전부 0으로 바꾼다.
        for (int j = 0; j < matrix[0].length; j++) {
            if (column[j]) nullifyColumn(matrix, j);
        }
    }

    private static void nullifyRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }

    private static void nullifyColumn(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }

    //Solution2
    //Time Complexity : O(NM)
    //Space Complexity : O(1)
    //첫 번째 행을 row 배열로, 첫 번째 열을 col 배열로 사용하면 공간 효율을 O(1)으로 낮출 수 있다.
    private static void sol_setZeros2(int[][] matrix) {
        boolean rowHasZero = false;
        boolean colHasZero = false;

        //첫 번째 행에 0이 있는지 확인
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowHasZero = true;
                break;
            }
        }

        //첫 번째 열에 0이 있는지 확인
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }

        //나머지 배열에 0이 있는지 확인
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //첫 번째 열을 이용해서 행을 0으로 바꾼다.
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i);
            }
        }

        //첫 번째 행을 이용해서 행을 0으로 바꾼다.
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                nullifyColumn(matrix, j);
            }
        }

        //첫 번째 행을 0으로 바꾼다.
        if (rowHasZero) {
            nullifyRow(matrix, 0);
        }

        //첫 번째 열을 0으로 바꾼다.
        if (colHasZero) {
            nullifyColumn(matrix, 0);
        }
    }
}
