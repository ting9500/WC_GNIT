
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Advanced_Function extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 /*
	public Advanced_Function() {
		
		JFrame f = new JFrame("文件选择器");
        f.setSize(400, 600);
        f.setLocation(200, 100);
        f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JButton button = new JButton("选取文件");
		this.add(button);
       
        JPanel top_panel = new JPanel();
        JPanel bottom_panel = new JPanel();
        JTextField fileName = new JTextField(20);
        JButton button = new JButton("选取文件");
        top_panel.add(fileName);
        top_panel.add(button);
        
        JLabel label = new JLabel("统计结果");
        JTextArea result = new JTextArea(300,300);
        bottom_panel.add(result);
        
        this.add(top_panel,BorderLayout.NORTH);
		this.add(label);
		this.add(bottom_panel,BorderLayout.SOUTH);
        
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFrame f = new JFrame("选取文件");
				JTextArea showFile = new JTextArea();
				JFileChooser chooser = new JFileChooser();
				int choice = chooser.showOpenDialog(f);
				if(choice==JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					showFile.setText(file.getName());
					fileName.setText(file.getName());
				}
				
			}
		});
        
        
        
        
		
	}*/
	
	public static void UI_Swing() {
		JFrame f = new JFrame("文件选择器");
        f.setSize(400, 600);
        f.setLocation(200, 100);
        f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       
        JPanel top_panel = new JPanel();
        JPanel bottom_panel = new JPanel();
        JTextField fileName = new JTextField(20);
        JButton button = new JButton("选取文件");
        top_panel.add(fileName);
        top_panel.add(button);
        
        JLabel label = new JLabel("统计结果");
        JTextArea result = new JTextArea(300,300);
        bottom_panel.add(result);
        
        f.add(top_panel,BorderLayout.NORTH);
		f.add(label);
		f.add(bottom_panel,BorderLayout.SOUTH);
        
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFrame f = new JFrame("选取文件");
				JTextArea showFile = new JTextArea();
				JFileChooser chooser = new JFileChooser();
				int choice = chooser.showOpenDialog(f);
				if(choice==JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					showFile.setText(file.getName());
					fileName.setText(file.getName());
				}
				
			}
		});
	}
	
	public static void OpenFile() {
		
		JFrame frame = new JFrame("打开文件");
		JTextArea text = new JTextArea();
		JFileChooser chooser = new JFileChooser();
		int choice = chooser.showOpenDialog(frame);
		if(choice==JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			text.setText(file.getName());
		}
		
		
		/*
		JFrame f = new JFrame("文件选择器");
		JTextArea tarea = new JTextArea();
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("txt(*.txt)", "txt"));
        int result = fileChooser.showOpenDialog(f);
        if(result==1)
        	return ;
        if (result == JFileChooser.APPROVE_OPTION) {
        	try {  
        		tarea.setText("");
        		File file = fileChooser.getSelectedFile();
        		FileInputStream fis=new FileInputStream(file);
                try{
                    byte[] data = new byte[1024];
                    int i = fis.read(data);
                    String s = new String(data,0,i);
                    tarea.append(s);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    try{
                        fis.close();
                    }catch(Exception e){}
                }
                f.setTitle(file.getName()+" - 文本编辑器");
        	} catch (IOException e) {
				e.printStackTrace();
			}
        }
		*/
	}


}
