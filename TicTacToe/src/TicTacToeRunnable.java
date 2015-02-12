import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JApplet;


public class TicTacToeRunnable extends JApplet implements ActionListener,MouseListener, Runnable{
	Board theBoard = new Board();
	AI eddie = new AI();
	BufferedImage offScreen;
	Boolean playerTurn = false;
	Boolean printMessageDisplayed = false;
	boolean toggle1 = false;
	boolean toggle2 = false;

	public void init()
	{    
		Graphics g = getGraphics();
		addMouseListener(this);
		setFocusable(true);
		Container screen = getContentPane(); 
		offScreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		screen.setBackground(Color.white); 
		screen.setLayout (new FlowLayout() ); 
		turnSet();
	}
	public void paint (Graphics g)
	{
		super.paint(g);          
		g.drawImage(offScreen,0,0,this); 
	}

	public void update()
	{
		Graphics g = getGraphics();
		Graphics oG = offScreen.getGraphics();
//		oG.setColor(Color.white);
//		oG.fillRect(0, 0, 500, 500);
		if(theBoard.GameStatus().equals("Undecided"))
		{

			if(toggle1 == false)
			{
				printTurn(oG);
				toggle1 = true;
			}
			else if(playerTurn == false)
			{
				eddie.makeMove(theBoard);
				playerTurn=true;
				toggle1 = false;
			}
		}

		else
		{
			if (toggle2==false)
			{
				Font f = new Font("Helvetica", Font.BOLD, 20);
				oG.setFont(f);
				oG.setColor(Color.black);
				oG.drawString("Game Over",200,250);
				toggle2 = true;
			}
			else
			{
				if(theBoard.GameStatus().equals("Win"))
				{
					oG.drawString("Player Wins",200,250);
				}
				if(theBoard.GameStatus().equals("Tie"))
				{
					oG.drawString("Tie!",200,250);
				}
				if(theBoard.GameStatus().equals("Loss"))
				{
					oG.drawString("Eddy Wins",200,250);
				}
			}
		}
		theBoard.draw(oG);
		g.drawImage(offScreen,0,0,this);

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


	@Override
	public void mousePressed(MouseEvent e) {
		if(playerTurn == true)
		{
			int x=e.getX();
			int y=e.getY();
			if (theBoard.makeUserMove(x,y) == true)
			{
				Graphics g = getGraphics();
				playerTurn = false;
				repaint();
			}	
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	/*********************************************************************************************/
	/* BELOW IS FOR ANIMATION.  THE ONLY THING THAT YOU NEED TO CHANGE IS DELAY */

	int frame;
	int delay=2000;   // this is the time of the delay in milliseconds.
	Thread animator;

	/**
	 * This method is called when the applet becomes visible on
	 * the screen. Create a thread and start it.
	 */
	public void start()
	{
		animator = new Thread(this);
		animator.start();
	}

	/**
	 * This method is called by the thread that was created in
	 * the start method. It does the main animation.
	 */
	public void run()
	{
		// Remember the starting time
		long tm = System.currentTimeMillis();
		while (Thread.currentThread() == animator)
		{
			// Display the next frame of animation.
			update();
			try
			{
				tm += delay;
				Thread.sleep(Math.max(0, tm - System.currentTimeMillis()));
			}
			catch (InterruptedException e)
			{
				break;
			}
			// Advance the frame
			frame++;
		}
	}

	/**
	 * This method is called when the applet is no longer
	 * visible. Set the animator variable to null so that the
	 * thread will exit before displaying the next frame.
	 */
	public void stop()
	{
		animator = null;
	}
}
