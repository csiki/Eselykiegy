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

public class NewGameDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField height;
	private JTextField name;
	private JTextField weight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewGameDialog dialog = new NewGameDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewGameDialog() {
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
			contentPanel.add(name, "8, 4, left, default");
			name.setColumns(15);
		}
		{
			JLabel lblSex = new JLabel("Sex:");
			contentPanel.add(lblSex, "6, 6");
		}
		{
			ButtonGroup sex = new ButtonGroup();
			
			JRadioButton rdbtnMale = new JRadioButton("male");
			contentPanel.add(rdbtnMale, "8, 6");
			JRadioButton rdbtnFemale = new JRadioButton("female");
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
				JButton okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
