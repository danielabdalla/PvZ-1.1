public class Bullet extends Entity{
	private boolean direction;
	private int x;
	private int y;
	private String name;
	private boolean moved;
	
/**
*This method helps indicate that the zombie (attacking the plants) in the game
*has moved further down the lane it was chosen to. This methods return
*is efficient and manages to take place immediately. This is a type
*boolean method expecting whether it is true or not if the zombie has moved.
*@param - N/A
*@return true/false if the zombie moved
*/
	public boolean isMoved()
  	{
    		return moved;
  	}

/**
*This method sets the instruction that a character(in this case the zombie) has
*moved. This works when you have multiple zombies moving at the same time/simultaneously.
*It gives a chance for the user to be able to check when is the next step going to be towards.
*@param - moved, the boolean value of whether or not the zombie moved.
*@return - moved, assigned to the global variable moved from move.
*/
  public void setMoved(boolean moved)
  {
    this.moved = moved;
  }

/**
*This method is responsible for determining where the bullet starts from.
* It will be shot from the location at which the plant has been created
* and will follow in a straight line towards the oncoming target.
* @param - stats, what the current statistics are of the game.
* @return - sets the current stats of the game.
*/
  public Bullet(Stats stats)
	{
	  super(0,0);
	  this.setStats(stats);
	  //this.name = name;
	  //this.setStats(new Stats(0, 0, 20, 0, 0));
	}

/**
 * This method is just a tester to check for the programmer if 
 * the code implemented worked or not.
 * @param - N/A
 * @return - print statement "worked"
*/
public void bul()
{
	System.out.println("worked");
}
}
