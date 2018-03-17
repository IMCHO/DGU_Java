
public class Practice1 {
	/**
	* 1���� num���� ���� �� ��ȯ
	* @param num ������ ���� �ǿ�����
	* @return 1~num ����  ���� ��ȯ
	*/
	public static int addNumber(int num) {
		int result=0;                 // ���� ����� ����
		for(int i=1;i<=num;i++) {     // 1���� num���� ����ϱ����� for��
			result+=i;
		}
		return result;
	}
	
	/**
	* 1���� num���� �� �� ��ȯ
	* @param num ������ ���� �ǿ�����
	* @return 1~num ��  ���� ��ȯ
	*/
	public static int subNumber(int num) {
		int result=0;                  // ���� ����� ����
		for(int i=1;i<=num;i++) {     // 1���� num���� ����ϱ����� for��
			result-=i;
		}
		return result;
	}
	
	/**
	* 1���� num���� ���� �� ��ȯ
	* @param num ������ ���� �ǿ�����
	* @return 1~num ����  ���� ��ȯ
	*/
	public static int mulNumber(int num) {
		int result=1;                 // ���� ����� ����
		for(int i=1;i<=num;i++) {     // 1���� num���� ����ϱ����� for��
			result*=i;
		}
		return result;
	}
	
	/**
	* 1���� num���� ���� �� ��ȯ
	* @param num ������ ���� �ǿ�����
	* @return 1~num ����  ���� ��ȯ
	*/
	public static int divNumber(int num) {
		int result=1;                 // ���� ����� ����
		for(int i=1;i<=num;i++) {     // 1���� num���� ����ϱ����� for��
			result/=i;
		}
		return result;
	}
	
	public static void main(String[] args) {   // main�Լ�
		System.out.println(addNumber(5));
		System.out.println(subNumber(5));
		System.out.println(mulNumber(5));
		System.out.println(divNumber(5));
		}      // �� ��� ���
}
