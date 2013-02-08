package frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * One line in BeverageList.
 * @author csiki
 *
 */
public class BeverageHandler {
	
	private BevListInterfaceForHandler bevList;
	private int id;
	
	/// items
	private JButton btnDrink;
	private JButton btnPour;
	private JLabel lblBevName;
	private JLabel lblVol;
	private JLabel lblDl;
	
	
	BeverageHandler(BevListInterfaceForHandler bevList, int id, String name, String vol) {
		this.bevList = bevList;
		this.id = id;
		
		/// items
		this.btnDrink = new JButton("Drink");
		this.btnPour = new JButton("Pour");
		this.lblBevName = new JLabel(name);
		this.lblVol = new JLabel(vol);
		this.lblDl = new JLabel("dl");
	}
	
	public void updateVol(String vol) {
		this.lblVol.setText(vol);
	}
	
	public GridBagConstraints initBtnDrink() {
		this.btnDrink = new JButton("Drink");
		this.btnDrink.setFont(new Font("Tahoma", Font.PLAIN, 9));
		this.btnDrink.setMinimumSize(new Dimension(60, 23));
		this.btnDrink.setEnabled(false);
		this.btnDrink.setAction(new DrinkAction());
		GridBagConstraints gbc_btnDrink = new GridBagConstraints();
		gbc_btnDrink.insets = new Insets(0, 0, 0, 5);
		gbc_btnDrink.gridx = 0;
		gbc_btnDrink.gridy = this.id;
		
		return gbc_btnDrink;
	}
	
	public GridBagConstraints initBtnPour() {
		this.btnPour = new JButton("Pour");
		this.btnPour.setFont(new Font("Tahoma", Font.PLAIN, 9));
		this.btnPour.setEnabled(true);
		this.btnPour.setAction(new PourAction());
		GridBagConstraints gbc_btnPour = new GridBagConstraints();
		gbc_btnPour.insets = new Insets(0, 0, 0, 5);
		gbc_btnPour.gridx = 1;
		gbc_btnPour.gridy = this.id;
		
		return gbc_btnPour;
	}
	
	public GridBagConstraints initLblBevName() {
		this.lblBevName.setMaximumSize(new Dimension(100, 14));
		this.lblBevName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.lblBevName.setMinimumSize(new Dimension(20, 14));
		this.lblBevName.setPreferredSize(new Dimension(58, 14));
		GridBagConstraints gbc_lblBevName = new GridBagConstraints();
		gbc_lblBevName.insets = new Insets(0, 0, 0, 5);
		gbc_lblBevName.gridx = 2;
		gbc_lblBevName.gridy = this.id;
		
		return gbc_lblBevName;
	}
	
	public GridBagConstraints initLblVol() {
		GridBagConstraints gbc_lbldl = new GridBagConstraints();
		gbc_lbldl.insets = new Insets(0, 0, 0, 5);
		gbc_lbldl.gridx = 3;
		gbc_lbldl.gridy = this.id;
		
		return gbc_lbldl;
	}
	
	public GridBagConstraints initLblDl() {
		this.lblDl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblDl = new GridBagConstraints();
		gbc_lblDl.insets = new Insets(0, 0, 0, 5);
		gbc_lblDl.gridx = 4;
		gbc_lblDl.gridy = this.id;
		
		return gbc_lblDl;
	}
	
	public void setDrinkBtnEnabled(boolean e) {
		this.btnDrink.setEnabled(e);
	}
	
	
	/*
	 * Getters
	 */
	public int getId() {
		return id;
	}

	public JButton getBtnDrink() {
		return btnDrink;
	}

	public JButton getBtnPour() {
		return btnPour;
	}

	public JLabel getLblBevName() {
		return lblBevName;
	}

	public JLabel getLblVol() {
		return lblVol;
	}

	public JLabel getLblDl() {
		return lblDl;
	}
	
	private class PourAction extends AbstractAction {

		private static final long serialVersionUID = 8354977488379078992L;

		public PourAction() {
			putValue(NAME, "Pour");
			putValue(SHORT_DESCRIPTION, "Extends the volume of the drink");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			bevList.pourPressed(id);
		}
	}
	
	private class DrinkAction extends AbstractAction {

		private static final long serialVersionUID = 8261130949357386433L;

		public DrinkAction() {
			putValue(NAME, "Drink");
			putValue(SHORT_DESCRIPTION, "Consume that drink");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			bevList.drinkPressed(id);
		}
	}
}
