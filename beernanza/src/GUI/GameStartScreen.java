package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Graphics.CardPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameStartScreen extends JFrame {
	
	// GameStartScreen provides the welcome screen for the player when the game is executed. It is a JFrame populated with various 
	// buttons, sliders, and check boxes to select game preferences before execution. From this screen the player sets the speed 
	// difficulty, board dimensions, single or multi player mode, and can select the alternate card deck. The player may also view
	// the game help/rules of play window, high scores of past game sessions, and can reset the high score contents. Finally, the
	// player may create a new game session.  
	
	private static final long serialVersionUID = 1L;
	
	// user can set the difficulty setting for the game timer using a slider 
	static JSlider difficultySetting; 
	
	// JButtons establish starting the game, looking at the game play manual, viewing high scores and resetting them
	private JButton startGame;   
	private JButton helpButton; 
	private JButton resetScores; 
	
	// a second version of this Button is added to the in-game card panel
	private static JButton gameScores;
	
	// declare an instance of the welcome screen to be visible when the game is loaded
	static GameStartScreen welcomeWindow; 
	
	// the default timer value is set  to 1100 - the amount of time that a card is visible before it is returned to its
	// starting face 
	private static int timerValue = 1100; 
	
	// spinners help to set the board dimensions before the board is created 
	JSpinner boardColumns;
	JSpinner boardRows; 
	
	// the default deck is the classic card deck. if the user wants to switch to the alternate trappist deck, it can be done
	// with the JCheckbox changing this boolean value
	
	ImageIcon welcomeScreenBackground = new ImageIcon(this.getClass().getResource("/special_card/beer.png"));
	
	// JSpinner values are saved to doubles and used to calculate the number of cards to cast
	private static double columnsToDisplay;
	private static double rowsToDisplay; 
	
	    public GameStartScreen() throws IOException {
	    
	        setTitle("Beernanza!");
	        setSize(400,400);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setVisible(true);
	        
	        setLayout(new BorderLayout());
	        getContentPane().setBackground(Color.WHITE); 
	        
	        // add all visual components to the label which is added to the frame
	        JLabel background=new JLabel(welcomeScreenBackground);
	        
	        add(background);
	        background.setLayout(null);
	       
	        // Create JButtons for starting the game, viewing the help function, and viewing the saved scores 
	        startGame=new JButton("Click here to start!");
	        startGame.setBounds(130, 350, 250, 50);
	        helpButton = new JButton("Click Here for Help");
	        helpButton.setBounds(310, 20, 180, 30);
	        helpButton.addActionListener(new GameHelp()); 	       	
	        startGame.addActionListener(new GameStarter()); 
	        setGameScores(new JButton("View High Scores!"));
	        getGameScores().setBounds(25, 260, 200, 30);
	        getGameScores().addActionListener(new ScoreActivator()); 
	        
	        // Create a JSlider to establish a difficulty setting. This difficulty is applied to the game timer
	        // to influence the speed at which cards are viewed during game play
	        difficultySetting = new JSlider(150, 2500, 1100);
	        difficultySetting.setBounds(10, 80, 180, 30);
	        JTextArea difficultyText = new JTextArea("1. Choose your Difficulty:"); 
	        difficultyText.setBounds(20, 70, 165, 30);
	        JTextArea easyLabel = new JTextArea("Easy"); 
	        easyLabel.setBounds(157, 98, 30, 15); 
	        JTextArea hardLabel = new JTextArea("Hard"); 
	        hardLabel.setBounds(15, 98, 30, 15); 
	        difficultySetting.addChangeListener(new ChangeListener() {
	        	public void stateChanged(ChangeEvent event) {
	        		setTimerValue(difficultySetting.getValue()); 
	        		
	        	}
	        	
	        });
	        
	       // JButton for resetting high scores	  
	       resetScores = new JButton("Reset High Scores"); 
	       resetScores.setBounds(275, 260, 200, 30);
	       resetScores.addActionListener(new ScoreResetter());
	       
	       // JSpinners for board dimensions (columns and rows) with accompanying text labels
	       // JSpinners have action listeners to retrieve values for board generation, linked to the card panel array 
	       SpinnerNumberModel columnDimensions = new SpinnerNumberModel(0.0, 0.0, 7.0, 1.0); 
	       SpinnerNumberModel columnRows = new SpinnerNumberModel(0.0, 0.0, 7.0, 1.0); 
	       JSpinner boardColumns = new JSpinner(columnDimensions); 
	       boardColumns.setBounds(430, 120, 40, 30);
	       JTextArea columnText = new JTextArea("Columns:"); 
	       columnText.setBounds(365, 125, 70, 30);
	       boardColumns.addChangeListener(new ChangeListener() {
	        	public void stateChanged(ChangeEvent event) {
	        		setColumnsToDisplay((double) boardColumns.getValue()); 
	        		
	        	}
	        	
	        });
	        JSpinner boardRows = new JSpinner(columnRows); 
	        boardRows.setBounds(430, 170, 40, 30);
	        JTextArea rowText = new JTextArea("Rows:"); 
	        rowText.setBounds(385, 175, 40, 30);
	        boardRows.addChangeListener(new ChangeListener() {
	        	public void stateChanged(ChangeEvent event) {
	        		setRowsToDisplay((double) boardRows.getValue()); 
	        		
	        	}
	        	
	        });
	        JTextArea chooseDimensions = new JTextArea("2.Choose Board \n Dimensions:");
	        chooseDimensions.setBounds(380, 70, 150, 30);
	        // add all components to the welcome screen
	        background.add(difficultySetting);
	        background.add(startGame);
	        background.add(helpButton); 
	        background.add(getGameScores()); 
	        background.add(resetScores); 
	        background.add(boardColumns); 
	        background.add(boardRows);
	        background.add(columnText); 
	        background.add(rowText); 
	        background.add(difficultyText);
	        background.add(chooseDimensions); 
	        background.add(easyLabel); 
	        background.add(hardLabel); 
	        // the default playing deck can be changed to the trappist deck by selecting this check box before a new game
	        JCheckBox selectAlternateDeck = new JCheckBox("Trappist Deck"); 	        
	        selectAlternateDeck.addChangeListener(new ChangeListener() {
	        	public void stateChanged(ChangeEvent event) {	         
	                    	CardPanel.setDefaultDeck(false);	                
	                }
	                	         		
	        });
	        
	        // using this check box switches to multi-player mode (single screen)
	        JCheckBox setHumanOpponent = new JCheckBox("Two-Player"); 
	        setHumanOpponent.addChangeListener(new ChangeListener() {
	        	public void stateChanged(ChangeEvent event) {		                	      
	                    	CardPanel.setPlayHumanOpponent(true);	                	          
	                }	         		
	        });
	        JTextArea specialAdditions = new JTextArea("3.Modes & Options"); 
	        
	        selectAlternateDeck.setBounds(10, 200, 180, 45);
	        setHumanOpponent.setBounds(10, 180, 170, 30);
	        specialAdditions.setBounds(10, 140, 122, 35);
	        background.add(setHumanOpponent); 
	        background.add(selectAlternateDeck); 
	        background.add(specialAdditions);
	        setSize(499,499);
	        setSize(500,500);
	       
	    }
	    
	    // set a background for the welcome screen
	    public Image setStartingBackground () throws IOException {
	        Image backgroundImage = ImageIO.read(new File("/special_card/beer.png"));
	        return backgroundImage; 
	    }

	    public void paintComponent(Graphics g) throws IOException {
	      super.paintComponents(g);

	      // Draw the background image for the welcome screen
	      g.drawImage(setStartingBackground (), 0, 0, this);
	      return; 
	      
	    }

	    // getter and setter methods for private static variables

	    public static int getTimerValue() {
	    	return timerValue;
	    }

	    public static void setTimerValue(int timerValue) {
	    	GameStartScreen.timerValue = timerValue;
	    }

	    public static double getColumnsToDisplay() {
	    	return columnsToDisplay;
	    }

	    public static void setColumnsToDisplay(double columnsToDisplay) {
	    	GameStartScreen.columnsToDisplay = columnsToDisplay;
	    }

	    public static double getRowsToDisplay() {
	    	return rowsToDisplay;
	    }

	    public static void setRowsToDisplay(double rowsToDisplay) {
	    	GameStartScreen.rowsToDisplay = rowsToDisplay;
	    }

	    public static void main(String args[]) throws IOException
	    {
	    	welcomeWindow = new GameStartScreen();
	       
	    }

		public static JButton getGameScores() {
			return gameScores;
		}

		public static void setGameScores(JButton gameScores) {
			GameStartScreen.gameScores = gameScores;
		}
	    }