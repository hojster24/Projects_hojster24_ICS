
/**
 * AI artificial intelligence class, gets the board and makes the best move 
 * possible.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AI
{
    //Attempts to make the best move against the player in this order:
    //1.looks for win
    //2. Looks to block player
    //3. Sets up for a win(2 in a row with 3rd free)
    //4. Chooses random corner
    //5. Chooses anywhere randomly
    public void makeMove(Board b)
    {
        
        //basic code below moves randomly;
        //currently we just find an empty spot and place an O there.
        int r=findRandom(0,2);
        int c=findRandom(0,2);
        if (b.theBoard[r][c]==0)
        {
            b.theBoard[r][c]=2;
            return;
        }
        else makeMove(b);  //in case our spot is not empty, we try again
    }
    
    
    public int findRandom (int low, int high)
    {
        int num = (int) (Math.random()*(high-low+1))+low;
        return num;
    }
 
    //This testing method tries out your makemove method
    public void testMove()
    {
        Board b=new Board();
        System.out.println("Before the move:");
        b.printBoard();
        makeMove(b);
        System.out.println("After the move:");        
        b.printBoard();
 
    }
}
