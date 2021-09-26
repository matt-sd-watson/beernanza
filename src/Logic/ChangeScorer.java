package Logic;

import java.time.Duration;
import java.time.Instant;

import Graphics.CardController;
import Graphics.CardPanel;

 
public class ChangeScorer {
	
	// Class provides logic to generate scenarios for adding the score for the user during game play. Methods are written for both 
	// single player and multi player options, and write the score to a card panel variables that is visible in a text field during
	// game play. Three scoring scenarios are possible. The system uses a duration class to track the amount of time passed between
	// card matches, and incorporates this duration into score calculation
	
	// start time begins when two cards are not matched successfully. end time is calculated when the next two cards are matched 
	// time between is the duration of time between these two instances, and influences the score given to a player (a longer
	// duration between matches results in a lower score)
	
	static Instant startTime; 
	static Instant endTime; 
	private static Duration timeBetween;
		
	// scoring logic- the scenarios depend on the number of cards clicked since the previous match as well as the time taken
	// in two player mode, the second player faces a small scoring deficit for going second
	
	public static void positiveFirstGameScore() {
		// if the player achieves a match on the first two cards of the game, apply a
				// 500 bonus relative to the number of cards in play
				if (CardClicker.getCurrentGameClicks() == 2) {
					int firstBonus = (int) 500 * (int)CardPanel.getBoardDimensions(); 
					CardPanel.setFirstPlayerScore(CardPanel.getFirstPlayerScore() + firstBonus);
					CardController.setScore(); 
					CardController.setCurrentTurn (); 
				} 
				// if the player achieves a match immediately after a previous match, apply a 
				// 200 bonus relative to the number of cards in play
				// this scoring system does not apply to two-player mode where alternating turns occur
				if (CardClicker.getCurrentGameClicks() == 4 && CardPanel.isPlayHumanOpponent() == false) {
				int backToBackBonus = (int) 200 * (int)CardPanel.getBoardDimensions(); 
					CardPanel.setFirstPlayerScore(CardPanel.getFirstPlayerScore() + backToBackBonus); 
					CardController.setScore();
					CardController.setCurrentTurn (); 

				} 
				// if the player selects the reset card, reset the player score to 0
				if ((CardPanel.gameCards[CardClicker.getCurrentPosition()].toString().equals(CardPanel.resetCard.toString()))) {
					CardPanel.setFirstPlayerScore(0); 
					CardController.setScore();
						
					} else {
					// if bonuses do not apply, apply a score addition based on the amount of time taken to fine a match
					int calculatedScore = (int) (1000000 /(Duration.between(startTime,endTime).toMillis())); 
					CardPanel.setFirstPlayerScore(CardPanel.getFirstPlayerScore() + calculatedScore); 
					CardController.setScore(); 
					CardController.setCurrentTurn (); 
					
		
	}
	}
	
	public static void positiveSecondGameScore() {
		// if the player achieves a match on the first two cards of the game, apply a
		// 500 bonus relative to the number of cards in play
		if (CardClicker.getCurrentGameClicks() == 2) {
			int firstBonus = (int) 500 * (int)CardPanel.getBoardDimensions(); 
			CardPanel.setSecondPlayerScore(CardPanel.getSecondPlayerScore() + firstBonus);
			CardController.setSecondScore (); 
		} 
		// if the player achieves a match immediately after a previous match, apply a 
		// 200 bonus relative to the number of cards in play
		if (CardClicker.getCurrentGameClicks() == 4) {
		int backToBackBonus = (int) 200 * (int)CardPanel.getBoardDimensions(); 
			CardPanel.setSecondPlayerScore(CardPanel.getSecondPlayerScore() + backToBackBonus); 
			CardController.setSecondScore ();

		} 
		
		// if the player selects the reset card, reset the player score to 0
		if ((CardPanel.gameCards[CardClicker.getCurrentPosition()].toString().equals(CardPanel.resetCard.toString()))) {
			CardPanel.setSecondPlayerScore(0); 
			CardController.setSecondScore();
			
		} else {
			// if bonuses do not apply, apply a score addition based on the amount of time taken to fine a match
			int calculatedScore = (int) (1000000 /(Duration.between(startTime,endTime).toMillis())); 
			CardPanel.setSecondPlayerScore(CardPanel.getSecondPlayerScore() + calculatedScore); 
			CardController.setSecondScore (); 
	}
}

	public static Duration getTimeBetween() {
		return timeBetween;
	}

	public static void setTimeBetween(Duration timeBetween) {
		ChangeScorer.timeBetween = timeBetween;
	}
}
