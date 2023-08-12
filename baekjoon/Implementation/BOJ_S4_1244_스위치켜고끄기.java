package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S4_1244_스위치켜고끄기 {
    static int N; // 스위치 개수
    static int[] switches;
    static int stuNum;
    static int[][] info;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        switches = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < switches.length; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        stuNum = Integer.parseInt(br.readLine());
        info = new int[stuNum][2];

        for (int i = 0; i < stuNum; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken()); //성별
            info[i][1] = Integer.parseInt(st.nextToken()); //학생이 받은 수
        }
        //----------------------입력 완료
        for (int i = 0; i < stuNum; i++) {
            if (info[i][0] == 1) { //남학생이면
                for (int j = 0; j < switches.length; j++) {
                    if ((j + 1) % info[i][1] == 0) {
                        switches[j] = switches[j] == 1 ? 0 : 1; //숫자 바꾸기
                    }
                }
            } else { //여학생이면
                int l = info[i][1] - 1;
                int k = info[i][1] + 1;
                int start = info[i][1];
                int end = info[i][1];
                while (l > 0 && k <= N) {
                    if (switches[l - 1] != switches[k - 1]) {
                        break;
                    } else {
                        start = l;
                        end = k;
                    }
                    l--;
                    k++;
                }
                for (int j = start - 1; j < end; j++) {
                    switches[j] = switches[j] == 1 ? 0 : 1; //숫자 바꾸기
                }
            }
        }
        for (int i = 0; i < switches.length; i++) {
            if (i != 0 && i % 20 == 0) {
                System.out.println();
            }
            System.out.print(switches[i] + " ");
        }

    }

}
