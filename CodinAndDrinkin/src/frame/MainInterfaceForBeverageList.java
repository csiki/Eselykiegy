package frame;

/**
 * MainFrame realizes, BeverageList uses.
 * @author csiki
 *
 */
public interface MainInterfaceForBeverageList {
	
	/**
	 * Called by BeverageList when a Drink button is pressed.
	 * @param bevID
	 */
	public void bevDrink(int bevID);
	
	/**
	 * Called by BeverageList when a Pour button is pressed.
	 * @param bevID
	 */
	public void bevPour(int bevID);
}
