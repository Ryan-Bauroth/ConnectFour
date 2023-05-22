/**
 * ConnectFourTest tests the data backend ConnectFour class for
 * the ConnectFour project.
 * @author Julian Cochran
 * @version 01/29/2013
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class ConnectFourTest {
	private ConnectFour tester;
	private Position[] winningPositions;
	private int[][] array;
	private int startPos;	//starting position for wins
	private Position tempPos;
	
	/**
	 * Establish the backend data objects
	 * @throws <code>Exception</code> if the items can not be initialized.
	 */
	@Before
	public void setUp() throws Exception {
		tester = new ConnectFour();
		array = new int[6][7];
		winningPositions = new Position[4];
		startPos = -1;
		tempPos = new Position(-1, -1);
	}
	
	////////// PRIVATE METHODS //////////
	/**
	 * Prints the contents of the data array to the terminal window
	 * for debugging purposes.
	 */
	private void printArray()	
	{
		array = tester.getBoard();
		for(int a = 0; a < array.length; a++)	{
			for(int b = 0; b < array[a].length; b++)
				System.out.print(array[a][b] + " ");
			System.out.println();
		}
	}
	/**
	 * P1 wins horizontally; random column placement
	 */
	private void p1HorizWin()	
	{
		int ranCol = (int)(Math.random()*4);
		tester.play(ranCol);		//p1, col 0
		tester.play(ranCol);		//p2, col 0
		tester.play(ranCol+1);	//p1, col 1
		tester.play(ranCol+1);	//p2, col 1
		tester.play(ranCol+2);	//p1, col 2
		tester.play(ranCol+2);	//p2, col 2
		tester.play(ranCol+3);	//p1, col 3; p1 should be the winner
		System.out.println("** Horizontal win example P1 **");
		printArray();
		startPos = ranCol;
	}
	/**
	 * P2 wins horizontally; random column placement
	 */
	private void p2HorizWin()	
	{
		tester.play(0);		//switch to p2
		int ranCol = 1+(int)(Math.random()*3);
		tester.play(ranCol);		//p2
		tester.play(ranCol);		//p1
		tester.play(ranCol+1);	//p2
		tester.play(ranCol+1);	//p1
		tester.play(ranCol+2);	//p2
		tester.play(ranCol+2);	//p1
		tester.play(ranCol+3);	//p2 should be the winner
		System.out.println("** Horizontal win example P2 **");
		printArray();
		startPos = ranCol;
	}
	/**
	 * P1 wins vertically; random column placement
	 */
	private void p1VertWin()	
	{
		int ranCol = (int)(Math.random()*6);
		tester.play(ranCol);		//p1, col 0
		tester.play(ranCol+1);	//p2, col 0
		tester.play(ranCol);		//p1, col 1
		tester.play(ranCol+1);	//p2, col 1
		tester.play(ranCol);		//p1, col 2
		tester.play(ranCol+1);	//p2, col 2
		tester.play(ranCol);		//p1, col x; p1 should be the winner
		System.out.println("** Vertical win example P1 **");
		printArray();
		startPos = ranCol;
	}
	/**
	 * P2 wins vertically; random column placement
	 */
	private void p2VertWin()	
	{
		tester.play(0);	//switch to p2
		int ranCol = 1+(int)(Math.random()*5);
		tester.play(ranCol);		//p2
		tester.play(ranCol+1);	//p1
		tester.play(ranCol);		//p2
		tester.play(ranCol+1);	//p1
		tester.play(ranCol);		//p2
		tester.play(ranCol+1);	//p1
		tester.play(ranCol);		//p2 should be the winner
		System.out.println("** Vertical win example P2 **");
		printArray();
		startPos = ranCol;
	}
	/**
	 * P1 wins forward slash; random column placement
	 */
	private void p1FwdSlashWin()	
	{
		int ranCol = (int)(Math.random()*4);
		tester.play(ranCol);		//p1 1st ROW
		tester.play(ranCol+1);	//p2
		tester.play(ranCol+2);	//p1
		tester.play(ranCol+3);	//p2
		tester.play(ranCol+1);	//p1 2nd ROW
		tester.play(ranCol+2);	//p2
		tester.play(ranCol+3);	//p1
		tester.play(ranCol+3);	//p2 3rd ROW
		tester.play(ranCol+2);	//p1
		tester.play(ranCol+1);	//p2
		tester.play(ranCol+3);	//p1 4th ROW WIN
		System.out.println("** Forward slash win example P1 **");
		printArray();
		startPos = ranCol;
	}
	/**
	 * P2 wins forward slash; random column placement
	 */
	private void p2FwdSlashWin()	
	{
		tester.play(0);	//switch to P2
		int ranCol = 1+(int)(Math.random()*3);
		tester.play(ranCol);		//p2 1st ROW
		tester.play(ranCol+1);	//p1
		tester.play(ranCol+2);	//p2
		tester.play(ranCol+3);	//p1
		tester.play(ranCol+1);	//p2 2nd ROW
		tester.play(ranCol+2);	//p1
		tester.play(ranCol+3);	//p2
		tester.play(ranCol+3);	//p1 3rd ROW
		tester.play(ranCol+2);	//p2
		tester.play(ranCol+1);	//p1
		tester.play(ranCol+3);	//p2 4th ROW WIN
		System.out.println("** Forward slash win example P2 **");
		printArray();
		startPos = ranCol;
	}
	/**
	 * P1 wins backslash; random column placement
	 */
	private void p1BackSlashWin()	
	{
		int ranCol = 3+(int)(Math.random()*4);
		tester.play(ranCol);	//p1 1st ROW
		tester.play(ranCol-1);	//p2
		tester.play(ranCol-2);	//p1
		tester.play(ranCol-3);	//p2
		tester.play(ranCol-1);	//p1 2nd ROW
		tester.play(ranCol-2);	//p2
		tester.play(ranCol-3);	//p1
		tester.play(ranCol-3);	//p2 3rd ROW
		tester.play(ranCol-2);	//p1
		tester.play(ranCol-1);	//p2
		tester.play(ranCol-3);	//p1 4th ROW WIN
		System.out.println("** Backslash win example P1 **");
		printArray();
		startPos = ranCol;
	}
	/**
	 * P2 wins backslash; random column placement
	 */
	private void p2BackSlashWin()	
	{
		tester.play(0);	//switch to p1
		int ranCol = 4+(int)(Math.random()*3);
		tester.play(ranCol);	//p2 1st ROW
		tester.play(ranCol-1);	//p1
		tester.play(ranCol-2);	//p2
		tester.play(ranCol-3);	//p1
		tester.play(ranCol-1);	//p2 2nd ROW
		tester.play(ranCol-2);	//p1
		tester.play(ranCol-3);	//p2
		tester.play(ranCol-3);	//p1 3rd ROW
		tester.play(ranCol-2);	//p2
		tester.play(ranCol-1);	//p1
		tester.play(ranCol-3);	//p2 4th ROW WIN
		System.out.println("** Backslash win example P2 **");
		printArray();
		startPos = ranCol;
	}
	////////// END OF PRIVATE METHODS //////////
	/**
	 * Asserts false: with empty board, gameOver() == false
	 * Tests gameOver method of ConnectFour
	 */
	@Test
	public void testGameOverEmptyBoard() {
		assertFalse("An empty board should return false", tester.gameOver());
	}
	/**
	 * Asserts false: with empty board, getWinner() == 0
	 * Tests getWinner method of ConnectFour
	 */
	@Test
	public void testGetWinnerEmptyBoard()	{
		assertEquals("An empty board has no winner", 0, tester.getWinner());
	}
	/**
	 * Asserts true: each individual column is full
	 * Asserts equal: a full board of non-matching ints returns 0
	 * as the winner (aka a draw)
	 */
	@Test
	public void testFullColumns()	{
		for(int c = 0; c < 7; c+=2)	{
			while(!tester.columnFull(c))	{
				tester.play(c);
			}
		}
		for(int r = 5; r >= 0; r--)	{
			for(int c = 1; c <= 5; c += 2)	{
				tester.play(c);
			}
		}
		
		for(int c = 0; c < 7; c++)
			assertTrue("Column " + c + " is full.", tester.columnFull(c));

		assertEquals("A full board with non-matching numbers has no winner",
				0, tester.getWinner());
		tester.newGame();
	}
	/**
	 * Plays a horizontal win for P1
	 * Asserts true: gameOver() == true when P1 wins
	 * Asserts equal: getWinner() returns 1
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP1HorizontalWinner()	{
		p1HorizWin();	//p1 wins horiz
		assertTrue("Testing if the game is over", tester.gameOver());	//is gameOver == true
		assertEquals("P1 horizontal winner correct", 1, tester.getWinner());	//is the correct winner noted
		winningPositions = tester.getWinningPositions();
		tempPos = new Position(5, startPos); 
		for(int c = startPos; c < startPos+4; c++)	{
			tempPos.setCol(c);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[c-startPos]));
		}
		tester.newGame();
	}
	/**
	 * Plays a horizontal win for P2
	 * Asserts true: gameOver() == true when P2 wins
	 * Asserts equal: getWinner() returns 2
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP2HorizontalWinner()	{
		p2HorizWin();
		assertTrue("Testing if the game is over", tester.gameOver());
		assertEquals("P2 horizontal win working correctly", 2, tester.getWinner());
		winningPositions = tester.getWinningPositions();
		tempPos = new Position(5, startPos); 
		for(int c = startPos; c < startPos+4; c++)	{
			tempPos.setCol(c);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[c-startPos]));
		}
		tester.newGame();
	}
	/**
	 * Plays a vertical win for P1
	 * Asserts true: gameOver() == true when P1 wins
	 * Asserts equal: getWinner() returns 1
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP1VerticalWinner()	{
		p1VertWin();
		assertTrue("Testing if the game is over", tester.gameOver());
		assertEquals("P1 vertical win working correctly", 1, tester.getWinner());
		winningPositions = tester.getWinningPositions();
		int rowStart = 5;
		tempPos = new Position(rowStart, startPos);
		for(int i = 0; i < winningPositions.length; i++){
			tempPos.setRow(rowStart);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[i]));
			rowStart--;
		}
		tester.newGame();
	}
	/**
	 * Plays a vertical win for P2
	 * Asserts true: gameOver() == true when P2 wins
	 * Asserts equal: getWinner() returns 2
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP2VerticalWinner()	{
		p2VertWin();
		assertTrue("Testing if the game is over", tester.gameOver());
		assertEquals("P2 vertical win working correctly", 2, tester.getWinner());
		winningPositions = tester.getWinningPositions();
		int rowStart = 5;
		tempPos = new Position(rowStart, startPos);
		for(int i = 0; i < winningPositions.length; i++){
			tempPos.setRow(rowStart);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[i]));
			rowStart--;
		}
		tester.newGame();
	}
	/**
	 * Plays a forward slash win for P1
	 * Asserts true: gameOver() == true when P1 wins
	 * Asserts equal: getWinner() returns 1
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP1ForwardSlashWinner()	{
		p1FwdSlashWin();
		assertTrue("Testing if the game is over", tester.gameOver());
		assertEquals("P1 forward slash win working correctly", 1, tester.getWinner());
		winningPositions = tester.getWinningPositions();
		int rowStart = 5;
		tempPos = new Position(rowStart, startPos);
		for(int i = 0; i < winningPositions.length; i++){
			tempPos.setRow(rowStart);
			tempPos.setCol(startPos);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[i]));
			rowStart--;
			startPos++;
		}
		tester.newGame();
	}
	/**
	 * Plays a forward slash win for P2
	 * Asserts true: gameOver() == true when P2 wins
	 * Asserts equal: getWinner() returns 2
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP2ForwardSlashWinner()	{
		p2FwdSlashWin();
		assertTrue("Testing if the game is over", tester.gameOver());
		assertEquals("P2 forward slash win working correctly", 2, tester.getWinner());
		winningPositions = tester.getWinningPositions();
		int rowStart = 5;
		tempPos = new Position(rowStart, startPos);
		for(int i = 0; i < winningPositions.length; i++){
			tempPos.setRow(rowStart);
			tempPos.setCol(startPos);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[i]));
			rowStart--;
			startPos++;
		}
		tester.newGame();
	}
	/**
	 * Plays a backslash win for P1
	 * Asserts true: gameOver() == true when P1 wins
	 * Asserts equal: getWinner() returns 1
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP1BackSlashWinner()	{
		p1BackSlashWin();
		assertTrue("Testing if the game is over", tester.gameOver());
		assertEquals("P1 backslash win working correctly", 1, tester.getWinner());
		winningPositions = tester.getWinningPositions();
		int rowStart = 5;
		tempPos = new Position(rowStart, startPos);
		for(int i = 0; i < winningPositions.length; i++){
			tempPos.setRow(rowStart);
			tempPos.setCol(startPos);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[i]));
			rowStart--;
			startPos--;
		}
		tester.newGame();
	}
	/**
	 * Plays a backslash win for P2
	 * Asserts true: gameOver() == true when P2 wins
	 * Asserts equal: getWinner() returns 2
	 * Asserts equal: getWinningPositions() returns correct winning positions
	 */
	@Test
	public void testP2BackSlashWinner()	{
		p2BackSlashWin();
		assertTrue("Testing if the game is over", tester.gameOver());
		assertEquals("P2 back slash win working correctly", 2, tester.getWinner());
		winningPositions = tester.getWinningPositions();
		int rowStart = 5;
		tempPos = new Position(rowStart, startPos);
		for(int i = 0; i < winningPositions.length; i++){
			tempPos.setRow(rowStart);
			tempPos.setCol(startPos);
			//System.out.println("DEBUG: " + tempPos + " - " + winningPositions[i]);
			assertTrue("Correct win placement", tempPos.equals(winningPositions[i]));
			rowStart--;
			startPos--;
		}
		tester.newGame();
	}
}
