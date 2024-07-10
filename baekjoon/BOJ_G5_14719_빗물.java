import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14719_빗물 {
    static int H, W;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            fill(i, Integer.parseInt(st.nextToken()));
        }

        int result = 0; //빗물의 총량
        for (int i = 0; i < H; i++) {
            boolean start = false; //이번 행에서 블록을 만난 적이 있는지 체크
            int count = 0; //이번 행에서 빗물이 고이는 칸 수
            for (int j = 0; j < W; j++) {
                if (!start && map[i][j] == 1) { //이번 행에서 블록을 처음 만난 경우
                    start=true;
                }
                else if (start && map[i][j] == 0) { //빗물이 고일 수 있는 공간
                    count++;
                }
                else if (start && map[i][j] == 1) { //이미 블록을 만난 후 또 다른 블록을 만난 경우 빗물 저장
                    result += count; //빗물 저장
                    count = 0; //빗물이 고이는 칸 초기화
                }
            }
        }
        System.out.println(result);
    }

    //map을 채우는 함수
    public static void fill(int col, int num) {
        for (int i = 1; i <= num; i++) {
            map[H - i][col] = 1;
        }
    }
}
