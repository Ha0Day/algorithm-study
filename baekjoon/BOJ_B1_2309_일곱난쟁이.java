import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_B1_2309_일곱난쟁이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[9];
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        boolean[] visited = new boolean[9];
        checkHeight(arr, visited);
    }

    static void checkHeight(int[] arr, boolean[] visited) {
        ArrayList<Integer> list = new ArrayList<>();
        checkHeight(arr, visited, list);
    }

    static void checkHeight(int[] arr, boolean[] visited, ArrayList<Integer> list) {

        if (list.size() == 7) {
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i);
            }
            if (sum == 100) {
                Collections.sort(list);
                for (int height : list) {
                    System.out.println(height);
                }
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(arr[i]);
                checkHeight(arr, visited, list);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
