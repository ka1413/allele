import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainDemo extends JFrame implements ActionListener,
		ListSelectionListener, MouseListener {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JSplitPane splitPane;
	private JTable table1, table2;
	private JScrollPane scrollPane1, scrollPane2;
	private JPanel panel;
	private static TrnaDatabase db = new TrnaDatabase();;

	private static final long serialVersionUID = 1L;

	public MainDemo() throws Exception {
		db.loadFromFile("sample.dat");

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
				"Save/Load the database of tRNAs");
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("Load database", KeyEvent.VK_L);
		menu.getAccessibleContext().setAccessibleDescription(
				"Load database other than the default");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save", KeyEvent.VK_S);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save As...", KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
				"Save in a different filename");
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menu.add(menuItem);

		// Build second menu in the menu bar.
		menu = new JMenu("Functions");
		menu.setMnemonic(KeyEvent.VK_U);
		menu.getAccessibleContext().setAccessibleDescription(
				"Use different functions provided by the application");
		menuBar.add(menu);

		menuItem = new JMenuItem("Phylogegnetic Tree", KeyEvent.VK_T);
		menu.add(menuItem);

		menuItem = new JMenuItem("Neural Network", KeyEvent.VK_N);
		menu.add(menuItem);

		this.setJMenuBar(menuBar);

		String[] columnNames = { "Name", "Known as", "Kingdom", "Phylum",
				"Class", "Order", "Family", "Genus", "Number of tRNAs" };

		Object[][] data = db.toArray();

		table1 = new JTable(data, columnNames) {
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};

		columnNames = new String[] { "Name", "Anti-codon", "Isotype",
				"Free Energy" };

		data = db.getSelectedNode(44).toArray();

		table2 = new JTable(data, columnNames) {
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};

		// table1.getSelectionModel().addListSelectionListener(this);
		// table2.getSelectionModel().addListSelectionListener(this);

		table1.addMouseListener(this);
		table2.addMouseListener(this);

		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane1 = new JScrollPane(table1);
		scrollPane2 = new JScrollPane(table2);

		// Create a split pane with the two scroll panes in it.
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane1,
				scrollPane2);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(300);

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 100);
		scrollPane1.setMinimumSize(minimumSize);
		scrollPane2.setMinimumSize(minimumSize);

		panel = new JPanel();
		panel.add(splitPane);
		this.add(panel);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.setSize(500, 500);
		this.pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent x) {
		System.out.println("Item clicked: " + x.getActionCommand());
	}

	public void valueChanged(ListSelectionEvent x) {
		// ListSelectionModel lsm = (ListSelectionModel) x.getSource();
		// System.out.println("Item clicked: " + lsm.toString());
		if (table2.getSelectedRow() > -1 && table2.getSelectedRow() > -1) {
			// print first column value from selected row
			if (!x.getValueIsAdjusting()) {
				System.out.println("--table2 is selected");
				table1.clearSelection();
			}
		} else if (table1.getSelectedRow() > -1) {
			if (!x.getValueIsAdjusting()) {
				System.out.println("table1 is selected");
				table2.clearSelection();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		JTable table = (JTable) me.getSource();

		// Point p = me.getPoint();
		// int row = table.rowAtPoint(p);

		// if (me.getClickCount() == 2) {
		// System.out.println("double-clicked");
		// }

		if (table1.equals(table)) {
			System.out.println("clicked table1");

			Point p = me.getPoint();
			int row = table.rowAtPoint(p);
			System.out.println(row);
			// if (table2.getSelectedRow() > -1){
			// table2.clearSelection();
			// }

			this.remove(panel);
			panel.removeAll();
			// jpan.remove(splitPane);
			// splitPane.remove(scrollPane2);
			String[] columnNames = { "Name", "Anti-codon", "Isotype",
					"Free Energy" };
			Object[][] data = db.getSelectedNode(row).toArray();
			JTable table3 = new JTable(data, columnNames);
			// scrollPane2.remove(table2);
			panel = new JPanel();
			// scrollPane2.remove(table2);
			// scrollPane2.add(table3); ->wala to dapat new JScrollPane :)
			/*
			 * Instead na scrollPane2.add(table#) scrollPane2 = new
			 * JScrollPane(table#) tapos re-declare mo yugn values nung talbe
			 */
			scrollPane2 = new JScrollPane(table3);
			int divider = splitPane.getDividerLocation();
			splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					scrollPane1, scrollPane2);
			splitPane.setDividerLocation(divider);
			panel.add(splitPane);
			this.add(panel);
			this.revalidate();
			this.repaint();
		}

		if (table2.equals(table)) {
			System.out.println("clicked table2");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String args[]) throws Exception {
		MainDemo w;
		try {
			w = new MainDemo();
			w.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
