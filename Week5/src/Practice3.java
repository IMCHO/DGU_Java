import java.util.Scanner;

public class Practice3 {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // int �ڷ��� ���� �Է¹ޱ�
		boolean prime_check = true;
		for (int i = 2; i < num; i++) { // 1�� �ڱ��ڽ��� �������� ����
			if (num % i == 0) {
				prime_check = false;
				break; // �������� 0�̶�� num�� ����� �����ϹǷ� �Ҽ��� �ƴ� -> �ݺ��� Ż��
			}
		}
		if (prime_check)
			System.out.println(num + "�� �Ҽ�"); // �Ҽ��� ���
		else
			System.out.println(num + "�� �Ҽ��� �ƴ�"); // �Ҽ��� �ƴ� ���
		sc.close();
	}
}
