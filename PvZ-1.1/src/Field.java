import java.util.ArrayList;

public class Field 
{

  private static int COLUMNS = 15;
  private int ROWS = 5;
  private static int totalpoints = 1000;
  private Stats stats;
  private Game game;
  private ArrayList<Entity> entities;
  
  public Entity getEntity(int x,int y)
  {
    //System.out.println("x = " + x + " y =" + y);
    for(Entity e: entities)
    {
      //System.out.println(e.getX() + " "+ e.getY());
      if(e.getX() == x && e.getY() == y)
      {
        return e;
      }
    }
    return null;
  }
  public void setEntity(Entity e)
  {
    int indexX = 0;
    int indexY = 0;
    for(Entity entity: this.entities)
    {

      if(entity.getX() == e.getX() && entity.getY() == e.getY())
      {
        indexX = entity.getX();
        indexY = entity.getY();
        break;
      }//if
    }//for
    this.entities.remove(indexX * 15 + indexY );
    this.entities.add(indexX * 15 + indexY, e);
   }
  public Game getGame()
  {
    return game;
  }

  public void setGame(Game game)
  {
    this.game = game;
  }

  public Plant plant;
	
  public Field(int COLUMNS, int ROWS) 
  {
	this.COLUMNS = COLUMNS;
	this.ROWS = ROWS;
	this.entities = new ArrayList<Entity>();
	for(int i = 0; i < ROWS; i++)
	  for(int j = 0; j < COLUMNS; j++)
	    this.entities.add(new Entity(i, j));
  }

	public int getcolumns() {
		return COLUMNS;
	}

	public int getrows() {
		return ROWS;
	}

	public void currentpoints() {
		if ( true) {
			stats.setSunpts(totalpoints);
			//if(plant.Peashooter(plant) == true)
			//{
			//	totalpoints = totalpoints - 100;
			//	stats.setSunpts(totalpoints);
			//}
			//else if(plant.Sunflower() == true)
			//{
			//	totalpoints = totalpoints - 50;
			//	stats.setSunpts(totalpoints);
			//}
			//System.out.println("Your current total points are: " + totalpoints);
		}
	}


}
