import java.util.Scanner;

public class WC_Project {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while (true) {
			//������
			System.out.println();
			System.out.println("************************************  WC.exe  ************************************");
			System.out.println();
			System.out.println("    -c [�ļ�·��]		�����ļ� file.c ���ַ���          ");
			System.out.println("    -w [�ļ�·��]		�����ļ� file.c �ĵ�����          ");
			System.out.println("    -l [�ļ�·��]		�����ļ� file.c ������              ");
			System.out.println("    -s [�ļ�(��)·��]		�ݹ鴦��Ŀ¼�·����������ļ�(ͳ���ַ�����������������)");
			System.out.println("    -a [�ļ�·��]		���ظ����ӵ�����(������ / ���� / ע����)  ");
			System.out.println("    -f [�ļ�·��] [*/?.�ļ�����]	�ļ���Ŀ¼�������Դ���һ��ͨ���");
			System.out.println("    -x          		ͨ������ѡȡ�����ļ�,��ȡ�ļ�������Ϣ  ");
			System.out.println();
			System.out.println("**********************************************************************************");
			
			System.out.println("�����빦�ܴ��룺");
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
				System.out.println("��������������������룡");
				break;
			}
			
		}

	}

}
