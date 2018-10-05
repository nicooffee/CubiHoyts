package utilidades;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JScrollPaneCellRenderer implements TableCellRenderer{
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
	
		// Devolvemos el botón tal cual
		if (value instanceof JScrollPane)
			return (JScrollPane) value;		
		return null;
	}
}
