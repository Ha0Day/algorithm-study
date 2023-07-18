import java.io.*;
import java.util.StringTokenizer;

public class Q10163 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] map = new int[1001][1001];
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            if (x1 < minX) minX = x1;
            int y1 = Integer.parseInt(st.nextToken());
            if (y1 < minY) minY = y1;
            int x2 = x1 + Integer.parseInt(st.nextToken());
            if (x2 > maxX) maxX = x2;
            int y2 = y1 + Integer.parseInt(st.nextToken());
            if (y2 > maxY) maxY = y2;

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    map[j][k] = i + 1;
                }
            }
        }

        for (int k = 1; k <= N; k++) {
            int count = 0;
            for (int i = minX; i < maxX; i++) {
                for (int j = minY; j < maxY; j++) {
                    if (map[i][j] == k) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }


    }
}
