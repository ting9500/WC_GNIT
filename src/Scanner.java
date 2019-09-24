
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

/*
public class PortScanner {
		
//������
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		new Scanner();
	}
}
*/


public class Scanner extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public static void Open() {
		JLabel scan_Type , start_IP , end_IP , start_Port , end_Port , port_Thread , status;
		JComboBox<String> select_type;
		String[] type = {"��ַ��","��ַ"};
		JTextField t_startip , t_endip , t_startport , t_endport , t_portthread ;
		JTextArea scan_Result;
		JScrollPane roll;
		JButton Scanner , Empty, Reset , Exit ;
		JPanel top_panel , bottom_panel , left_panel , right_panel ;
		String startip , endip ;
		int startport , endport , portthread , threadNum;
		//��ȡ����������
		InetAddress address; 
		String name;
		
		JFrame frame = new JFrame();
		frame.setTitle("�˿�ɨ����") ;
		frame.setBounds(150,150,1000,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//top
		scan_Type = new JLabel("ɨ������");
		start_IP = new JLabel("    ��ʼIP��ַ");
		end_IP = new JLabel("    ����IP��ַ");
		start_Port = new JLabel("    ��ʼ�˿ں�");
		end_Port = new JLabel("    �����˿ں�");
		port_Thread = new JLabel("    ÿ���߳�ɨ��˿���");
		scan_Result = new JTextArea(100, 100);
		
		select_type = new JComboBox<String>(type);
		t_startip = new JTextField(9);
		t_endip = new JTextField(9);
		t_startport = new JTextField(4);
		t_endport = new JTextField(4);
		t_portthread = new JTextField(4);
		
		top_panel = new JPanel();
		top_panel.add(scan_Type);
		top_panel.add(select_type);
		top_panel.add(start_IP);
		top_panel.add(t_startip);
		top_panel.add(end_IP);
		top_panel.add(t_endip);
		top_panel.add(start_Port);
		top_panel.add(t_startport);
		top_panel.add(end_Port);
		top_panel.add(t_endport);
		top_panel.add(port_Thread);
		top_panel.add(t_portthread);
		
		//middle
		scan_Result = new JTextArea(100,100);
		scan_Result.setEditable(false);
		scan_Result.setLineWrap(true);
		roll = new JScrollPane(scan_Result);
		roll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//bottom
		status=new JLabel("δ��ʼɨ��");
		Scanner = new JButton("ɨ��");
		Empty = new JButton("���");
		Reset = new JButton("����");
		Exit = new JButton("�˳�");
		left_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		right_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom_panel = new JPanel(new GridLayout(1,2));
		
		
		left_panel.add(status);
		right_panel.add(Scanner);
		right_panel.add(Empty);
		right_panel.add(Reset);
		right_panel.add(Exit);
		bottom_panel.add(left_panel);
		bottom_panel.add(right_panel);
						
		frame.add(top_panel,BorderLayout.NORTH);
		frame.add(roll);
		frame.add(bottom_panel,BorderLayout.SOUTH);
		/*
		select_type.addActionListener(this);
		Scanner.addActionListener(this);
		Empty.addActionListener(this);
		Reset.addActionListener(this);
		Exit.addActionListener(this);
		*/
		
		
	}
	//end Port_Win()
/*
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource() == select_type){
			String Type=(String) select_type.getSelectedItem();
			if(Type.equals("��ַ")){
				end_IP.setVisible(false) ;
				t_endip.setVisible(false) ;
				start_IP.setText("    ɨ���IP��ַ") ;
			}else{
				end_IP.setVisible(true) ;
				t_endip.setVisible(true) ;
				start_IP.setText("    ��ʼIP��ַ") ;
			}
		}
		
		else if(e.getSource() == Scanner) {			
			startip = t_startip.getText();
			label:
			if(Split(startip)) {
				try {
					startport = Integer.parseInt(t_startport.getText());
					endport = Integer.parseInt(t_endport.getText());
					portthread = Integer.parseInt(t_portthread.getText());
					threadNum = (endport-startport)/portthread+1 ;
					//�ж϶˿����߳��������Ƿ����
					if(startport<0||startport>endport||endport>65535) {
						JOptionPane.showMessageDialog(this, "�˿ڵ���Ч��Χ��1~65535������ʼ�˿�ӦС�ڽ����˿�");
						break label;
					}						
					else if(portthread>endport-startport||portthread<1) {
						JOptionPane.showMessageDialog(this, "ÿ���߳�ɨ��˿���Ӧ����������ɨ��˿����Ҵ���0");
						break label;
					}
						
					//IP��ַ���
					
					//IP��ַ
					if(((String) select_type.getSelectedItem()).equals("��ַ")) {
						scan_Result.append("\n*************************����ɨ���IP��ַ*************************"+"\n"+"\n");
						scan_Result.append("IP��ַ��"+startip+"\n");
						scan_Result.append("��ʼ�˿ڣ�"+startport+"                        "+"�����˿ڣ�"+endport+"\n");
						scan_Result.append("\n***********ɨ����***********\n");
						getName(startip);	

						//��ʼ����ɨ��	
						for(int i = 0;i<threadNum;i++) {
							Scanip scanip = new Scanip(startport,endport,startip,threadNum,i);
							scanip.start();
						}
						
					}
					//IP��ַ�����
					else {

						endip = t_endip.getText();
						if(Split(endip)) {

							ArrayList<String> allips = new ArrayList<String>();
							String[] Arraystart = startip.split("\\.");
							String[] Arrayend = endip.split("\\.");
							int[] arraystart = new int[4];
							int[] arrayend = new int[4];
							for(int i = 0;i<4;i++) {
								arraystart[i] = Integer.parseInt(Arraystart[i]);
								arrayend[i] = Integer.parseInt(Arrayend[i]);								
							}
							
							//�жϿ�ʼIP��ַ�ͽ���IP��ַ�Ƿ�˳����ȷ
							for(int i=0;i<4;i++) {
								if(arraystart[i]>arrayend[i]) {
									JOptionPane.showMessageDialog(this, "��ʼIP��ַ�ͽ���IP��ַ������˳�����") ;
									break label;
								}
							}							

							scan_Result.append("\n*************************����ɨ���IP��ַ��*************************"+"\n"+"\n");
							scan_Result.append("\n"+"��ʼIP��ַ��"+startip+"      "+"����IP��ַ��"+endip+"\n");
							scan_Result.append("��ʼ�˿ڣ�"+startport+"      "+"�����˿ڣ�"+endport+"\n");
							scan_Result.append("\n***********ɨ����***********\n");

							for(int a = arraystart[0];a <= arrayend[0];a++) {
								for(int b = (a==arraystart[0]?arraystart[1]:0);b<=(a==arrayend[0]?arrayend[1]:255);b++) {
									for(int c = (b==arraystart[1]?arraystart[2]:0);c<=(b==arrayend[1]?arrayend[2]:255);c++) {
										for(int d = (c==arraystart[2]?arraystart[3]:0);d<=(c==arrayend[2]?arrayend[3]:255);d++) {
											allips.add(a+"."+b+"."+c+"."+d);						
										}
									}
								}
							}
							//endfor
							ExecutorService threadPool = Executors.newCachedThreadPool();
							for(int i = 0;i<threadNum;i++) {								
								threadPool.execute(new Scanips(allips, startport, endport, threadNum, i));
							}
							threadPool.shutdown();
							
						}					
					}			
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(this, "��ʼIP��ַ�ͽ���IP��ַ������˳�����") ;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "��������ȷ��IP��ַ") ;
			}		
		}
		
		else if(e.getSource() == Empty) {
			scan_Result.setText("") ;
		}
		
		else if(e.getSource() == Reset) {
			t_startip.setText("");
			t_endip.setText("");
			t_startport.setText("");
			t_endport.setText("");
			t_portthread.setText("");
		}
		
		else {
			System.exit(0);
		}		
	}
	//end actionPerformed
/*	
	//�ָ�IP���ж��Ƿ�Ϸ�
	private boolean Split(String IP) {
		// TODO Auto-generated method stub
		String[] Array = IP.split("\\.",4);
		for(String array:Array) {
			int a = Integer.parseInt(array);
			if(a<0 || a>255)
				return false;		
		}
		return true;
	}
	
	//��ȡ������
	public void getName(String IP) {
		try {
			address = InetAddress.getByName(IP);
			name = address.getHostName();
			scan_Result.append("\nIPΪ "+IP+" ��������Ϊ��"+name+"\n");
		}catch(UnknownHostException e3) {
			JOptionPane.showMessageDialog(this, "���������ڻ��������Ӵ���");
		}
	}

	//ɨ��IP��ַ
	public class Scanip extends Thread{
		int port_1,port_2,threadNum,thread_th;
		String IP;		
		Scanip(int port_1,int port_2,String IP,int threadNum,int thread_th){
			this.port_1 = port_1;
			this.port_2 = port_2;
			this.IP = IP;
			this.threadNum = threadNum;
			this.thread_th = thread_th;
		}
		public synchronized void run() {		
			Socket socket = null;
			DatagramSocket dgst = null;
			for(int i = startport+thread_th;i<=endport;i += threadNum) {
				try {
					socket = new Socket(IP,i);
					if(socket.isConnected()) {
						scan_Result.append("���ŵĶ˿ںţ�"+i+"���˿����ͣ�TCP�˿�\n");
					}
					socket.close();								
				}catch(Exception e) {
					
				}
				try {
					dgst = new DatagramSocket(i);						
					dgst.close();
				}catch(SocketException e4) {					
					scan_Result.append("���ŵĶ˿ںţ�"+i+"���˿����ͣ�UDP�˿�\n");
				}
				status.setText("����ɨ��IP��ַΪ"+IP+"��"+i+"�˿�") ;
			}						
			status.setText("ɨ�����") ;			
		}	
		
	}
	
	//ɨ��IP��ַ��

	public class Scanips implements Runnable{

		private ArrayList<String> allips;
		private int startport, endport, threadNum, thread_th;
		private Socket socket = null;
		private DatagramSocket dgst = null;
		public Scanips(ArrayList<String> allips,int startport,int endport,int threadNum,int thread_th) {
			this.allips = allips;
			this.startport = startport;
			this.endport = endport;
			this.threadNum = threadNum;
			this.thread_th = thread_th;
		}
		
		@Override
		public synchronized void run() {
			// TODO Auto-generated method stub
			for(String IP:allips) {	
				
				if(thread_th==1) {
					getName(IP);
				}
				
				for(int i = startport+thread_th;i<=endport;i += threadNum) {
					try {
						socket = new Socket(IP,i);
						if(socket.isConnected()) {
							scan_Result.append("���ŵĶ˿ںţ�"+i+"���˿����ͣ�TCP�˿�\n");
						}
						socket.close();								
					}catch(Exception e) {
						
					}
					try {
						dgst = new DatagramSocket(i);						
						dgst.close();
					}catch(SocketException e4) {					
						scan_Result.append("���ŵĶ˿ںţ�"+i+"���˿����ͣ�UDP�˿�\n");
					}
					status.setText("����ɨ��IP��ַΪ"+IP+"��"+i+"�˿�") ;
				}				
			}
			status.setText("ɨ�����") ;
		}		
	}
*/
}
//end
