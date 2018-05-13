import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===메뉴선택===\n" + "데이터 파일 불러오기(O), 문자열 검색(S), 검색결과 파일 출력(P), 수정 후 결과 출력(R), 프로그램종료(Q)";

	public static void startConsoleMenu() { // 콘솔메뉴 출력 메소드
		Scanner sc = new Scanner(System.in);
		String findWord;
		boolean isExitProgram = false; // 프로그램 종료 플래그
		while (!isExitProgram) {
			System.out.println(MENU_TEXT);
			findWord = sc.nextLine();
			switch (findWord) {
			case "O":
			case "o": {
				System.out.println("불러올 파일명을 입력하세요. ");
				Editor.loadFile();
				break;
			}
			case "S":
			case "s": {
				System.out.println("검색할 단어를 입력하세요. ");
				Editor.searchString();
				break;
			}
			case "P":
			case "p": {
				System.out.println("저장 할 파일명을 입력하세요. ");
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
				System.out.println("프로그램을 종료합니다. ");
				isExitProgram = true;
				break;
			}
			default:
				System.out.println("알 수 없는 명령입니다. 한번 더 입력해주세요!");
				break;
			}
		}
		sc.close();
	}

}
