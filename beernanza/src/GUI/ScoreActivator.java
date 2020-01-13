package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

// create a JFrame to view the saved high scores. If no high scores exist in the save file, create a JFrame to specify
// this to the viewer

public class ScoreActivator implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		try {
			ScoreViewer.main(null);
		} catch (Exception e1) {
			JFrame noScores = new JFrame(); 
			JTextArea noScoreText = new JTextArea("No high scores have been saved!"); 
			noScores.add(noScoreText); 
			noScores.setVisible(true);
			noScores.setSize(400, 400);
		}
		
	}

}