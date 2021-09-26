package Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

import Graphics.CardController;
import Graphics.CardPanel;

public class CardClicker implements ActionListener {
	
	// This class establishes the logic for when cards are clicked. Use of a timer dictates the amount of time that cards are
	// visible before the are either matched, or reset. Two cards need to be viewed at a time to find a potential match. The
	// system keeps track of the number of clicked images in order to evaluate scoring when matches occur. If the player
	// selects the multi-player mode, the system tracks a player turn string to make alternate turns possible 
	// determines the location of the image in array to track image placement relative to user clicks
	
	private static int currentPosition; 
	
	private static int oddNumberClicks = 1; 
			
			// game clicks can be tracked and evaluated if even or odd, and reset depending on the outcome of an attempted match
	private static int CurrentGameClicks; 
			
	// in two-player mode, playerTurn changes each time a set of clicks is attempted
	private static String playerTurn = "First"; 
	
	// the number of cards successfully matched is tracked in order to end the game
	private static int cardsRevealed; 
	
	// Action listener to establish logic for when cards are clicked
	public void actionPerformed(ActionEvent e ) {
		if (CardPanel.gameTimer.isRunning()) 
			return; 
		// when the game timer is running, add 1 to the number of face up images
		CardPanel.setVisibleImages(CardPanel.getVisibleImages() + 1); 	 
		
				setCurrentGameClicks(getCurrentGameClicks() + 1); 
		
		for (int i = 0; i < CardPanel.getButtonNumbers(); i++) {
			if (e.getSource() == CardPanel.cardButtons[i]) {
				// set the card clicked to an image from the deck array
				CardPanel.cardButtons[i].setIcon(CardPanel.gameCards[i]); 
				setCurrentPosition(i); 
}
		}
		
		// enforce that two clicks need to be completed before the game evaluates the card faces (two matching tiles)
		if (CardPanel.getVisibleImages() % 2 == 0) {
			if (getCurrentPosition() == getOddNumberClicks()) {
				
				// reset the total game clicks to an even number 
				setCurrentGameClicks(getCurrentGameClicks() - 1);   
				return; 

	}
		// when two images file paths do not match, they are reset and the match attempt persists
		if (CardPanel.gameCards[getCurrentPosition()] != CardPanel.gameCards[getOddNumberClicks()]) {
			if (CardPanel.isPlayHumanOpponent() == true) {
				if (getPlayerTurn().equals("First")) {
					setPlayerTurn("Second"); 
					CardPanel.gameTimer.start();
					ChangeScorer.startTime = Instant.now(); 
					// the score decrease is proportional to the number of clicks done since the last match 
					CardPanel.setFirstPlayerScore(CardPanel.getFirstPlayerScore() - (2*(getCurrentGameClicks() -2))); 
					CardController.setScore();
					CardController.setCurrentTurn(); 
					CardController.endGame();
					
				} else if (getPlayerTurn().equals("Second")){
					setPlayerTurn("First"); 
					CardPanel.gameTimer.start();
					ChangeScorer.startTime = Instant.now(); 
					// the score decrease is proportional to the number of clicks done since the last match 
					CardPanel.setSecondPlayerScore(CardPanel.getSecondPlayerScore() - (2*(getCurrentGameClicks() -2))); 
					CardController.setSecondScore();
					CardController.setCurrentTurn (); 
					CardController.endGame();
				} 
			} else {
				// in single-player mode, the score decrease is applied in the same way to two-player mode
				CardPanel.gameTimer.start();
				ChangeScorer.startTime = Instant.now(); 
				// the score decrease is proportional to the number of clicks done since the last match 
				CardPanel.setFirstPlayerScore(CardPanel.getFirstPlayerScore() - (2*(getCurrentGameClicks() -2))); 
				CardController.setScore();
				CardController.endGame();
			}
		
			} else {
						
				// if the cards do match, calculate the score based on the amount of time taken to match
				if (CardPanel.isPlayHumanOpponent() == true) {
					if (getPlayerTurn().equals("First")) {
						setPlayerTurn("Second"); 
						ChangeScorer.endTime = Instant.now(); 
						ChangeScorer.positiveFirstGameScore(); 
						setCurrentGameClicks(2); 
						setCardsRevealed(getCardsRevealed() + 2); 
						CardController.setCurrentTurn (); 
						CardController.endGame();
						
					} else if (getPlayerTurn().equals("Second")) {
						setPlayerTurn("First"); 
						ChangeScorer.endTime = Instant.now(); 
						ChangeScorer.positiveSecondGameScore();
						setCurrentGameClicks(2); 
						setCardsRevealed(getCardsRevealed() + 2); 
						CardController.setCurrentTurn (); 
						CardController.endGame();
					}
				} else {
				// in single player mode, the scoring logic using the timer is applied in the same way as in two-player mode
				setCardsRevealed(getCardsRevealed() + 2); 
				ChangeScorer.endTime = Instant.now(); 
				ChangeScorer.positiveFirstGameScore(); 
				setCurrentGameClicks(2); 
				
				CardController.endGame();
				
	}
			}
	} else {
		// when 1 card is clicked, the loop will continue in order to reach 2 clicks for tile matching
		// this enforces that two cards must be clicked before the system can evaluate a match
		setOddNumberClicks(getCurrentPosition()); 

}
}
	
	public static int getCardsRevealed() {
		return cardsRevealed;
	}

	public static void setCardsRevealed(int cardsRevealed) {
		CardClicker.cardsRevealed = cardsRevealed;
	}

	public static String getPlayerTurn() {
		return playerTurn;
	}

	public static void setPlayerTurn(String playerTurn) {
		CardClicker.playerTurn = playerTurn;
	}

	public static int getCurrentPosition() {
		return currentPosition;
	}

	public static void setCurrentPosition(int currentPosition) {
		CardClicker.currentPosition = currentPosition;
	}

	public static int getOddNumberClicks() {
		return oddNumberClicks;
	}

	public static void setOddNumberClicks(int oddNumberClicks) {
		CardClicker.oddNumberClicks = oddNumberClicks;
	}

	public static int getCurrentGameClicks() {
		return CurrentGameClicks;
	}

	public static void setCurrentGameClicks(int currentGameClicks) {
		CurrentGameClicks = currentGameClicks;
	}

}
