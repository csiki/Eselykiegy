package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewGameDialog extends JDialog {
	private static final long serialVersionUID = -4923393269220341951L;
	
	InterfaceForDialogs main;
	private final JPanel contentPanel = new JPanel();
	private JTextField height;
	private JTextField name;
	private JTextField weight;
	JRadioButton rdbtnMale = new JRadioButton("male");
	JRadioButton rdbtnFemale = new JRadioButton("female");
	JButton okButton = new JButton("OK");
	private final Action action = new SwingAction(this);
	
	/*
	 * Own methods
	 */
	void checkIfOK() {
		okButton.setEnabled(inputValidation());
	}
	
	boolean inputValidation() {
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
	public NewGameDialog(InterfaceForDialogs main) {
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
				okButton.addMouseListener(new OkAction(this, this.main));
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(action);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = -6281457554887252620L;
		
		JDialog jd;
		
		public SwingAction(JDialog jd) {
			this.jd = jd;
			putValue(NAME, "Cancel");
		}
		public void actionPerformed(ActionEvent e) {
			jd.dispose();
		}
	}
	
	private class OkAction extends MouseAdapter {
		InterfaceForDialogs main;
		JDialog jd;
		
		OkAction(JDialog jd, InterfaceForDialogs main) {
			this.jd = jd;
			this.main = main;
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			this.main.newGameDialogReady();
			jd.dispose();
		}
	}
}
