import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame{
	JMenuBar myMenu=new JMenuBar();
	JMenu file=new JMenu("파일");
	JMenu tool=new JMenu("도구");
	JMenuItem item_open=new JMenuItem("열기");
	JMenuItem item_print=new JMenuItem("출력");
	JMenuItem item_search=new JMenuItem("검색");
	JMenuItem item_change=new JMenuItem("변환");

	public MyFrame(String text) {
		super(text);
	
		file.add(item_open);
		file.add(item_print);
		tool.add(item_search);
		tool.add(item_change);
		myMenu.add(file);
		myMenu.add(tool);
		setJMenuBar(myMenu);	
	}

}

public class Menu {
	public static final String MENU_TEXT = "\t\t===메뉴선택===\n" + "데이터 파일 불러오기(O), 문자열 검색(S), 검색결과 파일 출력(P), 수정 후 결과 출력(R), 단어 덧붙이기(A), 특정 라인에서만 검색(L), 프로그램종료(Q)";

	public static void startConsoleMenu() { // 콘솔메뉴 출력 메소드
		MyFrame myFrame= new MyFrame("CSE2019-01 조인택 2014112103");
		JButton btn_search=new JButton("검색");
		JButton btn_output=new JButton("결과출력");
		JTextField tf_search=new JTextField(8);
		JTextField tf_change=new JTextField(8);
		JTextField tf_input=new JTextField(12);
		JTextField tf_output=new JTextField(6);
		JTextArea ta_result=new JTextArea(600,300);
		JLabel lb_search=new JLabel("검색할 단어: ");
		JLabel lb_change=new JLabel("변환할 단어: ");
		JLabel lb_input=new JLabel("입력 파일명: ");
		JLabel lb_output=new JLabel("출력 파일명: ");
		
		myFrame.setSize(800,600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.setLayout(null);
		
		myFrame.add(btn_search);
		myFrame.add(btn_output);
		btn_search.setBounds(300, 10, 100, 100);
		btn_output.setBounds(600, 450, 100, 50);
		
		myFrame.add(tf_search);
		myFrame.add(tf_change);
		myFrame.add(tf_input);
		myFrame.add(tf_output);
		myFrame.add(ta_result);
		tf_search.setBounds(150, 10, 100, 25);
		tf_change.setBounds(150, 80, 100, 25);
		tf_input.setBounds(150, 120, 200, 25);
		tf_output.setBounds(380, 450, 200, 25);
		ta_result.setBounds(10, 150, 750, 280);

		myFrame.add(lb_search);
		myFrame.add(lb_change);
		myFrame.add(lb_input);
		myFrame.add(lb_output);
		lb_search.setBounds(50,10,100,20);
		lb_change.setBounds(50,80,100,20);
		lb_input.setBounds(50,120,100,20);
		lb_output.setBounds(300,450,100,20);

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
