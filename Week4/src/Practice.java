import java.io.*; // input, output ����� ���� ���� ����
import java.util.Scanner; // �˻��� ��Ʈ���� �Է� �ޱ����� �ʿ��� import

public class Practice {
	public static final String INPUT_PATH = "c:\\test.txt"; // ��μ���
	public static final int BUFFER_SIZE = 130; // ���ۻ����� ���� -> ���� �� �ٴ� �ܾ 130���̹Ƿ�

	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream(INPUT_PATH); // ���Ͻ�Ʈ�� ���
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE); // ���۽�Ʈ�� ���

		String input = ""; // �Է� ���� ���ڿ�
		Scanner in = new Scanner(System.in); // ���ڿ� �Է�
		input = in.next(); // �Է��� ���ڿ� input�� �Ҵ�
		int count = 0; // ���� ������ ���� ���� ����
		char space = ' '; // ���� ������ ���� ���� �ʿ��� ����
		int word_count = 0; // �ܾ� ������ ���� ���� ����
		String[] strTemp = new String[100000];

		byte[] readBuffer = new byte[BUFFER_SIZE]; // ���� ������ ����
		while (bufferedInputStream.read(readBuffer, 0, readBuffer.length) != -1) { // ���ۻ����ŭ ������ �о�� ����
			strTemp[count++] = new String(readBuffer); // �о�� ���۸� ��Ʈ������ ��ȯ
		}
		for (int i = 0; i < count - 1; i++) {
			if (strTemp[i].charAt(strTemp[i].length() - 1) != space) { // �� ���ٿ� ������ ���� ��� �̾����� �ܾ� ����
				strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1); // �����ٷ� �Ѿ�� ù��° ���������� �ҷ���
																								// �� �ڿ� �ٿ���
				strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, 130); // �� ���� ���� �ܾ� �����ϰ� ����
																									// ����
			}
		}
		for (int i = 0; i <= count; i++) {
			int check = strTemp[i].indexOf(input); // �ٽ� �ڵ� -> �˻��ϰ��� �ϴ� ��Ʈ���� �ִ��� Ȯ��
			if (check == -1)
				continue; // �� ���ۿ� ������ ���� ���� ����
			else { // ã���� ��ġ ���
				for (int j = 0; j < check; j++) {
					if (strTemp[i].charAt(j) == space)
						word_count++; // ���� ������ �������� �ܾ� ���� �ľ�
				}
				System.out.println((i + 1) + "��° �� " + (word_count + 1) + "��° �ܾ�");
				break;
			}

		}

		bufferedInputStream.close(); // ��Ʈ�� �ݱ�
		in.close();
	}

}
