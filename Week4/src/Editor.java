import java.io.*; // input, output 기능을 쓰기 위한 과정
import java.util.Scanner; // 검색할 스트링을 입력 받기위해 필요한 import

public class Editor {
	public static int count = 0; // 버퍼 개수를 세기 위한 변수
	public static String INPUT_PATH;
	public static String OUTPUT_PATH;
	public static final int BUFFER_SIZE = 130; // 버퍼사이즈 결정 -> 파일 한 줄당 단어가 130개이므로
	public static FileInputStream inputStream; // 파일스트림 사용
	public static BufferedInputStream bufferedInputStream; // 버퍼스트림 사용
	public static String[] strTemp = new String[100000];
	public static char space = ' '; // 공백 개수를 세기 위해 필요한 변수
	public static String result; // 최종 결과를 저장할 변수

	public static void loadFile() {
		Scanner sc = new Scanner(System.in);
		boolean isCorrectPath = true; // 반복조건 플래그
		count = 0; // count 초기화
		strTemp = new String[100000]; // strTemp 초기화

		while (isCorrectPath) {
			INPUT_PATH = sc.nextLine();
			try {
				inputStream = new FileInputStream(INPUT_PATH); // 입력된 경로값으로 new 할당
			} catch (Exception e) {
				System.out.println("잘못된 경로입니다. 다시 입력해주세요!"); // 경로가 존재하지 않을 경우 다시 반복
				continue;
			}

			bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
			byte[] readBuffer = new byte[BUFFER_SIZE]; // 버퍼 사이즈 결정

			try {
				while (bufferedInputStream.read(readBuffer, 0, readBuffer.length) != -1) { // 버퍼사이즈만큼 파일을 읽어와 저장
					strTemp[count++] = new String(readBuffer); // 읽어온 버퍼를 스트링으로 변환
				}
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}

			for (int i = 0; i < count - 1; i++) {

				if (strTemp[i].charAt(strTemp[i].length() - 1) != space && strTemp[i + 1].charAt(0) != space) { // 맨 뒷줄에
																												// 공백이
																												// 없는 경우
																												// 이어지는
																												// 단어 존재
																												// & 다음줄
																												// 첫 글자도
																												// 공백이
																												// 아니여야함(수정되었을
																												// 시 고려)
					strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1); // 다음줄로 넘어가서 첫번째
																									// 공백전까지 불러온 뒤
																									// 뒤에 붙여줌
					strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, 130); // 앞 쪽을 떼인 단어
																										// 제외하고 문장
																										// 수정
				}
			}
			for (int i = 0; i < count; i++) {
				strTemp[i] = strTemp[i].replaceAll("\r\n|\n\r|\r|\n", ""); // 개행문자 삭제
			}
			isCorrectPath = false; // 반복문 탈출
		}
	}

	public static void searchString() {
		String input = ""; // 입력 받을 문자열
		Scanner in = new Scanner(System.in); // 문자열 입력
		input = in.next(); // 입력한 문자열 input에 할당
		int word_count = 0; // 단어 개수를 세기 위한 변수
		result = "\r\n" + INPUT_PATH + " 파일에서 " + input + "의 검색 결과는 다음과 같습니다.\r\n"; // 결과를 저장해 줄 변수

		for (int i = 0; i < count; i++) {
			String splitStrArr[] = strTemp[i].split(" "); // 한 줄을 공백 기준으로 나눔 -> 단어마다 쪼개짐
			for (int j = 0; j < splitStrArr.length; j++) {
				if (input.matches(splitStrArr[j])) {
					result += (i + 1) + "번째 줄 " + (j + 1) + "번째\r\n"; // 검색단어와 저장되어있는 단어와 같은지 비교
					word_count++;
				}
			}
		}
		result += "이상 검색결과로 총 " + word_count + "개의 검색이 완료되었습니다.\r\n";
		System.out.println(result); // 결과 출력
	}

	public static void writeFile() {
		Scanner in = new Scanner(System.in); // 문자열 입력
		OUTPUT_PATH = in.nextLine();
		File file = new File(OUTPUT_PATH);
		FileWriter filewriter;

		if (file.exists()) { // 파일이 존재하면 붙여넣기 유무 물어보기
			System.out.println("붙여 넣기 하시겠습니까?(y/n)");
			String findWord;
			boolean isExitProgram = false; // 프로그램 종료 플래그

			while (!isExitProgram) {
				findWord = in.nextLine();
				switch (findWord) {
				case "Y":
				case "y": {
					try {
						filewriter = new FileWriter(file, true); // 붙여넣기
						filewriter.write(result);
						filewriter.close();
					} catch (IOException e) {
						// TODO 자동 생성된 catch 블록
						e.printStackTrace();
					}
					isExitProgram = true;
					break;
				}
				case "N":
				case "n": {
					try {
						filewriter = new FileWriter(file, false); // 덮어쓰기
						filewriter.write(result);
						filewriter.close();
					} catch (IOException e) {
						// TODO 자동 생성된 catch 블록
						e.printStackTrace();
					}
					isExitProgram = true;
					break;
				}
				default:
					System.out.println("알 수 없는 명령입니다. 한번 더 입력해주세요!");
					break;
				}
			}
		} else {
			try {
				filewriter = new FileWriter(file, false); // 새로 쓰기
				filewriter.write(result);
				filewriter.close();
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
	}

	public static void replaceWord() {
		Scanner in = new Scanner(System.in);
		System.out.println("검색할 단어를 입력해주세요:");
		String searchStr = in.nextLine(); // 검색 단어
		System.out.println("교체할 단어를 입력해주세요:");
		String replaceStr = in.nextLine(); // 바꿀 단어

		for (int i = 0; i < count; i++) {
			String splitStrArr[] = strTemp[i].split(" "); // 한 줄을 공백 기준으로 나눔 -> 단어마다 쪼개짐
			strTemp[i] = ""; // 변경된 값을 담기위해 초기화
			for (int j = 0; j < splitStrArr.length; j++) {
				if (searchStr.matches(splitStrArr[j])) {
					splitStrArr[j] = replaceStr; // 단어 바꿔줌
				}
				strTemp[i] += (splitStrArr[j] + " "); // 다시 한줄로 합침
			}
		}

		// 각 스트링을 130사이즈에 맞게 재조정 시작
		String temp; // 길이를 초과하는 경우 그 부분만 저장할 변수
		int remain; // 미만인 경우 부족한 길이를 저장할 변수
		for (int i = 0; i < count - 1; i++) {
			if (strTemp[i].length() > 128) { // 길이가 128을 넘는 경우 -> 128인 이유는 개행문자 "\r\n"을 제외해야 하므로
				temp = strTemp[i].substring(128); // 128~ 의 스트링 떼어옴
				strTemp[i] = strTemp[i].substring(0, 128) + "\r\n"; // 0~127 스트링으로 바꾸고 뒤에 개행문자 붙여줌
				strTemp[i + 1] = temp + strTemp[i + 1]; // 초과된 부분인 temp를 그 다음 라인 앞에 붙여줌
			} else if (strTemp[i].length() < 128) { // 길이가 128을 넘지 못하는 경우
				remain = 128 - strTemp[i].length(); // 남는 길이 계산
				strTemp[i] = strTemp[i] + strTemp[i + 1].substring(0, remain) + "\r\n"; // 남는길이 만큼 그 다음 라인에서 불러와서 뒤에
																						// 붙여주고 맨 뒤에는 개행문자 삽입
				strTemp[i + 1] = strTemp[i + 1].substring(remain); // 앞 명령에서 불러온 만큼 없애줌
			} else {
				strTemp[i] += "\r\n"; // 길이가 딱 128이면 개행문자만 붙여줌
			}
		}
		if (strTemp[count - 1].length() > 128) { // 마지막라인의 경우 처리가 안되어있음 -> 128을 초과하는 경우만 생각해주면됨
			temp = strTemp[count - 1].substring(128);
			strTemp[count - 1] = strTemp[count - 1].substring(0, 128) + "\r\n";
			strTemp[count++] = temp; // 초과된부분은 새로 strTemp에 할당
		}
		printToFile(); // 파일로 출력
	}

	public static void printToFile() {
		Scanner in = new Scanner(System.in);
		System.out.println("출력할 파일경로를 입력하세요:");
		OUTPUT_PATH = in.nextLine();
		byte receive[];
		FileOutputStream file;

		try {
			file = new FileOutputStream(OUTPUT_PATH); // 파일 생성경로 설정
			BufferedOutputStream b_writer = new BufferedOutputStream(file);

			for (int i = 0; i < count; i++) {
				receive = strTemp[i].getBytes(); // 스트링을 바이트로 변환
				b_writer.write(receive); // write 해줌
			}
			b_writer.flush(); // 남은 데이터 모두 write

			file.close();
			b_writer.close();
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		System.out.println("파일출력이 완료되었습니다.");
	}

	public static boolean isExistFile(String str) { // 파일이 존재하는지 확인해주는 메소드
		File file = new File(str);
		if (file.exists())
			return true;
		else
			return false;
	}
}
