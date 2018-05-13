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
		strTemp = new String[100000]; // strTemp �ʱ�ȭ

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

				if (strTemp[i].charAt(strTemp[i].length() - 1) != space && strTemp[i + 1].charAt(0) != space) { // �� ���ٿ�
																												// ������
																												// ���� ���
																												// �̾�����
																												// �ܾ� ����
																												// & ������
																												// ù ���ڵ�
																												// ������
																												// �ƴϿ�����(�����Ǿ���
																												// �� ���)
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

	public static void replaceWord() {
		Scanner in = new Scanner(System.in);
		System.out.println("�˻��� �ܾ �Է����ּ���:");
		String searchStr = in.nextLine(); // �˻� �ܾ�
		System.out.println("��ü�� �ܾ �Է����ּ���:");
		String replaceStr = in.nextLine(); // �ٲ� �ܾ�

		for (int i = 0; i < count; i++) {
			String splitStrArr[] = strTemp[i].split(" "); // �� ���� ���� �������� ���� -> �ܾ�� �ɰ���
			strTemp[i] = ""; // ����� ���� ������� �ʱ�ȭ
			for (int j = 0; j < splitStrArr.length; j++) {
				if (searchStr.matches(splitStrArr[j])) {
					splitStrArr[j] = replaceStr; // �ܾ� �ٲ���
				}
				strTemp[i] += (splitStrArr[j] + " "); // �ٽ� ���ٷ� ��ħ
			}
		}

		// �� ��Ʈ���� 130����� �°� ������ ����
		String temp; // ���̸� �ʰ��ϴ� ��� �� �κи� ������ ����
		int remain; // �̸��� ��� ������ ���̸� ������ ����
		for (int i = 0; i < count - 1; i++) {
			if (strTemp[i].length() > 128) { // ���̰� 128�� �Ѵ� ��� -> 128�� ������ ���๮�� "\r\n"�� �����ؾ� �ϹǷ�
				temp = strTemp[i].substring(128); // 128~ �� ��Ʈ�� �����
				strTemp[i] = strTemp[i].substring(0, 128) + "\r\n"; // 0~127 ��Ʈ������ �ٲٰ� �ڿ� ���๮�� �ٿ���
				strTemp[i + 1] = temp + strTemp[i + 1]; // �ʰ��� �κ��� temp�� �� ���� ���� �տ� �ٿ���
			} else if (strTemp[i].length() < 128) { // ���̰� 128�� ���� ���ϴ� ���
				remain = 128 - strTemp[i].length(); // ���� ���� ���
				strTemp[i] = strTemp[i] + strTemp[i + 1].substring(0, remain) + "\r\n"; // ���±��� ��ŭ �� ���� ���ο��� �ҷ��ͼ� �ڿ�
																						// �ٿ��ְ� �� �ڿ��� ���๮�� ����
				strTemp[i + 1] = strTemp[i + 1].substring(remain); // �� ��ɿ��� �ҷ��� ��ŭ ������
			} else {
				strTemp[i] += "\r\n"; // ���̰� �� 128�̸� ���๮�ڸ� �ٿ���
			}
		}
		if (strTemp[count - 1].length() > 128) { // ������������ ��� ó���� �ȵǾ����� -> 128�� �ʰ��ϴ� ��츸 �������ָ��
			temp = strTemp[count - 1].substring(128);
			strTemp[count - 1] = strTemp[count - 1].substring(0, 128) + "\r\n";
			strTemp[count++] = temp; // �ʰ��Ⱥκ��� ���� strTemp�� �Ҵ�
		}
		printToFile(); // ���Ϸ� ���
	}

	public static void printToFile() {
		Scanner in = new Scanner(System.in);
		System.out.println("����� ���ϰ�θ� �Է��ϼ���:");
		OUTPUT_PATH = in.nextLine();
		byte receive[];
		FileOutputStream file;

		try {
			file = new FileOutputStream(OUTPUT_PATH); // ���� ������� ����
			BufferedOutputStream b_writer = new BufferedOutputStream(file);

			for (int i = 0; i < count; i++) {
				receive = strTemp[i].getBytes(); // ��Ʈ���� ����Ʈ�� ��ȯ
				b_writer.write(receive); // write ����
			}
			b_writer.flush(); // ���� ������ ��� write

			file.close();
			b_writer.close();
		} catch (IOException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		System.out.println("��������� �Ϸ�Ǿ����ϴ�.");
	}

	public static boolean isExistFile(String str) { // ������ �����ϴ��� Ȯ�����ִ� �޼ҵ�
		File file = new File(str);
		if (file.exists())
			return true;
		else
			return false;
	}
}
