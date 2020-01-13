package Graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.GameStartScreen;
import Logic.CardClicker;
import Logic.ScoreClicker;

public class CardController extends JFrame{
	
	// CardController extends a JFrame and provides the complete game play window for the player. In this window the card panel
	// is visible, as well as two side panels that show current scores for either single or multi player, as well as the current
	// turn for either player 1 or 2. Two buttons also provide the ability for the player to save current score, and exit the
	// current game session. 
	
	private static final long serialVersionUID = 1L;
	
	static CardController currentWindow;  
	
	private String gameTitle = "Beernanza!"; 
	
	// create labels for the player score (first or second), as well as the player who has the current move (two-player mode only)
	private static JLabel scoreLabel = new JLabel("First Player Score: 0"); 
	
	private static JLabel secondplayerScore = new JLabel("Second Player Score: 0");
	
	private static JLabel currentPlayerMove = new JLabel("Current Move: First");
	
	// create a JFrame to contain all of the in-game visuals
	public JFrame imagePanel; 
	
	// declare the panel containing the cards, which is added to the final frame
	public static CardPanel playingPanel; 
	
	public CardController() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createSetup();
		endGame(); 
	}
	
	private void createSetup() {
		// Initialize the frame and populate it with the card panel, scoring panel, and additional options side panel
		// the layouts are set to null and set bounds is used to place all visual aspects in appropriate locations
		JFrame imagePanel = new JFrame(); 
		imagePanel.setTitle(this.gameTitle);
		imagePanel.getContentPane().setLayout(null);
		imagePanel.setMinimumSize(new Dimension(1000, 600));
		imagePanel.setPreferredSize(new Dimension(1500, 900));
		CardPanel playingPanel = new CardPanel(); 
		playingPanel.setBounds(10, 10, 1000, 700);
		JPanel sidePanel = new JPanel(new FlowLayout()); 
		JPanel scorePanel = new JPanel(new FlowLayout());
		sidePanel.setBounds(1025, 100, 250, 250);
		scorePanel.setBounds(1025, 400, 250, 250);
		imagePanel.add(playingPanel); 
		scoreLabel.setBounds(800,800,20,30);
		scorePanel.add(scoreLabel); 
		
		JButton endGame = new JButton("Exit Beernanza"); 
		endGame.setBounds(800, 1000, 30, 30);
		JButton writeScore = new JButton("Save the Highest Score!");
		endGame.addActionListener(new GameEnder()); 
		writeScore.addActionListener(new ScoreClicker()); 
		sidePanel.add(endGame); 
		writeScore.setBounds(800, 1100, 30, 30); 
		sidePanel.add(writeScore); 
		sidePanel.add(GameStartScreen.getGameScores()); 
		imagePanel.add(sidePanel);
		imagePanel.add(scorePanel); 
		imagePanel.setFocusable(true);
		imagePanel.setVisible(true);
		imagePanel.pack(); 
		revalidate();
		
		setVisible(true);
		
		// add a second score display to the score panel for player 2 if the two-player option is selected 
		if (CardPanel.isPlayHumanOpponent() == true)
		{
			secondplayerScore.setBounds(800,1400,20,40);
			scorePanel.add(secondplayerScore); 
			scorePanel.add(currentPlayerMove);
			
			}
		}
	// set score methods will add the calculated score for each player to the score display
	public static void setScore () {
		scoreLabel.setText("First Player Score: " + CardPanel.getFirstPlayerScore());
		
	}
	
	public static void setSecondScore () {
		if (CardPanel.isPlayHumanOpponent() == true)
		{
		secondplayerScore.setText("Second Player Player Score: " + CardPanel.getSecondPlayerScore());
		
	}
	}
	// in two-player mode, the class will write whose turn it currently is to the side panel display
	public static void setCurrentTurn () {
		if (CardPanel.isPlayHumanOpponent() == true) {
			currentPlayerMove.setText("Current Move: " + CardClicker.getPlayerTurn());
		}
	}
	
	private class GameEnder implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	// if all of the cards have been successfully matched, include a pop up to notify the player(s) that the game session is done
	public static void endGame() {
		if ((CardPanel.getBoardDimensions()*2) == CardClicker.getCardsRevealed()) {
			JFrame winner = new JFrame(); 
			JOptionPane.showMessageDialog(winner, "Game Over!"); 
			winner.setSize(300, 300);
			winner.setVisible(true);
		}
	}
	
	public static void main (String [] args) {
		
		currentWindow = new CardController();
}
}
