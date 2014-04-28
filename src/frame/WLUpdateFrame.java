package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import dbmanager.WLibManager;

public class WLUpdateFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane textPane;
	private JTextArea info;
	private JLabel keyLabel;
	private JComboBox keyBox;
	private JLabel kVLabel;
	private JTextField kVField;
	private JLabel aimLabel;
	private JComboBox aimBox;
	private JLabel aVLabel;
	private JTextField aVField;
	private JButton subButton;
	
	private String key;
	private String keyValue;
	private String aim;
	private String aimValue;
	private String[] indexs = {"id","word","length","category","part"};
	

	private WLibManager wlman = new WLibManager();
	
	public WLUpdateFrame() {
		setTitle("词库修改");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(subButton);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,300));
		setContentPane(contentPane);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {100, 300};
		gbl.rowHeights = new int[] {100, 35, 40, 40, 35, 50};
		contentPane.setLayout(gbl);
		
		info = new JTextArea();
		info.setBounds(0, 0, 380, 90);
		info.setBackground(Color.WHITE);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		
		textPane = new JScrollPane(info);
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridwidth = 2;
		gbc_info.fill = GridBagConstraints.BOTH;
		gbc_info.insets = new Insets(5, 10, 5, 10);
		gbc_info.gridx = 0;
		gbc_info.gridy = 0;
		contentPane.add(textPane, gbc_info);
		
		keyLabel = new JLabel("查询关键词：");
		GridBagConstraints gbc_keyLabel = new GridBagConstraints();
		gbc_keyLabel.insets = new Insets(5, 10, 0, 0);
		gbc_keyLabel.gridx = 0;
		gbc_keyLabel.gridy = 1;
		contentPane.add(keyLabel, gbc_keyLabel);
		
		keyBox = new JComboBox(indexs);
		GridBagConstraints gbc_keyBox = new GridBagConstraints();
		gbc_keyBox.fill = GridBagConstraints.BOTH;
		gbc_keyBox.insets = new Insets(5, 0, 0, 10);
		gbc_keyBox.gridx = 1;
		gbc_keyBox.gridy = 1;
		contentPane.add(keyBox, gbc_keyBox);
		
		kVLabel = new JLabel("查询值：");
		GridBagConstraints gbc_kVLabel = new GridBagConstraints();
		gbc_kVLabel.insets = new Insets(5, 10, 5, 0);
		gbc_kVLabel.gridx = 0;
		gbc_kVLabel.gridy = 2;
		contentPane.add(kVLabel, gbc_kVLabel);
		
		kVField = new JTextField();
		GridBagConstraints gbc_kVField = new GridBagConstraints();
		gbc_kVField.fill = GridBagConstraints.BOTH;
		gbc_kVField.insets = new Insets(5, 0, 5, 10);
		gbc_kVField.gridx = 1;
		gbc_kVField.gridy = 2;
		contentPane.add(kVField, gbc_kVField);
		kVField.setColumns(10);
		
		aimLabel = new JLabel("修改关键词：");
		GridBagConstraints gbc_aimLabel = new GridBagConstraints();
		gbc_aimLabel.insets = new Insets(5, 10, 5, 0);
		gbc_aimLabel.gridx = 0;
		gbc_aimLabel.gridy = 3;
		contentPane.add(aimLabel, gbc_aimLabel);
		
		aimBox = new JComboBox(indexs);
		GridBagConstraints gbc_aimBox = new GridBagConstraints();
		gbc_aimBox.fill = GridBagConstraints.BOTH;
		gbc_aimBox.insets = new Insets(5, 0, 5, 10);
		gbc_aimBox.gridx = 1;
		gbc_aimBox.gridy = 3;
		contentPane.add(aimBox, gbc_aimBox);
		
		aVLabel = new JLabel("修改值：");
		GridBagConstraints gbc_aVLabel = new GridBagConstraints();
		gbc_aVLabel.insets = new Insets(0, 10, 5, 0);
		gbc_aVLabel.gridx = 0;
		gbc_aVLabel.gridy = 4;
		contentPane.add(aVLabel, gbc_aVLabel);
		
		aVField = new JTextField();
		GridBagConstraints gbc_aVField = new GridBagConstraints();
		gbc_aVField.fill = GridBagConstraints.BOTH;
		gbc_aVField.insets = new Insets(0, 0, 5, 10);
		gbc_aVField.gridx = 1;
		gbc_aVField.gridy = 4;
		contentPane.add(aVField, gbc_aVField);
		aVField.setColumns(10);
		//setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		
		subButton = new JButton("提交修改");
		subButton.setBounds(80, 210, 120, 40);
		GridBagConstraints gbc_SubButton = new GridBagConstraints();
		gbc_SubButton.insets = new Insets(5, 10, 10, 10);
		gbc_SubButton.gridwidth = 2;
		gbc_SubButton.fill = GridBagConstraints.BOTH;
		gbc_SubButton.gridx = 0;
		gbc_SubButton.gridy = 5;
		contentPane.add(subButton, gbc_SubButton);
		subButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				key = (String) keyBox.getSelectedItem();
				keyValue = kVField.getText();
				aim = (String) aimBox.getSelectedItem();
				aimValue = aVField.getText();
				if(wlman.Update(key, keyValue, aim, aimValue)){
					info.append("指定项目修改成功！\n");
				} else{
					info.append("修改失败\n");
				}
			}
		});
		GridBagConstraints gbc = new GridBagConstraints();
		gbc_SubButton.fill = GridBagConstraints.BOTH;
		gbc_SubButton.gridx = 2;
		gbc_SubButton.gridy = 0;
		
		pack();
		setLocationRelativeTo(null);
	}
	
}
