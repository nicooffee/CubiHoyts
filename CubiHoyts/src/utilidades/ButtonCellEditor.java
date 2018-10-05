package utilidades;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellEditor;

public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 1L;
	/** Componente que estamos editando. */
	private Component currentValue;
 
	@Override
	public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, final int row, int column) {
		JButton button = null;
		JTextArea combo= null;
		JScrollPane pane=null;
		if (value instanceof JButton) {
			button = (JButton) value;
			currentValue = button;
			return button;
		}else if(value instanceof JTextArea) {
			combo = (JTextArea) value;
			currentValue = combo;
			return combo;
		}else if(value instanceof JScrollPane) {
			pane=(JScrollPane) value;
			currentValue=pane;
			return pane;
		}
		return null;
		
		
	}
	@Override
 	public Object getCellEditorValue() {
	 	return currentValue;
 	}
}