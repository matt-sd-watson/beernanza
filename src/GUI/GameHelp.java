package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameHelp implements ActionListener {
	 // establish JButton Action Listener to view the help page
	
	static GameInformation rulesWindow; 
	
	public void actionPerformed(ActionEvent e) {
		GameInformation rulesWindow = null;
		try {
			rulesWindow = new GameInformation();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.print("Error loading the game information");
		} 
		rulesWindow.setVisible(true);
		
	}
}
