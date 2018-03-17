
public class simpleCal {
	static int resultNumber;
	
	/**
	* num1과 num2를 합하고 그 결과를 반환한다.
	* @param num1 더하기 연산을 위한 첫번째 피연산자
	* @param num2 더하기 연산을 위한 두번째 피연산자
	* @return num1 + num2 값을반환
	*/
	public static int addNumber(int num1, int num2) {
		resultNumber= num1 + num2;
	return resultNumber;
	}
	
	/**
	* num1과 num2를 빼고 그 결과를 반환한다.
	* @param num1 빼기 연산을 위한 첫번째 피연산자
	* @param num2 빼기 연산을 위한 두번째 피연산자
	* @return num1 - num2 값을 반환
	*/
	public static int subNumber(int num1, int num2) {
		resultNumber= num1 - num2;
	return resultNumber;
	}
	
	/**
	* num1과 num2를 곱하고 그 결과를 반환한다.
	* @param num1 곱하기 연산을 위한 첫번째 피연산자
	* @param num2 곱하기 연산을 위한 두번째 피연산자
	* @return num1 * num2 값을 반환
	*/
	public static int mulNumber(int num1, int num2) {
		resultNumber= num1 * num2;
	return resultNumber;
	}
	
	/**
	* num1과 num2를 나누고 그 결과를 반환한다.
	* @param num1 나누기 연산을 위한 첫번째 피연산자
	* @param num2 나누기 연산을 위한 두번째 피연산자
	* @return num1 / num2 값을 반환
	*/
	public static int divNumber(int num1, int num2) {
		resultNumber= num1 / num2;
	return resultNumber;
	}
	public static void main(String[] args) {
	int number;
	number = addNumber(5, 8);
	System.out.println(number);
	}
}
