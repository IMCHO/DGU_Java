
public class Practice4 {
	public static void main(String[] arg) {
		for (int j = 1; j <= 100; j++) { // 1���� 100���� �Ҽ� �˻�
			for (int i = 2; i < j; i++) { // 1�� �ڱ��ڽ��� �������� ����
				if (j % i == 0) {
					break; // �������� 0�̶�� num�� ����� �����ϹǷ� �Ҽ��� �ƴ� -> �ݺ��� Ż��
				}
				if (i == j - 1)
					System.out.print(j + " "); // ���� �ݺ����� �� �������� break�� Ż������ ������ �Ҽ��̹Ƿ� ���!
			}
		}
	}
}