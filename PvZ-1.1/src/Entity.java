public class Entity
{
  protected Stats stats;
  public void setStats(Stats stats)
  {
    this.stats = stats;
  }
  private int x, y;
  public int getX()
  {
    return x;
  }
 
  public void setX(int x)
  {
    this.x = x;
  }
  public int getY()
  {
    return y;
  }
  public void setY(int y)
  {
    this.y = y;
  }
  public Stats getStats()
  {
    return stats;
  }
  public Entity(int x, int y)
  {
    this.setX(x);
    this.setY(y);
  }
  
}
