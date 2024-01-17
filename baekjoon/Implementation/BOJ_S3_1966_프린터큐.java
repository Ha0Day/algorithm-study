package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S3_1966_프린터큐 {
    static int T, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int cnt = 0; //현재까지 인쇄된 문서 수
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            Queue<int[]> q = new ArrayDeque<>();
            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //현재 q에 남은 값 중 가장 큰 값을 알기 위해 사용

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                pq.offer(num);
                if (i == M) {
                    q.offer(new int[]{num, 1}); //내가 추적 중인 문서는 1로 표시
                } else {
                    q.offer(new int[]{num, 0});
                }
            }
            while (true) {
                int[] head = q.peek(); //현재 q 맨 앞의 값 확인

                if (head[0] != pq.peek()) { //제일 큰 값이 아니면 큐의 맨 뒤로 보냄
                    q.offer(q.poll());
                } else { //제일 큰 값을 만나면
                    pq.poll();
                    q.poll();
                    cnt++;

                    //내가 찾던 값이라면
                    if (head[1] == 1) {
                        System.out.println(cnt);
                        break;
                    }
                }
            }
        }
    }
}