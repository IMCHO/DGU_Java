
public class Practice2 {
	public static void main(String[] arg) {
		int result = 0;
		for (int j = 1; j <= 100; j++) { // 1���� 100 ���� �˻�
			for (int i = 1; i < j; i++) {
				if (j % i == 0)
					result += i; // �������� 0�̶�� num�� ����̹Ƿ� result ������ ������
			}
			if (j == result)
				System.out.print(j + " "); // ��������� ���
			result = 0; // �񱳰� �ʱ�ȭ
		}
	}
}