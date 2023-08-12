
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_1210_Ladder1 {
    static int N;
    static int map[][];
    static int target;
    static int[] dc = {-1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine()); // 덤프 횟수
            map = new int[100][100];

            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // -----------입력 완료

            for (int j = 0; j < 100; j++) {
                if (map[99][j] == 2) {
                    target = j;
                    break;
                }
            }
            // 타겟 찾음

            int r = 98;
            int c = target;
            boolean goingLeft = false, goingRight = false;
            while (true) {
                if (r == 0) {
                    break;
                }
                if (!goingRight && c - 1 >= 0 && map[r][c - 1] == 1) { // 왼쪽에 길이 있는 경우
                    c = c - 1;
                    goingLeft = true;
                } else if (!goingLeft && c + 1 < 100 && map[r][c + 1] == 1) { // 오른쪽에 길이 있는 경우
                    c = c + 1;
                    goingRight = true;
                } else { // 계속 위로 가기
                    r = r - 1;
                    goingRight = false;
                    goingLeft = false;
                }
            }

            sb.append("#" + t + " " + c + "\n");
        }
        System.out.println(sb);

    }

}
