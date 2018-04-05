
public class Practice2 {
	public static void main(String[] arg) {
		int result = 0;
		for (int j = 1; j <= 100; j++) { // 1부터 100 사이 검사
			for (int i = 1; i < j; i++) {
				if (j % i == 0)
					result += i; // 나머지가 0이라면 num의 약수이므로 result 변수에 더해줌
			}
			if (j == result)
				System.out.print(j + " "); // 완전수라면 출력
			result = 0; // 비교값 초기화
		}
	}
}