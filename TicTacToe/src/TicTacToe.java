import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

/**
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * It is the applet
 * Not runable
 * Needs a mouse listener
 * Needs buttons preformed 
 * Randomly chooses who makes first move
 * If (computer plays first): Tells AI to make a move, 
 * draws board
 * Else: User selects where to go after prompt
 * Repeats until board returns a winner/tie
 * Congratulates winner/laughs in your face
 * 
 */

public class TicTacToe extends JApplet implements ActionListener,MouseListener
{
	Board theBoard = new Board();
	AI eddie = new AI();
	BufferedImage offScreen;
	Boolean playerTurn = false;

	public void init()
	{    
		Graphics g = getGraphics();
		addMouseListener(this);
		setFocusable(true);
		Container screen = getContentPane(); 
		screen.setBackground(Color.white); 
		screen.setLayout (new FlowLayout() ); 
		turnSet();
//		repaint();
	}

	public void paint (Graphics g)
	{
		super.paint(g); 
		if(theBoard.GameStatus().equals("Undecided"))
		{
			printTurn(g);
			theBoard.draw(g);
			delay(2000);
			if(playerTurn == false)
			{
				eddie.makeMove(theBoard);
				playerTurn=true;
				repaint();
			}
		}
		else
		{
			Font f = new Font("Helvetica", Font.BOLD, 20);
			g.setFont(f);
			g.setColor(Color.black);
			theBoard.draw(g);
			delay(2000);
			g.drawString("Game Over",200,250);
			delay(2000);
			g.clearRect(0,0,500,500);
			if(theBoard.GameStatus().equals("Win"))
			{
				g.drawString("Player Wins",200,250);
			}
			if(theBoard.GameStatus().equals("Tie"))
			{
				g.drawString("Tie!",200,250);
			}
			if(theBoard.GameStatus().equals("Loss"))
			{
				g.drawString("Eddy Wins",200,250);
			}
		}
	}

	public void printTurn(Graphics g)
	{
		Font f = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.black);
		if(playerTurn == true)
		{
			g.drawString("Its the player's turn",200,250);
		}
		else
		{
			g.drawString("Its the computer's turn",200,250);
		}
	}

	public void turnSet()
	{
		Random rand = new Random();
		if(rand.nextInt(2)==1)
		{
			playerTurn = true;
		}
		else
		{
			playerTurn = false;
		}

	}

	public void actionPerformed(ActionEvent thisEvent)
	{
		Object source = thisEvent.getSource();

		//now have if statements seeing finding out where the action occured

		//below will get the focus of the keyboard back to the applet
		requestFocusInWindow();
	}

	//when someone presses the mouse button
	public void mousePressed(MouseEvent e)
	{  
		if(playerTurn == true)
		{
			int x=e.getX();
			int y=e.getY();
			if (theBoard.makeUserMove(x,y) == true)
			{
				Graphics g = getGraphics();
				playerTurn = false;
				g.fillOval(x,y,25,25); 
				repaint();
			}	
		}
	}
	public void delay(int time)
	{
		try {
			Thread.sleep(time);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}	
	}

	//when someone releases the mouse button
	public void mouseReleased(MouseEvent e)
	{       

	}

	// when the mouse enters the applet
	public void mouseEntered(MouseEvent e)
	{       

	}

	//when the mouse leaves the applet
	public void mouseExited(MouseEvent e)
	{       

	}

	//when the mouse button is clicked
	public void mouseClicked(MouseEvent e)
	{
	}

	public int findRandom (int low, int high)
	{
		int num = (int) (Math.random()*(high-low+1))+low;
		return num;
	}

	public Color findRandomColor () 
	{ 
		int red= (int)(256 * Math.random()); 
		int green= (int)(256 * Math.random()); 
		int blue= (int)(256 * Math.random()); 
		Color randomColor=new Color(red,green,blue); 
		return randomColor; 
	}  
	//    public static void main (String ars[])
	//	{
	//		TicTacToe p=new TicTacToe();
	//		p.turnSet();
	//	}
}
