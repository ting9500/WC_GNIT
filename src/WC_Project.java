import java.util.Scanner;

public class WC_Project {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while (true) {
			//命令行
			System.out.println();
			System.out.println("************************************  WC.exe  ************************************");
			System.out.println();
			System.out.println("    -c [文件路径]		返回文件 file.c 的字符数          ");
			System.out.println("    -w [文件路径]		返回文件 file.c 的单词数          ");
			System.out.println("    -l [文件路径]		返回文件 file.c 的行数              ");
			System.out.println("    -s [文件(夹)路径]		递归处理目录下符合条件的文件(统计字符数、单词数、行数)");
			System.out.println("    -a [文件路径]		返回更复杂的数据(代码行 / 空行 / 注释行)  ");
			System.out.println("    -f [文件路径] [*/?.文件类型]	文件或目录名，可以处理一般通配符");
			System.out.println("    -x          		通过界面选取单个文件,获取文件具体信息  ");
			System.out.println();
			System.out.println("**********************************************************************************");
			
			System.out.println("请输入功能代码：");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			String[] array = choice.split(" ");
			switch(array[0]) {
			
			case "-c":
				Basic_Function.CharCount(array[1]);
				break;
			case "-w":
				Basic_Function.WordCount(array[1]);
				break;	
			case "-l":
				Basic_Function.LineCount(array[1]);
				break;	
			case "-s":
				Extended_Function.getFileName(array[1]);			
				break;
			case "-a":
				Extended_Function.Count(array[1]);
				break;
			case "-x":
				Advanced_Function.OpenFile();
				break;
			case "-f":					
				Extended_Function.Wildcard(array[1],array[2]);
				break;
			default:
				System.out.println("代码输入错误，请重新输入！");
				break;
			}
			
		}

	}

}
