
public class Practice3 {
	public static int power(int num1,int power) {
		int result=1;             // ������� ������ ���� ����
		while(power--!=0) {       // power�� ����� Ƚ����ŭ �ݺ��� ����
			result*=num1;         // �ݺ����� �� ������ num1 ������
		}
		return result;
	}
	
	public static void main(String[] args) {   // main�Լ�
		System.out.println(power(3,5));
		}      // ��� ���
}
