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
		// TODO 自动生成的构造函数存根
		setTitle("词库查询");
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
		
		keyLabel = new JLabel("查询关键字：");
		operaPane.add(keyLabel);
		
		keyBox = new JComboBox(indexs);
		operaPane.add(keyBox);
		
		valueLabel = new JLabel("查询值：");
		operaPane.add(valueLabel);
		
		valueField = new JTextField();
		valueField.setPreferredSize(new Dimension(80,25));
		operaPane.add(valueField);
		
		subButton = new JButton("开始查询");
		operaPane.add(subButton);
		subButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				key = (String) keyBox.getSelectedItem();
				value = valueField.getText();
				list = wlman.queryByKey(key, value);
				if(!list.isEmpty()){
					info.append("查询完成，所有匹配结果如下：\nid\tword\tlength\tcategory\tpart\n\n");
					for(Word w:list){
						info.append(w.getID()+"\t"+w.getValue()+"\t"+w.getLength()+"\t"+w.getCategory()+"\t"+w.getPart()+"\n");
					}
					info.append("\n======================================================================\n");
				} else{
					info.append("没有查询到匹配结果\n\n");
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
			info.append("查询完成，全部词库如下：\nid\tword\tlength\tcategory\tpart\n\n");
			for(Word w:list){
				info.append(w.getID()+"\t"+w.getValue()+"\t"+w.getLength()+"\t"+w.getCategory()+"\t"+w.getPart()+"\n");
			}
			info.append("\n======================================================================\n");
		} else{
			info.append("没有查询到任何结果\n\n");
		}
		list.clear();
	}

}
