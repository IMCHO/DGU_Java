import java.util.Scanner;

public class Practice1 {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // int �ڷ��� ���� �Է¹ޱ�
		int result = 0;
		for (int i = 1; i <= num; i++) {
			if (num % i == 0)
				result += i; // �������� 0�̶�� num�� ����̹Ƿ� result ������ ������
		}
		System.out.println(result); // ���� ��� ���
		sc.close();
	}
}
