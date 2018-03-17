
public class Practice2 {
	/**
	* 1부터 num까지 곱한 후 반환
	* @param num 범위를 위한 피연산자
	* @return 1~num 곱한  값을 반환
	*/
	public static int factorial(int num) {
		int result=1;               // 결과 값을 저장한 변수 선언
		for(int i=1;i<=num;i++) {  // 1부터 num까지 차례대로 곱해줌
			result*=i;
		}
		return result;   // 반환
	}
	public static void main(String[] args) {   // main함수
		System.out.println(factorial(5));
		}      // 결과 출력

}
