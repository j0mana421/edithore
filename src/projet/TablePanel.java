package projet;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 *
 */
public class TablePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	/**
	 * @param model
	 */
	public TablePanel(TableModel model) {
	    table = new JTable(model);
	    setLayout(new BorderLayout());
	    add(new JScrollPane(table), BorderLayout.CENTER);   
	}
	
}