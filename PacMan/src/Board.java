import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;
/**
 * Write a description of class Board here.
 * 
 */
public class Board 
{
    Image img;
    int theBoard [][] = new int [22][19];
    public Board()
    {
        File theFile = new File("Dots.csv");
        try{
            FileInputStream fstream = new FileInputStream(theFile);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            int r=0;
            while ((strLine = br.readLine()) != null)   {
                String splits[]=strLine.split(",");
                for (int c=0;c<19;c++)
                    theBoard[r][c]=Integer.parseInt(splits[c]);
                r++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try
        {
            img = javax.imageio.ImageIO.read(this.getClass().getResource("background.png"));
        }
        catch (Exception e){}
    }

    public void drawBoard(Graphics g)
    {
       // g.drawImage(img,0,0,null);
        g.setColor(Color.white);
        for (int r=0;r<22;r++)
            for (int c=0;c<19;c++)
            {
            	g.setColor(Color.white);
                if (theBoard[r][c]==1)
                {
                    g.fillOval(c*30+15,r*30+15,5,5);
                }
                else if (theBoard[r][c]==2)
                {
                    g.fillOval(c*30+15,r*30+15,12,12);
                }
                else if (theBoard[r][c]==3)
                {
                	g.setColor(Color.BLUE);
                    g.fillRect(c*30,r*30,30,30);
                }
                else if (theBoard[r][c]==0)
                {
                    //g.fillRect(c*30,r*30,5,5);
                }
        }
    }

    public boolean isValid(int x,int y)
    {
        if(theBoard[y/30][x/30]==3)
        {
           return false;
        }
        return true;
    }
    public boolean isSmallDot(int x,int y)
    {
        if(theBoard[y/30][x/30]==1)
        {
        	theBoard[y/30][x/30]=0;
           return true;
        }
        return false;
    }
    public boolean isBigDot(int x,int y)
    {
        if(theBoard[y/30][x/30]==2)
        {
        	theBoard[y/30][x/30]=0;
           return true;
        }
        return false;
    }

}