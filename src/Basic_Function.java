import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Basic_Function {

	public static String s = null;
	Scanner scanner = new Scanner(System.in);
	String choice = scanner.nextLine();
	
	//统计字符数
	public static void CharCount(String path) {
		
		try {
			int charNum = 0;
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((s = br.readLine())!=null) {
				s = s.replaceAll("\\s", "");
				charNum += s.length();
			}
			System.out.println("字符数为："+charNum);
			br.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("文件异常！");
		}		
		
	}
	
	//统计单词数
	public static void WordCount(String path) {
		
		try {
			int wordNum = 0;
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((s = br.readLine())!=null) {
				if(s.replaceAll("\\s", "")!=null&&s.split("\\s+").length==1) {
					wordNum = 1;
				}else if(s.split("\\s+").length>1) {
					wordNum += s.split("\\s+").length;
				}					
			}
			System.out.println("单词数为："+wordNum);
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("文件异常！");
		}
		
	}
	
	//统计行数
	public static void LineCount(String path) {
		try {
			int lineNum = 0;
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (br.readLine()!=null) {
				lineNum ++ ;
			}
			System.out.println("行数为："+lineNum);
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("文件异常！");
		}
		
	}
}
