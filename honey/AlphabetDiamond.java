import java.util.Scanner;

//구현 문제
//알파벳 다이아몬드 모양으로 찍는 문제
public class AlphabetDiamond {

	static int N;
	static int[][] alpha;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		alpha = new int[N][N];
		StringBuilder sb = new StringBuilder();

		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < Math.abs(i - N / 2); j++) {
				sb.append(" ");
			}
			for (int j = 0; j <  - 2 * Math.abs(i - N / 2) + N; j++) {
				sb.append((char) ((index % 26) + 'A'));
				index++;
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
