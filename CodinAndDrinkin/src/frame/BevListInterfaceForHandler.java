package frame;

/**
 * BeverageList realizes, BeverageHandler uses.
 * @author csiki
 *
 */
public interface BevListInterfaceForHandler {
	
	/**
	 * Called when Drink button pressed.
	 * @param bevID
	 */
	public void drinkPressed(int bevID);
	
	/**
	 * Called when Pour button pressed.
	 * @param bevID
	 */
	public void pourPressed(int bevID);
}
