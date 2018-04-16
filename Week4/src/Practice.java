import java.io.*; // input, output 기능을 쓰기 위한 과정
import java.util.Scanner; // 검색할 스트링을 입력 받기위해 필요한 import

public class Practice {
	public static final int BUFFER_SIZE = 130; // 버퍼사이즈 결정 -> 파일 한 줄당 단어가 130개이므로
	public static String INPUT_PATH;
	public static String OUTPUT_PATH;
	public static final String MENU_TEXT = "\t\t===메뉴선택===\n" + "데이터 파일 불러오기(O), 문자열 검색(S), 검색결과 파일 출력(P), 프로그램종료(Q)";
	public static FileInputStream inputStream; // 파일스트림 사용
	public static BufferedInputStream bufferedInputStream; // 버퍼스트림 사용
	public static String[] strTemp = new String[100000];
	public static int count = 0; // 버퍼 개수를 세기 위한 변수
	public static char space = ' '; // 공백 개수를 세기 위해 필요한 변수
	public static String result; // 최종 결과를 저장할 변수

	public static void main(String[] args) throws IOException {
		startConsoleMenu();
		inputStream.close();
		bufferedInputStream.close(); // 스트림 닫기

	}

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
				if (count != 0)
					count = 0;
				System.out.println("불러올 파일명을 입력하세요. ");
				loadFile();
				break;
			}
			case "S":
			case "s": {
				System.out.println("검색할 단어를 입력하세요. ");
				searchString();
				break;
			}
			case "P":
			case "p": {
				System.out.println("저장 할 파일명을 입력하세요. ");
				writeFile();
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
			// sc.nextLine();
		}
		sc.close();
	}

	public static void loadFile() {
		Scanner sc2 = new Scanner(System.in);
		boolean isCorrectPath = true; // 반복조건 플래그
		while (isCorrectPath) {
			INPUT_PATH = sc2.nextLine();
			try {
				inputStream = new FileInputStream(INPUT_PATH); // 입력된 경로값으로 new 할당
				bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
				byte[] readBuffer = new byte[BUFFER_SIZE]; // 버퍼 사이즈 결정
				while (bufferedInputStream.read(readBuffer, 0, readBuffer.length) != -1) { // 버퍼사이즈만큼 파일을 읽어와 저장
					strTemp[count++] = new String(readBuffer); // 읽어온 버퍼를 스트링으로 변환
				}

				for (int i = 0; i < count - 1; i++) {

					if (strTemp[i].charAt(strTemp[i].length() - 1) != space) { // 맨 뒷줄에 공백이 없는 경우 이어지는 단어 존재
						strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1); // 다음줄로 넘어가서 첫번째
																										// 공백전까지 불러온
																										// 뒤 뒤에 붙여줌
						strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, 130); // 앞 쪽을 떼인 단어
																											// 제외하고 문장
																											// 수정
					}
				}
				for (int i = 0; i < count; i++) {
					strTemp[i] = strTemp[i].replaceAll("\r\n|\n\r|\r|\n", ""); // 개행문자 삭제
				}
				isCorrectPath = false; // 반복문 탈출
			} catch (Exception e) {
				System.out.println("잘못된 경로입니다. 다시 입력해주세요!"); // 경로가 존재하지 않을 경우 다시 반족
				continue;
			}
		}

	}

	public static void searchString() {
		String input = ""; // 입력 받을 문자열
		Scanner in = new Scanner(System.in); // 문자열 입력
		input = in.next(); // 입력한 문자열 input에 할당
		int word_count = 0; // 단어 개수를 세기 위한 변수
		result = INPUT_PATH + " 파일에서 " + input + "의 검색 결과는 다음과 같습니다.\n"; // 결과를 저장해 줄 변수

		// System.out.println(strTemp[64897]);
		for (int i = 0; i < count; i++) {

			String splitStrArr[] = strTemp[i].split(" "); // 한 줄을 공백 기준으로 나눔 -> 단어마다 쪼개짐
			// System.out.println(strTemp[i]);

			for (int j = 0; j < splitStrArr.length; j++) {

				if (input.matches(splitStrArr[j])) {
					result += (i + 1) + "번쨰 줄 " + (j + 1) + "번째\n"; // 검색단어와 저장되어있는 단어와 같은지 비교
					word_count++;
				}
			}
			// if(i==64897) System.out.println(splitStrArr[splitStrArr.length-1]);
			// break;
		}

		result += "이상 검색결과로 총 " + word_count + "개의 검색이 완료되었습니다.\n";
		System.out.println(result); // 결과 출력
	}

	public static void writeFile() {
		Scanner in = new Scanner(System.in); // 문자열 입력
		OUTPUT_PATH=in.nextLine();
		File file = new File(OUTPUT_PATH);
		//FileOutputStream fos=new FileOutputStream(file);
		if(file.exists()) {
			System.out.println("붙여 넣기 하시겠습니까?(y/n)");
			String findWord;
			boolean isExitProgram = false; // 프로그램 종료 플래그
			while (!isExitProgram) {
				findWord = in.nextLine();
				switch (findWord) {
				case "Y":
				case "y": {
					
					isExitProgram = true;
					break;
				}
				case "N":
				case "n": {
					file.w
					isExitProgram = true;
					break;
				}
				default:
					System.out.println("알 수 없는 명령입니다. 한번 더 입력해주세요!");
					break;
				}
		}
	}
		else {
			
		}
}
}
