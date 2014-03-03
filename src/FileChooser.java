import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JPanel implements ActionListener {
	JButton openButton,saveButton;
	TrnaDatabase db;
	JTextArea log;
	
	FileChooser(){
	}
	FileChooser(int par) {
		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		
		if(par == 1){
			saveButton = new JButton("Save a File...");
			saveButton.addActionListener(this);
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(saveButton);
			add(buttonPanel, BorderLayout.PAGE_START);
			add(logScrollPane, BorderLayout.CENTER);
		}
		if(par == 2){
			// Create the open button. We use the image from the JLF
			// Graphics Repository (but we extracted it from the jar).
			openButton = new JButton("Open a File...");
			openButton.addActionListener(this);
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(openButton);
			add(buttonPanel, BorderLayout.PAGE_START);
			add(logScrollPane, BorderLayout.CENTER);
		}
		// For layout purposes, put the buttons in a separate panel
		

		// Add the buttons and the log to this panel.
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// Handle open button action.
		if (e.getSource() == openButton) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"FASTA & CSV", "fa", "csv");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: "
						+ chooser.getSelectedFile().getAbsolutePath());
				log.append("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath() + "\n");
				log.setCaretPosition(log.getDocument().getLength());
			}
		}
	/*	else{
			db= new TrnaDatabase();
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"DAT", ".dat");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: "
						+ chooser.getSelectedFile().getAbsolutePath());
				log.append("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath() + "\n");
				log.setCaretPosition(log.getDocument().getLength());
				db.saveToFile(chooser.getSelectedFile().getAbsolutePath());
			}
		}*/
	}

	public void createAndShowGUI(int par) {
		// Create and set up the window.
		JFrame frame = new JFrame("FileChooser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		
		frame.add(new FileChooser(par));

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public String saveFileChooser(){
		String retVal = new String("sample.dat");
		try{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"DAT", "dat");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: "
						+ chooser.getSelectedFile().getAbsolutePath());
				retVal = chooser.getSelectedFile().getAbsolutePath();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return retVal;
	}
	

	/*public static void main(String args[]) {
		//Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });

	}*/
}
