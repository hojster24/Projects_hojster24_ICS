import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Pong extends JApplet implements MouseListener, MouseMotionListener,ActionListener,KeyListener,Runnable  {

	//All objects/components
	
	Paddle rightPaddle = new Paddle();
	Paddle leftPaddle = new Paddle();

	ScoreKeeper ref1 = new ScoreKeeper();

	JLabel credit1 = new JLabel("P0NG V3");
	JLabel credit2 = new JLabel("by: Henry Jones");
	

	JButton startButton = new JButton("Start");
	JButton stopButton = new JButton("Stop");
	
	Ball ball1= new Ball();
	
	Color background = Color.white;
	
	boolean started = false;
	
	BufferedImage offScreen; 

	public void init() 
	{
		//sets up paddle static X values
		
		rightPaddle.x = 460;
		leftPaddle.x = 30;


		addMouseListener(this); 
		addMouseMotionListener(this); 
		addKeyListener(this); 
		setFocusable(true); 
		
		//This is for the image background which i chose not to use
		//setContentPane(new ImagePanel("pong.png"));
		
		Container screen = getContentPane(); 
		screen.setBackground(background); 
		screen.setLayout (new FlowLayout() ); 
		screen.add(startButton);
		screen.add(credit1);
		screen.add(credit2);

		offScreen = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);    
		startButton.addActionListener(this);
		stop();
		repaint();
		
	}
	

	public void paint (Graphics g) 
	{ 
		super.paint(g);	
	} 

	public void update() 
	{ 
		Graphics g=getGraphics(); 
		Graphics oG=offScreen.getGraphics(); 
		oG.setColor(Color.black);
		oG.fillRect(0,0,500,500);
		super.paint(oG);
		
		//"Move methods"
		
		ball1.move();
		leftPaddle.move();
		rightPaddle.move();
		
		//"Draw" methods
		
		ball1.draw(oG);
		leftPaddle.draw(oG);
		rightPaddle.draw(oG);
		
		//Style Selection
		
		Font m = new Font("Helvetica", Font.BOLD, 35);
		oG.setFont(m);
		oG.setColor(Color.green);
		//oG.setColor(Color.black);
		
		//Score/Framework setup
		
		oG.fillRect(0, 40, 500, 10);
		oG.fillRect(245,40,10,460);
		oG.drawString(""+ref1.scoreL, 270, 30);
		oG.drawString(""+ref1.scoreR, 205, 30);
		
		//Changes font size
		
		Font z = new Font("Helvetica", Font.BOLD, 20);
        
		//Paints version ID
		
		oG.setFont(z);
        oG.drawString("Pong V3", 10, 25);
        oG.drawString("by: Henry Jones", 330, 25);
        
        //If statements handle ball motion and limits

		if (ball1.ballX>=rightPaddle.x && ball1.ballX<=rightPaddle.x+20 && ball1.ballY>=rightPaddle.y && ball1.ballY<=rightPaddle.y+100)
		{ 
			ball1.speedX=ball1.speedX*-1;
			ball1.skew = (rightPaddle.y+100 - ball1.ballY)-50;
			ball1.bounce();
		}
		if (ball1.ballX>=leftPaddle.x && ball1.ballX<=leftPaddle.x+20 && ball1.ballY>=leftPaddle.y && ball1.ballY<=leftPaddle.y+100)
		{ 
			ball1.speedX=ball1.speedX*-1;
			ball1.skew = (leftPaddle.y+100 - ball1.ballY)-50;
			ball1.bounce();
		}
		
		//If statements handle scoreing and winning
		
		if (ball1.scoreL==true)
		{
			ref1.leftScored(); 
			ball1.reset();
			ball1.scoreL = false;
		}
		if (ball1.scoreR==true)
		{
			ref1.rightScored(); 
			ball1.reset();
			ball1.scoreR = false;
		}
		if (ref1.scoreR >=5)
		{
			ref1.scoreL = 0;
			ref1.scoreR = 5;
			oG.setColor(findRandomColor());
			oG.fillRect(0,0,500,500);
			Font f = new Font("Helvetica", Font.BOLD, 26);
	        oG.setFont(f);
	        oG.setColor(Color.white);
	        oG.drawString("LEFT WINS!", 200, 250);
		}
		if (ref1.scoreL >=5)
		{
			ref1.scoreR = 0;
			ref1.scoreL = 5;
			oG.setColor(findRandomColor());
			oG.fillRect(0,0,500,500);
			Font f = new Font("Helvetica", Font.BOLD, 26);
	        oG.setFont(f);
	        oG.setColor(Color.white);
	        oG.drawString("RIGHT WINS!", 200, 250);
			
		}
		g.drawImage(offScreen,0,0,this);
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	
	//Key pressed booleans to turn on motion
	
	@Override
	public void keyPressed(KeyEvent e) {
		int theCode=e.getKeyCode();
		if(theCode == KeyEvent.VK_UP)
		{
			rightPaddle.movingUp = true;
		}
		if(theCode == KeyEvent.VK_DOWN)
		{
			rightPaddle.movingDown = true;
		}
		if(theCode == KeyEvent.VK_W)
		{
			leftPaddle.movingUp = true;
		}
		if(theCode == KeyEvent.VK_S)
		{
			leftPaddle.movingDown = true;
		}
	}
	
	//Key released booleans to turn off motion
	
	public void keyReleased(KeyEvent e) {
		int theCode=e.getKeyCode();
		if(theCode == KeyEvent.VK_UP)
		{
			rightPaddle.movingUp = false;
		}
		if(theCode == KeyEvent.VK_DOWN)
		{
			rightPaddle.movingDown = false;
		}
		if(theCode == KeyEvent.VK_W)
		{
			leftPaddle.movingUp = false;
		}
		if(theCode == KeyEvent.VK_S)
		{
			leftPaddle.movingDown = false;
		}
	}

	//Handles the start button and hides it
	
	@Override
	public void actionPerformed(ActionEvent thisEvent) {
		Object source = thisEvent.getSource();
		Graphics g=getGraphics(); 
		Graphics oG=offScreen.getGraphics(); 
		if(source == startButton)
		{
			background = Color.black;
			started = true;
			init();
			start();
			requestFocus(); 
			startButton.setVisible(false);
			credit1.setVisible(false);
			credit2.setVisible(false);
			Font f = new Font("Helvetica", Font.BOLD, 35);
		}
	}
	public Color findRandomColor()
	{
		Random Rand = new Random();
		int r = Rand.nextInt(255);
		int g = Rand.nextInt(255);
		int b = Rand.nextInt(255);		
		Color random = new Color(r,g,b);
		
		return random;
		
	}
	
	//Unused auto-generated methods, errors are produced when removed
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
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
	int delay=50;   // this is the time of the delay in milliseconds. 
	Thread animator; 

	/** 
	 * This method is called when the applet becomes visible on 
	 * the screen. Create a thread and start it. 
	 */
	public void start() 
	{ 
		//added if statement that makes the program start in an "off" state
		if(started == true)
		{
			animator = new Thread(this); 
			animator.start(); 
		}
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
		if(started == false)
		{
			animator = null; 
		}
	} 

}

//code for background, unused

class ImagePanel extends JComponent 
{
    private Image image;
    public ImagePanel(String fileName) {
        try
        {
             image=javax.imageio.ImageIO.read(new java.io.File(fileName));
        }
        catch (Exception e){}
    }
 
 
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(50,50,5,50);
        g.drawImage(image, 0, 0, null);        
    }
}