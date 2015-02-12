import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;

public class PacManGame extends JApplet implements Runnable,MouseListener,MouseMotionListener,KeyListener
{
	private static final long serialVersionUID = 1L;
	BufferedImage offScreen;
	PacMan Pacman = new PacMan();
	Board TheBoard = new Board();
	Ghost allGhosts[]={new Ghost(250,570),new Ghost(270,570),new Ghost(290,570),new Ghost(350,570)};
	ScoreKeeper ScoreBoard = new ScoreKeeper();
	boolean gameStarted = false;
	boolean gameLost = false;
	int countDown = 100;
	public void init()
	{      
		addKeyListener(this);
		setFocusable(true);
		Container screen = getContentPane(); 
		screen.setLayout (new FlowLayout() );
		offScreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	public void paint (Graphics g)
	{
		super.paint(g);          
		g.drawImage(offScreen,0,0,this); 
	}

	//Tells "Pacman" to move in 4 different directions, and resets "Pacman" booleans 
	public void keyPressed(KeyEvent e) {
		int theCode=e.getKeyCode();
		Pacman.isMovingLeft = false;
		Pacman.isMovingDown = false;
		Pacman.isMovingUp = false;
		Pacman.isMovingRight = false;



		if (theCode == KeyEvent.VK_UP)
		{
			Pacman.isMovingUp = true;
		}
		if (theCode == KeyEvent.VK_DOWN)
		{
			Pacman.isMovingDown = true;
		}           
		if (theCode == KeyEvent.VK_RIGHT)
		{
			Pacman.isMovingRight = true;
		}
		if (theCode == KeyEvent.VK_LEFT)
		{
			Pacman.isMovingLeft = true;
		}	
	}

	public void update()
	{
		Graphics g=getGraphics();
		Graphics oG=offScreen.getGraphics();
		if(gameStarted == false)
		{
			oG.setColor(Color.black);
			oG.fillRect(0, 0, 560, 640);
			oG.setColor(Color.yellow);
			oG.fillRect(50,50,460,540);
			oG.setColor(Color.black);
			Font f = new Font("Helvetica", Font.BOLD, 60);
			oG.setFont(f);
//			oG.drawString("Pacman",175,300);
			Font z = new Font("Helvetica", Font.BOLD, 30);
			oG.setFont(z);
//			oG.drawString("By: Henry and Eddy",150,340);
			if(gameLost == false)
			{
				oG.setFont(f);
				oG.drawString("Pacman",175,300);
				oG.setFont(z);
				oG.drawString("By: Henry and Eddy",150,340);
				countDown--;
				if(countDown <=0)
				{
					gameStarted = true;
				}
			}
			else
			{
				oG.setFont(f);
				oG.drawString("YOU LOSE",150,400);
			}
				
		}
		else

		{
			oG.clearRect(0,0,560,640);
			TheBoard.drawBoard(oG);
			ScoreBoard.draw(oG,Pacman);
			for(int i = 0;i<allGhosts.length;i++)
			{
				allGhosts[i].draw(oG,TheBoard);
				allGhosts[i].isCollision(oG, Pacman,ScoreBoard);

			}
			Pacman.draw(oG,TheBoard,ScoreBoard);
		}
//		if(Pacman.hostileMobs=false)
//		{
//			for(int i =0;i<=5;i++)
//			{
//				if(i == 5)
//				{
//					Pacman.hostileMobs = true;
//					i=0;
//				}
//			}
//		}
		if(Pacman.lives <=0)
		{
			gameLost = true;
			gameStarted = false;
		}
		g.drawImage(offScreen,0,0,this);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	/*********************************************************************************************/
	/* BELOW IS FOR ANIMATION.  THE ONLY THING THAT YOU NEED TO CHANGE IS DELAY */

	int frame;
	int delay=50;   // this is the time of the delay in milliseconds.
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
