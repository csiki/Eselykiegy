package library;

import java.util.List;

public class Crate implements CrateInterface {
	protected List<Beverage> beverages;
	
	public int add(String name, float vol, float ABV ) { // TODO
		int bevID = 0;
		return bevID;
	}
	
	public void pour(int bevID, float vol) { // TODO
	}
	
	public void consume(int bevID, float vol) { // TODO
	}
	
	/*
	 * Implemented methods from interface CrateInterface
	 */
	@Override
	public String getBevName(int bevID) { // TODO
		return null;
	}

	@Override
	public float getBevVol(int bevID) { // TODO
		return 0;
	}

	@Override
	public float getBevABV(int bevID) { // TODO
		return 0;
	}
	
}
