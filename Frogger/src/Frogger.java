/*
e * My version of "Frogger" for Intro To Java S1 at DHS 
 *
 * @HenryJones 
 * @December 15 2014 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

public class Frogger extends JApplet implements ActionListener,KeyListener,Runnable
{
	Bar allBars[]={new Bar(75),new Bar(50),new Bar(300),new Bar(325),new Bar(350),new Bar(400),new Bar(425),new Bar(450)};
	Log allLogs[]={new Log(250),new Log(225),new Log(200),new Log(175),new Log(150),new Log(125)};
	Frog theFrog=new Frog();
	BufferedImage offScreen;
	Image frog;
	int frogLives=3;
	int score = 0;
	boolean inRiver=false;
	boolean debugMode = false;
	boolean youWin=false;
	JButton reset = new JButton ("reset");
	JButton debug = new JButton ("debug");

	public void init()
	{        
		addKeyListener(this);
		setFocusable(true);
		Container screen = getContentPane(); 
		screen.setLayout (new FlowLayout() );
		screen.add(reset);
		screen.add(debug);
		offScreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		//Loading the frog graphics purely for the scorekeeping sprite
		try
		{	
			frog = javax.imageio.ImageIO.read(this.getClass().getResource("frog.png"));
		}
		catch (Exception e){}
		reset.addActionListener(this);
		debug.addActionListener(this);
	}

	public void paint (Graphics g)
	{
		super.paint(g);          
		g.drawImage(offScreen,0,0,this); 
	}
	//Stock Code for controling the frog
	public void keyPressed(KeyEvent e)
	{
		char theChar=e.getKeyChar();
		int theCode=e.getKeyCode();
		if (theCode == KeyEvent.VK_UP)
		{
			theFrog.moveUp();
		}
		if (theCode == KeyEvent.VK_DOWN)
		{
			theFrog.moveDown();
		}           
		if (theCode == KeyEvent.VK_RIGHT)
		{
			theFrog.moveRight();
		}
		if (theCode == KeyEvent.VK_LEFT)
		{
			theFrog.moveLeft();
		}
	}
	//Unused
	public void keyReleased(KeyEvent e)
	{  
	}
	//Unused
	public void keyTyped(KeyEvent e)
	{    
	}
	//Code for the two buttons included
	public void actionPerformed(ActionEvent thisEvent)
	{
		Object source = thisEvent.getSource();

		//now have if statements seeing finding out where the action occured

		//This one calls all of the reset methods and resets the score and frog position
		if(source == reset)
		{
			reset();
			score = 0;
			frogLives = 3;
			theFrog.y=475;
			theFrog.x=250;	
			youWin=false;
		}
		if(source == debug)
		{
			debugMode = !debugMode;
		}
		//below will get the focus of the keyboard back to the applet
		requestFocusInWindow();
	}
	//Most of the code for automated NPC movement and scorekeeping 
	public void update()
	{
		Graphics oG=offScreen.getGraphics();
		super.paint(oG);

		//This is all of the visual static elements

		oG.setColor(Color.black);
		oG.fillRect(0,300,500,175);
		oG.fillRect(0,50,500,50);
		oG.setColor(Color.green);
		oG.fillRect(0,475,500,25);
		oG.fillRect(0,275,500,25);
		oG.fillRect(0,100,500,25);
		oG.fillRect(0,30,500,20);
		oG.setColor(Color.blue);
		oG.fillRect(0,125,500,150);
		oG.setColor(Color.yellow);
		drawDashedLine(oG,0,385,500,385);
		drawDashedLine(oG,0,395,500,395);
		drawDashedLine(oG,0,75,500,75);
		oG.drawImage(frog,0,0,null);


		if(youWin == false)
		{
			//Collision code for the logs/river
			//In all of these, I draw the elements
			//last so they dont interfere with other graphics

			if (theFrog.y <275 && theFrog.y>100)
			{
				inRiver=true;
			}
			else
			{
				inRiver=false;
			}
			for(int i = 0; i<allLogs.length; i++)
			{
				allLogs[i].move();
				if(allLogs[i].isCollision(theFrog)==true)
				{
					if(allLogs[i].movingRight)
					{
						theFrog.x = theFrog.x + allLogs[i].logSpeed();
					}
					else
					{
						theFrog.x = theFrog.x - allLogs[i].logSpeed();
					}
					inRiver = false;
				}
				allLogs[i].draw(oG);
			}
			if (inRiver==true)
			{
				theFrog.y=475;
				theFrog.x=250;
				frogLives--;
			}
			//Similar collision code for the bars (trucks)
			for(int i = 0; i<allBars.length; i++)
			{
				allBars[i].move();
				if(allBars[i].isCollision(theFrog)==true)
				{
					theFrog.y=475;
					theFrog.x=250;
					frogLives--;
				}
				allBars[i].draw(oG);
			}
			if(theFrog.x+25>500 || theFrog.x<0)
			{
				//maybe add the ability for the frog to wrap around
				theFrog.y=475;
				theFrog.x=250;
				frogLives--;
			}
			//I draw the frog and text here so its hidden by the win/lose screen
			Font m = new Font("Helvetica", Font.BOLD, 25);
			oG.setFont(m);
			oG.setColor(Color.black);
			oG.drawString("X "+frogLives, 25, 20);
			oG.drawString("Score: "+score, 400, 20);
			//version # and credit
			Font z = new Font("Helvetica", Font.BOLD, 20);
			oG.setFont(z);
			oG.setColor(Color.black);
			oG.drawString("Frogger v6 by; Henry Jones",130,295);
			
			//draws the frog on top of all swing items
			theFrog.drawFrog(oG);
			
			//displays useful information for codeing
			if(debugMode == true)
			{
				Font l = new Font("Helvetica", Font.BOLD, 25);
				oG.setFont(l);
				oG.setColor(Color.red);
				oG.drawString("BarSpeed: "+allBars[1].speedMod +" LogSpeed: " +allLogs[1].speedMod + " In River: " + inRiver,0,300);
			}
			
			//Losing code
			if(frogLives <0)
			{
				oG.setColor(findRandomColor());
				oG.fillRect(0,0,500,500);
				Font c = new Font("Helvetica", Font.BOLD, 35);
				oG.setFont(c);
				oG.setColor(Color.white);
				oG.drawString("YOU LOSE",200,250);	
			}
			
			//#WINNING code
			if(score >= 3)
			{
				oG.setColor(findRandomColor());
				oG.fillRect(0,0,500,500);
				Font c = new Font("Helvetica", Font.BOLD, 35);
				oG.setFont(c);
				oG.setColor(Color.white);
				oG.drawString("YOU WIN!",200,250);	
			}
		}

		//Decides When you win(a point)

		if(theFrog.y<25)
		{
			youWin = true;
			reset();
		}
		
		//These are all of the actions for when you win
		if(youWin == true)
		{
				score++;
				frogLives = 3;
				theFrog.y=475;
				theFrog.x=250;
				for(int i = 0; i<allBars.length; i++)
				{
					allBars[i].speedMod++;
				}
				youWin = false;
		}

		Graphics g=getGraphics();      
		g.drawImage(offScreen,0,0,this); 

	}
	//A "fake" reset method that calls the reset methods that actually do something
	public void reset()
	{
		for(int i = 0; i<allBars.length; i++)
		{
			allBars[i].reset();
		}
		for(int i = 0; i<allLogs.length; i++)
		{
			allLogs[i].reset();
		}	
	}
	//Sets the line for the roads median to dashed(propoties inside)
	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

		Graphics2D g2d = (Graphics2D) g;
		//float dash[] = {10.0f};
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		g2d.setStroke(dashed);
		g2d.drawLine(x1, y1, x2, y2);
	}
	//Spits out a random color value
	public Color findRandomColor()
	{
		Random Rand = new Random();
		int r = Rand.nextInt(255);
		int g = Rand.nextInt(255);
		int b = Rand.nextInt(255);		
		Color random = new Color(r,g,b);

		return random;

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