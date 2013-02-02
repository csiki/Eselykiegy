package misc;

public interface Trigger {
	
	/**
	 * Called by Stopper, every time warnTime elapsed, again and again, if Stopper.warnTime!=0.
	 * @param elapsedTime in millisecs
	 */
	public void warn(long elapsedTime);
	
	/**
	 * Called by Stopper, when time expires.
	 */
	public void shoot();
	
}
