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
	//递归处理目录下符合条件的文件
	public static void getFileName(String path) {
		
		try {
			List<File> filelist = new ArrayList<>();
			System.out.println("请输入要查找的文件类型：");
			Scanner scanner = new Scanner(System.in);
			String type = scanner.nextLine();
			filelist = Extended_Function.IsFile(path);
			System.out.println("查找结果：");
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
			System.out.println("文件异常！");
		}
		
	}
	
	
	//判断文件or文件夹
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
	
	
	//统计代码行数 / 空行数 / 注释行数
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
			System.out.println("代码行数："+codeNum);
			System.out.println("空白行数："+blankNum);
			System.out.println("注释行数："+noteNum);
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("文件异常！");
		}
		
	}
	
	//文件或目录名，可以处理一般通配符
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
        		System.out.println("输入格式错误！");
        	}
        }
        
	}
	
}
