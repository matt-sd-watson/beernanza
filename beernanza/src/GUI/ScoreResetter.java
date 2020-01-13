package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScoreResetter implements ActionListener {
	// Links with JButton on the welcome screen to delete the contents of the game score file 
	// New game score files are automatically generated when new scores are added and no game score file exists (write new csv)
	public void actionPerformed(ActionEvent e) {
		Path fileToDeletePath = Paths.get("game_score.csv"); 
		try {
			Files.delete(fileToDeletePath);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("There was an error deleting the score file"); 
		}

}
}
