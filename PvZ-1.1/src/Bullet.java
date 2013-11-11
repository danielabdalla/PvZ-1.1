
public class Bullet extends Entity{
	private boolean direction;
	private int x;
	private int y;
	private String name;
	private boolean moved;
	
	
	public boolean isMoved()
  {
    return moved;
  }


  public void setMoved(boolean moved)
  {
    this.moved = moved;
  }


  public Bullet(Stats stats)
	{
	  super(0,0);
	  this.setStats(stats);
	  //this.name = name;
	  //this.setStats(new Stats(0, 0, 20, 0, 0));
	}


	public void bul(){System.out.println("worked");}
}
