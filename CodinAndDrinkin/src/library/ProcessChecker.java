package library;

/**
 * Runs till Process is running, so the process runtime can be checked outside implicitly.
 * @author csiki
 *
 */
public class ProcessChecker implements Runnable {
	
	private Process pr;
	
	public ProcessChecker(Process pr) {
		this.pr = pr;
	}
	
	@Override
	public void run() {
		try {
			pr.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
