
public class Practice1 {
	/**
	* 1부터 num까지 더한 후 반환
	* @param num 범위를 위한 피연산자
	* @return 1~num 더한  값을 반환
	*/
	public static int addNumber(int num) {
		int result=0;                 // 최종 결과값 선언
		for(int i=1;i<=num;i++) {     // 1부터 num까지 계산하기위한 for문
			result+=i;
		}
		return result;
	}
	
	/**
	* 1부터 num까지 뺀 후 반환
	* @param num 범위를 위한 피연산자
	* @return 1~num 뺀  값을 반환
	*/
	public static int subNumber(int num) {
		int result=0;                  // 최종 결과값 선언
		for(int i=1;i<=num;i++) {     // 1부터 num까지 계산하기위한 for문
			result-=i;
		}
		return result;
	}
	
	/**
	* 1부터 num까지 곱한 후 반환
	* @param num 범위를 위한 피연산자
	* @return 1~num 곱한  값을 반환
	*/
	public static int mulNumber(int num) {
		int result=1;                 // 최종 결과값 선언
		for(int i=1;i<=num;i++) {     // 1부터 num까지 계산하기위한 for문
			result*=i;
		}
		return result;
	}
	
	/**
	* 1부터 num까지 나눈 후 반환
	* @param num 범위를 위한 피연산자
	* @return 1~num 나눈  값을 반환
	*/
	public static int divNumber(int num) {
		int result=1;                 // 최종 결과값 선언
		for(int i=1;i<=num;i++) {     // 1부터 num까지 계산하기위한 for문
			result/=i;
		}
		return result;
	}
	
	public static void main(String[] args) {   // main함수
		System.out.println(addNumber(5));
		System.out.println(subNumber(5));
		System.out.println(mulNumber(5));
		System.out.println(divNumber(5));
		}      // 각 결과 출력
}
