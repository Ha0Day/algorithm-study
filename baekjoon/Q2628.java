import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2628 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(br.readLine());

        ArrayList<Integer> horList = new ArrayList<>();
        ArrayList<Integer> verList = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int val = Integer.parseInt(st.nextToken());
            if (val == 1) {
                horList.add(Integer.parseInt(st.nextToken()));
            } else {
                verList.add(Integer.parseInt(st.nextToken()));
            }
        }

        Collections.sort(horList);
        Collections.sort(verList);

        int horMax = horList.isEmpty() ? W : horList.get(0);
        int verMax = verList.isEmpty() ? H : verList.get(0);

        for (int i = 1; i < horList.size(); i++) {
            if (horList.get(i) - horList.get(i - 1) > horMax) {
                horMax = horList.get(i) - horList.get(i - 1);
            }
        }

        if (!horList.isEmpty() && (W - horList.get(horList.size() - 1) > horMax)) {
            horMax = W - horList.get(horList.size() - 1);
        }

        for (int i = 1; i < verList.size(); i++) {
            if (verList.get(i) - verList.get(i - 1) > verMax) {
                verMax = verList.get(i) - verList.get(i - 1);
            }
        }

        if (!verList.isEmpty() && (H - verList.get(verList.size() - 1) > verMax)) {
            verMax = H - verList.get(verList.size() - 1);
        }

        System.out.print(horMax * verMax);
    }
}
