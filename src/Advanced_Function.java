import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Advanced_Function {
	
	public static String path;
	public static void OpenFile() {
		
		try {
			JFrame frame = new JFrame("打开文件");
			JTextArea text = new JTextArea();
			JFileChooser chooser = new JFileChooser();
			int choice = chooser.showOpenDialog(frame);
			if(choice==JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				text.setText(file.getName());
				path = file.getAbsolutePath();
			}
			Basic_Function.CharCount(path);
			Basic_Function.WordCount(path);
			Basic_Function.LineCount(path);
			Extended_Function.Count(path);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("文件异常！");
		}
		
		
	}

}
