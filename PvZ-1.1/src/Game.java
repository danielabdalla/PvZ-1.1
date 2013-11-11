import java.util.*;

public class Game {

  private boolean gameFinished = false;
  private ArrayList<Zombie> zombies;
  private ArrayList<Plant> plants;
  private int sunPoints = 100;
  private int time = 0;
  private Field field;
  public Field getField()
  {
    return field;
  }
  int flag = 0;
  private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
  public static final String GAME_END = "Game Over! 'Game > New Game' to start a new game.";
  public static final String NEW_LINE = "\n";

  public int getTime()
  {
    return this.time;
  }
  public void setTime(int time)
  {
    this.time = time;
  }
  public void next()
  {
    this.setTime(this.getTime() + 1);
  }
  public int getSunPoints()
  {
    return sunPoints;
  }

  public void setSunPoints(int sunPoints)
  {
    this.sunPoints = sunPoints;
  }

  public boolean isGameFinished()
  {
    return this.gameFinished;
  }

  public void setGameFinished(boolean gameFinished)
  {
    this.gameFinished = gameFinished;
  }

  public boolean startGame() {
		System.out.println("Would you like to begin the game? \nType Y or N");
		Scanner sc = new Scanner(System.in);
		for(;;)
		{
		  String k = sc.next();
		  if (k.equalsIgnoreCase("Y") ) {
		    System.out.println("You have begun the game");
		    return true;
		  }else if (k.equalsIgnoreCase("N")) {
		    System.out.println("You have decided not to play the game. \nHave a good day!");
		    return false;
		  }
		  System.out.println("Error 401; You have entered an invalid character. \nPlease try again.");
		}//for
		
	}
  public String play()
  {
    //if( !this.startGame() )
     // return;
    flag = 0;

      this.time++;
      System.out.println("time = " + time);
      
      //Every 5 times, add 20 sunPoints thanks to the sun!
      if(time % 5 == 0)
      {
        this.sunPoints += 20;
       
      }
        
      
      //flag all the bullets as not moved for the next round
      for(int i = 0; i < field.getrows(); i++)
      {
        for(int j = 0; j < field.getcolumns(); j++)
        {      
          if(field.getEntity(i, j) instanceof Bullet) 
          {
            ((Bullet)field.getEntity(i, j)).setMoved(false);
            if(((Bullet)field.getEntity(i, j)).isMoved() == false)
              System.out.println("ok");
          }
        }
      }   
      
      for(int i = 0; i < field.getrows(); i++)
      {
        for(int j = 0; j < field.getcolumns(); j++)
        {      
          if(field.getEntity(i, j) instanceof Zombie) 
          {
            if(field.getEntity(i, j-1 ) instanceof Plant)
            {
              field.getEntity(i, j-1 ).getStats().setHealth( field.getEntity(i, j-1 ).getStats().getHealth() - field.getEntity(i, j).getStats().getAtkpts());
              field.getEntity(i, j ).getStats().setHealth( field.getEntity(i, j ).getStats().getHealth() - field.getEntity(i, j-1).getStats().getAtkpts());
              
              if( field.getEntity(i, j ).getStats().getHealth() <= 0 )  //zombie dead
              {
                field.setEntity(new Entity(i, j));
                System.out.println("Zombie at (" + i + ", " + j + ") is dead.");
              }
              else if ( field.getEntity(i, j-1 ).getStats().getHealth() <= 0 )  //plant dead, zombie takes its place
              {
                System.out.println("Plant at (" + i + ", " + j + ") is dead.");
                Zombie z = (Zombie)field.getEntity(i, j );
                z.setY(j-1);
                field.setEntity(z);
              }
              
            }//if plant on the next entity
            
          //zombie moves ahead
            else
            {
              if(j == 0)
              {
               gameFinished = true;
                System.out.println("You just lost the game!!! please choose one of the following options.");
                break;
              }
              else
              {
                Zombie z = (Zombie)field.getEntity(i, j);
                z.setY(j-1);
                field.setEntity(z);
                field.setEntity(new Entity(i, j));
              }
            }
          }//if zombie  is the entity
         
          //if bullet is the entity
          if(field.getEntity(i, j) instanceof Bullet)
          {
            if( ((Bullet)field.getEntity(i, j)).isMoved() == false )
            {
              Bullet b = (Bullet)field.getEntity(i, j);
              b.setY(j + 2);              
              field.setEntity(b);
              ((Bullet)field.getEntity(i, j+2)).setMoved(true);
              field.setEntity(new Entity(i, j));
              
            }
          }

          //if plant is the entity
          if(field.getEntity(i, j) instanceof Plant)
          {
            
            //if the plant is a SunFlower
            if(((Plant)this.field.getEntity(i, j)).getPrice() == Plant.SUNFLOWERPRICE)
            {   
              if(((Plant)this.field.getEntity(i, j)).getSunCycle() == 0)
              {
                this.sunPoints += 50;
                System.out.println("sunflower plant at (" + i + ", "+j + ") just produced 50 sunpoints.");
                ((Plant)this.field.getEntity(i, j)).newSunCycle();
              }
              else
                ((Plant)this.field.getEntity(i, j)).incrementSunCycle();
            }//if
            
            //if the plant is PeaShooter
            else
            {
              boolean BulletsAhead = false;
              boolean zombieAhead = false;
              int zombieColumn = -1;
              
              if(!(field.getEntity(i, j+1) instanceof Zombie))
              {
              for(int y = field.getcolumns() - 2; y > j; y--)
              { 
                if(field.getEntity(i, y) instanceof Zombie)
                {
                  zombieAhead = true;
                  zombieColumn = 0;
                }  
                if(field.getEntity(i, y) instanceof Bullet)
                {
                  Zombie z1;
                  //If the bullet is close enough to the zombie, then hit the zombie(possible kill)
                  if(zombieAhead == true && (field.getEntity(i, y+1) instanceof Zombie || field.getEntity(i, y+2) instanceof Zombie) )
                  {
                    if( field.getEntity(i, y+1) instanceof Zombie )
                    {
                        z1 = (Zombie)field.getEntity(i, y+1);
                        zombieColumn = y+1;
                    }
                    else
                    {
                        z1 = (Zombie)field.getEntity(i, y+2);
                        zombieColumn = y+2;
                    }
                    Zombie z = (Zombie)field.getEntity(i, zombieColumn);
                    
                    //if zombies health is high, bullet hits the zombie
                    if( z.getStats().getHealth() - field.getEntity(i, y).getStats().getAtkpts() > 0)
                    {
                      z.getStats().setHealth(z.getStats().getHealth() - field.getEntity(i, y).getStats().getAtkpts());
                      field.setEntity(z);
                    }
                    //otherwise Zombie dies
                    else
                      field.setEntity(new Entity(i, zombieColumn));          
                  }//if
                  
                  //bullet is going out of the field
                  else if( y > field.getcolumns() - 2)
                    field.setEntity(new Entity(i, y));
                  
                  //If nothing ahead, bullet just moves forward
                  else
                  {
                    Entity b = (Bullet)field.getEntity(i, y);
                    b.setY(y+2);              
                    ((Bullet)b).setMoved(true);
                    field.setEntity(b);
                    field.setEntity(new Entity(i, y));
                    BulletsAhead = true;
                  }//else
                  
                  field.setEntity(new Entity(i, y));
                }//if 
              } //if
                //////////////////////////////////////////////////////////
                 if(BulletsAhead == true && zombieAhead == true)
                 {
                   Entity b = new Bullet(new Stats(0, 0, 20, 0, 0));
                   b.setX(i);
                   b.setY(j+1);
                   ((Bullet)b).setMoved(true);
                   bullets.add((Bullet)b);
                   field.setEntity((Bullet)b);
                 }//if
                 else if(zombieAhead == true)
                 {
                   for(int y = field.getcolumns(); y > j; y--)
                   {
                     if(field.getEntity(i, y) instanceof Zombie)
                     {
                       Entity b = new Bullet(new Stats(0, 0, 20, 0, 0));
                       b.setX(i);
                       b.setY(j+1);
                       ((Bullet)b).setMoved(true);
                       bullets.add((Bullet)b);
                       field.setEntity((Bullet)b);
                     }//if
                   }//for
                 }//if
                 }//else
                 
             }//else
          }//if

        }//for
      }//for
      for(Zombie z: zombies)
      { 
        //new zombies are coming in!!!
        if(time == z.getEnteringTime())
        {
          z.setY(z.getY() - 1 );
          this.field.setEntity((Zombie)z);
          
        }//if
      }//for
    
     return this.print();
  }  
 

  public void initiation()
  {
    flag = 0;
    System.out.println("Here are a list of commands you can use: q to quit, n to go to next time interval, s12 to" +
            "plant sunflower at 1,2,   ");
    //this.initiation();
    this.setTime(0);
    this.setSunPoints(100);
    this.field = new Field(15, 5);
    this.field.setGame(this);
    this.gameFinished = false;
    
    zombies = new ArrayList<Zombie>();
    plants = new ArrayList<Plant>();
    Zombie z = new Zombie(new Stats(0, 0, 50, 1, 20), 1);
    z.setX(0);
    z.setY(15);
    zombies.add(z);
    z = new Zombie(new Stats(0, 0, 10, 1, 20), 2);
    z.setX(1);
    z.setY(15);
    zombies.add(z);
    z = new Zombie(new Stats(0, 0, 10, 1, 20), 4);
    z.setX(2);
    z.setY(15);
    zombies.add(z);
    z = new Zombie(new Stats(0, 0, 10, 1, 20), 6);
    z.setX(3);
    z.setY(15);
    zombies.add(z);    
  }

  public String print()
  {
      for(int i = 0; i < field.getrows(); i++)
      {
        for(int j = 0; j < field.getcolumns(); j++)
        {
          if(field.getEntity(i, j) instanceof Zombie) 
            System.out.print(" z");
          else if(field.getEntity(i, j) instanceof Plant) 
          {
            if(((Plant)field.getEntity(i, j)).getPrice() == Plant.SUNFLOWERPRICE)
              System.out.print(" s");
            else
              System.out.print("p");
          }
          else if(field.getEntity(i, j) instanceof Bullet) 
            System.out.print(" b");
          else
            System.out.print(" -");
        }//for
        System.out.println();
      }//for

      String s = "";
      for(int i = 0; i < field.getrows(); i++)
      {
        for(int j = 0; j < field.getcolumns(); j++)
        {
          if(field.getEntity(i, j) instanceof Zombie) 
          {
            System.out.println("Zombie at ("+ i + "," + j +") has health of " + field.getEntity(i, j).getStats().getHealth());
            s = s + "Zombie at ("+ i + "," + j +") has health of " + field.getEntity(i, j).getStats().getHealth()+"\n";
          }
          else if(field.getEntity(i, j) instanceof Plant) 
          {
            System.out.println("Plant at ("+ i + "," + j +") has health of " + field.getEntity(i, j).getStats().getHealth());
            s = s + "Plant at ("+ i + "," + j +") has health of " + field.getEntity(i, j).getStats().getHealth() + "\n";
          }
        }//for
        System.out.println();
      }//for

      s = s + "You have " + this.sunPoints + " Sunpoints\n";
      System.out.println("You have " + this.sunPoints + " Sunpoints" );
      
            
      s = s + "Choose one of these commands! \nType c to continue, Type s(x,y) to plant a sunflower at x,y" +
          "or Type p(x,y) to plant a PeaShooter at x,y.\n";
      System.out.println("Choose one of these commands! \nType c to continue, Type s(x,y) to plant a sunflower at x,y" +
      		"or Type p(x,y) to plant a PeaShooter at x,y.");
      return s;
      
      
      
    }
  public String next(String s)
  {
      String answer ="";
      if (s.equalsIgnoreCase("c") ) 
      {
        System.out.println("You have choosen to continue");
        answer = answer + "You have choosen to continue";
        return answer;
      }
      else if (s.startsWith("s("))
      {
        int x = Integer.parseInt(s.substring(2, 3));
        int y= Integer.parseInt(s.substring(4, 5));
        if(this.sunPoints < Plant.SUNFLOWERPRICE)
        {
          System.out.println("Sorry, you do not have enough sun points.");
          answer = answer + "Sorry, you do not have enough sun points.";
        }
        else
        {
          field.setEntity(new Plant(x, y).Sunflower());
          this.sunPoints = this.sunPoints - Plant.SUNFLOWERPRICE;
        }//else
        
      }//elseif
      else if (s.startsWith("p("))
      {
        int x = Integer.parseInt(s.substring(2, 3));
        int y= Integer.parseInt(s.substring(4, 5));
        if(this.sunPoints < Plant.PEASHOOTERPRICE)
        {
          System.out.println("Sorry, you do not have enough sun points.");
          answer = answer + "Sorry, you do not have enough sun points.";
        }
        else
        {           
          Plant p =new Plant(x, y).Peashooter();
          p.setStats(new Stats(0, 0, 20, 0, 0));
          field.setEntity((Plant)p);
          this.sunPoints = this.sunPoints - Plant.PEASHOOTERPRICE;
                     
        }//else
        
      }//elseif
      else
      {
        System.out.println("Error 401; You have entered an invalid character. \nPlease try again.");
        answer = answer + "Error 401; You have entered an invalid character. \nPlease try again.";
      }
    return answer;
    
  }

  public String getGameStatus()
  {
    
    return null;
  }
  public String dspWelcome() {
    String s = "";
    s += "\n---------------------------------------\n"
            + "Welcome to the !";
    return s;
}
  public String playGame(String s)
  {
    return null;
  }
  

}
