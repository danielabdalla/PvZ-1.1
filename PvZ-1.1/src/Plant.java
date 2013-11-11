import java.util.ArrayList;

public class Plant extends Entity {

	public Plant(int x, int y)
  {
    super(x, y);

  }
	
    private String plantname;
	private Stats stats;
	private int price;
	public static int SUNFLOWERPRICE = 100;
	public static int PEASHOOTERPRICE = 100;
	private int sunCycle;
	private ArrayList<Bullet> bullets;
	
	public int getSunCycle()
  {
    return sunCycle;
  }

  public int getPrice()
  {
    return price;
  }

  public void addBullet(Bullet b)
  {
    bullets.add(b);
  }
  public Plant Sunflower() {
		this.plantname = "SunFlower"; // Sunflower
		this.price = SUNFLOWERPRICE;
		this.setStats(new Stats(0, 50, 0, 0, 0));
		this.sunCycle = 5;
		return this;
	}

	public Plant Peashooter() {
		this.plantname = "PeaShooter"; // Peashooter
		this.bullets = new ArrayList<Bullet>();
		this.setStats(new Stats(50, 0, 0, 2, 0));
		return this;
	}

	public void incrementSunCycle()
	{
	  this.sunCycle --;
	}
    public void newSunCycle()
    {
      this.sunCycle = 5;
    }
	
}
