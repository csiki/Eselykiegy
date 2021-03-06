package frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import library.CrateInterface;

/**
 * List of beverages on GUI.
 * @author csiki
 *
 */
public class BeverageList extends JPanel implements BevListInterfaceForHandler { // TODO javadoc !

	private static final long serialVersionUID = -8202771727033276797L;
	
	MainInterfaceForBeverageList main;
	CrateInterface crate;
	List<BeverageHandler> beverages = new ArrayList<BeverageHandler>();
	
	BeverageList(MainInterfaceForBeverageList main) {
		super();
		this.main = main;
		
		/// settings
		this.setBackground(SystemColor.inactiveCaption);
		GridBagLayout gbl_bevPanel = new GridBagLayout();
		gbl_bevPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_bevPanel.rowHeights = new int[]{0, 0};
		gbl_bevPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bevPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.setLayout(gbl_bevPanel);
	}
	
	/**
	 * Called by MainFrame if a beverage is added. Adds it to the list.
	 */
	public void bevAdded() {
		if (beverages.size() == crate.getSize())
			return; // false call
		
		int newID = beverages.size();
		BeverageHandler bev = new BeverageHandler(this, newID, crate.getBevName(newID), String.format("%.1f", crate.getBevVol(newID)));
		beverages.add(bev);
		
		addBevToGUI(bev);
	}
	
	/**
	 * Adds a beverage to the GUI.
	 * @param bev - beverage to add
	 */
	private void addBevToGUI(BeverageHandler bev) {
		GridBagConstraints gbc = bev.initBtnDrink();
		this.add(bev.getBtnDrink(), gbc);
		
		gbc = bev.initBtnPour();
		this.add(bev.getBtnPour(), gbc);
		
		gbc = bev.initLblBevName();
		this.add(bev.getLblBevName(), gbc);
		
		gbc = bev.initLblVol();
		this.add(bev.getLblVol(),gbc);
		
		gbc = bev.initLblDl();
		this.add(bev.getLblDl(), gbc);
		
		this.validate();
	}
	
	/**
	 * Called by MainFrame if vol of a beverage changes. Updates beverage volume.
	 * @param bevID - id of beverage that should be updated
	 */
	public void bevVolChanged(int bevID) {
		beverages.get(bevID).updateVol(String.format("%.1f", crate.getBevVol(bevID)));
	}
	
	/**
	 * Enables or disables all Drink buttons.
	 * @param e - true to enable, false to disable
	 */
	public void setDrinkBtnEnabled(boolean e) {
		for (BeverageHandler bh : this.beverages)
			bh.setDrinkBtnEnabled(e);
	}
	
	/**
	 * Sets crate.
	 * @param crate
	 */
	public void setCrate(CrateInterface crate) {
		this.crate = crate;
	}
	
	/*
	 * Implemented methods from interface BevListInterfaceForHandler
	 */
	
	@Override
	public void drinkPressed(int bevID) {
		main.bevDrink(bevID);
	}

	@Override
	public void pourPressed(int bevID) {
		main.bevPour(bevID);
	}
}
