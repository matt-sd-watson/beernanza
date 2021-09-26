package Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import Graphics.CardPanel;
 
public class ScoreClicker implements ActionListener {
	
	// This class adds the ability to save your score by clicking the button on the card frame, with the option to input your name.
	// If high scores do not exist in the system, it will create a new CSV file. if scores already exist, it will append to 
	// the existing list in CSV format. For the two player setting, the game will automatically save the higher of the two when the 
	// prompt is selected 
	
	public void actionPerformed(ActionEvent e) {
		PrintWriter printWriter = null;
		try {
			String saveUserName = JOptionPane.showInputDialog("Enter your name:");
			String fileName = "game_score.csv"; 
			File f = new File(fileName);

			
			if (f.exists() && !f.isDirectory() ) {
				printWriter = new PrintWriter(new FileOutputStream(new File(fileName), true));
			}
			else {
				printWriter = new PrintWriter(fileName);
			}
			printWriter.println();
			printWriter.print(saveUserName);
			printWriter.print(",");
			if (CardPanel.isPlayHumanOpponent() == true) {
				if (CardPanel.getFirstPlayerScore() >= CardPanel.getSecondPlayerScore()) {
					printWriter.print((int) CardPanel.getFirstPlayerScore()); 
				} else {
					printWriter.print((int) CardPanel.getSecondPlayerScore()); 
				}
			} else {
				printWriter.print((int) CardPanel.getFirstPlayerScore()); 
			}
			printWriter.print(",");
			printWriter.print(LocalDate.now());
			printWriter.print(",");
			printWriter.print((int) CardPanel.getBoardDimensions()*2); 
			printWriter.print(",");
			printWriter.print(DifficultyAssessment.evaluateDifficulty());
			printWriter.print(",");
			// if multi player is selected, save this user selection in the scores window
			if (CardPanel.isPlayHumanOpponent() == true) {
				printWriter.print("Yes");
			}
			
			
		} catch (IOException e1) {
			
			System.out.println("There was an error saving the game score to the file.");
		} 
		printWriter.close();
	} 
	}
