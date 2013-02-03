package library;

/**
 * Stores and manages a drink.
 * @author csiki
 *
 */
public class Beverage {
	private String name;
	
	/**
	 * in rate, like 0.4
	 */
	private float abv;
	
	/**
	 * in dl
	 */
	private float vol = 0.0F;
	
	Beverage(String name, float vol, float abv) {
		this.name = name;
		this.vol = vol;
		this.abv = abv;
	}
	
	/**
	 * Increase the volume of the beverage by vol.
	 * @param vol in dl
	 */
	public void pour(float vol) {
		this.vol += vol;
	}
	
	/**
	 * Decrease the volume of the beverage by vol.
	 * @param vol in dl
	 * @throws NotEnoughAlcoholException if there is not enough beverage to consume (should not be thrown, previously should be checked.
	 */
	public void consume(float vol) throws NotEnoughAlcoholException {
		if (vol > this.vol) {
			this.vol = 0.0F;
			throw new NotEnoughAlcoholException("Pour sommore harrr!");
		}
		else
			this.vol -= vol;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getVol() {
		return this.vol;
	}
	
	public float getABV() {
		return this.abv;
	}
}
