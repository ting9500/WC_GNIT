import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Extended_Function {
	
	public static int codeNum = 0;
	public static int blankNum = 0;
	public static int noteNum = 0;
	public static String s = null;
	public static char c ;
	
	@SuppressWarnings("resource")
	//�ݹ鴦��Ŀ¼�·����������ļ�
	public static void getFileName(String path) {
		
		try {
			List<File> filelist = new ArrayList<>();
			System.out.println("������Ҫ���ҵ��ļ����ͣ�");
			Scanner scanner = new Scanner(System.in);
			String type = scanner.nextLine();
			filelist = Extended_Function.IsFile(path);
			System.out.println("���ҽ����");
	        for(File f:filelist) {
	        	if (f.getName().indexOf(type)!=-1) {
	        		System.out.println(f.getAbsolutePath());
	            	Basic_Function.CharCount(f.getAbsolutePath());
	            	Basic_Function.WordCount(f.getAbsolutePath());
	            	Basic_Function.LineCount(f.getAbsolutePath());
	            	System.out.println();
	            }      	
	        }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�ļ��쳣��");
		}
		
	}
	
	
	//�ж��ļ�or�ļ���
	public static List<File> IsFile(String path) {
		File file = new File(path);
		List<File> filelist = new ArrayList<>();
        if (file.isDirectory()) {
            File[] dirFile = file.listFiles();
            if (dirFile!=null) {
                for (File f : dirFile) {
                    if (f.isDirectory())
                        IsFile(f.getAbsolutePath());
                    else {
                    	filelist.add(f);                    	
                    }
                }
            }
        }else {
        	filelist.add(file);
        }
        return filelist;
	}
	
	
	//ͳ�ƴ������� / ������ / ע������
	public static void Count(String path) {
		
		try {
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
				else if(s.replaceAll("\\s", "").length()==0 || s.replaceAll("\\s", "")=="{" || s.replaceAll("\\s", "")=="}") {
					blankNum ++ ;
				}else { 
					codeNum ++ ;
				}					
			}
			System.out.println("����������"+codeNum);
			System.out.println("�հ�������"+blankNum);
			System.out.println("ע��������"+noteNum);
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�ļ��쳣��");
		}
		
	}
	
	//�ļ���Ŀ¼�������Դ���һ��ͨ���
	public static void Wildcard(String path,String type) {
		//System.out.println(type);
		
		List<File> filelist = new ArrayList<>();
        filelist = Extended_Function.IsFile(path);
        String[] array = type.split("\\.");        
        for(File f:filelist) {
        	if(array[0].equals("*")) {       		
        		if (f.getName().indexOf(array[1])!=-1) {
        			System.out.println(f.getAbsolutePath());
        		}
        	}else if(array[0].equals("?")) {
        		if(f.getName().split("\\.")[0].length()==1) {
        			if (f.getName().indexOf(array[1])!=-1) {
            			System.out.println(f.getAbsolutePath());
            		}
        		}
        	}else {
        		System.out.println("�����ʽ����");
        	}
        }
        
	}
	
}
