package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import m.Core;
import m.ServerRunnable;
import m.ServerSocketRunnable;
import warpper.*;

public class ServerFrame extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu,menuSet,menuWL;
	public JMenuItem menuExit,menuStart,menuStop,menuAdd,menuUpdate,menuQuery,menuDelete,menuAll;
	private Thread mainServer;
	public JTextArea info;
	private JScrollPane scrollPane;
	private Boolean serverRunning = false;
	
	private Image runningTip = Toolkit.getDefaultToolkit().getImage("src/image/runningTip.png");
	private Image stopTip = Toolkit.getDefaultToolkit().getImage("src/image/stopTip.png");
	private Image errorTip = Toolkit.getDefaultToolkit().getImage("src/image/errorTip.png");
	private Object[] EXIT_TIP = {"ȷ���˳�","�ݲ��˳�"};

	public ServerFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(stopTip);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,300));
		contentPane.setOpaque(false);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		final ServerFrame s = this;
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int i = JOptionPane.showOptionDialog(s, "ȷ���˳����رշ�����ô��", "��ʾ",JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, EXIT_TIP, EXIT_TIP[0]);
				if (i == JOptionPane.YES_OPTION){
					stopServer();
					dispose();
					System.exit(0);
				}
			}
		});
		
		info = new JTextArea();
		info.setBounds(0, 0, 400, 300);
		info.setBackground(Color.WHITE);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		
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
				int i = JOptionPane.showOptionDialog(s, "ȷ���˳����رշ�����ô��", "��ʾ",JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, EXIT_TIP, EXIT_TIP[0]);
				if (i == JOptionPane.YES_OPTION){
					stopServer();
					dispose();
					System.exit(0);
				}
			}
		});
		menu.add(menuExit);
		
		menuSet = new JMenu("\u670D\u52A1\u7BA1\u7406");
		menuBar.add(menuSet);
		
		menuStart = new JMenuItem("\u542F\u52A8\u670D\u52A1\u5668");
		menuStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				try {
					Core.SERVER=new ServerRunnable(8888);
					mainServer=new Thread(Core.SERVER);
					mainServer.start();
					setIconImage(runningTip);
					serverRunning = true;
					info.append("�����������\n");//����ǰ�����IPΪ��"+InetAddress.getLocalHost().getHostAddress()+"\n");
					Core.serverframe.menuStart.setEnabled(false);
					Core.serverframe.menuStop.setEnabled(true);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
					setIconImage(errorTip);
					serverRunning = false;
				}
			}
		});
		menuSet.add(menuStart);
		
		menuStop = new JMenuItem("\u5173\u95ED\u670D\u52A1\u5668");
		menuStop.setEnabled(false);
		menuStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
				ServerRunnable.closeServer();
				setIconImage(stopTip);
				serverRunning = false;
				info.append("������ѹر�\n");
				Core.serverframe.menuStart.setEnabled(true);
				Core.serverframe.menuStop.setEnabled(false);
			}
		});
		menuSet.add(menuStop);
		
		menuWL = new JMenu("�ʿ����");
		menuBar.add(menuWL);
		
		menuAdd = new JMenuItem("���");
		menuAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				WLAddFrame f = new WLAddFrame();
				f.setVisible(true);
			}
		});
		menuWL.add(menuAdd);
		
		menuUpdate = new JMenuItem("�޸�");
		menuUpdate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				WLUpdateFrame f = new WLUpdateFrame();
				f.setVisible(true);
			}
		});
		menuWL.add(menuUpdate);
		
		menuQuery = new JMenuItem("����");
		menuQuery.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				WLQueryFrame f = new WLQueryFrame();
				f.setVisible(true);
				
			}
		});
		menuWL.add(menuQuery);
		
		menuDelete = new JMenuItem("ɾ��");
		menuDelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				WLDeleteFrame f = new WLDeleteFrame();
				f.setVisible(true);
			}
		});
		menuWL.add(menuDelete);
		
		menuAll = new JMenuItem("�鿴ȫ��");
		menuAll.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				WLQueryFrame f = new WLQueryFrame();
				f.setVisible(true);
				f.QueryAll();
			}
		});
		menuWL.add(menuAll);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void stopServer(){
		for(ServerSocketRunnable client:Core.CLIENT_RUNNABLE.values()){
			client.SendMsg(new Protocol(15,null));
		}
	}
}
