package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewGameDialog extends JDialog implements DialogInputValidationInterface {
	private static final long serialVersionUID = -4923393269220341951L;
	
	private MainInterfaceForDialogs main;
	private final JPanel contentPanel = new JPanel();
	private JTextField height;
	private JTextField name;
	private JTextField weight;
	private JRadioButton rdbtnMale = new JRadioButton("male");
	private JRadioButton rdbtnFemale = new JRadioButton("female");
	private JButton okButton = new JButton("OK");
	
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
		
		/// sex
		if (!rdbtnMale.isSelected() && !rdbtnFemale.isSelected())
			return false;
		
		/// height
		Pattern hp = Pattern.compile("[1-2][0-9][0-9]");
		Matcher hm = hp.matcher(height.getText());
		if (!hm.matches())
			return false;
		
		/// weight
		Pattern wp = Pattern.compile("[1-2]?[0-9][0-9]");
		Matcher wm = wp.matcher(weight.getText());
		if (!wm.matches())
			return false;
		
		return true;
	}
	
	/*
	 * Getters
	 */
	public String getInputName() {
		return this.name.getText();
	}
	
	public String getInputHeight() {
		return this.height.getText();
	}
	
	public String getInputWeight() {
		return this.weight.getText();
	}
	
	public boolean getInputSexMale() {
		return this.rdbtnMale.isSelected();
	}
	
	public boolean getInputSexFemale() {
		return this.rdbtnFemale.isSelected();
	}
	
	/**
	 * Create the dialog.
	 */
	public NewGameDialog(MainInterfaceForDialogs main) {
		this.main = main;
		
		setTitle("Let's get drunk");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JLabel lblName = new JLabel("Name:");
			contentPanel.add(lblName, "6, 4, left, default");
		}
		{
			name = new JTextField();
			name.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					checkIfOK();
				}
			});
			contentPanel.add(name, "8, 4, left, default");
			name.setColumns(15);
		}
		{
			JLabel lblSex = new JLabel("Sex:");
			contentPanel.add(lblSex, "6, 6");
		}
		{
			ButtonGroup sex = new ButtonGroup();
			
			rdbtnMale.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					checkIfOK();
				}
			});
			contentPanel.add(rdbtnMale, "8, 6");
			
			rdbtnFemale.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JOptionPane.showMessageDialog(null, "Looking for a real man? I'd check the coderteam right away!");
					checkIfOK();
				}
			});
			contentPanel.add(rdbtnFemale, "8, 8");
			
			sex.add(rdbtnMale);
			sex.add(rdbtnFemale);
		}
		{
			JLabel lblNewLabel = new JLabel("Height:");
			contentPanel.add(lblNewLabel, "6, 10, left, default");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "8, 10, left, default");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				height = new JTextField();
				height.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (height.getText().matches("[1-2][0-9][0-9]"))
							if (Integer.parseInt(height.getText()) > 190)
								JOptionPane.showMessageDialog(null, "Have you considered playing basketball?");
						checkIfOK();
					}
				});
				height.setHorizontalAlignment(SwingConstants.RIGHT);
				height.setPreferredSize(new Dimension(5, 20));
				height.setMinimumSize(new Dimension(5, 20));
				panel.add(height);
				height.setColumns(3);
			}
			{
				JLabel lblCm = new JLabel("cm");
				panel.add(lblCm);
			}
		}
		{
			JLabel lblWeight = new JLabel("Weight:");
			contentPanel.add(lblWeight, "6, 12, right, default");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "8, 12, left, fill");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				weight = new JTextField();
				weight.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (weight.getText().matches("[1-2]?[0-9][0-9]"))
							if (Integer.parseInt(weight.getText()) > 100)
								JOptionPane.showMessageDialog(null, "A little workout now and then shouldn't heart that much.");
						checkIfOK();
					}
				});
				weight.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(weight);
				weight.setColumns(3);
			}
			{
				JLabel lblKg = new JLabel("kg");
				panel.add(lblKg);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setAction(new OkAction(this, this.main));
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(new CancelAction(this));
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
	
	private class OkAction extends AbstractAction {
		
		private static final long serialVersionUID = -1563354365974572438L;
		
		MainInterfaceForDialogs main;
		JDialog jd;
		
		OkAction(JDialog jd, MainInterfaceForDialogs main) {
			this.jd = jd;
			this.main = main;
			putValue(NAME, "OK");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.main.newGameDialogReady();
			jd.dispose();
		}
	}
}
