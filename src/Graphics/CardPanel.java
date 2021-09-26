package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.GameStartScreen;
import Logic.CardClicker;
import Logic.TimerTracker;


public class CardPanel extends JPanel{
	
		// CardPanel creates a panel that contains the cards for basic game play, each contained within a JButton that can be clicked
		// and matched. Images are read into string arrays from folders in the source directory, and populate the panel in a random
		// configuration each time the panel is created. 
	
		private static final long serialVersionUID = 1L;
		
		// establish array of image locations to populate our card panel with JButtons

		private static String [] defaultDeckArray = {"/default_deck/chouffe.png", "/default_deck/de_konick.png", "/default_deck/delirium_tremens.jpg", 
				"/special_card/reset.jpg", "/default_deck/duvel.png", "/default_deck/jupiler.jpg", "/default_deck/kwak.jpg",
				"/default_deck/st_bernardus.png", "/default_deck/stella_artois.jpg", "/default_deck/tripel_karmeleit.png", 
				"/default_deck/cuvee.jpg","/default_deck/duchesse.jpg"}; 
		
		// the player can alternate between the classic and secondary trappist theme deck array for game sessions
		 
		private static String [] trappistDeckArray = {"/trappist_deck/achel.jpg", "/trappist_deck/chimay.jpg", "/trappist_deck/gregorius.jpg",
				"/special_card/reset.jpg", "/trappist_deck/la_trappe.jpg", "/trappist_deck/orval.jpg", "/trappist_deck/spencer.jpg", 
				"/trappist_deck/spencer_2.jpg","/trappist_deck/stift.jpg", "/trappist_deck/tre_fontane.jpg", "/trappist_deck/westmalle.jpg", 
				"/trappist_deck/westvleteren.jpg"}; 
		
		// establish two instances of each image button to match
		private static int buttonNumbers = defaultDeckArray.length * 2; 
		
		// establish array to aggregate the JButtons for each card 
		public static JButton [] cardButtons; 
		
		// the starting card face is common to all cards when the game starts
		public static ImageIcon startingCardFace = new ImageIcon(CardPanel.class.getResource("/special_card/beer_can.png"));
		
		// if the sabotage card is selected, the selecting player's score is reset to 0
		public static ImageIcon resetCard = new ImageIcon(CardPanel.class.getResource("/special_card/reset.jpg"));
		
		// establish an array of image icons to aggregate the JButtons for each card 
		public static ImageIcon [] gameCards; 
		
		private static ImageIcon tempFace; 
		
		// initialize the players' scores at 0
		private static int firstPlayerScore = 0; 
		private static int secondPlayerScore = 0; 
		
		private static boolean gameOver = false; 
		
		// the game timer dictates the amount of time that a card is visible while the system evaluates the match
		public static javax.swing.Timer gameTimer = new javax.swing.Timer (GameStartScreen.getTimerValue(), new TimerTracker()); 
		
		// track number of images seen at once- should be 2
		private static int visibleImages; 
		
		
		// the dimensions of the board dictate the number of cards needed to populate the board, taken from the dimensions
		// that the player sets before a session
		private static int boardDimensions = (int) ((GameStartScreen.getColumnsToDisplay()*GameStartScreen.getRowsToDisplay())/2); 
		
		// the classic card deck is the default playing set. the user can change this to the trappist set 
		private static boolean defaultDeck = true; 
		
		// by default, the playing mode will be single player. changing this boolean activates two-player mode 
		private static boolean playHumanOpponent = false; 
		
		int cardPosition; 
		
		// establish a board and populate it with cards 
		public CardPanel () {
			setBorder(new EmptyBorder(0,0,0,0)); 
			setLayout(new GridLayout((int)GameStartScreen.getRowsToDisplay(), (int)GameStartScreen.getColumnsToDisplay())); 
			setBackground(Color.WHITE); 
			setVisible(true); 
			addCards();  
			 
		}
		
		public static ImageIcon getStartingFace() {
		    return startingCardFace;
		}
		
		public void addCards () {
			// the required number of cards based on board dimensions are added to the panel in the form of JButtons
			// require multiples of each card (2)
			
			cardButtons = new JButton[getButtonNumbers()]; 
			gameCards = new ImageIcon[getButtonNumbers()]; 
			
			// standard for loop to obtain and set each card with an image from the array
			
			// use the board dimensions given by the user to decide how many cards populate the board
			for (int i = 0, cardPosition = 0; i < getBoardDimensions(); i++) {
				if (isDefaultDeck() == true) {
					gameCards[cardPosition] = new ImageIcon(CardPanel.class.getResource(defaultDeckArray[i]));
				} else {
					gameCards[cardPosition] = new ImageIcon(CardPanel.class.getResource(trappistDeckArray[i]));
				} 
				cardPosition = populateBoard(cardPosition);  
				
				// add corresponding matching cards to the deck and randomize the locations
				
				gameCards[cardPosition] = gameCards[cardPosition - 1]; 
				cardPosition = populateBoard(cardPosition);  
					
				}
			
				randomizeCards(); 	
		}
		
		public static void randomizeCards () {
			// randomize the location of cards by randomizing the reading of the card images into the array
			Random randomizeCards = new Random(); 
			for (int a = 0; a < (getBoardDimensions()*2); a++) {
				int b = randomizeCards.nextInt((int) getBoardDimensions()*2); 
				tempFace = gameCards[a]; 
				gameCards[a] = gameCards[b]; 
				gameCards[b] = tempFace;
			
			}
		}
		
		// make a JButton from each image icon and add an action listener to the button, allowing the user to click it
		public int populateBoard(int cardPosition) {
			cardButtons[cardPosition] = new JButton(""); 
			cardButtons[cardPosition].addActionListener(new CardClicker());
			cardButtons[cardPosition].setIcon(getStartingFace()); 
			cardButtons[cardPosition].setBackground(Color.BLACK); 
			cardButtons[cardPosition].setPreferredSize(new Dimension(200, 200));
			add(cardButtons[cardPosition++]);
			return cardPosition;
		}

		public static int getButtonNumbers() {
			return buttonNumbers;
		}

		public static void setButtonNumbers(int buttonNumbers) {
			CardPanel.buttonNumbers = buttonNumbers;
		}

		public static boolean isPlayHumanOpponent() {
			return playHumanOpponent;
		}

		public static void setPlayHumanOpponent(boolean playHumanOpponent) {
			CardPanel.playHumanOpponent = playHumanOpponent;
		}

		public static int getBoardDimensions() {
			return boardDimensions;
		}

		public static void setBoardDimensions(int boardDimensions) {
			CardPanel.boardDimensions = boardDimensions;
		}

		public static boolean isDefaultDeck() {
			return defaultDeck;
		}

		public static void setDefaultDeck(boolean defaultDeck) {
			CardPanel.defaultDeck = defaultDeck;
		}

		public static int getFirstPlayerScore() {
			return firstPlayerScore;
		}

		public static void setFirstPlayerScore(int firstPlayerScore) {
			CardPanel.firstPlayerScore = firstPlayerScore;
		}

		public static int getSecondPlayerScore() {
			return secondPlayerScore;
		}

		public static void setSecondPlayerScore(int secondPlayerScore) {
			CardPanel.secondPlayerScore = secondPlayerScore;
		}

		public static boolean isGameOver() {
			return gameOver;
		}

		public static void setGameOver(boolean gameOver) {
			CardPanel.gameOver = gameOver;
		}

		public static int getVisibleImages() {
			return visibleImages;
		}

		public static void setVisibleImages(int visibleImages) {
			CardPanel.visibleImages = visibleImages;
		}
}			