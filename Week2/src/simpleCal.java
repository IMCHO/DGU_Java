
public class simpleCal {
	static int resultNumber;  // ���� ����� ���� ����
	
	/**
	* num1�� num2�� ���ϰ� �� ����� ��ȯ�Ѵ�.
	* @param num1 ���ϱ� ������ ���� ù��° �ǿ�����
	* @param num2 ���ϱ� ������ ���� �ι�° �ǿ�����
	* @return num1 + num2 ������ȯ
	*/
	public static int addNumber(int num1, int num2) {
		resultNumber= num1 + num2;
	return resultNumber;
	}                               // �Ű����� 2���� ���� �� ��ȯ
	
	/**
	* num1�� num2�� ���� �� ����� ��ȯ�Ѵ�.
	* @param num1 ���� ������ ���� ù��° �ǿ�����
	* @param num2 ���� ������ ���� �ι�° �ǿ�����
	* @return num1 - num2 ���� ��ȯ
	*/
	public static int subNumber(int num1, int num2) {
		resultNumber= num1 - num2;
	return resultNumber;  
	}                                // �Ű����� 2���� �� �� ��ȯ
	
	/**
	* num1�� num2�� ���ϰ� �� ����� ��ȯ�Ѵ�.
	* @param num1 ���ϱ� ������ ���� ù��° �ǿ�����
	* @param num2 ���ϱ� ������ ���� �ι�° �ǿ�����
	* @return num1 * num2 ���� ��ȯ
	*/
	public static int mulNumber(int num1, int num2) {
		resultNumber= num1 * num2;
	return resultNumber;
	}                                 // �Ű����� 2���� ���� �� ��ȯ
	
	/**
	* num1�� num2�� ������ �� ����� ��ȯ�Ѵ�.
	* @param num1 ������ ������ ���� ù��° �ǿ�����
	* @param num2 ������ ������ ���� �ι�° �ǿ�����
	* @return num1 / num2 ���� ��ȯ
	*/
	public static int divNumber(int num1, int num2) {
		resultNumber= num1 / num2;
	return resultNumber;
	}                                 // �Ű����� 2���� ���� �� ��ȯ
	public static void main(String[] args) {
	int number;
	number = addNumber(5, 8);
	System.out.println(number);
	}
}
