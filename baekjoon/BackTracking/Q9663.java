package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663 {
    static int N;
    static int count;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];   //인덱스가 열, 값이 행
        count = 0;

        nQueen(0);
        System.out.print(count);
    }

    private static void nQueen(int col) {
        if (col == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[col] = i;
            if (check(col)) {
                nQueen(col + 1);
            }
        }
    }

    static boolean check(int col) {
        for (int i = 0; i < col; i++) {
            //같은 행에 위치한 경우
            if (arr[i] == arr[col]){
                return false;
            }
            //대각선에 놓여 있는 경우
            // 열의 차와 행의 차가 같을 경우가 대각선에 놓여있는 경우다
            else if (Math.abs(i - col) == Math.abs(arr[i] - arr[col])){
                return false;
            }
        }
        return true;
    }
}
