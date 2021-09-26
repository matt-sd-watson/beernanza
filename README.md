# beernanza!
beernanza is an Object-oriented tile-based memory board game in implemented in Java using the Swing GUI. The design and gameplay functionality takes advantage of many Java object-oriented concepts to enable game session customization, high score tracking, and single and multi-player modes. 

## Starting The Game 

The welcome screen for beernanza appears as follows: 
![Image description](https://github.com/matt-sd-watson/beernanza/tree/master/screenshot/home_screen.png)

## Basic Gameplay

In each game session, a board of anonymous beer logos is created at random, each hidden under the default card setting. The player is responsible for trying to match two of the same beer logos as quickly as possible. Each board can accommodate up to 12 logo pairs (or 24 total cards). From the main menu, the player can change the difficulty setting, board dimensions, and modes (see below). 

![Image description](https://github.com/matt-sd-watson/beernanza/tree/master/screenshot/gameplay.png)

### An example of beernanza being played in two-player split-screen mode. 

## Modes and Options

By default, Beernanza is set to be played as a single-player mode with one player tracking a single score. From the welcome screen, under Modes & Options, the user can select a two-player single-window shared session, where two players alternate card matching turns, and
two separate scores are tracked for each player. Similarly to the single player mode, the game ends when all of the cards have been 
matched.  The game session is loaded with the default classic dard deck, containing the card logos of many well-known Belgian
beers. For a new challenge, the player may also select the alternate "Trappist Traditions" card deck, which contains
the logos of several internationally renowned Trappist breweries, a specific subset of breweries that originated in Belgium. 

## Scoring
During game play, the player can either increase or decrease his/her score using the following actions and logic: 
- With the exception of the first attempt of the game, selecting mismatches in Beernanza results in a score decrease. This decrease is proportional to the number of cards clicked since the last match i.e. As you find more and more mismatches, your score will continue to decrease by a greater degree!
- If a match is achieved on the first two clicks of the game, the selecting player receives a large bonus that is proportional to the number of cards on the board
- If a match is achieved right after a previous match, the selecting player receives a modest bonus that is  proportional to the number of cards on the board. Note that in two-player mode, this does not apply, as turns are always alternated. 
- In all other scenarios, the player receives a score increase based on a timer that is activated once a mismatch happens, and stopped once a match occurs. 

Therefore, it is always more advantageous to find a match faster than slower!

![Image description](https://github.com/matt-sd-watson/beernanza/tree/master/screenshot/score_list.png)
### Score tracking enables the viewer to save and view key game session statistics. 

For each session, there is the possibility to save the player’s score (note that in two-player mode, only the highest score in saved).
From the in-game window, selecting "Save the highest score" will allow for the user to input a user name, which saves with their
particular score along with the date of play, number of playing cards, a difficulty setting, and whether or not the session was 
single or multi-player. 

Beernanza automatically generates a string that summarizes the difficulty setting of the game session. The options are: 
- Easing into it- The number of cards on the board is less than 6, or the speed of the timer is quite slow
- Stepping it up- The number of cards on the board is between 6 and 12, or the timer is increased a little bit
- Putting in work- The number of cards on the board is between 12 and 18, or the timer is increased a quite a bit
- True Bonanza- The number of cards on the board is greater than 18, or the timer is very quick!

Finally, these scores can be seen at any time from the main menu, and reset at any time if the user activates the reset from the
"Reset High Scores" option from the main menu.  

## Special Card
Beernanza employs a special action card called "Hair of the Dog", which resets the selecting player's score to 0. The card appears as the following image in game: 

![Image description](https://github.com/matt-sd-watson/beernanza/tree/master/screenshot/reset.jpg)

This card has both advantages and disadvantages: 
	- If the player currently has a negative score, the card will effectively increase their score back to 0, providing a benefit
	- If the player has a positive high score, selecting this card will effectively undo all progress and send their score
	  back to 0. Based on this logic, it is important to consider when to use this card in-game!
