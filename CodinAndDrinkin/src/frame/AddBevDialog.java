package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import javax.swing.SwingConstants;

public class AddBevDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField vol;
	private JTextField alcVol;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddBevDialog dialog = new AddBevDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddBevDialog() {
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
