import java.io.*; // input, output 기능을 쓰기 위한 과정
import java.util.ArrayList;
import java.util.Scanner; // 검색할 스트링을 입력 받기위해 필요한 import

import edu.dongguk.cse.pl.reportwritter.ReportWriter; // Jar 파일을 사용하기 위해

public class Editor extends Thread {
	public static int count = 0; // 버퍼 개수를 세기 위한 변수
	public static String INPUT_PATH;
	public static String OUTPUT_PATH;
	// public static final int BUFFER_SIZE = 130; // 버퍼사이즈 결정 -> 파일 한 줄당 단어가 130개이므로
	public static MyBufferedReader bufferedReader; // 버퍼리더 사용
	public static FileReader fileReader; // 파일리더 사용
	public static String[] strTemp = new String[100000];
	public static char space = ' '; // 공백 개수를 세기 위해 필요한 변수
	public static String result; // 최종 결과를 저장할 변수
	public static ArrayList<String> strArrList = new ArrayList<String>(); // 최종 결과를 저장할 변수
	public static boolean isFileOpen = false; // 열렸는지 안 열렸는지 알려줌
	public static CReport report = new CReport();
	public static ReportWriter reportWriter = new ReportWriter(); // 검색결과를 출력하기위한 변수 2개 선언
	public static int word_count = 0; // 단어 개수를 세기 위한 변수

	public void run() {

	}

	public static boolean loadFile(String path) { // 파일 열기 여부를 알리기 위해 반환형을 boolean으로 변경
		// Scanner sc = new Scanner(System.in);
		INPUT_PATH = path;
		// boolean isCorrectPath = true; // 반복조건 플래그
		count = 0; // count 초기화
		word_count = 0; // 초기화
		strTemp = new String[100000]; // strTemp 초기화

		// INPUT_PATH = sc.nextLine();
		try {
			fileReader = new FileReader(path); // 입력된 경로값으로 new 할당
		} catch (Exception e) {
			return false;
		}

		bufferedReader = new MyBufferedReader(fileReader); // MyBufferedReader 객체 사용
		isFileOpen = true;
		// byte[] readBuffer = new byte[BUFFER_SIZE]; // 버퍼 사이즈 결정

		try {
			while ((strTemp[count] = bufferedReader.readLine()) != null) { // 개행문자 제외하고 한줄 한줄 읽어와서 strTemp에 저장
				count++; // 개수 증가
			}
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		arrangeString();

		/*
		 * for (int i = 0; i < count; i++) { strTemp[i]+="/r/n"; //isCorrectPath =
		 * false; // 반복문 탈출 }
		 */
		// sc.close();
		return true;

	}

	public static void searchString(String searchWord) {
		// Editor editor = new Editor();
		// editor.start();
		// System.out.println("검색할 단어를 입력하세요. ");
		// String input = ""; // 입력 받을 문자열
		// Scanner in = new Scanner(System.in); // 문자열 입력
		// input = in.next(); // 입력한 문자열 input에 할당
		if (report.getSearchList() != null)
			report.getSearchList().clear(); // 출력 결과가 중복되지 않게 하기위해
		word_count = 0;
		strArrList.clear();
		strArrList.add("\r\n" + INPUT_PATH + " 파일에서 " + searchWord + "의 검색 결과는 다음과 같습니다."); // 결과를 저장해 줄 변수

		for (int i = 0; i < count; i++) {
			String splitStrArr[] = strTemp[i].split(" "); // 한 줄을 공백 기준으로 나눔 -> 단어마다 쪼개짐
			for (int j = 0; j < splitStrArr.length; j++) {
				if (searchWord.matches(splitStrArr[j])) { // 검색단어와 저장되어있는 단어와 같은지 비교
					strArrList.add((i + 1) + "번째 줄 " + (j + 1) + "번째");
					CSearchItem item = new CSearchItem(); // 위치를 설정할 변수 선언
					word_count++; // 검색된 단어 카운트
					item.setIndexNo(j + 1); // 몇번째 단어인지 설정
					item.setLineNo(i + 1); // 몇번째 줄 단어인지 설정
					report.addSearchItm(item); // list에 추가
				}
			}
		}
		strArrList.add("이상 검색결과로 총 " + word_count + "개의 검색이 완료되었습니다.");
		// System.out.println(str); // 결과 출력

		// in.close();
	}

	public static boolean writeFile(String path) {
		OUTPUT_PATH = path;
		report.setFileName(path); // 출력 경로 및 이름 설정
		report.setTotalSearchedNo(word_count); // 검색된 단어의 총 개수 설정
		try {
			reportWriter.makeReport(report); // 출력경로로 파일 출력
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
			return false;
		}
		return true;
		/*
		 * Scanner in = new Scanner(System.in); // 문자열 입력 String OUTPUT_PATH =
		 * in.nextLine(); File file = new File(OUTPUT_PATH); FileWriter filewriter;
		 * 
		 * if (file.exists()) { // 파일이 존재하면 붙여넣기 유무 물어보기
		 * System.out.println("붙여 넣기 하시겠습니까?(y/n)"); String findWord; boolean
		 * isExitProgram = false; // 프로그램 종료 플래그
		 * 
		 * while (!isExitProgram) { findWord = in.nextLine(); switch (findWord) { case
		 * "Y": case "y": { try { filewriter = new FileWriter(file, true); // 붙여넣기
		 * filewriter.write(result); filewriter.close(); } catch (IOException e) { //
		 * TODO 자동 생성된 catch 블록 e.printStackTrace(); } isExitProgram = true; break; }
		 * case "N": case "n": { try { filewriter = new FileWriter(file, false); // 덮어쓰기
		 * filewriter.write(result); filewriter.close(); } catch (IOException e) { //
		 * TODO 자동 생성된 catch 블록 e.printStackTrace(); } isExitProgram = true; break; }
		 * default: System.out.println("알 수 없는 명령입니다. 한번 더 입력해주세요!"); break; } } } else
		 * { try { filewriter = new FileWriter(file, false); // 새로 쓰기
		 * filewriter.write(result); filewriter.close(); } catch (IOException e) { //
		 * TODO 자동 생성된 catch 블록 e.printStackTrace(); } } // in.close();
		 */
	}

	public static boolean replaceWord(String searchWord, String changeWord, String path) {
		/*
		 * Scanner in = new Scanner(System.in); System.out.println("검색할 단어를 입력해주세요:");
		 * String searchStr = in.nextLine(); // 검색 단어
		 * System.out.println("교체할 단어를 입력해주세요:"); String replaceStr = in.nextLine(); //
		 * 바꿀 단어
		 */

		for (int i = 0; i < count; i++) {
			String splitStrArr[] = strTemp[i].split(" "); // 한 줄을 공백 기준으로 나눔 -> 단어마다 쪼개짐
			strTemp[i] = ""; // 변경된 값을 담기위해 초기화
			for (int j = 0; j < splitStrArr.length; j++) {
				if (searchWord.matches(splitStrArr[j])) {
					splitStrArr[j] = changeWord; // 단어 바꿔줌
				}
				strTemp[i] += (splitStrArr[j] + " "); // 다시 한줄로 합침
			}
		}
		arrangeString_130(); // 스트링 130 사이즈로 재배치
		if (printToFile(path)) { // 파일로 출력
			return true;
		} else {
			return false;
		}
		// in.close();
	}

	public static boolean printToFile(String path) {
		/*
		 * Scanner in = new Scanner(System.in); System.out.println("출력할 파일경로를 입력하세요:");
		 * OUTPUT_PATH = in.nextLine();
		 */
		byte receive[];
		FileOutputStream file;
		OUTPUT_PATH = path;
		try {
			file = new FileOutputStream(path); // 파일 생성경로 설정
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(file);

			for (int i = 0; i < count; i++) {
				receive = strTemp[i].getBytes(); // 스트링을 바이트로 변환
				bufferedOutputStream.write(receive); // write 해줌
			}
			bufferedOutputStream.flush(); // 남은 데이터 모두 write

			file.close();
			bufferedOutputStream.close();
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
			return false;
		}
		// System.out.println("파일출력이 완료되었습니다.");
		// in.close();
		return true;
	}

	public static boolean insertWord(String row, String col, String str, String path) { // 단어 삽입 메소드
		arrangeString();
		StringBuilder strBuilder = new StringBuilder(strTemp[Integer.parseInt(row) - 1]); // 배열이므로 row 값에 1을 빼줘야 알맞은 행에
																							// 대한 수정이 가능
		strBuilder.insert(Integer.parseInt(col) - 1, str); // col부터 삽입된 문자가 들어가야 하므로 col에 1을 빼준 값을 대입
		strTemp[Integer.parseInt(row) - 1] = strBuilder.toString(); // 다시 string 변수로 변환 한 뒤 대입
		arrangeString_130();
		if (printToFile(path)) { // 파일로 출력
			return true;
		} else {
			return false;
		}
	}

	public static boolean isExistFile(String str) { // 파일이 존재하는지 확인해주는 메소드
		File file = new File(str);
		if (file.exists())
			return true;
		else
			return false;
	}

	public static void arrangeString_130() { // 각 스트링을 130사이즈에 맞게 재조정 시작, 개행문자도 붙여줌
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
	}

	public static void arrangeString() { // 글자 수에 상관없이 재배치, 개행문자 없음
		for(int i=0;i<count;i++) { // 개행문자가 없는 상태여야 제대로 돌아감
			strTemp[i]=strTemp[i].replaceAll("\r\n", "");
		}
		for (int i = 0; i < count - 1; i++) {

			if (strTemp[i].charAt(strTemp[i].length() - 1) != space && strTemp[i + 1].charAt(0) != space) {
				// 맨 뒷줄에 공백이 없는 경우 이어지는 단어가 다음줄에 존재
				// & 다음줄 첫 글자도 공백이 아니여야함(수정되었을 시 고려)
				strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1);
				// 다음줄로 넘어가서 첫번째 공백전까지 불러온 뒤 원래 줄 뒤에 붙여줌
				strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, strTemp[i+1].length());
				// 다음줄에서 앞 쪽을 떼인 단어 제외한 상태로 문장 수정
			}
		}
	}
}

class MyBufferedReader extends BufferedReader {
	FileReader fileReader;

	public MyBufferedReader(FileReader fileReader) { // 생성자
		super(fileReader);
		this.fileReader = fileReader;
	}

	@Override
	public String readLine() throws IOException { // 오버라이딩 사용
		return super.readLine();
	}

	public MyBufferedReader resetToFisrt() {
		try {
			fileReader = new FileReader(Editor.OUTPUT_PATH); // 방금 출력한 경로를 입력 경로로 넣음
		} catch (FileNotFoundException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		Editor.count = 0; // count 초기화
		Editor.strTemp = new String[100000]; // strTemp 초기화
		Editor.result = ""; // result 초기화
		return new MyBufferedReader(fileReader);
	}
}

class ImprovedEditor extends Editor {
	public static void appendString(String str, int line) {
		arrangeString_130(); // 재정렬
		strTemp[line - 1] = strTemp[line - 1].substring(0, 128) + str + "\r\n";
		// 개행문자 전까지 잘라내고 붙일 문자열을 붙인 뒤 개행문자 다시 붙여줌
		// printToFile(); // 파일로 출력
	}

	public static void searchString(String word, int[] line) { // 오버로딩 사용

		if (!isFileOpen) {
			return;
		} else {
			bufferedReader = bufferedReader.resetToFisrt(); // 열었던 파일을 다시 처음부터 엶
		}

		try {
			while ((strTemp[count] = bufferedReader.readLine()) != null) { // 개행문자 제외하고 한줄 한줄 읽어와서 strTemp에 저장
				count++; // 개수 증가
			}
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}

		for (int i = 0; line[i] != 0; i++) { // 맨뒤에 저장된 0은 제외해
			int length; // 몇번째 단어인지 확인하기 위해
			if (strTemp[line[i] - 1].substring(0, 1) != " ") {
				length = 0; // 맨 앞 단어가 공백이 아니면 0부터시작
			} else {
				length = 1; // 맨 앞 단어가 공백이면 1부터 시작 -> 왜냐하면 공백 기준으로 문자열을 자를 것이기 때문에 미리 고려해 놓음
			}

			String splitStrArr[] = strTemp[line[i] - 1].split(" "); // 한 줄을 공백 기준으로 나눔 -> 단어마다 쪼개짐
			for (int j = 0; j < splitStrArr.length; j++) {
				if (word.matches(splitStrArr[j])) { // 검색단어와 저장되어있는 단어와 같은지 비교
					result += line[i] + "번째 줄의 " + (length + 1) + "번째 단어!\r\n";
				} else if (j == splitStrArr.length - 1) {
					result += line[i] + "번째 줄에는 " + word + "를 찾을 수 없습니다.\r\n";
				}
				length += (splitStrArr[j].length() + 1); // 여태까지 참조한 문자열의 길이를 계속 더해줘서 위치 표현할 떄 사용
			}
		}
		System.out.println("저장 할 파일명을 입력하세요. ");
		writeFile(OUTPUT_PATH);
	}
}
