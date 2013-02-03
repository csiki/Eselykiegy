package library;

/**
 * Crate interface for functionalities Game and UserInterface uses.
 * @author csiki
 *
 */
public interface CrateInterface {
	
	/**
	 * Returns name of the beverage.
	 * @param bevID as in Crate
	 * @return beverage name
	 */
	public String getBevName(int bevID);
	
	/**
	 * Returns volume of the beverage.
	 * @param bevID
	 * @return in dl
	 */
	public float getBevVol(int bevID);
	
	/**
	 * Returns alcohol by volume of the beverage.
	 * @param bevID
	 * @return in rate, like 0.4
	 */
	public float getBevABV(int bevID);
	
	/**
	 * Returns the number of beverages in crate.
	 * @return size
	 */
	public int getSize();
}
