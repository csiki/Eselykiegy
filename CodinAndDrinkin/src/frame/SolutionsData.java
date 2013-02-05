package frame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import library.SolutionInterface;

public class SolutionsData extends AbstractTableModel {
	
	private static final long serialVersionUID = 3345494844960327520L;
	
	List<SolutionInterface> solutions = new ArrayList<SolutionInterface>();
	
	String[] columnNames = {"Done", "Time used", "Attempt", "Lang."};
	
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
		// TODO Auto-generated method stub
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
				return Integer.toString(secs / 60) + ":" + Integer.toString(secs % 60);
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
