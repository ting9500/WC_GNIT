import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Basic_Function {

	public static String s = null;
	public static int charNum = 0;
	public static int wordNum = 0;
	public static int lineNum = 0;

	Scanner scanner = new Scanner(System.in);
	String choice = scanner.nextLine();
	
	//ͳ���ַ���
	public static void CharCount(String path) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((s = br.readLine())!=null) {
			s = s.replaceAll("\\s", "");
			charNum += s.length();
		}
		System.out.println("�ַ���Ϊ��"+charNum);
		br.close();
	}
	
	//ͳ�Ƶ�����
	public static void WordCount(String path) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		while((s = br.readLine())!=null) {
			if(s.split("\\s+").length>1) {
				wordNum += s.split("\\s+").length;
			}					
		}
		System.out.println("������Ϊ��"+wordNum);
		br.close();
	}
	
	//ͳ������
	public static void LineCount(String path) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		while (br.readLine()!=null) {
			lineNum ++ ;
		}
		System.out.println("����Ϊ��"+lineNum);
		br.close();
	}
}
