import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===�޴�����===\n" + "������ ���� �ҷ�����(O), ���ڿ� �˻�(S), �˻���� ���� ���(P), ���� �� ��� ���(R), ���α׷�����(Q)";

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
				System.out.println("�˻��� �ܾ �Է��ϼ���. ");
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
