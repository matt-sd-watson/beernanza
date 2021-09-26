package Logic;

import GUI.GameStartScreen;
import Graphics.CardPanel;

public class DifficultyAssessment {
	
	// using a combination of the timer speed and number of cards selected, each session of the game where a score is saved gets
	// a string representation of the game difficulty, saved to the high scores page and score CSV
	
	// userDifficulty can be seen in the score viewing for each game session
	private static String userDifficulty; 
	
	static String evaluateDifficulty () {
		if ((GameStartScreen.getTimerValue() >= 2000) || CardPanel.getBoardDimensions() < 6) {
			userDifficulty = "Easing into it"; 
		}
		if ((((GameStartScreen.getTimerValue() >= 1000) && (GameStartScreen.getTimerValue() <= 2000)) ||
				((CardPanel.getBoardDimensions() >= 6) && (CardPanel.getBoardDimensions() <= 12)))) {
			userDifficulty = "Stepping it Up"; 
		}
		if ((((GameStartScreen.getTimerValue() >= 500) && (GameStartScreen.getTimerValue() <= 1000)) ||
				((CardPanel.getBoardDimensions() >= 12) && (CardPanel.getBoardDimensions() <= 18)))) {
			userDifficulty = "Putting in Work!"; 
		}
		if ((GameStartScreen.getTimerValue() < 500) || CardPanel.getBoardDimensions() > 18) {
			userDifficulty = "True Bonanza!"; 
		}
		return userDifficulty; 
	}
}
