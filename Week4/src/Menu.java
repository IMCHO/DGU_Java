import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame extends JFrame {
	public MyFrame(String text) {
		super(text);
	}
}

public class Menu implements ActionListener {
	/*
	 * public static final String MENU_TEXT = "\t\t===메뉴선택===\n" +
	 * "데이터 파일 불러오기(O), 문자열 검색(S), 검색결과 파일 출력(P), 수정 후 결과 출력(R), 단어 덧붙이기(A), 특정 라인에서만 검색(L), 프로그램종료(Q)"
	 * ;
	 */

	MyFrame myFrame = new MyFrame("CSE2019-01 조인택 2014112103");
	JButton btn_search = new JButton("검색");
	JButton btn_change = new JButton("변경");
	JButton btn_load = new JButton("열기");
	JButton btn_output = new JButton("결과 출력");
	JButton btn_insert = new JButton("삽입"); // 버튼을 위한 변수

	JMenuBar myMenu = new JMenuBar();
	JMenu file = new JMenu("파일");
	JMenu tool = new JMenu("도구");
	JMenuItem item_open = new JMenuItem("열기");
	JMenuItem item_print = new JMenuItem("검색 결과 출력");
	JMenuItem item_search = new JMenuItem("검색");
	JMenuItem item_change = new JMenuItem("변환 후 파일 출력");
	JMenuItem item_insert = new JMenuItem("삽입 후 파일 출력");

	JTextField tf_search = new JTextField(8);
	JTextField tf_change = new JTextField(8);
	JTextField tf_input = new JTextField(12);
	JTextField tf_output = new JTextField(6);
	JTextField tf_insert = new JTextField(8);
	JTextField tf_row = new JTextField(6);
	JTextField tf_col = new JTextField(6); // 입력을 위한 변수

	JLabel lb_search = new JLabel("검색할 단어: ");
	JLabel lb_change = new JLabel("변환할 단어: ");
	JLabel lb_input = new JLabel("입력 파일명: ");
	JLabel lb_output = new JLabel("출력 파일명: ");
	JLabel lb_insert = new JLabel("삽입할 단어: ");
	JLabel lb_row = new JLabel("삽입할 행: ");
	JLabel lb_col = new JLabel("삽입할 열: "); // 입력공간에 대한 설명텍스트를 위한 변수

	Vector<String> strList = new Vector<String>();
	JList list = new JList();
	JScrollPane scroller = new JScrollPane(list); // 검색결과를 출력하기 위한 스크롤 변수

	public class SearchThread extends Thread {
		public synchronized void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			Editor.searchString(tf_search.getText());
			strList.clear(); // 그 전 내용 삭제
			for (int i = 0; i < Editor.strArrList.size(); i++) // 내용을 출력할 스트링을 담은 배열 사용
			{
				strList.add(Editor.strArrList.get(i));
			}
			list.setListData(strList);
			// btn_search.setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == item_open) { // 메뉴의 열기 선택한 경우
			openFile1();
		} else if (e.getSource() == btn_load) {
			openFile2();
		} else if (e.getSource() == item_search) {
			Search1();
		} else if (e.getSource() == btn_search) {
			SearchThread thread = new SearchThread();
			thread.start();
		} else if (e.getSource() == item_print) {
			Print1();
		} else if (e.getSource() == btn_output) {
			Print2();
		} else if (e.getSource() == item_change) {
			Change1();
		} else if (e.getSource() == btn_change) {
			Change2();
		} else if (e.getSource() == item_insert) {
			Insert1();
		} else if (e.getSource() == btn_insert) {
			Insert2();
		}

	}

	public void openFile1() { // 메뉴 선택
		btn_load.setEnabled(true); // 버튼 활성화
		btn_load.addActionListener(this);
	}

	public void openFile2() { // 경로에 따라 파일 로드
		strList.clear(); // 그 전 내용 삭제
		if (Editor.loadFile(tf_input.getText())) {
			strList.add("성공적으로 열었습니다!");
			list.setListData(strList);
		} else {
			strList.add("재입력 해주세요!");
			list.setListData(strList);
		}
		btn_load.setEnabled(false); // 버튼 비활성화
	}

	public void Search1() { // 메뉴에서 선택
		btn_search.setEnabled(true);
		btn_search.addActionListener(this);
	}

	public void Search2() { // 입력된 단어에 따라 검색
		Editor.searchString(tf_search.getText());
		strList.clear(); // 그 전 내용 삭제
		for (int i = 0; i < Editor.strArrList.size(); i++) // 내용을 출력할 스트링을 담은 배열 사용
		{
			strList.add(Editor.strArrList.get(i));
		}
		list.setListData(strList);
		btn_search.setEnabled(false);
	}

	public void Print1() { // 메뉴에서 선택
		btn_output.setEnabled(true);
		btn_output.addActionListener(this);
	}

	public void Print2() { // 입력된 경로에 따라 파일 출력
		strList.clear(); // 그 전 내용 삭제
		if (Editor.writeFile(tf_output.getText())) {
			strList.add("검색 내용을 성공적으로 출력했습니다!");
			list.setListData(strList);
		} else {
			strList.add("재입력 해주세요!");
			list.setListData(strList);
		}
		btn_output.setEnabled(false);
	}

	public void Change1() {
		btn_change.setEnabled(true);
		btn_change.addActionListener(this);
	}

	public void Change2() {
		strList.clear(); // 그 전 내용 삭제
		if (Editor.replaceWord(tf_search.getText(), tf_change.getText(), tf_output.getText())) {
			// 검색단어, 변경단어, 출력 경로 입력 후 변경 버튼 누르기
			strList.add("변경 결과를 성공적으로 출력했습니다!");
			list.setListData(strList);
		} else {
			strList.add("재입력 해주세요!");
			list.setListData(strList);
		}
		btn_change.setEnabled(false);
	}

	public void Insert1() {
		btn_insert.setEnabled(true);
		btn_insert.addActionListener(this);
	}

	public void Insert2() {
		strList.clear(); // 그 전 내용 삭제
		if (Editor.insertWord(tf_row.getText(), tf_col.getText(), tf_insert.getText(), tf_output.getText())) {
			// 행, 열, 출력 경로 입력 후 삽입 버튼 누르기
			strList.add("삽입 결과를 성공적으로 출력했습니다!");
			list.setListData(strList);
		} else {
			strList.add("재입력 해주세요!");
			list.setListData(strList);
		}
		btn_insert.setEnabled(false);
	}

	public void startConsoleMenu() { // GUI 출력 메소드

		file.add(item_open);
		file.add(item_print); // 파일 메뉴 추가
		tool.add(item_search);
		tool.add(item_change); // 도구 메뉴 추가
		tool.add(item_insert);
		myMenu.add(file);
		myMenu.add(tool); // 2개 메뉴를 메뉴바에 추가
		myFrame.setJMenuBar(myMenu); // 메뉴바를 프레임에 추가

		myFrame.setSize(800, 600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.setLayout(null); // 프레임 설정

		myFrame.add(btn_search);
		myFrame.add(btn_output);
		myFrame.add(btn_insert);
		myFrame.add(btn_load);
		myFrame.add(btn_change);
		btn_search.setEnabled(false);
		btn_output.setEnabled(false);
		btn_load.setEnabled(false);
		btn_change.setEnabled(false);
		btn_insert.setEnabled(false);
		btn_search.setBounds(300, 10, 100, 50);
		btn_output.setBounds(600, 450, 100, 50);
		btn_load.setBounds(370, 120, 60, 25);
		btn_change.setBounds(300, 60, 100, 50);
		btn_insert.setBounds(600, 10, 100, 100); // 버튼 설정

		myFrame.add(tf_search);
		myFrame.add(tf_change);
		myFrame.add(tf_input);
		myFrame.add(tf_output);
		myFrame.add(tf_col);
		myFrame.add(tf_row);
		myFrame.add(tf_insert);
		tf_search.setBounds(150, 10, 100, 25);
		tf_change.setBounds(150, 80, 100, 25);
		tf_input.setBounds(150, 120, 200, 25);
		tf_output.setBounds(380, 450, 200, 25);
		tf_row.setBounds(490, 10, 100, 25);
		tf_col.setBounds(490, 40, 100, 25);
		tf_insert.setBounds(490, 80, 100, 25); // 텍스트 필드 설정

		myFrame.add(lb_search);
		myFrame.add(lb_change);
		myFrame.add(lb_input);
		myFrame.add(lb_output);
		myFrame.add(lb_row);
		myFrame.add(lb_col);
		myFrame.add(lb_insert);
		lb_search.setBounds(50, 10, 100, 20);
		lb_change.setBounds(50, 80, 100, 20);
		lb_input.setBounds(50, 120, 100, 20);
		lb_output.setBounds(300, 450, 100, 20);
		lb_row.setBounds(420, 10, 100, 20);
		lb_col.setBounds(420, 40, 100, 20);
		lb_insert.setBounds(420, 80, 100, 20); // 텍스트필드 설명을 위한 레이블 설정

		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		list.setVisibleRowCount(10);
		scroller.setBounds(10, 150, 750, 280);
		myFrame.add(scroller); // 스크롤 설정

		item_open.addActionListener(this);
		item_search.addActionListener(this);
		item_print.addActionListener(this);
		item_change.addActionListener(this);
		item_insert.addActionListener(this);
		/*
		 * Scanner sc = new Scanner(System.in); String findWord; boolean isExitProgram =
		 * false; // 프로그램 종료 플래그 while (!isExitProgram) { System.out.println(MENU_TEXT);
		 * findWord = sc.nextLine(); switch (findWord) { case "O": case "o": {
		 * System.out.println("불러올 파일명을 입력하세요. "); //Editor.loadFile(); break; } case
		 * "S": case "s": { Editor.searchString(); break; } case "P": case "p": {
		 * System.out.println("저장 할 파일명을 입력하세요. "); Editor.writeFile(); break; } case
		 * "R": case "r": { Editor.replaceWord(); break; } case "A": case "a": { Scanner
		 * in = new Scanner(System.in); System.out.println("덧붙일 단어를 입력해주세요:"); String
		 * appendStr = in.nextLine(); // 붙일 단어 System.out.println("덧붙일 라인을 입력해주세요:");
		 * int appendLine = in.nextInt(); // 붙일 라인
		 * ImprovedEditor.appendString(appendStr, appendLine); break; } case "L": case
		 * "l": { Scanner in = new Scanner(System.in);
		 * System.out.println("검색할 단어를 입력해주세요:"); String searchStr = in.nextLine(); //
		 * 검색할 단어 System.out.println("검색할 라인들을 입력하고, '0'을 입력해주세요: "); int[] line = new
		 * int[100]; int count = 0; while ((line[count] = in.nextInt()) != 0) count++;
		 * // 0이 입력되면 종료 ImprovedEditor.searchString(searchStr, line); break; } case
		 * "Q": case "q": { System.out.println("프로그램을 종료합니다. "); isExitProgram = true;
		 * break; } default: System.out.println("알 수 없는 명령입니다. 한번 더 입력해주세요!"); break; }
		 * } sc.close();
		 */
	}

}
