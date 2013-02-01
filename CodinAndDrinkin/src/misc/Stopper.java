package misc;

public class Stopper implements Runnable {
	
	/**
	 * where the shot goes
	 */
	private final Trigger trigger;
	/**
	 * in millisecs, check with System.currentTimeMillis()
	 */
	private final long shootTime;
	/**
	 * if true, the shot happens in the given time
	 */
	private volatile boolean danger = true;
	
	
	public Stopper(Trigger trigger, long shootTime) {
		this.trigger = trigger;
		this.shootTime = shootTime;
	}
	
	@Override
	public void run() {
		
		while (this.danger) {
			if (System.currentTimeMillis() >= shootTime)
				break;
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
