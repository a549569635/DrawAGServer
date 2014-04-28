package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import warpper.Word;
import dbmanager.WLibManager;

public class WLQueryFrame extends JFrame {
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
	
	private ArrayList<Word> list;

	public WLQueryFrame(){
		super();
		// TODO �Զ����ɵĹ��캯�����
		setTitle("�ʿ��ѯ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(subButton);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(500,300));
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
		contentPane.add(operaPane,BorderLayout.NORTH);
		
		keyLabel = new JLabel("��ѯ�ؼ��֣�");
		operaPane.add(keyLabel);
		
		keyBox = new JComboBox(indexs);
		operaPane.add(keyBox);
		
		valueLabel = new JLabel("��ѯֵ��");
		operaPane.add(valueLabel);
		
		valueField = new JTextField();
		valueField.setPreferredSize(new Dimension(80,25));
		operaPane.add(valueField);
		
		subButton = new JButton("��ʼ��ѯ");
		operaPane.add(subButton);
		subButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				key = (String) keyBox.getSelectedItem();
				value = valueField.getText();
				list = wlman.queryByKey(key, value);
				if(!list.isEmpty()){
					info.append("��ѯ��ɣ�����ƥ�������£�\nid\tword\tlength\tcategory\tpart\n\n");
					for(Word w:list){
						info.append(w.getID()+"\t"+w.getValue()+"\t"+w.getLength()+"\t"+w.getCategory()+"\t"+w.getPart()+"\n");
					}
					info.append("\n======================================================================\n");
				} else{
					info.append("û�в�ѯ��ƥ����\n\n");
				}
				list.clear();
			}
		});
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void QueryAll(){
		list = wlman.queryAll();
		if(list != null){
			info.append("��ѯ��ɣ�ȫ���ʿ����£�\nid\tword\tlength\tcategory\tpart\n\n");
			for(Word w:list){
				info.append(w.getID()+"\t"+w.getValue()+"\t"+w.getLength()+"\t"+w.getCategory()+"\t"+w.getPart()+"\n");
			}
			info.append("\n======================================================================\n");
		} else{
			info.append("û�в�ѯ���κν��\n\n");
		}
		list.clear();
	}

}
