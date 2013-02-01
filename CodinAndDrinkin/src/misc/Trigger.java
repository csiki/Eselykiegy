package misc;

public interface Trigger {
	
	/**
	 * Called by Stopper, when time expires.
	 */
	public void shoot();
	
}
