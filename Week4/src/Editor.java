import java.io.*; // input, output ����� ���� ���� ����
import java.util.Scanner; // �˻��� ��Ʈ���� �Է� �ޱ����� �ʿ��� import

public class Editor {
	public static int count = 0; // ���� ������ ���� ���� ����
	public static String INPUT_PATH;
	public static String OUTPUT_PATH;
	public static final int BUFFER_SIZE = 130; // ���ۻ����� ���� -> ���� �� �ٴ� �ܾ 130���̹Ƿ�
	public static FileInputStream inputStream; // ���Ͻ�Ʈ�� ���
	public static BufferedInputStream bufferedInputStream; // ���۽�Ʈ�� ���
	public static String[] strTemp = new String[100000];
	public static char space = ' '; // ���� ������ ���� ���� �ʿ��� ����
	public static String result; // ���� ����� ������ ����

	public static void loadFile() {
		Scanner sc = new Scanner(System.in);
		boolean isCorrectPath = true; // �ݺ����� �÷���
		count = 0; // count �ʱ�ȭ

		while (isCorrectPath) {
			INPUT_PATH = sc.nextLine();
			try {
				inputStream = new FileInputStream(INPUT_PATH); // �Էµ� ��ΰ����� new �Ҵ�
			} catch (Exception e) {
				System.out.println("�߸��� ����Դϴ�. �ٽ� �Է����ּ���!"); // ��ΰ� �������� ���� ��� �ٽ� �ݺ�
				continue;
			}

			bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
			byte[] readBuffer = new byte[BUFFER_SIZE]; // ���� ������ ����

			try {
				while (bufferedInputStream.read(readBuffer, 0, readBuffer.length) != -1) { // ���ۻ����ŭ ������ �о�� ����
					strTemp[count++] = new String(readBuffer); // �о�� ���۸� ��Ʈ������ ��ȯ
				}
			} catch (IOException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}

			for (int i = 0; i < count - 1; i++) {

				if (strTemp[i].charAt(strTemp[i].length() - 1) != space) { // �� ���ٿ� ������ ���� ��� �̾����� �ܾ� ����
					strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1); // �����ٷ� �Ѿ�� ù��°
																									// ���������� �ҷ��� ��
																									// �ڿ� �ٿ���
					strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, 130); // �� ���� ���� �ܾ�
																										// �����ϰ� ����
																										// ����
				}
			}
			for (int i = 0; i < count; i++) {
				strTemp[i] = strTemp[i].replaceAll("\r\n|\n\r|\r|\n", ""); // ���๮�� ����
			}
			isCorrectPath = false; // �ݺ��� Ż��
		}
	}

	public static void searchString() {
		String input = ""; // �Է� ���� ���ڿ�
		Scanner in = new Scanner(System.in); // ���ڿ� �Է�
		input = in.next(); // �Է��� ���ڿ� input�� �Ҵ�
		int word_count = 0; // �ܾ� ������ ���� ���� ����
		result = "\r\n" + INPUT_PATH + " ���Ͽ��� " + input + "�� �˻� ����� ������ �����ϴ�.\r\n"; // ����� ������ �� ����

		for (int i = 0; i < count; i++) {
			String splitStrArr[] = strTemp[i].split(" "); // �� ���� ���� �������� ���� -> �ܾ�� �ɰ���
			for (int j = 0; j < splitStrArr.length; j++) {
				if (input.matches(splitStrArr[j])) {
					result += (i + 1) + "��° �� " + (j + 1) + "��°\r\n"; // �˻��ܾ�� ����Ǿ��ִ� �ܾ�� ������ ��
					word_count++;
				}
			}
		}
		result += "�̻� �˻������ �� " + word_count + "���� �˻��� �Ϸ�Ǿ����ϴ�.\r\n";
		System.out.println(result); // ��� ���
	}

	public static void writeFile() {
		Scanner in = new Scanner(System.in); // ���ڿ� �Է�
		OUTPUT_PATH = in.nextLine();
		File file = new File(OUTPUT_PATH);
		FileWriter filewriter;

		if (file.exists()) { // ������ �����ϸ� �ٿ��ֱ� ���� �����
			System.out.println("�ٿ� �ֱ� �Ͻðڽ��ϱ�?(y/n)");
			String findWord;
			boolean isExitProgram = false; // ���α׷� ���� �÷���

			while (!isExitProgram) {
				findWord = in.nextLine();
				switch (findWord) {
				case "Y":
				case "y": {
					try {
						filewriter = new FileWriter(file, true); // �ٿ��ֱ�
						filewriter.write(result);
						filewriter.close();
					} catch (IOException e) {
						// TODO �ڵ� ������ catch ���
						e.printStackTrace();
					}
					isExitProgram = true;
					break;
				}
				case "N":
				case "n": {
					try {
						filewriter = new FileWriter(file, false); // �����
						filewriter.write(result);
						filewriter.close();
					} catch (IOException e) {
						// TODO �ڵ� ������ catch ���
						e.printStackTrace();
					}
					isExitProgram = true;
					break;
				}
				default:
					System.out.println("�� �� ���� ����Դϴ�. �ѹ� �� �Է����ּ���!");
					break;
				}
			}
		} else {
			try {
				filewriter = new FileWriter(file, false); // ���� ����
				filewriter.write(result);
				filewriter.close();
			} catch (IOException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}
		}
	}

	public static boolean isExistFile(String str) { // ������ �����ϴ��� Ȯ�����ִ� �޼ҵ�
		File file = new File(str);
		if (file.exists())
			return true;
		else
			return false;
	}
}
