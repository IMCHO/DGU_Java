
public class Practice2 {
	/**
	* 1���� num���� ���� �� ��ȯ
	* @param num ������ ���� �ǿ�����
	* @return 1~num ����  ���� ��ȯ
	*/
	public static int factorial(int num) {
		int result=1;               // ��� ���� ������ ���� ����
		for(int i=1;i<=num;i++) {  // 1���� num���� ���ʴ�� ������
			result*=i;
		}
		return result;   // ��ȯ
	}
	public static void main(String[] args) {   // main�Լ�
		System.out.println(factorial(5));
		}      // ��� ���

}
