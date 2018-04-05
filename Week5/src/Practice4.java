
public class Practice4 {
	public static void main(String[] arg) {
		for (int j = 1; j <= 100; j++) { // 1부터 100까지 소수 검사
			for (int i = 2; i < j; i++) { // 1과 자기자신은 범위에서 제외
				if (j % i == 0) {
					break; // 나머지가 0이라면 num의 약수가 존재하므로 소수가 아님 -> 반복문 탈출
				}
				if (i == j - 1)
					System.out.print(j + " "); // 만약 반복문이 다 돌때까지 break로 탈출하지 않으면 소수이므로 출력!
			}
		}
	}
}