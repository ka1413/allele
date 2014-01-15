
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * This application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
 
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
 
import java.awt.*;
import java.awt.event.*;

 
public class GenTreeDemo extends JPanel
					  implements ActionListener, TreeSelectionListener {
	private JTree tree;
    private static boolean DEBUG = false;
    
	private static TrnaDatabase db = new TrnaDatabase();
 
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
     
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    
    JScrollPane treeView = null;

	public static String[] acodonTable = new String[] { "AAA", "AAC", "AAG",
			"AAT", "ACA", "ACC", "ACG", "ACT", "AGA", "AGC", "AGG", "AGT",
			"ATA", "ATC", "ATG", "ATT", "CAA", "CAC", "CAG", "CAT", "CCA",
			"CCC", "CCG", "CCT", "CGA", "CGC", "CGG", "CGT", "CTA", "CTC",
			"CTG", "CTT", "GAA", "GAC", "GAG", "GAT", "GCA", "GCC", "GCG",
			"GCT", "GGA", "GGC", "GGG", "GGT", "GTA", "GTC", "GTG", "GTT",
			"TAA", "TAC", "TAG", "TAT", "TCA", "TCC", "TCG", "TCT", "TGA",
			"TGC", "TGG", "TGT", "TTA", "TTC", "TTG", "TTT" };
 
    @SuppressWarnings("rawtypes")
	public GenTreeDemo() throws Exception {
        super(new GridLayout(1,0));
        
        generateTree(0);
        
        @SuppressWarnings("unchecked")
		JComboBox optionView = new JComboBox(acodonTable);
        optionView.setSelectedIndex(4);
        optionView.addActionListener(this);
 
        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(optionView);
        splitPane.setBottomComponent(treeView);
 
        Dimension minimumSize = new Dimension(100, 50);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));
 
        //Add the split pane to this panel.
        add(splitPane);
    }
    
    private void generateTree (int codon) throws Exception {
        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("Eukaryota");
        createNodes(top, codon);
 
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
 
        //Expand tree.
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}

        
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
 
        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }
 
        treeView = new JScrollPane(tree);
    }
    
    public void actionPerformed(ActionEvent e) {
    	@SuppressWarnings("rawtypes")
		int codon = ((JComboBox) e.getSource()).getSelectedIndex();
        try {
			generateTree(codon);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();
 
        if (node == null) return;
 
        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            NodeInfo node1 = (NodeInfo)nodeInfo;
            if (DEBUG) {
                System.out.print(node1.name + ":  \n    ");
            }
        } else {

        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }
 
    private class NodeInfo {
        public String rank;
        public String name;
        public int numTrna;
        public int aveNumTrna;
        public int codonCount[] = new int[acodonTable.length];
        
        public NodeInfo (String rank, String name) {
        	this.rank = rank;
        	this.name = name;
        }
        
        public NodeInfo (String rank, String name, int numTrna) {
        	this.rank = rank;
        	this.name = name;
        	this.numTrna = numTrna;
        }
        
        public String toString () {
        	String show = this.name + " (" + this.numTrna + ")";
        	if (!rank.equals("Species")) {
        		show += "Ave = " + this.aveNumTrna;
        	}
        	return show; //show;
        }
    }
 
    private void createNodes(DefaultMutableTreeNode top, int codon) throws Exception {
		db.loadFromFile("sample.dat");
		
		DefaultMutableTreeNode kingdom = null;
		DefaultMutableTreeNode phylum = null;
		DefaultMutableTreeNode classs = null;
        DefaultMutableTreeNode order = null;
        DefaultMutableTreeNode family = null;
        DefaultMutableTreeNode genus = null;
        DefaultMutableTreeNode species = null;
        
        
        for (int i = 0; i < db.length(); i++) { // 5; i++) {
			Species n = db.listIterator();
			kingdom = addChild(top, "Kingdom", n.kingdom);
			phylum = addChild(kingdom, "Phylum", n.phylum);
			classs = addChild(phylum, "Class", n.classs);
			order = addChild(classs, "Order", n.order);
			family = addChild(order, "Family", n.family);
			genus = addChild(family, "Genus", n.genus);
			species = addSpecies(genus, n);			
        }
    }
         
    private DefaultMutableTreeNode addChild (DefaultMutableTreeNode parent, String rank, String name) {
       	if (name.isEmpty()) name = "Unknown";
       	
    	for (int i = 0; i < parent.getChildCount(); i++) {
    		NodeInfo current = (NodeInfo)(((DefaultMutableTreeNode) parent.getChildAt(i)).getUserObject());
	   		if (name.equals(current.name)){
	   			return (DefaultMutableTreeNode) parent.getChildAt(i);
	   		}
	   	}
	    DefaultMutableTreeNode child = new DefaultMutableTreeNode (new NodeInfo(rank, name));
	    parent.add(child);
		
		return child;
    }
    
    private DefaultMutableTreeNode addSpecies (DefaultMutableTreeNode parent, Species species) {
    	DefaultMutableTreeNode child = new DefaultMutableTreeNode (new NodeInfo ("Species", species.name, species.numTrna()));
		parent.add(child);
		DefaultMutableTreeNode rover = child;
		for (int i = child.getLevel(); i > 1; i--) {
			rover = (DefaultMutableTreeNode) rover.getParent();
    		NodeInfo current = (NodeInfo)(rover.getUserObject());
    		current.numTrna += species.numTrna();
    		current.aveNumTrna = current.numTrna/rover.getChildCount();
		}
		
		return child;
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
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
        frame.add(new GenTreeDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) throws Exception {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowGUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

}
