package StringManipulation;

import java.util.HashSet;
import java.util.Set;

//1.8 Zero Matrix
//MxN행렬의 한 원소가 0일 경우 해당 원소가 속한 행과 열의 모든 월소를 0으로 설정하는 알고리즘 작성
public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 2, 0, 4}, {8, 89, 2, 7}, {7, 8, 9, 10}, {9, 10, 11, 12}, {11, 1, 0, 0}};

        printMatrix(matrix);

        changeMatrix(matrix);
        System.out.println();

        printMatrix(matrix);
    }

    //Draft - O(NM)
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

    private static void printMatrix(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
