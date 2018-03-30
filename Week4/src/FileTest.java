import java.io.*;

public class FileTest {
	public static final String TEXT_PATH = "c:\\test1.txt";
	public static void main(String[] args) throws IOException{
		FileInputStream in = new FileInputStream(TEXT_PATH);
		int ch;
		while((ch=in.read())!=-1) {
			System.out.print((char) ch);
		}
		in.close();
	}
}
