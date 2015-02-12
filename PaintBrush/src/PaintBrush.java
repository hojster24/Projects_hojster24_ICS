import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.util.Random;
/**
 * This is the starter code for paintbrush.
 * I have included 3 panels, put your tools all in the toolpanel.
 * Put your colors in colorPanel
 * If you want to change the size, change the dimensions included
 * 
 *
 * @original author Nate Rhinehardt
 * @updated 11-6-14
 */
public class PaintBrush extends JApplet implements MouseListener,ActionListener, MouseMotionListener, ChangeListener
{   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String toolSelected; 
	Color colorSelected;

	//This time we are using 3 panels (sections) on the applet    
	JPanel toolPanel=new JPanel();
	JPanel colorPanel=new JPanel();
	JPanel drawingPanel=new JPanel();

	//Most of these buttons will have pictures instead of text.
	JButton drawButton;
	JButton circleButton;
	JButton squareButton;
	JButton lineButton;
	JButton eraserButton;
	JButton rainbowButton;
	JButton clearButton=new JButton("Clear");
	//all of the color buttons will have 
	JButton colorBlackButton = new JButton();
	JButton colorRedButton = new JButton();
	JButton colorOrangeButton = new JButton();
	JButton colorYellowButton = new JButton();
	JButton colorGreenButton = new JButton();
	JButton colorBlueButton = new JButton();
	JButton colorMagentaButton = new JButton();
	JButton colorWhiteButton = new JButton();    
	JLabel colorChoice = new JLabel("");
	JLabel toolChoice = new JLabel("");
	JSlider sizeSlider = new JSlider(0,50);
	JLabel sizeChoice = new JLabel("");

	public void init()
	{

		//I create a toolpanel so you have tools lined up on the left. 
		//If you want to change it, change the dimension below, or see me
		toolPanel.setPreferredSize(new Dimension(150,450));
		toolPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		toolPanel.setBackground(Color.green);

		//Add images to the JButtons
		drawButton=new JButton(new ImageIcon(getImage(getCodeBase(),"pencil.png")));
		circleButton=new JButton(new ImageIcon(getImage(getCodeBase(),"circle.png")));
		squareButton=new JButton(new ImageIcon(getImage(getCodeBase(),"Square.png")));
		lineButton=new JButton(new ImageIcon(getImage(getCodeBase(),"Line.png")));
		eraserButton=new JButton(new ImageIcon(getImage(getCodeBase(),"eraser.png")));
		rainbowButton=new JButton(new ImageIcon(getImage(getCodeBase(),"rainbow.png")));
		
		//Add all your tools to the tool panel
		toolPanel.add(drawButton);
		toolPanel.add(circleButton);
		toolPanel.add(squareButton);
		toolPanel.add(lineButton);
		toolPanel.add(eraserButton);
		toolPanel.add(rainbowButton);
		toolPanel.add(clearButton);
		toolPanel.add(sizeSlider);
		toolPanel.add(sizeChoice);
		toolPanel.add(colorChoice);
		toolPanel.add(toolChoice);


		//Now I add my panel for drawing        
		drawingPanel.setPreferredSize(new Dimension(340,450));
		drawingPanel.setBackground(Color.white);
		drawingPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		//Now we create a color panel for the 8 color buttons.  I also specify the size and color of those buttons      
		colorPanel.setPreferredSize(new Dimension(500,50));
		colorPanel.setBackground(Color.gray);      
		colorPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		sizeSlider.setSnapToTicks(false);
		sizeSlider.setMajorTickSpacing(5);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.addChangeListener(this);

		colorWhiteButton.setOpaque(true);
		colorWhiteButton.setBorderPainted(false);
		colorBlackButton.setOpaque(true);
		colorBlackButton.setBorderPainted(false);
		colorRedButton.setOpaque(true);
		colorRedButton.setBorderPainted(false);
		colorOrangeButton.setOpaque(true);
		colorOrangeButton.setBorderPainted(false);
		colorYellowButton.setOpaque(true);
		colorYellowButton.setBorderPainted(false);
		colorGreenButton.setOpaque(true);
		colorGreenButton.setBorderPainted(false);
		colorBlueButton.setOpaque(true);
		colorBlueButton.setBorderPainted(false);
		colorMagentaButton.setOpaque(true);
		colorMagentaButton.setBorderPainted(false); 


		colorWhiteButton.setPreferredSize(new Dimension(30, 30));
		colorWhiteButton.setBackground(Color.white);
		colorPanel.add(colorWhiteButton);        
		colorBlackButton.setPreferredSize(new Dimension(30, 30));
		colorBlackButton.setBackground(Color.black);
		colorPanel.add(colorBlackButton);
		colorRedButton.setPreferredSize(new Dimension(30, 30));
		colorRedButton.setBackground(Color.red);
		colorPanel.add(colorRedButton);
		colorOrangeButton.setPreferredSize(new Dimension(30, 30));
		colorOrangeButton.setBackground(Color.orange);
		colorPanel.add(colorOrangeButton);        
		colorYellowButton.setPreferredSize(new Dimension(30, 30));
		colorYellowButton.setBackground(Color.yellow);
		colorPanel.add(colorYellowButton);
		colorGreenButton.setPreferredSize(new Dimension(30, 30));
		colorGreenButton.setBackground(Color.green);
		colorPanel.add(colorGreenButton);
		colorBlueButton.setPreferredSize(new Dimension(30, 30));
		colorBlueButton.setBackground(Color.blue);
		colorPanel.add(colorBlueButton);
		colorMagentaButton.setPreferredSize(new Dimension(30, 30));
		colorMagentaButton.setBackground(Color.magenta);
		colorPanel.add(colorMagentaButton); 



		//now I add all the panels to the screen
		//note: not flowlayout
		Container screen= getContentPane();
		screen.setLayout (new BorderLayout());
		screen.add(toolPanel,BorderLayout.WEST);
		screen.add(drawingPanel,BorderLayout.CENTER);
		screen.add(colorPanel,BorderLayout.SOUTH);

		//Add listeners
		drawButton.addActionListener(this);
		circleButton.addActionListener(this);
		squareButton.addActionListener(this);
		lineButton.addActionListener(this);
		clearButton.addActionListener(this);
		eraserButton.addActionListener(this);
		rainbowButton.addActionListener(this);
		colorBlackButton.addActionListener(this);
		colorWhiteButton.addActionListener(this);
		colorRedButton.addActionListener(this);
		colorBlueButton.addActionListener(this);
		colorYellowButton.addActionListener(this);
		colorOrangeButton.addActionListener(this);
		colorGreenButton.addActionListener(this);
		colorMagentaButton.addActionListener(this);

		drawingPanel.addMouseListener(this);
		drawingPanel.addMouseMotionListener(this);
	}

	public void paint (Graphics g)
	{
		super.paint(g);//This method is necessary. LEAVE IT ALONE.

	}
	//I decided to add ints here, outside of the init block
	int x;
	int y;
	int x1;
	int y1;
	int size;
	Point startDrag,endDrag;
	
	public Color findRandomColor()
	{
		Random Rand = new Random();
		int r = Rand.nextInt(255);
		int g = Rand.nextInt(255);
		int b = Rand.nextInt(255);		
		Color random = new Color(r,g,b);
		
		return random;
		
	}
	
	//When someone presses the mouse button
	public void mousePressed(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		
		
		/*I had brackets here for actions, but moved them to mouse pressed and released for clairity and bugs*/
	}

	//When someone releases the mouse button
	public void mouseReleased(MouseEvent e)
	{
		//I was supprised with the code being housed here, but it makes sense, and speeds up what would otherwise be a
		//complicated Process
		x1 = e.getX();
		y1 = e.getY();
		Graphics dg = drawingPanel.getGraphics();
		if(toolSelected == "square")
		{
			dg.setColor(colorSelected);
			dg.fillRect(x, y, x1-x, y1-y);
		}
		if(toolSelected == "circle")
		{
			dg.setColor(colorSelected);
			dg.fillOval(x,y,x1-x,y1-y);
		}
		if(toolSelected == "line")
		{
			Graphics2D g2 = (Graphics2D)dg; 
			g2.setStroke(new BasicStroke(size));
			dg.setColor(colorSelected);
			dg.drawLine(x, y, x1, y1);

		}
	}

	//When the mouse enters the applet
	public void mouseEntered(MouseEvent e)
	{
	}

	//When the mouse leaves the applet
	public void mouseExited(MouseEvent e)
	{
	}

	//When the mouse button is clicked
	public void mouseClicked(MouseEvent e)
	{
	}

	//The mouse button is pressed and the mouse makes a significantly large movement
	public void mouseDragged(MouseEvent e)
	{
		int x2 = e.getX();
		int y2 = e.getY();	
		Graphics dg = drawingPanel.getGraphics();
		if(toolSelected == "rainbow")
		{
			Graphics2D g2 = (Graphics2D)dg; 
			g2.setStroke(new BasicStroke(size));
			dg.setColor(findRandomColor());
			dg.drawLine(x1,y1,x2,y2);
		}
		if(toolSelected == "draw")
		{
			Graphics2D g2 = (Graphics2D)dg; 
			g2.setStroke(new BasicStroke(size));
			dg.setColor(colorSelected);
			dg.drawLine(x1,y1,x2,y2);

		}
		if(toolSelected == "eraser")
		{
			Graphics2D g2 = (Graphics2D)dg; 
			g2.setStroke(new BasicStroke(size));
			dg.setColor(Color.white);
			dg.drawLine(x1,y1,x2,y2);
		}
		x1 = x2;
		y1 = y2;
	}

	//The mouse makes a significantly large movement
	public void mouseMoved(MouseEvent e)
	{
		Graphics dg = drawingPanel.getGraphics();
		if(toolSelected == "draw"||toolSelected == "eraser"||toolSelected == "rainbow")
		{
			x1=e.getX();
			y1=e.getY();
		}
	}

	public void actionPerformed(ActionEvent thisEvent)
	{

		Object source = thisEvent.getSource();
		Graphics g=getGraphics();

		if (source == drawButton)
		{
			toolSelected = "draw";
			toolChoice.setText(""+toolSelected);
		}
		if (source==circleButton)
		{
			toolSelected = "circle";
			toolChoice.setText(""+toolSelected);
		}
		if (source==squareButton)
		{
			toolSelected = "square";
			toolChoice.setText(""+toolSelected);
		}
		if (source==lineButton)
		{
			toolSelected = "line";
			toolChoice.setText(""+toolSelected);
		}  
		if (source==eraserButton)
		{
			toolSelected = "eraser";
			toolChoice.setText(""+toolSelected);
		}
		if (source==rainbowButton)
		{
			toolSelected = "rainbow";
			toolChoice.setText(""+toolSelected);
			colorChoice.setText("");
		}
		if (source==clearButton)
		{
			toolSelected = "clear";
			colorSelected = Color.white;
			toolChoice.setText("");
			colorChoice.setText("");
			repaint();

		}
		if (source==colorBlackButton)
		{
			colorSelected = Color.black; 
			colorChoice.setText("black");

		}
		if (source==colorWhiteButton)
		{
			colorSelected = Color.white;
			colorChoice.setText("white");
		}
		if (source==colorRedButton)
		{
			colorSelected = Color.red; 
			colorChoice.setText("red");
		}
		if (source==colorMagentaButton)
		{
			colorSelected = Color.magenta;
			colorChoice.setText("magenta");
		}
		if (source==colorOrangeButton)
		{
			colorSelected = Color.orange;
			colorChoice.setText("orange");
		}
		if (source==colorYellowButton)
		{
			colorSelected = Color.yellow; 
			colorChoice.setText("yellow");
		}
		if (source==colorGreenButton)
		{
			colorSelected = Color.green; 
			colorChoice.setText("green");
		}
		if (source==colorBlueButton)
		{
			colorSelected = Color.blue; 
			colorChoice.setText("blue");
		}

	}
	public void stateChanged(ChangeEvent e)
	{
		Object source = (JSlider)e.getSource();
		if (source == sizeSlider)
		{  
			size = sizeSlider.getValue();
			//second If statement to make there be less confusion around size selection using square and circle
			if(toolSelected == "draw"||toolSelected =="line"||toolSelected == "eraser"||toolSelected == "rainbow")
			{
				sizeChoice.setText("size: "+size);
			}
			else
			{
				sizeChoice.setText("size: N/A");
			}

		}
	}
}