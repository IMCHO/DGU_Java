import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===메뉴선택===\n" + "데이터 파일 불러오기(O), 문자열 검색(S), 검색결과 파일 출력(P), 수정 후 결과 출력(R), 단어 덧붙이기(A), 특정 라인에서만 검색(L), 프로그램종료(Q)";

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
			case "A":
			case "a": {
				Scanner in = new Scanner(System.in);
				System.out.println("덧붙일 단어를 입력해주세요:");
				String appendStr = in.nextLine(); // 붙일 단어
				System.out.println("덧붙일 라인을 입력해주세요:");
				int appendLine = in.nextInt(); // 붙일 라인
				ImprovedEditor.appendString(appendStr,appendLine);
				break;
			}
			case "L":
			case "l": {
				Scanner in = new Scanner(System.in);
				System.out.println("검색할 단어를 입력해주세요:");
				String searchStr = in.nextLine(); // 검색할 단어
				System.out.println("검색할 라인들을 입력하고, '0'을 입력해주세요: ");
				int[] line=new int[100];
				int count=0;
				while((line[count]=in.nextInt())!=0) count++; // 0이 입력되면 종료
				ImprovedEditor.searchString(searchStr,line);
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
