package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddBevDialog extends JDialog implements DialogInputValidationInterface {
	
	private MainInterfaceForDialogs main;
	private final JPanel contentPanel = new JPanel();
	private JTextField vol;
	private JTextField alcVol;
	private JTextField name;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private final Action cancelAction = new CancelAction(this);
	
	/*
	 * Implemented methods from interface DialogInputValidationInterface
	 */
	@Override
	public void checkIfOK() {
		okButton.setEnabled(inputValidation());
	}
	
	@Override
	public boolean inputValidation() {
		/// name
		if (name.getText().length() == 0)
			return false;
		
		/// vol
		Pattern vp = Pattern.compile("[1-2]?[0-9]?[0-9][.]?[0-9]?");
		Matcher vm = vp.matcher(vol.getText());
		if (!vm.matches())
			return false;
		
		/// alcVol
		Pattern avp = Pattern.compile("[1-9]?[0-9]");
		Matcher avm = avp.matcher(alcVol.getText());
		if (!avm.matches())
			return false;
		
		return true;
	}
	
	/*
	 * Getters
	 */
	public String getInputName() {
		return this.name.getText();
	}
	
	public String getInputVol() {
		return this.vol.getText();
	}
	
	public String getInputAlcVol() {
		return this.alcVol.getText();
	}
	
	/**
	 * Create the dialog.
	 */
	public AddBevDialog(MainInterfaceForDialogs main) {
		this.main = main;
		setTitle("Moar booze arrr!");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPanel.add(lblName, "6, 6, left, default");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "8, 6, fill, fill");
			{
				name = new JTextField();
				name.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						checkIfOK();
					}
				});
				panel.add(name);
				name.setColumns(10);
			}
		}
		{
			JLabel lblVolume = new JLabel("Volume:");
			lblVolume.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPanel.add(lblVolume, "6, 10");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "8, 10, left, fill");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				vol = new JTextField();
				vol.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						checkIfOK();
					}
				});
				vol.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(vol);
				vol.setColumns(2);
			}
			{
				JLabel lblDl = new JLabel("dl");
				lblDl.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(lblDl);
			}
		}
		{
			JLabel lblAlcolholByVolume = new JLabel("Alcolhol by volume:");
			lblAlcolholByVolume.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPanel.add(lblAlcolholByVolume, "6, 14");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "8, 14, left, fill");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				alcVol = new JTextField();
				alcVol.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						checkIfOK();
					}
				});
				alcVol.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(alcVol);
				alcVol.setColumns(2);
			}
			{
				JLabel label = new JLabel("%");
				label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel.add(label);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addMouseListener(new OkAction(this, this.main));
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setAction(cancelAction);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private class CancelAction extends AbstractAction {
		private static final long serialVersionUID = -6281457554887252620L;
		
		JDialog jd;
		
		public CancelAction(JDialog jd) {
			this.jd = jd;
			putValue(NAME, "Cancel");
		}
		public void actionPerformed(ActionEvent e) {
			jd.dispose();
		}
	}
	
	private class OkAction extends MouseAdapter {
		MainInterfaceForDialogs main;
		JDialog jd;
		
		OkAction(JDialog jd, MainInterfaceForDialogs main) {
			this.jd = jd;
			this.main = main;
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			this.main.addBevDialogReady();
			jd.dispose();
		}
	}
}
