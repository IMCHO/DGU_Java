import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===�޴�����===\n" + "������ ���� �ҷ�����(O), ���ڿ� �˻�(S), �˻���� ���� ���(P), ���� �� ��� ���(R), �ܾ� �����̱�(A), Ư�� ���ο����� �˻�(L), ���α׷�����(Q)";

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
				Editor.loadFile();
				break;
			}
			case "S":
			case "s": {
				Editor.searchString();
				break;
			}
			case "P":
			case "p": {
				System.out.println("���� �� ���ϸ��� �Է��ϼ���. ");
				Editor.writeFile();
				break;
			}
			case "R":
			case "r": {
				Editor.replaceWord();
				break;
			}
			case "A":
			case "a": {
				Scanner in = new Scanner(System.in);
				System.out.println("������ �ܾ �Է����ּ���:");
				String appendStr = in.nextLine(); // ���� �ܾ�
				System.out.println("������ ������ �Է����ּ���:");
				int appendLine = in.nextInt(); // ���� ����
				ImprovedEditor.appendString(appendStr,appendLine);
				break;
			}
			case "L":
			case "l": {
				Scanner in = new Scanner(System.in);
				System.out.println("�˻��� �ܾ �Է����ּ���:");
				String searchStr = in.nextLine(); // �˻��� �ܾ�
				System.out.println("�˻��� ���ε��� �Է��ϰ�, '0'�� �Է����ּ���: ");
				int[] line=new int[100];
				int count=0;
				while((line[count]=in.nextInt())!=0) count++; // 0�� �ԷµǸ� ����
				ImprovedEditor.searchString(searchStr,line);
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
		}
		sc.close();
	}

}
