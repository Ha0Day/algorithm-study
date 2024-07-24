import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G2_1202_보석도둑_sol {
    public static int N, K;
    public static Jewel[] jewels;
    public static int[] bags;
    public static long maxV;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //보석 개수
        K = Integer.parseInt(st.nextToken()); //가방 개수

        jewels = new Jewel[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        //===================== 입력 완료

        //보석 무게를 기준으로 오름차순 정렬
        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if (o1.M < o2.M) {
                    return -1;
                } else if (o1.M > o2.M) {
                    return 1;
                }
                return 0;
            }
        });

        //가방 무게를 기준으로 오름차순 정렬
        Arrays.sort(bags);

        //가격 기준으로 내림차순하는 우선순위 큐
        PriorityQueue<Jewel> queue = new PriorityQueue<>(new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if (o2.V < o1.V) {
                    return -1;
                } else if (o2.V > o1.V) {
                    return 1;
                }
                return 0; // o2.V == o1.V
            }
        });

        int index = 0;

        for (int i = 0; i < K; i++) {
            // 현재 가방에 넣을 수 있는 보석을 모두 큐에 넣음
            // 가방과 보석 모두 무게 기준으로 오름차순되어 있기 때문에 새로운 가방에 넣을 보석을 처음부터 탐색하지 않아도 됨
            // (index 변수가 for문 밖에서 선언된 이유)
            // 이미 넣은 보석은 다음 가방에 무조건 들어갈 수 있기 때문
            // 따라서 시간복잡도도 이득을 보게 됨 O(K*N)가 아닌 O(K+NlogN). 큐에 넣을 때마다 정렬되므로 N*logN이 더해짐
            while (index < N && bags[i] >= jewels[index].M) {
                queue.offer(jewels[index]);
                index++;
            }

            //현재 가방에 넣을 수 있는 보석 중 가장 비싼 보석을 꺼냄
            if (!queue.isEmpty()) {
                maxV += queue.poll().V;
            }
        }
        System.out.println(maxV);
    }

    public static class Jewel {
        int M; //무게
        int V; //가격

        Jewel(int M, int V) {
            this.M = M;
            this.V = V;
        }
    }
}
