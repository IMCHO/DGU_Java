import java.util.Scanner;

public class Practice3 {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // int 자료형 변수 입력받기
		boolean prime_check = true;
		for (int i = 2; i < num; i++) { // 1과 자기자신은 범위에서 제외
			if (num % i == 0) {
				prime_check = false;
				break; // 나머지가 0이라면 num의 약수가 존재하므로 소수가 아님 -> 반복문 탈출
			}
		}
		if (prime_check)
			System.out.println(num + "은 소수"); // 소수인 경우
		else
			System.out.println(num + "은 소수가 아님"); // 소수가 아닌 경우
		sc.close();
	}
}
