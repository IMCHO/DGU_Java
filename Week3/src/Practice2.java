public class Practice2 {
	public static double sum(long n1,long n2) {  // long �Ű������� 2�� ���� �� ���� �� ����
		double temp1=n1;
		double temp2=n2;
		return temp1+temp2;
	}
	public static void main(String[] args) {   // main �Լ�
		long num1=5555555555555555555L;        // long ����
		long num2=6666666666666666666L;
		System.out.println(sum(num1,num2));    // �� ���
	}
}