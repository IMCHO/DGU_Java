import java.util.Scanner;

public class Practice2 {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // int 자료형 변수 입력받기
		int result = 0;
		for (int i = 1; i < num; i++) {
			if (num % i == 0)
				result += i; // 나머지가 0이라면 num의 약수이므로 result 변수에 더해줌
		}
		if (num == result)
			System.out.println(num + "은(는) 완전수"); // 완전수의 경우
		else
			System.out.println(num + "은(는) 완전수가 아님"); // 완전수가 아닌 경우
		sc.close();
	}
}