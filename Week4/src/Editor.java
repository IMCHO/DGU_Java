import java.io.*; // input, output ����� ���� ���� ����
import java.util.Scanner; // �˻��� ��Ʈ���� �Է� �ޱ����� �ʿ��� import

import edu.dongguk.cse.pl.reportwritter.ReportWriter; // Jar ������ ����ϱ� ����

public class Editor extends Thread {
	public static int count = 0; // ���� ������ ���� ���� ����
	public static String INPUT_PATH;
	public static String OUTPUT_PATH;
	// public static final int BUFFER_SIZE = 130; // ���ۻ����� ���� -> ���� �� �ٴ� �ܾ 130���̹Ƿ�
	public static MyBufferedReader bufferedReader; // ���۸��� ���
	public static FileReader fileReader; // ���ϸ��� ���
	public static String[] strTemp = new String[100000];
	public static char space = ' '; // ���� ������ ���� ���� �ʿ��� ����
	public static String result; // ���� ����� ������ ����
	public static boolean isFileOpen = false; // ���ȴ��� �� ���ȴ��� �˷���

	public void run() {

	}

	public static void loadFile() {
		Scanner sc = new Scanner(System.in);
		boolean isCorrectPath = true; // �ݺ����� �÷���
		count = 0; // count �ʱ�ȭ
		strTemp = new String[100000]; // strTemp �ʱ�ȭ

		while (isCorrectPath) {
			INPUT_PATH = sc.nextLine();
			try {
				fileReader = new FileReader(INPUT_PATH); // �Էµ� ��ΰ����� new �Ҵ�
			} catch (Exception e) {
				System.out.println("�߸��� ����Դϴ�. �ٽ� �Է����ּ���!"); // ��ΰ� �������� ���� ��� �ٽ� �ݺ�
				continue;
			}

			bufferedReader = new MyBufferedReader(fileReader); // MyBufferedReader ��ü ���
			isFileOpen = true;
			// byte[] readBuffer = new byte[BUFFER_SIZE]; // ���� ������ ����

			try {
				while ((strTemp[count] = bufferedReader.readLine()) != null) { // ���๮�� �����ϰ� ���� ���� �о�ͼ� strTemp�� ����
					count++; // ���� ����
				}
			} catch (IOException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}
			arrangeString();
			/*
			 * for (int i = 0; i < count; i++) { strTemp[i] =
			 * strTemp[i].replaceAll("\r\n|\n\r|\r|\n", ""); // ���๮�� ���� }
			 */
			isCorrectPath = false; // �ݺ��� Ż��
		}
		//sc.close();
	}

	public static synchronized void searchString() {
		// Editor editor = new Editor();
		// editor.start();
		System.out.println("�˻��� �ܾ �Է��ϼ���. ");
		String input = ""; // �Է� ���� ���ڿ�
		Scanner in = new Scanner(System.in); // ���ڿ� �Է�
		input = in.next(); // �Է��� ���ڿ� input�� �Ҵ�
		int word_count = 0; // �ܾ� ������ ���� ���� ����
		String str = ""; // �˻���� ����
		str = "\r\n" + INPUT_PATH + " ���Ͽ��� " + input + "�� �˻� ����� ������ �����ϴ�.\r\n"; // ����� ������ �� ����

		CReport report = new CReport();
		ReportWriter reportWriter = new ReportWriter(); // �˻������ ����ϱ����� ���� 2�� ����
		report.setFileName("C:\\out.txt"); // ��� ��� �� �̸� ����

		for (int i = 0; i < count; i++) {
			String splitStrArr[] = strTemp[i].split(" "); // �� ���� ���� �������� ���� -> �ܾ�� �ɰ���
			for (int j = 0; j < splitStrArr.length; j++) {
				if (input.matches(splitStrArr[j])) { // �˻��ܾ�� ����Ǿ��ִ� �ܾ�� ������ ��
					str += (i + 1) + "��° �� " + (j + 1) + "��°\r\n";
					CSearchItem item = new CSearchItem(); // ��ġ�� ������ ���� ����
					word_count++; // �˻��� �ܾ� ī��Ʈ
					item.setIndexNo(j + 1); // ���° �ܾ����� ����
					item.setLineNo(i + 1); // ���° �� �ܾ����� ����
					report.addSearchItm(item); // list�� �߰�
				}
			}
		}
		str += "�̻� �˻������ �� " + word_count + "���� �˻��� �Ϸ�Ǿ����ϴ�.\r\n";
		result = str; // ���Ŀ� ����ϱ� ���� ���������� ����
		// System.out.println(str); // ��� ���

		report.setTotalSearchedNo(word_count); // �˻��� �ܾ��� �� ���� ����
		try {
			reportWriter.makeReport(report); // ��°�η� ���� ���
		} catch (IOException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		//in.close();
	}

	public static void writeFile() {
		Scanner in = new Scanner(System.in); // ���ڿ� �Է�
		String OUTPUT_PATH = in.nextLine();
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
		//in.close();
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
		arrangeString_130(); // ��Ʈ�� 130 ������� ���ġ
		printToFile(); // ���Ϸ� ���
		//in.close();
	}

	public static void printToFile() {
		Scanner in = new Scanner(System.in);
		System.out.println("����� ���ϰ�θ� �Է��ϼ���:");
		OUTPUT_PATH = in.nextLine();
		byte receive[];
		FileOutputStream file;

		try {
			file = new FileOutputStream(OUTPUT_PATH); // ���� ������� ����
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(file);

			for (int i = 0; i < count; i++) {
				receive = strTemp[i].getBytes(); // ��Ʈ���� ����Ʈ�� ��ȯ
				bufferedOutputStream.write(receive); // write ����
			}
			bufferedOutputStream.flush(); // ���� ������ ��� write

			file.close();
			bufferedOutputStream.close();
		} catch (IOException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		System.out.println("��������� �Ϸ�Ǿ����ϴ�.");
		//in.close();
	}

	public static void insertWord(int row, int col, String str) { // �ܾ� ���� �޼ҵ�
		StringBuilder strBuilder = new StringBuilder(strTemp[row - 1]); // �迭�̹Ƿ� row ���� 1�� ����� �˸��� �࿡ ���� ������ ����
		strBuilder.insert(col - 1, str); // col���� ���Ե� ���ڰ� ���� �ϹǷ� col�� 1�� ���� ���� ����
		strTemp[row - 1] = strBuilder.toString(); // �ٽ� string ������ ��ȯ �� �� ����
	}

	public static boolean isExistFile(String str) { // ������ �����ϴ��� Ȯ�����ִ� �޼ҵ�
		File file = new File(str);
		if (file.exists())
			return true;
		else
			return false;
	}

	public static void arrangeString_130() { // �� ��Ʈ���� 130����� �°� ������ ����, ���๮�ڵ� �ٿ���
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
	}

	public static void arrangeString() { // ���� ���� ������� ���ġ, ���๮�� ����
		for (int i = 0; i < count - 1; i++) {

			if (strTemp[i].charAt(strTemp[i].length() - 1) != space && strTemp[i + 1].charAt(0) != space) {
				// �� ���ٿ� ������ ���� ��� �̾����� �ܾ �����ٿ� ����
				// & ������ ù ���ڵ� ������ �ƴϿ�����(�����Ǿ��� �� ���)
				strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1);
				// �����ٷ� �Ѿ�� ù��° ���������� �ҷ��� �� ���� �� �ڿ� �ٿ���
				strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, 128);
				// �����ٿ��� �� ���� ���� �ܾ� ������ ���·� ���� ����
			}
		}
	}
}

class MyBufferedReader extends BufferedReader {
	FileReader fileReader;

	public MyBufferedReader(FileReader fileReader) { // ������
		super(fileReader);
		this.fileReader = fileReader;
	}

	@Override
	public String readLine() throws IOException { // �������̵� ���
		return super.readLine();
	}

	public MyBufferedReader resetToFisrt() {
		try {
			fileReader = new FileReader(Editor.OUTPUT_PATH); // ��� ����� ��θ� �Է� ��η� ����
		} catch (FileNotFoundException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		Editor.count = 0; // count �ʱ�ȭ
		Editor.strTemp = new String[100000]; // strTemp �ʱ�ȭ
		Editor.result = ""; // result �ʱ�ȭ
		return new MyBufferedReader(fileReader);
	}
}

class ImprovedEditor extends Editor {
	public static void appendString(String str, int line) {
		arrangeString_130(); // ������
		strTemp[line - 1] = strTemp[line - 1].substring(0, 128) + str + "\r\n";
		// ���๮�� ������ �߶󳻰� ���� ���ڿ��� ���� �� ���๮�� �ٽ� �ٿ���
		printToFile(); // ���Ϸ� ���
	}

	public static void searchString(String word, int[] line) { // �����ε� ���

		if (!isFileOpen) {
			return;
		} else {
			bufferedReader = bufferedReader.resetToFisrt(); // ������ ������ �ٽ� ó������ ��
		}

		try {
			while ((strTemp[count] = bufferedReader.readLine()) != null) { // ���๮�� �����ϰ� ���� ���� �о�ͼ� strTemp�� ����
				count++; // ���� ����
			}
		} catch (IOException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}

		for (int i = 0; line[i] != 0; i++) { // �ǵڿ� ����� 0�� ������
			int length; // ���° �ܾ����� Ȯ���ϱ� ����
			if (strTemp[line[i] - 1].substring(0, 1) != " ") {
				length = 0; // �� �� �ܾ ������ �ƴϸ� 0���ͽ���
			} else {
				length = 1; // �� �� �ܾ �����̸� 1���� ���� -> �ֳ��ϸ� ���� �������� ���ڿ��� �ڸ� ���̱� ������ �̸� ����� ����
			}

			String splitStrArr[] = strTemp[line[i] - 1].split(" "); // �� ���� ���� �������� ���� -> �ܾ�� �ɰ���
			for (int j = 0; j < splitStrArr.length; j++) {
				if (word.matches(splitStrArr[j])) { // �˻��ܾ�� ����Ǿ��ִ� �ܾ�� ������ ��
					result += line[i] + "��° ���� " + (length + 1) + "��° �ܾ�!\r\n";
				} else if (j == splitStrArr.length - 1) {
					result += line[i] + "��° �ٿ��� " + word + "�� ã�� �� �����ϴ�.\r\n";
				}
				length += (splitStrArr[j].length() + 1); // ���±��� ������ ���ڿ��� ���̸� ��� �����༭ ��ġ ǥ���� �� ���
			}
		}
		System.out.println("���� �� ���ϸ��� �Է��ϼ���. ");
		writeFile();
	}
}
