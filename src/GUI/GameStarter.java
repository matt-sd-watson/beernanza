package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Graphics.CardController;

public class GameStarter implements ActionListener {
	// establish JButton Action Listener to start game and connect to the card controller 
	
	public void actionPerformed(ActionEvent e) {
		CardController CurrentWindow = new CardController();
		CurrentWindow.setVisible(true);
		// set the game timer to the value retrieved from the JSlider for game difficulty
		GameStartScreen.setTimerValue(GameStartScreen.difficultySetting.getValue()); 
		
	}
	
}
