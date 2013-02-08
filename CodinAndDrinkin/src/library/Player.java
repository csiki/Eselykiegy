package library;

/**
 * Stores player's data, crate. Manifest an ingame player with its functionalities.
 * @author csiki
 *
 */
public class Player {
	
	private final String name;
	private final Sex sex;
	/**
	 * in kg
	 */
	private final int weight;
	/**
	 * in cm
	 */
	private final int height;
	/**
	 * in dl
	 */
	private float consumedAlc = 0.0F;
	private Crate crate;

	Player(String name, Sex sex, int height, int weight) {
		this.name = name;
		this.sex = sex;
		this.height = height;
		this.weight = weight;
		
		this.crate = new Crate();
	}
	
	/**
	 * Calls the crate, and consume the given beverage. Increase consumedAlc by alcVol.
	 * @param bevID as in crate
	 * @param vol in dl
	 * @param alcVol in dl
	 */
	public void consume(int bevID, float vol, float alcVol) {
		try {
			crate.get(bevID).consume(vol);
		} catch (NotEnoughAlcoholException e) {
			e.printStackTrace();
		}
		
		consumedAlc += alcVol;
	}
	
	/**
	 * Calls the crate, and pour to the given beverage.
	 * @param bevID as in crate
	 * @param vol in dl
	 */
	public void pour(int bevID, float vol) {
		crate.get(bevID).pour(vol);
	}
	
	/**
	 * Creates a beverage and adds it to the crate.
	 * @param name
	 * @param vol in dl
	 * @param abv in rate, like 0.4
	 * @return the id of the added beverage, as in crate
	 */
	public int addBev(String name, float vol, float abv) {
		Beverage bev = new Beverage(name, vol, abv);
		
		return crate.add(bev);
	}
	
	/**
	 * Uses Game.savePlayer. For Game and UserUnterface.
	 * @return CrateInterface
	 */
	public CrateInterface getCrate() {
		return this.crate;
	}
	
	/*
	 * Getters
	 */
	
	public Sex getSex() {
		return this.sex;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public float getConsumedAlc() {
		return this.consumedAlc;
	}
}
