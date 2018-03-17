
public class simpleCal {
	static int resultNumber;
	public static int addNumber(int num1, int num2) {
		resultNumber= num1 + num2;
	return resultNumber;
	}
	public static int subNumber(int num1, int num2) {
		resultNumber= num1 - num2;
	return resultNumber;
	}
	public static int mulNumber(int num1, int num2) {
		resultNumber= num1 * num2;
	return resultNumber;
	}
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
