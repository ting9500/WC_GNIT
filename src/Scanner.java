
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
		
//主函数
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
		String[] type = {"地址段","地址"};
		JTextField t_startip , t_endip , t_startport , t_endport , t_portthread ;
		JTextArea scan_Result;
		JScrollPane roll;
		JButton Scanner , Empty, Reset , Exit ;
		JPanel top_panel , bottom_panel , left_panel , right_panel ;
		String startip , endip ;
		int startport , endport , portthread , threadNum;
		//获取主机名变量
		InetAddress address; 
		String name;
		
		JFrame frame = new JFrame();
		frame.setTitle("端口扫描器") ;
		frame.setBounds(150,150,1000,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//top
		scan_Type = new JLabel("扫描类型");
		start_IP = new JLabel("    开始IP地址");
		end_IP = new JLabel("    结束IP地址");
		start_Port = new JLabel("    开始端口号");
		end_Port = new JLabel("    结束端口号");
		port_Thread = new JLabel("    每个线程扫描端口数");
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
		status=new JLabel("未开始扫描");
		Scanner = new JButton("扫描");
		Empty = new JButton("清空");
		Reset = new JButton("重置");
		Exit = new JButton("退出");
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
			if(Type.equals("地址")){
				end_IP.setVisible(false) ;
				t_endip.setVisible(false) ;
				start_IP.setText("    扫描的IP地址") ;
			}else{
				end_IP.setVisible(true) ;
				t_endip.setVisible(true) ;
				start_IP.setText("    开始IP地址") ;
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
					//判断端口与线程数数据是否合理
					if(startport<0||startport>endport||endport>65535) {
						JOptionPane.showMessageDialog(this, "端口的有效范围是1~65535，且起始端口应小于结束端口");
						break label;
					}						
					else if(portthread>endport-startport||portthread<1) {
						JOptionPane.showMessageDialog(this, "每个线程扫描端口数应不超过所有扫描端口数且大于0");
						break label;
					}
						
					//IP地址情况
					
					//IP地址
					if(((String) select_type.getSelectedItem()).equals("地址")) {
						scan_Result.append("\n*************************正在扫描该IP地址*************************"+"\n"+"\n");
						scan_Result.append("IP地址："+startip+"\n");
						scan_Result.append("开始端口："+startport+"                        "+"结束端口："+endport+"\n");
						scan_Result.append("\n***********扫描结果***********\n");
						getName(startip);	

						//开始进行扫描	
						for(int i = 0;i<threadNum;i++) {
							Scanip scanip = new Scanip(startport,endport,startip,threadNum,i);
							scanip.start();
						}
						
					}
					//IP地址段情况
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
							
							//判断开始IP地址和结束IP地址是否顺序正确
							for(int i=0;i<4;i++) {
								if(arraystart[i]>arrayend[i]) {
									JOptionPane.showMessageDialog(this, "开始IP地址和结束IP地址的输入顺序错误") ;
									break label;
								}
							}							

							scan_Result.append("\n*************************正在扫描该IP地址段*************************"+"\n"+"\n");
							scan_Result.append("\n"+"起始IP地址："+startip+"      "+"结束IP地址："+endip+"\n");
							scan_Result.append("开始端口："+startport+"      "+"结束端口："+endport+"\n");
							scan_Result.append("\n***********扫描结果***********\n");

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
					JOptionPane.showMessageDialog(this, "开始IP地址和结束IP地址的输入顺序错误") ;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "请输入正确的IP地址") ;
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
	//分割IP，判断是否合法
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
	
	//获取主机名
	public void getName(String IP) {
		try {
			address = InetAddress.getByName(IP);
			name = address.getHostName();
			scan_Result.append("\nIP为 "+IP+" 的主机名为："+name+"\n");
		}catch(UnknownHostException e3) {
			JOptionPane.showMessageDialog(this, "主机不存在或网络连接错误");
		}
	}

	//扫描IP地址
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
						scan_Result.append("开放的端口号："+i+"，端口类型：TCP端口\n");
					}
					socket.close();								
				}catch(Exception e) {
					
				}
				try {
					dgst = new DatagramSocket(i);						
					dgst.close();
				}catch(SocketException e4) {					
					scan_Result.append("开放的端口号："+i+"，端口类型：UDP端口\n");
				}
				status.setText("正在扫描IP地址为"+IP+"的"+i+"端口") ;
			}						
			status.setText("扫描结束") ;			
		}	
		
	}
	
	//扫描IP地址段

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
							scan_Result.append("开放的端口号："+i+"，端口类型：TCP端口\n");
						}
						socket.close();								
					}catch(Exception e) {
						
					}
					try {
						dgst = new DatagramSocket(i);						
						dgst.close();
					}catch(SocketException e4) {					
						scan_Result.append("开放的端口号："+i+"，端口类型：UDP端口\n");
					}
					status.setText("正在扫描IP地址为"+IP+"的"+i+"端口") ;
				}				
			}
			status.setText("扫描结束") ;
		}		
	}
*/
}
//end
