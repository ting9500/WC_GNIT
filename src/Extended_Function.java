import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Extended_Function {
	
	public static int codeNum = 0;
	public static int blankNum = 0;
	public static int noteNum = 0;
	public static String s = null;
	public static char c ;
	
	public static void getFileName(String path) {
		System.out.println("请输入要查找的文件类型：");
		Scanner scanner = new Scanner(System.in);
		String type = scanner.nextLine();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] dirFile = file.listFiles();
            if (dirFile!=null) {
                for (File f : dirFile) {
                    if (f.isDirectory())
                        getFileName(f.getAbsolutePath());
                    else {
                        if (f.getName().indexOf(type)!=-1) {
                            System.out.println(f.getAbsolutePath());
                        }
                    }
                }
            }
 
        }
    }
	
	//统计代码行数 / 空行数 / 注释行数
		public static void Count(String path) throws IOException {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			boolean tag = false;
			while((s = br.readLine())!=null) {
				
				if(s.startsWith("/*")&&s.endsWith("*/")||s.trim().startsWith("//")) {
					noteNum ++ ;
				}else if(s.replaceAll("\\s", "").startsWith("}//")) {
					noteNum ++ ;
				}
				else if(s.startsWith("/*")&&!s.endsWith("*/")) {
					noteNum ++ ;
					tag = true;
				}else if(tag) {
					noteNum ++ ;
				}else if(!s.startsWith("/*")&&s.endsWith("*/")) {
					noteNum ++ ;
					tag = false;
				}
				else if(s.replaceAll("\\s", "").length()==0) {
					blankNum ++ ;
				}else {
					codeNum ++ ;
				}					
			}
			System.out.println("代码行数："+codeNum);
			System.out.println("空白行数："+blankNum);
			System.out.println("注释行数："+noteNum);
			br.close();
		}
}
