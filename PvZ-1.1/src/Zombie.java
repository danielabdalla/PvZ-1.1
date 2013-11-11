public class Zombie extends Entity{

	private String Zombiename;
	private int enteringTime;
	
	
	public int getEnteringTime()
  {
    return enteringTime;
  }


  public void setEnteringTime(int enteringTime)
  {
    this.setEnteringTime(enteringTime);
  }

  public Zombie( Stats stats, int enteringTime )
  {
    super(0,0);
    this.setStats(stats);
    this.enteringTime = enteringTime;
  }

  public void RegZombie(Zombie Z) {
		Z.Zombiename = "NZ"; // This is the Normal Zombie
		stats.setAtkpts(40);
		stats.setDefpts(10);
		stats.setHitpts(400);
		stats.setSpdpts(20);
		stats.setSunpts(0);
	}

}
