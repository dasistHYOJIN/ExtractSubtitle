package extractSubtitle;

import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DesignLayout extends JFrame {
	private JTextField textField_File;
	private JTextField textField_Save;
	private JTextArea textArea;
	
	/**
	 * Create the panel.
	 */
	
	public DesignLayout() {

	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setBounds(400, 400, 595, 441);
	    this.setResizable(false);
		setTitle("Extract Subtitle");
		setFont(new Font("08서울남산체 M", Font.PLAIN, 12));
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setBackground(SystemColor.inactiveCaptionBorder);
		getContentPane().setLayout(null);
		
		textField_File = new JTextField();
		textField_File.setEditable(false);
		textField_File.setText("\uD30C\uC77C\uC744 \uC120\uD0DD\uD558\uC138\uC694");
		textField_File.setBounds(149, 28, 222, 24);
		getContentPane().add(textField_File);
		textField_File.setColumns(10);
		
		JButton btnFile = new JButton("FILE");
		btnFile.addActionListener(new LoadBtnListener());
		btnFile.setBounds(385, 27, 75, 27);
		getContentPane().add(btnFile);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 64, 561, 272);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new SaveBtnListener());
		btnSave.setBounds(402, 351, 75, 27);
		getContentPane().add(btnSave);
		
		textField_Save = new JTextField();
		textField_Save.setEditable(false);
		textField_Save.setText("\uD30C\uC77C\uC744 \uC800\uC7A5\uD560 \uC704\uCE58\uB97C \uC120\uD0DD\uD558\uC138\uC694");
		textField_Save.setColumns(10);
		textField_Save.setBounds(130, 352, 258, 24);
		getContentPane().add(textField_Save);

	}

	class LoadBtnListener implements ActionListener {
	      public void actionPerformed(ActionEvent e) {
	    	  // FileDialog를 열어 불러올 파일 지정
	    	  JFileChooser chooser = new JFileChooser();
	    	  chooser.setCurrentDirectory(new File (System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
			  FileNameExtensionFilter filter = new FileNameExtensionFilter(
					  "자막 확장자 (.smi, .srt, .txt)", "smi", "txt", "srt");
			  chooser.setFileFilter(filter);
			  chooser.setVisible(true);
			  int returnVal = chooser.showOpenDialog(getParent());
			  // 불러올 파일의 경로명 저장
			  if(returnVal == JFileChooser.APPROVE_OPTION) {
				  textField_File.setText(chooser.getSelectedFile().toString());
				  System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
				  
				  ExtractSubtitle showSubtitle = new ExtractSubtitle(chooser.getSelectedFile().toString());
				  textArea.setText(showSubtitle.getSubtitle());

				  System.out.println("파일 저장함");
			  }
	      }
	}
	
	class SaveBtnListener implements ActionListener {
	      public void actionPerformed(ActionEvent e) {
	    	  //JOptionPane pane = null; //자바의 대부분의 경고창을 관리하는 클래스..
	    	  System.out.println(textField_File.getText());
	    	  if (textField_File.getText().equals("파일을 선택하세요"))
	    	  {
	    		  JOptionPane.showMessageDialog(getParent(), "파일을 선택해주세요");
	    		  return;
	    	  }
	    	  JFileChooser chooser = new JFileChooser();
	    	  chooser.setCurrentDirectory(new File (textField_File.getText()));
	    	  chooser.addChoosableFileFilter(new FileNameExtensionFilter(".smi", "smi"));
	    	  chooser.addChoosableFileFilter(new FileNameExtensionFilter(".srt", "srt"));
	    	  chooser.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
	    	  int returnVal = chooser.showSaveDialog(getParent());
			  // 불러올 파일의 경로명 저장
			  if(returnVal == JFileChooser.APPROVE_OPTION) {
				  textField_Save.setText(chooser.getSelectedFile().toString());
				  
				  BufferedWriter fw;
				  try {
					  fw = new BufferedWriter(new FileWriter(chooser.getSelectedFile().toString()+chooser.getFileFilter().getDescription(), true));
					  fw.write(textArea.getText());
					  fw.flush();
					  fw.close();

					  System.out.println("파일 저장함");
				  }
				  catch (IOException e1) {
					  // TODO Auto-generated catch block
					  e1.printStackTrace();
				  }
			  }
	      }
	}
}
