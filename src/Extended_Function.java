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
		System.out.println("������Ҫ���ҵ��ļ����ͣ�");
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
	
	//ͳ�ƴ������� / ������ / ע������
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
			System.out.println("����������"+codeNum);
			System.out.println("�հ�������"+blankNum);
			System.out.println("ע��������"+noteNum);
			br.close();
		}
}
