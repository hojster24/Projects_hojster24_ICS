import java.awt.*;

public class Splatter 
{
	Image splat;
	public Splatter()
	{
		try
		{	
			splat = javax.imageio.ImageIO.read(this.getClass().getResource("Splat.png"));
		}
		catch (Exception e){}
	}
	public void draw(int x, int y, Graphics g) 
	{
		g.drawImage(splat,x,y,null);
	}
}
