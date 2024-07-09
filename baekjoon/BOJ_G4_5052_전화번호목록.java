import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_G4_5052_전화번호목록 {
    public static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        a:
        for (int i = 1; i <= t; i++) {
            int size = Integer.parseInt(br.readLine());
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < size; j++) {
                list.add(br.readLine());
            }
            //문자열 정렬
            Collections.sort(list);

            String prev = list.get(0); //앞 문자열
            for (int j = 1; j < list.size(); j++) {
                String cur = list.get(j); //현재 문자열
                if (prev.length() <= cur.length() && cur.startsWith(prev)) { //앞 문자열을 접두사로 갖는 경우
                    System.out.println("NO"); //일관성이 없음
                    continue a;
                }
                prev = new String(cur);
            }
            //모든 문자열 확인 결과 일관성 없는 케이스가 없음 -> 일관성이 있음
            System.out.println("YES");
        }
    }
}
