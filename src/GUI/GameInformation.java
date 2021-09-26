package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GameInformation extends JFrame{

	// from the welcome screen, the player can retrieve basic game information and rules of play in a new frame from a JButton
	private static final long serialVersionUID = 1L;
	
	static GameInformation game_information; 
	
	JTextArea userHelp; 
	
	GameInformation() throws IOException {
		setLayout(new BorderLayout()); 
        getContentPane().setBackground(Color.WHITE); 
       
        File rules_file = new File("rules.txt"); 
        JTextArea userHelp = new JTextArea(); 	
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(rules_file));
		String line = "";
		 while ((line = br.readLine()) != null) {

			 userHelp.append(line + "\n");
			 
		getContentPane().add(userHelp); 
		setMinimumSize(new Dimension(1200, 1100));
		revalidate(); 
		setVisible(true); 
} 
	}
public static void main(String[] args) throws IOException {  

	game_information = new GameInformation(); 
	
}  
}  
