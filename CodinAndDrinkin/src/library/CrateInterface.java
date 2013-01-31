package library;

public interface CrateInterface {
	
	/**
	 * Returns name of the beverage.
	 * @param bevID as in Crate
	 * @return String: beverage name
	 */
	public String getBevName(int bevID);
	
	/**
	 * Returns volume of the beverage.
	 * @param bevID
	 * @return int: in dl
	 */
	public float getBevVol(int bevID);
	
	/**
	 * Returns alcohol by volume of the beverage.
	 * @param bevID
	 * @return float: in rate, like 0.4
	 */
	public float getBevABV(int bevID);
	
}
