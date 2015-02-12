import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;
public class ScoreKeeper {
	int score;
	Image img1,img2,img3;
	public ScoreKeeper()
	{
		try
		{
			img1 = javax.imageio.ImageIO.read(this.getClass().getResource("Pacman.png"));  
		}
		catch (Exception e){}
		try
		{
			img2 = javax.imageio.ImageIO.read(this.getClass().getResource("Pacman.png"));  
		}
		catch (Exception e){}
		try
		{
			img3 = javax.imageio.ImageIO.read(this.getClass().getResource("Pacman.png"));  
		}
		catch (Exception e){}

	}
	public int scoreBoard()
	{
		return score;	
	}
	public void addScore(int j)
	{
		score = score+j;
	}
	public void resetScore()
	{
		score = 0;
	}
	public void draw(Graphics g,PacMan p)
	{
		Font f = new Font("Helvetica", Font.BOLD, 25);
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("Score:",10,250);
		g.drawString(""+score,10,280);
		g.drawString("Lives:",460,250);
		if(p.lives>=3)
		{
			g.drawImage(img1,460,280,null);
		}
		if(p.lives>=2)
		{
			g.drawImage(img2,480,280,null);
		}
		if(p.lives>=1)
		{
			g.drawImage(img3,500,280,null);
		}
	}
}
