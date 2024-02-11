import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_S3_2108_통계학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] count = new int[8001]; //각 숫자의 빈도 저장하기 위한 배열
        int offset = 4000; //숫자 범위 -4000 ~ 4000 이지만 인덱스는 0부터 시작하므로 offset 4000 설정
        int sum = 0; //숫자의 합
        int min = Integer.MAX_VALUE; //최솟값
        int max = Integer.MIN_VALUE; //최댓값
        int countMax = Integer.MIN_VALUE; //최빈값
        Queue countMaxValue = new PriorityQueue<Integer>(); //최빈값을 저장하는 큐

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;

            min = num < min ? num : min;
            max = num > max ? num : max;

            count[num + offset]++; //빈도수 배열 증가

            if (count[num + offset] == countMax) { //최빈값이 여러개인 경우
                countMaxValue.add(num); //우선순위 큐에 저장
            }
            if (count[num + offset] > countMax) { //최빈값이 갱신됨
                countMaxValue.clear();  //큐룰 비우고
                countMaxValue.add(num); //새로운 최빈값 저장
                countMax = count[num + offset]; //최빈값 갱신
            }
        }

        //중앙값 구하기
        int mid = 0;
        int cnt = 0;
        for (int i = min + offset; i <= max + offset; i++) {
            cnt += count[i];

            if (cnt > N / 2) {
                mid = i - offset;
                break;
            }
        }
        //산술평균
        System.out.println(Math.round((double) sum / N));
        //중앙값
        System.out.println(mid);
        //최빈값
        if (countMaxValue.size() > 1) { //최빈값이 여러개인 경우
            countMaxValue.poll(); //가장 작은 값은 꺼내고
            System.out.println(countMaxValue.peek()); //두 번째로 작은 값 출력
        } else {
            System.out.println(countMaxValue.peek());
        }
        //범위
        System.out.println(max - min);
    }
}
