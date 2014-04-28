package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dbmanager.WLibManager;

public class WLDeleteFrame extends JFrame {
	private JPanel contentPane;
	JPanel operaPane;
	private JScrollPane scroPane;
	private JTextArea info;
	private JLabel keyLabel;
	private JLabel valueLabel;
	private JComboBox keyBox;
	private JTextField valueField;
	private JButton subButton;
	
	private String[] indexs = {"id","word","length","category","part"};
	private String key;
	private String value;
	
	private WLibManager wlman = new WLibManager();

	public WLDeleteFrame(){
		super();
		// TODO �Զ����ɵĹ��캯�����
		setTitle("�ʿ�ɾ��");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(subButton);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,200));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		info = new JTextArea();
		info.setBackground(Color.WHITE);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);

		scroPane = new JScrollPane(info);
		contentPane.add(scroPane,BorderLayout.CENTER);
		
		operaPane = new JPanel();
		contentPane.add(operaPane,BorderLayout.SOUTH);
		
		keyLabel = new JLabel("��ѯ�ؼ��֣�");
		operaPane.add(keyLabel);
		
		keyBox = new JComboBox(indexs);
		operaPane.add(keyBox);
		
		valueLabel = new JLabel("��ѯֵ��");
		operaPane.add(valueLabel);
		
		valueField = new JTextField();
		valueField.setPreferredSize(new Dimension(80,25));
		operaPane.add(valueField);
		
		subButton = new JButton("ɾ��");
		operaPane.add(subButton);
		subButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				key = (String) keyBox.getSelectedItem();
				value = valueField.getText();
				int i = wlman.delete(key, value);
				if(i != 0){
					info.append("ָ����Ŀɾ���ɹ������ι���"+i+"����Ŀ�����˲���\n\n");
				} else{
					info.append("ɾ��ʧ��,����δ���κ���Ŀ���в���\n\n");
				}
			}
		});
		
		pack();
		setLocationRelativeTo(null);
	}

}
