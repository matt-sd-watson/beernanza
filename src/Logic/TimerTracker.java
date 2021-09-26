package Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Graphics.CardPanel;

public class TimerTracker implements ActionListener {
	
	// This class establishes the starting default position of the cards at the beginning of the game, where the cards will be face
	// down with the starting face, and the timer will not be running until the first card click is executed by the player. 
		public void actionPerformed(ActionEvent e ) {
			
			CardPanel.cardButtons[CardClicker.getCurrentPosition()].setIcon(CardPanel.getStartingFace()); 
			CardPanel.cardButtons[CardClicker.getOddNumberClicks()].setIcon(CardPanel.getStartingFace()); 			
			CardPanel.gameTimer.stop();
			
}
}
