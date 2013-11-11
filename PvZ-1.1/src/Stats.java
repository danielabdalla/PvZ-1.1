public class Stats {
	private int hitpts;
	private int sunpts;
	private int atkpts;
	private int spdpts;
	private int defpts;
	private int health;

	public Stats(int hitpts, int sunpts, int atkpts, int spdpts, int defpts) {
		this.hitpts = hitpts;
		this.sunpts = sunpts;
		this.atkpts = atkpts;
		this.spdpts = spdpts;
		this.defpts = defpts;
		this.setHealth(100);
		this.toString();
	}

   public int getHealth()
  {
    return health;
  }

  public void setHealth(int health)
  {
    this.health = health;
  }

  public int getHitpts() {
		return hitpts;
	}

	public void setHitpts(int hitpts) {
		this.hitpts = hitpts;
	}

	public int getSunpts() {
		return sunpts;
	}

	public void setSunpts(int sunpts) {
		this.sunpts = sunpts;
	}

	public int getAtkpts() {
		return atkpts;
	}

	public void setAtkpts(int atkpts) {
		this.atkpts = atkpts;
	}

	public int getSpdpts() {
		return spdpts;
	}

	public void setSpdpts(int spdpts) {
		this.spdpts = spdpts;
	}

	public int getDefpts() {
		return defpts;
	}

	public void setDefpts(int defpts) {
		this.defpts = defpts;
	}

	public String toString() {
		return "Hit points: " + Integer.toString(hitpts) + "\nSun points: "
				+ Integer.toString(sunpts) + "\nAttack points: "
				+ Integer.toString(atkpts) + "\nSpeed points: "
				+ Integer.toString(spdpts) + "\nDefence points: "
				+ Integer.toString(defpts);
	}

}
