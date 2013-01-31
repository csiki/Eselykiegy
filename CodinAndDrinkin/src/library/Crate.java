package library;

import java.util.ArrayList;
import java.util.List;

public class Crate implements CrateInterface {
	private List<Beverage> beverages = new ArrayList<Beverage>();
	
	/**
	 * Add a beverage to the Crate
	 * @param bev
	 * @return the id of the added beverage as in Crate
	 */
	public int add(Beverage bev) {
		beverages.add(bev);
		return beverages.size() - 1;
	}
	
	/**
	 * Returns with a reference to a beverage by the given bevID.
	 * @param bevID as in Crate
	 * @return Beverage
	 */
	public Beverage get(int bevID) {
		return beverages.get(bevID);
	}
	
	/*
	 * Implemented methods from interface CrateInterface
	 */
	@Override
	public String getBevName(int bevID) {
		return beverages.get(bevID).getName();
	}

	@Override
	public float getBevVol(int bevID) {
		return beverages.get(bevID).getVol();
	}

	@Override
	public float getBevABV(int bevID) {
		return beverages.get(bevID).getABV();
	}

	@Override
	public int getSize() {
		return this.beverages.size();
	}
	
}
