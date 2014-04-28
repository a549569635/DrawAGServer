package frame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import warpper.Word;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dbmanager.WLibManager;

public class WLAddFrame extends JFrame {
	private JPanel contentPane;
	private JScrollPane scroPane;
	private JTextArea info;
	private JLabel wordLabel;
	private JLabel lengthLabel;
	private JLabel cateLabel;
	private JLabel partLabel;
	private JTextField wordField;
	private JTextField lengthField;
	private JTextField cateField;
	private JTextField partField;
	private JButton subButton;
	
	private String word;
	private String length;
	private String category;
	private String part;
	
	private WLibManager wlman = new WLibManager();
	
	public WLAddFrame() {
		setTitle("词库添加");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getRootPane().setDefaultButton(subButton);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,200));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {100, 100, 100, 100};
		gbl_contentPane.rowHeights = new int[] {80, 40, 40, 40};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);

		info = new JTextArea();
		info.setBounds(0, 0, 380, 90);
		info.setBackground(Color.WHITE);
		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);

		scroPane = new JScrollPane(info);
		GridBagConstraints gbc_scroPane = new GridBagConstraints();
		gbc_scroPane.gridwidth = 4;
		gbc_scroPane.insets = new Insets(5, 10, 5, 10);
		gbc_scroPane.fill = GridBagConstraints.BOTH;
		gbc_scroPane.gridx = 0;
		gbc_scroPane.gridy = 0;
		contentPane.add(scroPane, gbc_scroPane);

		wordLabel = new JLabel("word：");
		GridBagConstraints gbc_wordLabel = new GridBagConstraints();
		gbc_wordLabel.fill = GridBagConstraints.BOTH;
		gbc_wordLabel.insets = new Insets(5, 5, 5, 5);
		gbc_wordLabel.gridx = 0;
		gbc_wordLabel.gridy = 1;
		contentPane.add(wordLabel, gbc_wordLabel);

		lengthLabel = new JLabel("长度：");
		GridBagConstraints gbc_lengthLabel = new GridBagConstraints();
		gbc_lengthLabel.fill = GridBagConstraints.BOTH;
		gbc_lengthLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lengthLabel.gridx = 1;
		gbc_lengthLabel.gridy = 1;
		contentPane.add(lengthLabel, gbc_lengthLabel);

		cateLabel = new JLabel("分类：");
		GridBagConstraints gbc_cateLabel = new GridBagConstraints();
		gbc_cateLabel.fill = GridBagConstraints.BOTH;
		gbc_cateLabel.insets = new Insets(5, 5, 5, 5);
		gbc_cateLabel.gridx = 2;
		gbc_cateLabel.gridy = 1;
		contentPane.add(cateLabel, gbc_cateLabel);

		partLabel = new JLabel("词性：");
		GridBagConstraints gbc_partLabel = new GridBagConstraints();
		gbc_partLabel.insets = new Insets(5, 5, 5, 0);
		gbc_partLabel.fill = GridBagConstraints.BOTH;
		gbc_partLabel.gridx = 3;
		gbc_partLabel.gridy = 1;
		contentPane.add(partLabel, gbc_partLabel);

		wordField = new JTextField();
		GridBagConstraints gbc_wordField = new GridBagConstraints();
		gbc_wordField.anchor = GridBagConstraints.NORTH;
		gbc_wordField.insets = new Insets(0, 5, 0, 5);
		gbc_wordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_wordField.gridx = 0;
		gbc_wordField.gridy = 2;
		contentPane.add(wordField, gbc_wordField);

		lengthField = new JTextField();
		GridBagConstraints gbc_lengthField = new GridBagConstraints();
		gbc_lengthField.anchor = GridBagConstraints.NORTH;
		gbc_lengthField.insets = new Insets(0, 5, 0, 5);
		gbc_lengthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lengthField.gridx = 1;
		gbc_lengthField.gridy = 2;
		contentPane.add(lengthField, gbc_lengthField);

		cateField = new JTextField();
		GridBagConstraints gbc_cateField = new GridBagConstraints();
		gbc_cateField.anchor = GridBagConstraints.NORTH;
		gbc_cateField.insets = new Insets(0, 5, 0, 5);
		gbc_cateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cateField.gridx = 2;
		gbc_cateField.gridy = 2;
		contentPane.add(cateField, gbc_cateField);

		partField = new JTextField();
		GridBagConstraints gbc_partField = new GridBagConstraints();
		gbc_partField.anchor = GridBagConstraints.NORTH;
		gbc_partField.insets = new Insets(0, 5, 0, 5);
		gbc_partField.fill = GridBagConstraints.HORIZONTAL;
		gbc_partField.gridx = 3;
		gbc_partField.gridy = 2;
		contentPane.add(partField, gbc_partField);

		subButton = new JButton("提交添加");
		GridBagConstraints gbc_subButton = new GridBagConstraints();
		gbc_subButton.fill = GridBagConstraints.BOTH;
		gbc_subButton.gridwidth = 4;
		gbc_subButton.insets = new Insets(0, 10, 5, 10);
		gbc_subButton.gridx = 0;
		gbc_subButton.gridy = 3;
		contentPane.add(subButton, gbc_subButton);
		subButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				word = wordField.getText();
				length = lengthField.getText();
				category = cateField.getText();
				part = partField.getText();
				if(wlman.Add(new Word(word, Integer.parseInt(length), category, part))){
					info.append("指定项目添加成功！\n\n");
				} else{
					info.append("添加失败\n\n");
				}
			}
		});




		pack();
		setLocationRelativeTo(null);
	}

}
