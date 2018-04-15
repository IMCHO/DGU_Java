import java.io.*; // input, output ����� ���� ���� ����
import java.util.Scanner; // �˻��� ��Ʈ���� �Է� �ޱ����� �ʿ��� import

public class Practice {
	public static final int BUFFER_SIZE = 130; // ���ۻ����� ���� -> ���� �� �ٴ� �ܾ 130���̹Ƿ�
	public static String INPUT_PATH;
	public static final String MENU_TEXT = "\t\t===�޴�����===\n" + "������ ���� �ҷ�����(O), ���ڿ� �˻�(S), �˻���� ���� ���(P), ���α׷�����(Q)";
	public static FileInputStream inputStream; // ���Ͻ�Ʈ�� ���
	public static BufferedInputStream bufferedInputStream; // ���۽�Ʈ�� ���
	public static String[] strTemp = new String[100000];
	public static int count = 0; // ���� ������ ���� ���� ����
	public static char space = ' '; // ���� ������ ���� ���� �ʿ��� ����

	public static void main(String[] args) throws IOException {
		startConsoleMenu();
		inputStream.close(); 
		bufferedInputStream.close(); // ��Ʈ�� �ݱ�

	}

	public static void startConsoleMenu() { // �ָܼ޴� ��� �޼ҵ�
		Scanner sc = new Scanner(System.in);
		String findWord;
		boolean isExitProgram = false; // ���α׷� ���� �÷���
		while (!isExitProgram) {
			System.out.println(MENU_TEXT);
			findWord = sc.nextLine();
			switch (findWord) {
			case "O":
			case "o": {
				System.out.println("�ҷ��� ���ϸ��� �Է��ϼ���. ");
				loadFile();
				break;
			}
			case "S":
			case "s": {
				System.out.println("�˻��� �ܾ �Է��ϼ���. ");
				searchString();
				break;
			}
			case "P":
			case "p": {
				System.out.println("���� �� ���ϸ��� �Է��ϼ���. ");
				break;
			}
			case "Q":
			case "q": {
				System.out.println("���α׷��� �����մϴ�. ");
				isExitProgram = true;
				break;
			}
			default:
				System.out.println("�� �� ���� ����Դϴ�. �ѹ� �� �Է����ּ���!");
				break;
			}
			// sc.nextLine();
		}
		sc.close();
	}

	public static void loadFile() {
		Scanner sc2 = new Scanner(System.in);
		boolean isCorrectPath = true; // �ݺ����� �÷���
		while (isCorrectPath) {
			INPUT_PATH = sc2.nextLine();
			try {
				inputStream = new FileInputStream(INPUT_PATH); // �Էµ� ��ΰ����� new �Ҵ�
				bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
				byte[] readBuffer = new byte[BUFFER_SIZE]; // ���� ������ ����
				while (bufferedInputStream.read(readBuffer, 0, readBuffer.length) != -1) { // ���ۻ����ŭ ������ �о�� ����
					strTemp[count++] = new String(readBuffer); // �о�� ���۸� ��Ʈ������ ��ȯ
				}
				for (int i = 0; i < count - 1; i++) {
					strTemp[i]=strTemp[i].replaceAll("\r\n|\n\r|\r|\n", "");
					if (strTemp[i].charAt(strTemp[i].length() - 1) != space) { // �� ���ٿ� ������ ���� ��� �̾����� �ܾ� ����
						strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1); // �����ٷ� �Ѿ�� ù��°
																										// ���������� �ҷ���
																										// �� �ڿ� �ٿ���
						strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, 130); // �� ���� ���� �ܾ�
																											// �����ϰ� ����
																											// ����
					}
				}
				isCorrectPath = false; // �ݺ��� Ż��
			} catch (Exception e) {
				System.out.println("�߸��� ����Դϴ�. �ٽ� �Է����ּ���!"); // ��ΰ� �������� ���� ��� �ٽ� ����
				continue;
			}
		}

	}
	
	public static void searchString() {
		String input = ""; // �Է� ���� ���ڿ�
		Scanner in = new Scanner(System.in); // ���ڿ� �Է�
		input = in.next(); // �Է��� ���ڿ� input�� �Ҵ�
		int word_count = 0; // �ܾ� ������ ���� ���� ����
		String result=INPUT_PATH+" ���Ͽ��� "+input+"�� �˻� ����� ������ �����ϴ�.\n"; // ����� ������ �� ����
		
		for (int i = 0; i < count; i++) {
			String splitStrArr[]=strTemp[i].split(" "); // �� ���� ���� �������� ���� -> �ܾ�� �ɰ���
			//System.out.println(strTemp[64897].indexOf("lpmm"));

			for(int j=0;j<splitStrArr.length;j++) {
				
				if(input.matches(splitStrArr[j])) {
					result+= (i+1)+"���� �� "+(j+1)+"��°\n"; // �˻��ܾ�� ����Ǿ��ִ� �ܾ�� ������ ��
					word_count++;
					
				}
			}
			if(i==64897) System.out.println(splitStrArr[splitStrArr.length-1]);
			//break;
		}
		
		result+="�̻� �˻������ �� "+word_count+"���� �˻��� �Ϸ�Ǿ����ϴ�.\n";
		//System.out.println(result);
	}
}
