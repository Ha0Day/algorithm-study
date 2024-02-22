import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S2_3085_사탕게임 {
    static char[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        int totalMax = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        //----------------입력 완료

        //가로로 자리 바꾸기
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j - 1] != map[i][j]) {
                    //swap
                    char temp = map[i][j - 1];
                    map[i][j - 1] = map[i][j];
                    map[i][j] = temp;
                    int max = getMax(); //자리 바꾼 후 가장 긴 문자 개수
                    if (max > totalMax) {
                        totalMax = max;
                    }
                    //원위치
                    map[i][j] = map[i][j - 1];
                    map[i][j - 1] = temp;
                }

            }
        }

        //세로로 자리 바꾸기
        for (int j = 0; j < N; j++) {
            for (int i = 1; i < N; i++) {
                if (map[i - 1][j] != map[i][j]) {
                    char temp = map[i - 1][j];
                    map[i - 1][j] = map[i][j];
                    map[i][j] = temp;
                    int max = getMax(); //자리 바꾼 후 가장 긴 문자 개수
                    if (max > totalMax) {
                        totalMax = max;
                    }
                    map[i][j] = map[i - 1][j];
                    map[i - 1][j] = temp;
                }

            }
        }

        System.out.println(totalMax);
    }

    //현재 가장 긴 문자열 길이를 리턴하는 함수
    static int getMax() {
        int max = Integer.MIN_VALUE;

        //가로에서 찾기
        for (int i = 0; i < N; i++) {
            char prev = map[i][0];
            int count = 1;
            for (int j = 1; j < N; j++) {
                if (prev == map[i][j]) { //연속 진행
                    count++;
                } else { //연속이 깨짐
                    count = 1;
                }
                if (count > max) {
                    max = count;
                }
                prev = map[i][j];
            }
        }

        //세로에서 찾기
        for (int i = 0; i < N; i++) {
            char prev = map[0][i];
            int count = 1;
            for (int j = 1; j < N; j++) {
                if (prev == map[j][i]) { //연속 진행
                    count++;
                } else { //연속이 깨짐
                    count = 1;
                }
                if (count > max) {
                    max = count;
                }
                prev = map[j][i];
            }
        }
        return max;
    }
}
