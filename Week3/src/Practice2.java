import java.math.*;

public class Practice2 {
	public static BigInteger sum(long n1,long n2) {  // long �Ű������� 2�� ���� �� ���� �� ����
		String s1=String.valueOf(n1);                // long������ String������ ��ȯ
		String s2=String.valueOf(n2);
		BigInteger temp1=new BigInteger(s1);         // BigInteger ������ �̿��Ͽ� ���
		BigInteger temp2=new BigInteger(s2);
		BigInteger addResult=temp1.add(temp2);       // �� ���
		return addResult;
	}
	public static void main(String[] args) {   // main �Լ�
		long num1=5555555555555555555L;        // long ����
		long num2=6666666666666666666L;
		System.out.println(sum(num1,num2));    // �� ���
	}
}