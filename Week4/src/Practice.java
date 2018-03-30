import java.io.*; // input, output 기능을 쓰기 위한 과정
import java.util.Scanner; // 검색할 스트링을 입력 받기위해 필요한 import

public class Practice {
	public static final String INPUT_PATH = "c:\\test.txt"; // 경로설정
	public static final int BUFFER_SIZE = 130; // 버퍼사이즈 결정 -> 파일 한 줄당 단어가 130개이므로

	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream(INPUT_PATH); // 파일스트림 사용
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE); // 버퍼스트림 사용

		String input = ""; // 입력 받을 문자열
		Scanner in = new Scanner(System.in); // 문자열 입력
		input = in.next(); // 입력한 문자열 input에 할당
		int count = 0; // 버퍼 개수를 세기 위한 변수
		char space = ' '; // 공백 개수를 세기 위해 필요한 변수
		int word_count = 0; // 단어 개수를 세기 위한 변수
		String[] strTemp = new String[100000];

		byte[] readBuffer = new byte[BUFFER_SIZE]; // 버퍼 사이즈 결정
		while (bufferedInputStream.read(readBuffer, 0, readBuffer.length) != -1) { // 버퍼사이즈만큼 파일을 읽어와 저장
			strTemp[count++] = new String(readBuffer); // 읽어온 버퍼를 스트링으로 변환
		}
		for (int i = 0; i < count - 1; i++) {
			if (strTemp[i].charAt(strTemp[i].length() - 1) != space) { // 맨 뒷줄에 공백이 없는 경우 이어지는 단어 존재
				strTemp[i] += strTemp[i + 1].substring(0, strTemp[i + 1].indexOf(space) + 1); // 다음줄로 넘어가서 첫번째 공백전까지 불러온
																								// 뒤 뒤에 붙여줌
				strTemp[i + 1] = strTemp[i + 1].substring(strTemp[i + 1].indexOf(space) + 1, 130); // 앞 쪽을 떼인 단어 제외하고 문장
																									// 수정
			}
		}
		for (int i = 0; i <= count; i++) {
			int check = strTemp[i].indexOf(input); // 핵심 코드 -> 검색하고자 하는 스트링이 있는지 확인
			if (check == -1)
				continue; // 이 버퍼에 없으면 다음 버퍼 읽음
			else { // 찾으면 위치 출력
				for (int j = 0; j < check; j++) {
					if (strTemp[i].charAt(j) == space)
						word_count++; // 공백 개수를 기준으로 단어 개수 파악
				}
				System.out.println((i + 1) + "번째 줄 " + (word_count + 1) + "번째 단어");
				break;
			}

		}

		bufferedInputStream.close(); // 스트림 닫기
		in.close();
	}

}
