/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski     
ISBN: 1-893115-78-X
Publisher: APress
*/

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;


public class TaxonTree extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
	
	private static TrnaDatabase db = new TrnaDatabase();
	

	public static String[] acodonTable = new String[] { "AAA", "AAC", "AAG",
			"AAT", "ACA", "ACC", "ACG", "ACT", "AGA", "AGC", "AGG", "AGT",
			"ATA", "ATC", "ATG", "ATT", "CAA", "CAC", "CAG", "CAT", "CCA",
			"CCC", "CCG", "CCT", "CGA", "CGC", "CGG", "CGT", "CTA", "CTC",
			"CTG", "CTT", "GAA", "GAC", "GAG", "GAT", "GCA", "GCC", "GCG",
			"GCT", "GGA", "GGC", "GGG", "GGT", "GTA", "GTC", "GTG", "GTT",
			"TAA", "TAC", "TAG", "TAT", "TCA", "TCC", "TCG", "TCT", "TGA",
			"TGC", "TGG", "TGT", "TTA", "TTC", "TTG", "TTT" };
	
	
	public TaxonTree() {
        super(new GridLayout(0,1));
        
	    DefaultMutableTreeNode top = new DefaultMutableTreeNode("Eukaryota");
	    JTree tree = new JTree(top);
	
	    // Generate tree from database.

		try {
			db.loadFromFile("sample.dat");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefaultMutableTreeNode kingdom = null;
		DefaultMutableTreeNode phylum = null;
		DefaultMutableTreeNode classs = null;
	    DefaultMutableTreeNode order = null;
	    DefaultMutableTreeNode family = null;
	    DefaultMutableTreeNode genus = null;        
	    	    
	    for (int i = 0; i < db.length(); i++) { // 5; i++) {
			Species n = db.listIterator();
			n.updateTables();
			kingdom = addChild(top, "Kingdom", n.kingdom);
			phylum = addChild(kingdom, "Phylum", n.phylum);
			classs = addChild(phylum, "Class", n.classs);
			order = addChild(classs, "Order", n.order);
			family = addChild(order, "Family", n.family);
			genus = addChild(family, "Genus", n.genus);
			addSpecies(genus, n);		
	    }
	    
	    //Expand tree.
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
	
	    
	    TreeCellRenderer renderer = new TaxonCellRenderer();
	    tree.setCellRenderer(renderer);
	    JScrollPane scrollPane = new JScrollPane(tree);
	    add(scrollPane);
	    
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox optionView = new JComboBox(acodonTable);
        optionView.setSelectedIndex(4);
        optionView.addActionListener(this);
        add(optionView);
	    
	}
	
    private DefaultMutableTreeNode addChild (DefaultMutableTreeNode parent, String rank, String name) {
       	if (name.isEmpty()) name = "Unknown";
       	
    	for (int i = 0; i < parent.getChildCount(); i++) {
    		Taxon current = (Taxon)(((DefaultMutableTreeNode) parent.getChildAt(i)).getUserObject());
	   		if (name.equals(current.name)){
	   			return (DefaultMutableTreeNode) parent.getChildAt(i);
	   		}
	   	}
	    DefaultMutableTreeNode child = new DefaultMutableTreeNode (new Taxon(rank, name));
	    parent.add(child);
		
		return child;
    }
    
    private DefaultMutableTreeNode addSpecies (DefaultMutableTreeNode parent, Species species) {
    	DefaultMutableTreeNode child = new DefaultMutableTreeNode (new Taxon (species));
		parent.add(child);
		DefaultMutableTreeNode rover = child;
		for (int i = child.getLevel(); i > 1; i--) {
			rover = (DefaultMutableTreeNode) rover.getParent();
			Taxon taxon = (Taxon)(rover.getUserObject());
			taxon.numTrna += species.numTrna();
			taxon.aveNumTrna = taxon.numTrna/rover.getChildCount();
		}
		
		return child;
    }

	
    private static void createAndShowGUI() throws Exception {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }
 
        //Create and set up the window.
        JFrame frame = new JFrame("GenTreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new TaxonTree());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String args[]) {
		//Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
	    
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


class TaxonCellRenderer implements TreeCellRenderer {
	  JLabel nameLabel;
	  JLabel trnaLabel;
	  JLabel trnaCountLabel;

	  JPanel renderer;

	  DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

	  Color backgroundSelectionColor;

	  Color backgroundNonSelectionColor;

	  public TaxonCellRenderer() {
	    renderer = new JPanel(new GridLayout(0, 1));
	    nameLabel = new JLabel(" ");
	    nameLabel.setForeground(Color.blue);
	    renderer.add(nameLabel);
	    trnaLabel = new JLabel(" ");
	    trnaLabel.setForeground(Color.blue);
	    renderer.add(trnaLabel);
	    trnaCountLabel = new JLabel(" ");
	    trnaCountLabel.setHorizontalAlignment(JLabel.RIGHT);
	    trnaCountLabel.setForeground(Color.red);
	    renderer.add(trnaCountLabel);
	    backgroundSelectionColor = defaultRenderer
	        .getBackgroundSelectionColor();
	    backgroundNonSelectionColor = defaultRenderer
	        .getBackgroundNonSelectionColor();
	  }
	  
	  
	  public Component getTreeCellRendererComponent(JTree tree, Object value,
		      boolean selected, boolean expanded, boolean leaf, int row,
		      boolean hasFocus) {
		    Component returnValue = null;
		    if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
		      Object userObject = ((DefaultMutableTreeNode) value)
		          .getUserObject();
		      if (userObject instanceof Taxon) {
		        Taxon taxon = (Taxon) userObject;
		        nameLabel.setText(taxon.getName());
//		        trnaLabel.setText(taxon.getAuthors());
//		        trnaCountLabel.setText("" + taxon.getPrice());
		        if (selected) {
		        	// TODO change next pane
		        	System.out.println(taxon.getName());
		        	for (int i = 0; i < taxon.aminoCount.length; i++) {
		        		System.out.println(taxon.aminoCount[i][0] + " " + taxon.aminoCount[i][1]);
		        		
		        	}

		        	for (int i = 0; i < taxon.codonCount.length; i++) {
		        		System.out.println(taxon.codonCount[i][0] + " " + taxon.codonCount[i][1]);
		        	}
		          renderer.setBackground(backgroundSelectionColor);
		        } else {
		          renderer.setBackground(backgroundNonSelectionColor);
		        }
		        renderer.setEnabled(tree.isEnabled());
		        returnValue = renderer;
		      }
		    }
		    if (returnValue == null) {
		      returnValue = defaultRenderer.getTreeCellRendererComponent(tree,
		          value, selected, expanded, leaf, row, hasFocus);
		    }
		    return returnValue;
	  }
}

class Taxon {
	  String name;
	  String rank;
	  int numTrna;
	  int aveNumTrna;
	  Object[][] codonCount;
	  Object[][] aminoCount;	  
	  
	  public Taxon (String rank, String name) {
	  	this.rank = rank;
	  	this.name = name;
	  }
	  
	  public Taxon (Species species) {
	  	this.rank = "Species";
	  	this.name = species.name;
	  	this.numTrna = species.numTrna();
	  	this.codonCount = species.acodonTableToArray();
	  	this.aminoCount = species.aacidTableToArray();
	  }
	  
	  public String toString () {
	  	String show = this.name + " (" + this.numTrna + ")";
	  	if (!rank.equals("Species")) {
	  		show += "Ave = " + this.aveNumTrna;
	  	}
	  	return show; //show;
	  }
	  
	  public String getName() {
	  	return this.name;
	  }
}

class NamedVector extends Vector {
  String name;

  public NamedVector(String name) {
    this.name = name;
  }

  public NamedVector(String name, Object elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

  public String toString() {
    return "[" + name + "]";
  }
}

           
         