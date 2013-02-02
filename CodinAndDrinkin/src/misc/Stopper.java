package misc;

public class Stopper implements Runnable {
	
	/**
	 * where the shot goes
	 */
	private final Trigger trigger;
	/**
	 * in millisecs
	 */
	private final long startTime;
	/**
	 * in millisecs, check if reached with System.currentTimeMillis()
	 */
	private final long shootTime;
	/**
	 * in millisecs, the time after trigger should be warned over-and-over if >0
	 */
	private final long warnTime;
	/**
	 * if true, the shot happens in the given time
	 */
	private volatile boolean danger = true;
	
	
	public Stopper(Trigger trigger, long startTime, long shootTime, long warnTime) {
		this.trigger = trigger;
		this.startTime = startTime;
		this.shootTime = shootTime;
		this.warnTime = warnTime;
	}
	
	@Override
	public void run() {
		
		while (this.danger) {
			if (System.currentTimeMillis() >= this.shootTime)
				break;
			
			long elapsedTime = System.currentTimeMillis() - this.startTime;
			long timeTillNextWarn = this.warnTime - (elapsedTime % this.warnTime);
			
			try {
				Thread.sleep(timeTillNextWarn);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			trigger.warn(System.currentTimeMillis() - this.startTime);
		}
		
		if (this.danger)
			trigger.shoot();
	}
	
	/**
	 * Prevent the shot, if not late.
	 */
	public void surrender() {
		this.danger = false;
	}
	
}
