
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

/**
 * ConnectFourGUI drives the GUI for the ConnectFour game.
 * 
 * @author Julian Cochran (julian.cochran@da.org)
 * @version 1.0 (02/20/2012)
 */
public class ConnectFourGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Container container;
	private JButton[][] buttons;
	private JLabel directionsLabel, topLabel;
	private JButton newGameButton, quitButton;
	private JComboBox p1ColorChoices, p2ColorChoices;
	private ImageIcon p1Icon, p2Icon, gameWonIcon;
	
	private Timer timer;	//to be used once the game has been won
	
	private BoardGame game;

	/**
	 * Constructor for class ConnectFourDemo calls multiple private helper methods to
	 * establish the GUI for the application as well as set up the underlying data
	 * from the class ConnectFour.
	 * @param title The title to be displayed on the window
	 * @throws HeadlessException
	 */
	public ConnectFourGUI(String title) throws HeadlessException {
		super(title);
		//establish the underlying data for the game
		game = new ConnectFour();
		
		//GUI
		container = getContentPane();
		//add all three panels to the window using private helper methods
		container.setLayout(new BorderLayout());
		container.add(getTopPanel(), BorderLayout.NORTH);
		container.add(getMiddlePanel(), BorderLayout.CENTER);
		container.add(getBottomPanel(), BorderLayout.SOUTH);
		
		//establish window size & visibility
		setSize(500, 500);
		setVisible(true);
		
		//initialize the timer for later use
		timer = new Timer(500, this);
	}

	/**
	 * Override of method actionPerformed provides action listening for three events:
	 * 1) the user(s) click(s) the "new game" button and a new game starts, provided the players
	 *    have selected the proper colors for their button icons
	 * 2) the user(s) click(s) the "quit" button, closing the application
	 * 3) the user(s) click(s) one of the buttons in a column, playing the game
	 *    As #3 repeatedly happens, the game must check for win/draw/loss and react accordingly
	 * @param e An ActionEvent triggered by the user clicking a mouse
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGameButton)	{
			//sort out player colors first
			//make sure p1 has selected a color choice
			if(p1ColorChoices.getSelectedIndex() == 0)
				directionsLabel.setText("ERROR: please select a color choice for Player 1.");
			//then check to make sure p2 has selected a color choice
			else if(p2ColorChoices.getSelectedIndex() == 0)
				directionsLabel.setText("ERROR: please select a color choice for Player 2.");
			//output an error and don't allow the game to start if p1 & p2 chose identical colors
			else if(p1ColorChoices.getSelectedIndex() == p2ColorChoices.getSelectedIndex())
				directionsLabel.setText("ERROR: player 1 and player 2 may not select identical colors; try again.");
			else	{
				setupIcons(p1ColorChoices.getSelectedItem().toString(),
						p2ColorChoices.getSelectedItem().toString());	//establishes p1/p2 color disks plus red win disks
				game.newGame();
				directionsLabel.setText("Player " + ((ConnectFour)game).getCurrentPlayer() + " Starts the Game");
				setButtonsState(true);
				refreshBoard(game.getBoard());
				topLabel.setText("Click any button in a column to place your disk in that column.");
			}
			if(timer.isRunning())
				timer.stop();
		}
		else if(e.getSource() == quitButton)
			System.exit(0);
		else	{
			if(!game.gameOver())	{
				for(int r = 0; r < 6; r++)	{
					for(int c = 0; c < 7; c++)	{
						if(e.getSource() == buttons[r][c])	{
							game.play(c);
							refreshBoard(game.getBoard());
							if(game.gameOver())	{
								if(!game.getWinningPositions()[0].equals(new Position(-1, -1)))	{
									directionsLabel.setText("Player " + game.getWinner() + " has won the game!");
									displayWinningRow();
									timer.start();
								}
								else
									directionsLabel.setText("The game has ended in a draw.");
							}
						}
					}
				}
			}
		}
		//the timer gets started by the displayWinningRow method, so this test is always false
		//until the game is won
		//the code below makes the 4-piece win "flash" red
		if(e.getSource() == timer)	{
			int row, col;
			int whoWon = game.getWinner();
			for(int a = 0; a < game.getWinningPositions().length; a++)	{
				row = game.getWinningPositions()[a].getRow();
				col = game.getWinningPositions()[a].getCol();
				if(buttons[row][col].getIcon().equals(gameWonIcon))	{
					ImageIcon temp = (whoWon == 1) ? p1Icon : p2Icon;
					buttons[row][col].setIcon(temp);
				}
				else
					buttons[row][col].setIcon(gameWonIcon);
			}
		}
	}

	/**
	 * Main method for the class ConnectFourDemo
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectFourGUI connectFour = new ConnectFourGUI("Connect Four");
		connectFour.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//-------------------- PRIVATE METHODS --------------------
	/**
	 * getTopPanel returns a JPanel to be added/utilized at the top of the JFrame window
	 * @return A JPanel object with a directions JLabel in it.
	 */
	private JPanel getTopPanel()	{
		//top label
		topLabel = new JLabel("Select player colors below.");
		JPanel topPanel = new JPanel();
		topPanel.add(topLabel, SwingConstants.CENTER);
		
		return topPanel;
	}
	
	/**
	 * getMiddlePanel returns a JPanel to be added in the middle of the JFrame window
	 * @return A JPanel object with a 6x7 GridLayout containing 42 JButton objects
	 */
	private JPanel getMiddlePanel()	{
		//middle panel with the buttons
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(6, 7, 2, 4));
		//create JButtons & add to middlePanel
		buttons = new JButton[6][7];
		for(int r = 0; r < 6; r++)	{
			for(int c = 0; c < 7; c++)	{
				buttons[r][c] = new JButton();
				buttons[r][c].addActionListener(this);
				middlePanel.add(buttons[r][c]);
			}
		}
		//all buttons initially set disabled so players can't play until they select a color choice
		setButtonsState(false);
		return middlePanel;
	}
	
	/**
	 * getBottomPanel returns a JPanel to be added in the bottom of the JFrame window
	 * @return A JPanel object containing game selection/menu objects.
	 */
	private JPanel getBottomPanel()	{
		//bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(3, 3, 0, 5));
		directionsLabel = new JLabel("To begin, select a color for each player then click NEW GAME.", SwingConstants.CENTER);
		newGameButton = new JButton("NEW GAME");
		newGameButton.addActionListener(this);
		quitButton = new JButton("QUIT");
		quitButton.addActionListener(this);
		//top row of bottom panel
		bottomPanel.add(directionsLabel);
		//middle row of bottom panel
		JPanel tempMid = new JPanel();
		tempMid.setLayout(new GridLayout(1, 3));
		String[] colorLabels = {"PLACEHOLDER", "blue", "green", "orange", "purple", "yellow"};
		String p1 = "Player 1 color...";
		String p2 = "Player 2 color...";
		colorLabels[0] = p1;
		p1ColorChoices = new JComboBox(colorLabels);
		tempMid.add(p1ColorChoices);
		tempMid.add(newGameButton);
		colorLabels[0] = p2;
		p2ColorChoices = new JComboBox(colorLabels);
		tempMid.add(p2ColorChoices);
		bottomPanel.add(tempMid);
		//bottom row of bottom panel
		JPanel tempBottom = new JPanel();
		tempBottom.setLayout(new GridLayout(1, 3));
		tempBottom.add(new JLabel());
		tempBottom.add(quitButton);
		tempBottom.add(new JLabel());
		bottomPanel.add(tempBottom);
		
		return bottomPanel;
	}
	
	private void setupIcons(String p1, String p2)	{
		String p1Filename = "img/" + p1 + ".png";
		p1Icon = new ImageIcon(p1Filename);
		String p2Filename = "img/" + p2 + ".png";
		p2Icon = new ImageIcon(p2Filename);
		gameWonIcon = new ImageIcon("img/red.png");
	}
	
	private void setButtonsState(boolean enabled)	{
		for(int r = 0; r < 6; r++)	{
			for(int c = 0; c < 7; c++)	{
				buttons[r][c].setEnabled(enabled);
			}
		}
	}

	private void refreshBoard(int[][] board)	{
		for(int r = 0; r < 6; r++)	{
			for(int c = 0; c < 7; c++)	{
				if(board[r][c] == 1)	{
					buttons[r][c].setIcon(p1Icon);
				}
				else if(board[r][c] == 2)	{
					buttons[r][c].setIcon(p2Icon);
				}
				else
					buttons[r][c].setIcon(null);
			}
		}
	}
	
	private void displayWinningRow()	{
		int row, col;
		for(int a = 0; a < game.getWinningPositions().length; a++)	{
			row = game.getWinningPositions()[a].getRow();
			col = game.getWinningPositions()[a].getCol();
			buttons[row][col].setIcon(gameWonIcon);
		}
	}
}
