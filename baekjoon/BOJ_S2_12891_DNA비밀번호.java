import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_12891_DNA비밀번호 {

    static int S, P;
    static int numA, numC, numG, numT, a, c, g, t;
    static int count;
    static char prev;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        input = br.readLine();

        st = new StringTokenizer(br.readLine());
        numA = Integer.parseInt(st.nextToken());
        numC = Integer.parseInt(st.nextToken());
        numG = Integer.parseInt(st.nextToken());
        numT = Integer.parseInt(st.nextToken());

        prev = input.charAt(0);

        // 첫 번째 부분문자열 체크
        for (int i = 0; i < P; i++) {
            checkNext(input.charAt(i));
        }
        checkCount();

        // 그 다음부터 슬라이드윈도우
        int loop = 0;
        while (loop < S - P) {

            checkPrev(); // 가장 앞의 원소 제거
            checkNext(input.charAt(P + loop)); // 다음 원소 추가
            checkCount();

            prev = input.charAt(loop + 1);

            loop++;
        }

        System.out.print(count);

    }

    private static void checkPrev() {
        if (prev == 'A')
            a--;
        else if (prev == 'C')
            c--;
        else if (prev == 'G')
            g--;
        else if (prev == 'T')
            t--;
    }

    private static void checkCount() {
        if (a + c + g + t == P) {
            if (a >= numA && c >= numC && g >= numG && t >= numT) {
                count++;
            }
        }
    }

    static void checkNext(char next) {
        if (next == 'A') {
            a++;
        } else if (next == 'C') {
            c++;
        } else if (next == 'G') {
            g++;
        } else if (next == 'T') {
            t++;
        }
    }
}
