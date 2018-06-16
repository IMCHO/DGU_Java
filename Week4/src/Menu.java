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
	 * public static final String MENU_TEXT = "\t\t===�޴�����===\n" +
	 * "������ ���� �ҷ�����(O), ���ڿ� �˻�(S), �˻���� ���� ���(P), ���� �� ��� ���(R), �ܾ� �����̱�(A), Ư�� ���ο����� �˻�(L), ���α׷�����(Q)"
	 * ;
	 */

	MyFrame myFrame = new MyFrame("CSE2019-01 ������ 2014112103");
	JButton btn_search = new JButton("�˻�");
	JButton btn_change = new JButton("����");
	JButton btn_load = new JButton("����");
	JButton btn_output = new JButton("��� ���");
	JButton btn_insert = new JButton("����"); // ��ư�� ���� ����

	JMenuBar myMenu = new JMenuBar();
	JMenu file = new JMenu("����");
	JMenu tool = new JMenu("����");
	JMenuItem item_open = new JMenuItem("����");
	JMenuItem item_print = new JMenuItem("�˻� ��� ���");
	JMenuItem item_search = new JMenuItem("�˻�");
	JMenuItem item_change = new JMenuItem("��ȯ �� ���� ���");
	JMenuItem item_insert = new JMenuItem("���� �� ���� ���");

	JTextField tf_search = new JTextField(8);
	JTextField tf_change = new JTextField(8);
	JTextField tf_input = new JTextField(12);
	JTextField tf_output = new JTextField(6);
	JTextField tf_insert = new JTextField(8);
	JTextField tf_row = new JTextField(6);
	JTextField tf_col = new JTextField(6); // �Է��� ���� ����

	JLabel lb_search = new JLabel("�˻��� �ܾ�: ");
	JLabel lb_change = new JLabel("��ȯ�� �ܾ�: ");
	JLabel lb_input = new JLabel("�Է� ���ϸ�: ");
	JLabel lb_output = new JLabel("��� ���ϸ�: ");
	JLabel lb_insert = new JLabel("������ �ܾ�: ");
	JLabel lb_row = new JLabel("������ ��: ");
	JLabel lb_col = new JLabel("������ ��: "); // �Է°����� ���� �����ؽ�Ʈ�� ���� ����

	Vector<String> strList = new Vector<String>();
	JList list = new JList();
	JScrollPane scroller = new JScrollPane(list); // �˻������ ����ϱ� ���� ��ũ�� ����

	public class SearchThread extends Thread {
		public synchronized void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}
			Editor.searchString(tf_search.getText());
			strList.clear(); // �� �� ���� ����
			for (int i = 0; i < Editor.strArrList.size(); i++) // ������ ����� ��Ʈ���� ���� �迭 ���
			{
				strList.add(Editor.strArrList.get(i));
			}
			list.setListData(strList);
			// btn_search.setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == item_open) { // �޴��� ���� ������ ���
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

	public void openFile1() { // �޴� ����
		btn_load.setEnabled(true); // ��ư Ȱ��ȭ
		btn_load.addActionListener(this);
	}

	public void openFile2() { // ��ο� ���� ���� �ε�
		strList.clear(); // �� �� ���� ����
		if (Editor.loadFile(tf_input.getText())) {
			strList.add("���������� �������ϴ�!");
			list.setListData(strList);
		} else {
			strList.add("���Է� ���ּ���!");
			list.setListData(strList);
		}
		btn_load.setEnabled(false); // ��ư ��Ȱ��ȭ
	}

	public void Search1() { // �޴����� ����
		btn_search.setEnabled(true);
		btn_search.addActionListener(this);
	}

	public void Search2() { // �Էµ� �ܾ ���� �˻�
		Editor.searchString(tf_search.getText());
		strList.clear(); // �� �� ���� ����
		for (int i = 0; i < Editor.strArrList.size(); i++) // ������ ����� ��Ʈ���� ���� �迭 ���
		{
			strList.add(Editor.strArrList.get(i));
		}
		list.setListData(strList);
		btn_search.setEnabled(false);
	}

	public void Print1() { // �޴����� ����
		btn_output.setEnabled(true);
		btn_output.addActionListener(this);
	}

	public void Print2() { // �Էµ� ��ο� ���� ���� ���
		strList.clear(); // �� �� ���� ����
		if (Editor.writeFile(tf_output.getText())) {
			strList.add("�˻� ������ ���������� ����߽��ϴ�!");
			list.setListData(strList);
		} else {
			strList.add("���Է� ���ּ���!");
			list.setListData(strList);
		}
		btn_output.setEnabled(false);
	}

	public void Change1() {
		btn_change.setEnabled(true);
		btn_change.addActionListener(this);
	}

	public void Change2() {
		strList.clear(); // �� �� ���� ����
		if (Editor.replaceWord(tf_search.getText(), tf_change.getText(), tf_output.getText())) {
			// �˻��ܾ�, ����ܾ�, ��� ��� �Է� �� ���� ��ư ������
			strList.add("���� ����� ���������� ����߽��ϴ�!");
			list.setListData(strList);
		} else {
			strList.add("���Է� ���ּ���!");
			list.setListData(strList);
		}
		btn_change.setEnabled(false);
	}

	public void Insert1() {
		btn_insert.setEnabled(true);
		btn_insert.addActionListener(this);
	}

	public void Insert2() {
		strList.clear(); // �� �� ���� ����
		if (Editor.insertWord(tf_row.getText(), tf_col.getText(), tf_insert.getText(), tf_output.getText())) {
			// ��, ��, ��� ��� �Է� �� ���� ��ư ������
			strList.add("���� ����� ���������� ����߽��ϴ�!");
			list.setListData(strList);
		} else {
			strList.add("���Է� ���ּ���!");
			list.setListData(strList);
		}
		btn_insert.setEnabled(false);
	}

	public void startConsoleMenu() { // GUI ��� �޼ҵ�

		file.add(item_open);
		file.add(item_print); // ���� �޴� �߰�
		tool.add(item_search);
		tool.add(item_change); // ���� �޴� �߰�
		tool.add(item_insert);
		myMenu.add(file);
		myMenu.add(tool); // 2�� �޴��� �޴��ٿ� �߰�
		myFrame.setJMenuBar(myMenu); // �޴��ٸ� �����ӿ� �߰�

		myFrame.setSize(800, 600);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.setLayout(null); // ������ ����

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
		btn_insert.setBounds(600, 10, 100, 100); // ��ư ����

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
		tf_insert.setBounds(490, 80, 100, 25); // �ؽ�Ʈ �ʵ� ����

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
		lb_insert.setBounds(420, 80, 100, 20); // �ؽ�Ʈ�ʵ� ������ ���� ���̺� ����

		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		list.setVisibleRowCount(10);
		scroller.setBounds(10, 150, 750, 280);
		myFrame.add(scroller); // ��ũ�� ����

		item_open.addActionListener(this);
		item_search.addActionListener(this);
		item_print.addActionListener(this);
		item_change.addActionListener(this);
		item_insert.addActionListener(this);
		/*
		 * Scanner sc = new Scanner(System.in); String findWord; boolean isExitProgram =
		 * false; // ���α׷� ���� �÷��� while (!isExitProgram) { System.out.println(MENU_TEXT);
		 * findWord = sc.nextLine(); switch (findWord) { case "O": case "o": {
		 * System.out.println("�ҷ��� ���ϸ��� �Է��ϼ���. "); //Editor.loadFile(); break; } case
		 * "S": case "s": { Editor.searchString(); break; } case "P": case "p": {
		 * System.out.println("���� �� ���ϸ��� �Է��ϼ���. "); Editor.writeFile(); break; } case
		 * "R": case "r": { Editor.replaceWord(); break; } case "A": case "a": { Scanner
		 * in = new Scanner(System.in); System.out.println("������ �ܾ �Է����ּ���:"); String
		 * appendStr = in.nextLine(); // ���� �ܾ� System.out.println("������ ������ �Է����ּ���:");
		 * int appendLine = in.nextInt(); // ���� ����
		 * ImprovedEditor.appendString(appendStr, appendLine); break; } case "L": case
		 * "l": { Scanner in = new Scanner(System.in);
		 * System.out.println("�˻��� �ܾ �Է����ּ���:"); String searchStr = in.nextLine(); //
		 * �˻��� �ܾ� System.out.println("�˻��� ���ε��� �Է��ϰ�, '0'�� �Է����ּ���: "); int[] line = new
		 * int[100]; int count = 0; while ((line[count] = in.nextInt()) != 0) count++;
		 * // 0�� �ԷµǸ� ���� ImprovedEditor.searchString(searchStr, line); break; } case
		 * "Q": case "q": { System.out.println("���α׷��� �����մϴ�. "); isExitProgram = true;
		 * break; } default: System.out.println("�� �� ���� ����Դϴ�. �ѹ� �� �Է����ּ���!"); break; }
		 * } sc.close();
		 */
	}

}
