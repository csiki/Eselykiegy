package frame;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;

import library.CrateInterface;
import library.GameLogic;
import library.Sex;
import library.SolutionInterface;
import library.SolutionOutcome;
import library.Task;
import library.TaskValidationOutcome;
import library.UserInterface;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Action;

public class MainFrame implements Runnable, UserInterface, MainInterfaceForDialogs, MainInterfaceForBeverageList {

	private GameLogic game;
	private CrateInterface crate;
	private BeverageList bevList;
	private JFrame frmCodindrinkin = new JFrame();
	private JTable solutionsTable;
	SolutionsData solutionsData = new SolutionsData();
	
	/// dialogs
	private NewGameDialog ngd;
	private AddBevDialog abd;
	private FileChooserFrame fcf = new FileChooserFrame();
	
	/// items
	private JMenuItem mntmNewGame;
	private JMenuItem mntmAddBeverage;
	private JMenuItem mntmLoadNewTask;
	private JMenuItem mntmEndCurrentTask;
	private JComboBox langChoose;
	private JEditorPane code;
	private JButton btnGiveup;
	private JButton btnSend;
	private JLabel namelbl;
	private JLabel sexlbl;
	private JLabel bloodAlcContentlbl;
	private JLabel alcLeft;
	private JLabel taskName;
	private JLabel taskID;
	private JLabel preTaskID;
	private JLabel ellapsedTime;
	private JLabel deadline;
	private JLabel solutionsSent;
	private JLabel solutionsPermited;
	private JLabel outcome;
	private JTextPane taskDescription;
	private JLabel timeLeft;
	private JLabel rateOfSolutions;
	private JLabel consumedAlc;
	
	/// actions
	private final Action newGameAction = new NewGameAction(this);
	private final Action exitAction = new ExitAction(frmCodindrinkin);
	private final Action addBevAction = new AddBevAction(this);
	private final Action loadTaskAction = new LoadTaskAction(this);

	/**
	 * Create the application.
	 */
	public MainFrame(GameLogic game) {
		this.game = game;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void run() {
		initialize();
	}
	
	/*
	 * Methods run at state switch
	 */
	private void toStateOutOfBeverage() {
		// TODO message ablak
		/// enable/disable items
		mntmAddBeverage.setEnabled(true);
		mntmNewGame.setEnabled(false);
		mntmLoadNewTask.setEnabled(false);
		mntmEndCurrentTask.setEnabled(false);
		langChoose.setEnabled(false);
		code.setEnabled(false);
		btnGiveup.setEnabled(false);
		btnSend.setEnabled(false);
		
		this.bevList.setDrinkBtnEnabled(false);
	}
	
	private void toStateAbleToLoadTask() {
		// TODO message ablak
		/// enable/disable items
		mntmAddBeverage.setEnabled(true);
		mntmNewGame.setEnabled(false);
		mntmLoadNewTask.setEnabled(true);
		mntmEndCurrentTask.setEnabled(false);
		langChoose.setEnabled(false);
		code.setEnabled(false);
		btnGiveup.setEnabled(false);
		btnSend.setEnabled(false);
		
		this.bevList.setDrinkBtnEnabled(false);
	}
	
	private void toStateTaskStarted() {
		// TODO
		/// enable/disable items
		mntmAddBeverage.setEnabled(true);
		mntmNewGame.setEnabled(false);
		mntmLoadNewTask.setEnabled(false);
		mntmEndCurrentTask.setEnabled(true);
		langChoose.setEnabled(true);
		code.setEnabled(true);
		btnGiveup.setEnabled(true);
		btnSend.setEnabled(true);
		
		this.bevList.setDrinkBtnEnabled(false);
	}
	
	private void toStateMustDrink() {
		// TODO message ablak
		mntmAddBeverage.setEnabled(true);
		mntmNewGame.setEnabled(false);
		mntmLoadNewTask.setEnabled(false);
		mntmEndCurrentTask.setEnabled(false);
		langChoose.setEnabled(false);
		code.setEnabled(false);
		btnGiveup.setEnabled(false);
		btnSend.setEnabled(false);
		
		this.bevList.setDrinkBtnEnabled(true);
	}
	
	
	/*
	 * Implemented methods from interface UserInterface
	 */
	@Override
	public void chooseBev(float alcVol) {
		this.alcLeft.setText(Float.toString(alcVol * 10.0F)); // from dl to cl
		
		/// state transition
		toStateMustDrink();
	}

	@Override
	public void startTask(Task task) {
		
		/// set items
		this.taskID.setText(Integer.toString(task.id));
		this.preTaskID.setText(Integer.toString(task.priorTaskID));
		this.taskDescription.setText(task.description);
		
		this.outcome.setText(SolutionOutcome.Unvalidated.toString());
		this.ellapsedTime.setText("0");
		
		int seconds = (int) (task.timeAllowed / 1000);
		this.deadline.setText(seconds / 60 + ":" + seconds % 60);
		this.solutionsSent.setText("0");
		this.solutionsPermited.setText(Integer.toString(task.attemptsAllowed));
		
		/// state transition
		toStateTaskStarted();
	}

	@Override
	public void endTask(SolutionInterface solution) {
		/// state transition
		if (this.crate.gotAnyAlcohol())
			toStateAbleToLoadTask();
		else
			toStateOutOfBeverage();
		
		/// update GUI Solutions table
		solutionsData.addSolution(solution); // fire included
		
		/// update Time left, Rate of right solutions
		int secs = (int) (this.game.sumTimeLeft() / 1000);
		this.timeLeft.setText(Integer.toString(secs / 60) + ":" + Integer.toString(secs % 60));
		this.rateOfSolutions.setText(Integer.toString(this.game.rateOfRightSolutions()));
	}
	
	@Override
	public void refreshTime(int min, int sec) {
		this.ellapsedTime.setText(Integer.toString(min) + ":" + Integer.toString(sec));
	}
	
	@Override
	public void refreshAttemptNum(int num) {
		this.solutionsSent.setText(Integer.toString(num));		
	}
	
	/*
	 * Implemented methods from interface InterfaceForDialogs
	 */
	
	@Override
	public void setNewGameDialog(NewGameDialog ngd) {
		this.ngd = ngd;
	}
	
	@Override
	public void newGameDialogReady() {
		
		/// sex
		Sex sex;
		if (this.ngd.getInputSexMale())
			sex = Sex.Male;
		else
			sex = Sex.Female;
		
		this.crate = game.savePlayer(
				this.ngd.getInputName(),
				sex,
				Integer.parseInt(this.ngd.getInputHeight()),
				Integer.parseInt(this.ngd.getInputWeight()));
		
		/// state transition
		this.toStateOutOfBeverage();
		
		/// update user data on GUI
		this.namelbl.setText(this.ngd.getInputName());
		if (sex == Sex.Male)
			this.sexlbl.setText("male");
		else
			this.sexlbl.setText("female");
		this.bloodAlcContentlbl.setText("0");
		
		/// set crate for BeverageList
		bevList.setCrate(crate);
	}
	
	@Override
	public void setAddBevDialog(AddBevDialog abd) {
		this.abd = abd;
	}
	
	@Override
	public void addBevDialogReady() {
		/// add to Crate
		game.addBev(abd.getInputName(), Float.parseFloat(abd.getInputVol()), Integer.parseInt(abd.getInputAlcVol()));
		
		/// add beverage to GUI list
		bevList.bevAdded();
		
		/// state transition
		if (game.isAnyTaskLoaded())
			toStateTaskStarted();
		else
			toStateAbleToLoadTask();
	}
	
	@Override
	public void loadTask(File taskFile) {
		TaskValidationOutcome tvo = this.game.loadTask(taskFile);
		
		if (tvo == TaskValidationOutcome.NoFileFound) {
			// TODO message
		} else if (tvo == TaskValidationOutcome.NotEnoughAlcohol) {
			// TODO message
		} else if (tvo == TaskValidationOutcome.PreTaskNotSolved) {
			// TODO message
		}
		// if valid Game calls UserInterface.taskStarted()
	}
	
	
	/*
	 * Implemented methods from interface MainInterfaceForBeverageList
	 */
	
	@Override
	public void bevDrink(int bevID) {
		float howMuchToDrink = game.bevToDrink(bevID);

		String msg = "Drink " + howMuchToDrink + " dl of your " + crate.getBevName(bevID) + "!";
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public void bevPour(int bevID) {
		String amount = JOptionPane.showInputDialog(null, "How much to pour?");

		Pattern ap = Pattern.compile("[0-9]+[.]?[0-9]?");
		Matcher am = ap.matcher(amount);
		if (!am.matches() || Float.parseFloat(amount) == 0)
			return;
		
		this.game.bevToPour(bevID, Float.parseFloat(amount));
		this.bevList.bevVolChanged(bevID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getFrmCodindrinkin().setResizable(false);
		getFrmCodindrinkin().setTitle("Codin&Drinkin");
		getFrmCodindrinkin().setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		getFrmCodindrinkin().setMinimumSize(new Dimension(800, 600));
		getFrmCodindrinkin().setPreferredSize(new Dimension(800, 600));
		getFrmCodindrinkin().setBounds(100, 100, 450, 300);
		getFrmCodindrinkin().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		getFrmCodindrinkin().setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.setAction(newGameAction);
		mnGame.add(mntmNewGame);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAction(exitAction);
		mnGame.add(mntmExit);
		
		JMenu mnBeverage = new JMenu("Beverage");
		menuBar.add(mnBeverage);
		
		mntmAddBeverage = new JMenuItem("Add beverage");
		mntmAddBeverage.setAction(addBevAction);
		mntmAddBeverage.setEnabled(false);
		mnBeverage.add(mntmAddBeverage);
		
		JMenu mnTask = new JMenu("Task");
		menuBar.add(mnTask);
		
		mntmLoadNewTask = new JMenuItem("Loads new task");
		mntmLoadNewTask.setAction(this.loadTaskAction);
		mntmLoadNewTask.setEnabled(false);
		mnTask.add(mntmLoadNewTask);
		
		mntmEndCurrentTask = new JMenuItem("Ends current task");
		mntmEndCurrentTask.setEnabled(false);
		mnTask.add(mntmEndCurrentTask);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUsersManual = new JMenuItem("User's manual");
		mnHelp.add(mntmUsersManual);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getFrmCodindrinkin().getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("DesktopIcon.border"));
		panel_2.setBackground(SystemColor.inactiveCaption);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 7;
		gbc_panel_2.gridwidth = 16;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		getFrmCodindrinkin().getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_5.setMinimumSize(new Dimension(120, 10));
		panel_5.setPreferredSize(new Dimension(140, 0));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_2.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				RowSpec.decode("default:grow"),}));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setMaximumSize(new Dimension(10, 14));
		lblName.setPreferredSize(new Dimension(40, 14));
		lblName.setMinimumSize(new Dimension(20, 14));
		panel_5.add(lblName, "2, 2");
		
		namelbl = new JLabel("-");
		namelbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		namelbl.setMinimumSize(new Dimension(80, 14));
		namelbl.setPreferredSize(new Dimension(10, 14));
		panel_5.add(namelbl, "4, 2, 7, 1");
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSex.setPreferredSize(new Dimension(20, 14));
		lblSex.setMaximumSize(new Dimension(10, 14));
		lblSex.setMinimumSize(new Dimension(20, 14));
		panel_5.add(lblSex, "2, 4");
		
		sexlbl = new JLabel("-");
		sexlbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		sexlbl.setPreferredSize(new Dimension(10, 14));
		sexlbl.setMinimumSize(new Dimension(80, 14));
		panel_5.add(sexlbl, "4, 4, 7, 1");
		
		JLabel lblBloodalccontent = new JLabel("BloodAlcContent:");
		lblBloodalccontent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBloodalccontent.setMaximumSize(new Dimension(200, 14));
		lblBloodalccontent.setMinimumSize(new Dimension(60, 14));
		lblBloodalccontent.setPreferredSize(new Dimension(120, 14));
		panel_5.add(lblBloodalccontent, "2, 6, 5, 1");
		
		bloodAlcContentlbl = new JLabel("-");
		bloodAlcContentlbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_5.add(bloodAlcContentlbl, "8, 6");
		
		JLabel label_3 = new JLabel("\u2030");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setPreferredSize(new Dimension(8, 14));
		label_3.setMinimumSize(new Dimension(8, 14));
		panel_5.add(label_3, "10, 6");
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.inactiveCaption);
		panel_7.setPreferredSize(new Dimension(205, 60));
		panel_7.setMinimumSize(new Dimension(220, 50));
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panel_2.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("Current task:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_7.add(lblNewLabel_1, "2, 2");
		
		this.taskName = new JLabel("-");
		this.taskName.setPreferredSize(new Dimension(150, 14));
		this.taskName.setName("");
		this.taskName.setMaximumSize(new Dimension(130, 14));
		this.taskName.setMinimumSize(new Dimension(120, 14));
		this.taskName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_7.add(this.taskName, "4, 2, 5, 1");
		
		JLabel lblNewLabel_2 = new JLabel("Task ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_7.add(lblNewLabel_2, "2, 4");
		
		this.taskID = new JLabel("-");
		this.taskID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.taskID.setMaximumSize(new Dimension(100, 14));
		this.taskID.setPreferredSize(new Dimension(100, 14));
		this.taskID.setMinimumSize(new Dimension(25, 14));
		panel_7.add(this.taskID, "4, 4, default, center");
		
		JLabel lblPretaskId = new JLabel("PreTask ID:");
		lblPretaskId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_7.add(lblPretaskId, "2, 6");
		
		this.preTaskID = new JLabel("-");
		this.preTaskID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.preTaskID.setMaximumSize(new Dimension(100, 14));
		this.preTaskID.setPreferredSize(new Dimension(100, 14));
		panel_7.add(this.preTaskID, "4, 6");
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(SystemColor.inactiveCaption);
		panel_8.setPreferredSize(new Dimension(155, 10));
		panel_8.setMinimumSize(new Dimension(120, 10));
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 2;
		gbc_panel_8.gridy = 0;
		panel_2.add(panel_8, gbc_panel_8);
		panel_8.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_8.add(lblTime, "2, 2");
		
		this.ellapsedTime = new JLabel("-");
		this.ellapsedTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_8.add(this.ellapsedTime, "4, 2");
		
		JLabel label = new JLabel("/");
		panel_8.add(label, "6, 2");
		
		this.deadline = new JLabel("-");
		panel_8.add(this.deadline, "8, 2");
		
		JLabel lblSent = new JLabel("Attempt:");
		lblSent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_8.add(lblSent, "2, 4");
		
		this.solutionsSent = new JLabel("-");
		this.solutionsSent.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_8.add(this.solutionsSent, "4, 4");
		
		JLabel label_1 = new JLabel("/");
		panel_8.add(label_1, "6, 4");
		
		this.solutionsPermited = new JLabel("-");
		panel_8.add(this.solutionsPermited, "8, 4");
		
		this.outcome = new JLabel("-");
		this.outcome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.outcome.setPreferredSize(new Dimension(80, 14));
		this.outcome.setMaximumSize(new Dimension(100, 14));
		panel_8.add(this.outcome, "2, 6, 7, 1");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(2, 45));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_2.add(scrollPane, gbc_scrollPane);
		
		this.taskDescription = new JTextPane();
		this.taskDescription.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.taskDescription.setBackground(SystemColor.inactiveCaption);
		this.taskDescription.setMinimumSize(new Dimension(6, 200));
		this.taskDescription.setPreferredSize(new Dimension(6, 220));
		this.taskDescription.setEditable(false);
		this.taskDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.taskDescription.setText("Task description: -");
		scrollPane.setViewportView(this.taskDescription);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150, 10));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.gridheight = 19;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 16;
		gbc_panel.gridy = 0;
		getFrmCodindrinkin().getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 150));
		panel_1.setBackground(SystemColor.desktop);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9, BorderLayout.NORTH);
		
		JLabel lblBeverages = new JLabel("Beverages");
		lblBeverages.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_9.add(lblBeverages);
		
		alcLeft = new JLabel("0");
		alcLeft.setFont(new Font("Tahoma", Font.BOLD, 11));
		alcLeft.setForeground(Color.RED);
		panel_9.add(alcLeft);
		
		JLabel lblLeft = new JLabel("cl alc left to drink");
		lblLeft.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_9.add(lblLeft);
		
		/// BeverageList
		bevList = new BeverageList(this);
		bevList.setBackground(SystemColor.inactiveCaption);
		panel_1.add(bevList, BorderLayout.CENTER);
		GridBagLayout gbl_bevPanel = new GridBagLayout();
		gbl_bevPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_bevPanel.rowHeights = new int[]{0, 0};
		gbl_bevPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bevPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bevList.setLayout(gbl_bevPanel);
		
		
		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(10, 380));
		panel.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10, BorderLayout.NORTH);
		
		JLabel lblSolutions = new JLabel("Solutions");
		lblSolutions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_10.add(lblSolutions);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 2));
		panel_11.setPreferredSize(new Dimension(10, 100));
		panel_11.setBackground(SystemColor.inactiveCaption);
		panel_6.add(panel_11, BorderLayout.SOUTH);
		panel_11.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTimeLeft = new JLabel("Time left:");
		lblTimeLeft.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_11.add(lblTimeLeft, "2, 2");
		
		this.timeLeft = new JLabel("-");
		this.timeLeft.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_11.add(this.timeLeft, "4, 2");
		
		JLabel lblRateOf = new JLabel("Rate of right solutions:");
		lblRateOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_11.add(lblRateOf, "2, 4");
		
		this.rateOfSolutions = new JLabel("-");
		this.rateOfSolutions.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_11.add(this.rateOfSolutions, "4, 4, right, default");
		
		JLabel label_5 = new JLabel("%");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_11.add(label_5, "6, 4");
		
		JLabel lblConsumedAlcohol = new JLabel("Consumed alcohol:");
		lblConsumedAlcohol.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_11.add(lblConsumedAlcohol, "2, 6");
		
		this.consumedAlc = new JLabel("-");
		this.consumedAlc.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_11.add(this.consumedAlc, "4, 6");
		
		JLabel lblDl_1 = new JLabel("dl");
		lblDl_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_11.add(lblDl_1, "6, 6");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBackground(SystemColor.desktop);
		scrollPane_2.setPreferredSize(new Dimension(228, 2));
		scrollPane_2.setMaximumSize(new Dimension(150, 32767));
		panel_6.add(scrollPane_2, BorderLayout.WEST);
		
		solutionsTable = new JTable();
		solutionsTable.setBackground(SystemColor.desktop);
		solutionsTable.setModel(this.solutionsData);
		solutionsTable.getColumnModel().getColumn(0).setResizable(false);
		solutionsTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		solutionsTable.getColumnModel().getColumn(0).setMinWidth(11);
		solutionsTable.getColumnModel().getColumn(1).setResizable(false);
		solutionsTable.getColumnModel().getColumn(1).setPreferredWidth(45);
		solutionsTable.getColumnModel().getColumn(2).setResizable(false);
		solutionsTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		solutionsTable.getColumnModel().getColumn(3).setResizable(false);
		solutionsTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		scrollPane_2.setViewportView(solutionsTable);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 30));
		panel_3.setMinimumSize(new Dimension(10, 20));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 16;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 7;
		getFrmCodindrinkin().getContentPane().add(panel_3, gbc_panel_3);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),}));
		
		JLabel lblProgrammingLanguage = new JLabel("Programming language:");
		lblProgrammingLanguage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_3.add(lblProgrammingLanguage, "2, 2, right, default");
		
		langChoose = new JComboBox();
		langChoose.setEnabled(false);
		langChoose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		langChoose.setModel(new DefaultComboBoxModel(new String[] {"C", "C++", "Java"}));
		langChoose.setMinimumSize(new Dimension(200, 10));
		langChoose.setPreferredSize(new Dimension(80, 10));
		panel_3.add(langChoose, "4, 2, fill, fill");
		
		code = new JEditorPane();
		code.setEnabled(false);
		code.setBorder(new LineBorder(new Color(0, 0, 0)));
		code.setPreferredSize(new Dimension(106, 280));
		code.setMinimumSize(new Dimension(6, 220));
		code.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_code = new GridBagConstraints();
		gbc_code.gridwidth = 16;
		gbc_code.gridheight = 10;
		gbc_code.insets = new Insets(0, 0, 5, 5);
		gbc_code.fill = GridBagConstraints.BOTH;
		gbc_code.gridx = 0;
		gbc_code.gridy = 8;
		getFrmCodindrinkin().getContentPane().add(code, gbc_code);
		
		JPanel panel_4 = new JPanel();
		panel_4.setMinimumSize(new Dimension(200, 40));
		panel_4.setPreferredSize(new Dimension(250, 40));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.EAST;
		gbc_panel_4.gridwidth = 16;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 18;
		getFrmCodindrinkin().getContentPane().add(panel_4, gbc_panel_4);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnGiveup = new JButton("GiveUp");
		btnGiveup.setEnabled(false);
		btnGiveup.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(btnGiveup, "10, 2");
		
		btnSend = new JButton("Evaluate");
		btnSend.setEnabled(false);
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(btnSend, "12, 2, right, default");
		
		// set window visible
		getFrmCodindrinkin().setVisible(true);
	}

	public Dimension getFramePreferredSize() {
		return getFrmCodindrinkin().getPreferredSize();
	}
	public void setFramePreferredSize(Dimension preferredSize) {
		getFrmCodindrinkin().setPreferredSize(preferredSize);
	}
	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void setFrmCodindrinkin(JFrame frmCodindrinkin) {
		this.frmCodindrinkin = frmCodindrinkin;
	}

	public JFrame getFrmCodindrinkin() {
		return frmCodindrinkin;
	}
	
	private class NewGameAction extends AbstractAction {
		
		private static final long serialVersionUID = -2173983948654467929L;
		
		MainInterfaceForDialogs main;
		
		public NewGameAction(MainInterfaceForDialogs main) {
			this.main = main;
			
			putValue(NAME, "New Game");
			putValue(SHORT_DESCRIPTION, "Starts a new game");
		}
		public void actionPerformed(ActionEvent e) {
			NewGameDialog ngd = new NewGameDialog(this.main);
			ngd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			ngd.setVisible(true);
			
			main.setNewGameDialog(ngd);
		}
	}
	private class ExitAction extends AbstractAction {
		private static final long serialVersionUID = -1678701054654365490L;
		
		private JFrame frame;
		
		public ExitAction(JFrame frame) {
			this.frame = frame;
			
			putValue(NAME, "Exit");
		}
		
		public void actionPerformed(ActionEvent e) {
			this.frame.dispose();
		}
	}
	private class AddBevAction extends AbstractAction {
		private static final long serialVersionUID = 8189324588774534630L;
		
		MainInterfaceForDialogs main;
		
		public AddBevAction(MainInterfaceForDialogs main) {
			this.main = main;
			
			putValue(NAME, "Add beverage");
			putValue(SHORT_DESCRIPTION, "Adds a drink to the repertoire");
		}
		public void actionPerformed(ActionEvent arg0) {
			AddBevDialog abd = new AddBevDialog(this.main);
			abd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			abd.setVisible(true);
			
			main.setAddBevDialog(abd);
		}
	}
	private class LoadTaskAction extends AbstractAction {

		private static final long serialVersionUID = -525579174058082691L;
		
		MainInterfaceForDialogs main;

		public LoadTaskAction(MainInterfaceForDialogs main) {
			this.main = main;
			
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		public void actionPerformed(ActionEvent e) {
			int returnVal = fcf.fc.showOpenDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fcf.fc.getSelectedFile();
				if (selectedFile.canRead()) {
					main.loadTask(selectedFile);
					fcf.setSavedPath(selectedFile);
				}
			}
		}
	}
}
