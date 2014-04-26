package m;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ServerFrame extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu menu_1;
	private JMenuItem menuExit;
	private Thread mainServer;
	private JMenuItem menuStart;
	private JMenuItem menuStop;
	public JTextArea info;
	private JScrollPane scrollPane;
	private Boolean serverRunning = false;
	
	private Image runningTip = Toolkit.getDefaultToolkit().getImage("src/image/runningTip.png");
	private Image stopTip = Toolkit.getDefaultToolkit().getImage("src/image/stopTip.png");
	private Image errorTip = Toolkit.getDefaultToolkit().getImage("src/image/errorTip.png");

	public ServerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(stopTip);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,300));
		contentPane.setOpaque(false);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		info = new JTextArea("");
		info.setBounds(0, 0, 400, 300);
		info.setBackground(Color.WHITE);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setCaretPosition(info.getText().length());
		
		scrollPane = new JScrollPane(info);
		scrollPane.setBounds(0, 0, 400, 300);
		contentPane.add(scrollPane);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 400, 20);
		menuBar.setOpaque(false);
		setJMenuBar(menuBar);
		
		menu = new JMenu("\u7CFB\u7EDF");
		menu.setBorder(null);
		menu.setBackground(Color.WHITE);
		menuBar.add(menu);
		
		menuExit = new JMenuItem("\u9000\u51FA");
		menuExit.setBackground(Color.WHITE);
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(menuExit);
		
		menu_1 = new JMenu("\u670D\u52A1\u7BA1\u7406");
		menuBar.add(menu_1);
		
		menuStart = new JMenuItem("\u542F\u52A8\u670D\u52A1\u5668");
		menuStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				try {
					Core.SERVER=new ServerRunnable(8888);
					mainServer=new Thread(Core.SERVER);
					mainServer.start();
					setIconImage(runningTip);
					serverRunning = true;
					info.append("服务端已启动，当前服务端IP为："+InetAddress.getLocalHost().getHostAddress()+"\n");
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					setIconImage(errorTip);
					serverRunning = false;
				}
			}
		});
		menu_1.add(menuStart);
		
		menuStop = new JMenuItem("\u5173\u95ED\u670D\u52A1\u5668");
		menuStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerRunnable.closeServer();
				setIconImage(stopTip);
				serverRunning = false;
				info.append("服务端已关闭\n");
			}
		});
		menu_1.add(menuStop);
		
		pack();
		setLocationRelativeTo(null);
	}
}
