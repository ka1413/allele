import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class MainDemo extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JSplitPane splitPane;
	private JTable table1, table2;
	private JScrollPane scrollPane1, scrollPane2;
	
	private static final long serialVersionUID = 1L;

	public MainDemo() {
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

		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		
		table1 = new JTable(data, columnNames) {
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		
		table2 = new JTable(data, columnNames){
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		
		table1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table2.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		scrollPane1 = new JScrollPane(table1);
		scrollPane2 = new JScrollPane(table2);
		
		// Create a split pane with the two scroll panes in it.
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane1, scrollPane2);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 100);
		scrollPane1.setMinimumSize(minimumSize);
		scrollPane2.setMinimumSize(minimumSize);
		
		this.add(splitPane);

		this.setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent x) {
		System.out.println("Item clicked: " + x.getText());
	}

	public static void main(String args[]) {
		MainDemo w = new MainDemo();
		w.setVisible(true);
	}
}
