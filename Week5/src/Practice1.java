import java.util.Scanner;

public class Practice1 {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // int 자료형 변수 입력받기
		int result = 0;
		for (int i = 1; i <= num; i++) {
			if (num % i == 0)
				result += i; // 나머지가 0이라면 num의 약수이므로 result 변수에 더해줌
		}
		System.out.println(result); // 최종 결과 출력
		sc.close();
	}
}
