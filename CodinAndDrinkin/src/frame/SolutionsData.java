package frame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import library.SolutionInterface;

/**
 * Table model for representing solution results in a JTable.
 * @author csiki
 *
 */
public class SolutionsData extends AbstractTableModel {
	
	private static final long serialVersionUID = 3345494844960327520L;
	
	List<SolutionInterface> solutions = new ArrayList<SolutionInterface>();
	
	String[] columnNames = {"Done", "Time used", "Attempt", "Lang."};
	
	/**
	 * Adds a new row to the table. Calls fireTableDataChanged() as well.
	 * @param solution to add
	 */
	public void addSolution(SolutionInterface solution) {
		solutions.add(solution);
		this.fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return this.solutions.size();
	}
	
	@Override
	public String getColumnName(int col) {
        return columnNames[col];
    }

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
			case 0: return solutions.get(row).isSolved();
			case 1:
				int secs = (int) (solutions.get(row).getTimeUsed() / 1000);
				String secDisplay = Integer.toString(secs % 60);
				String minDisplay = Integer.toString(secs / 60);
				if ((secs % 60) < 10)
					secDisplay = "0" + secDisplay;
				if ((secs / 60) < 10)
					minDisplay = "0" + minDisplay;
				return minDisplay + ":" + secDisplay;
			case 2: return solutions.get(row).getAttemptNum();
			case 3: return solutions.get(row).getLang();
		}
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
