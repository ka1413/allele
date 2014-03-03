import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Species implements Serializable {
	public String name, nickname, domain, kingdom, phylum, classs, order, family, genus;
	public int numTrna;
	public ArrayList<Trna> trnaList;
	ArrayList<JCheckBox> jcb = new ArrayList<JCheckBox>();
	ArrayList<String> jcVal = new ArrayList<String>();
	public Species next;
	JFrame jf;
	Parser p;
	JTextField name1,known1,domain1,kingdom1,phylum1,clss1,ordr1,family1;
	public static String[] acodonTable = new String[] { "AAA", "AAC", "AAG", "AAT", "ACA", "ACC", "ACG",
											"ACT", "AGA", "AGC", "AGG", "AGT", "ATA", "ATC", "ATG", "ATT",
											"CAA", "CAC", "CAG", "CAT", "CCA", "CCC", "CCG", "CCT", "CGA",
											"CGC", "CGG", "CGT", "CTA", "CTC", "CTG", "CTT", "GAA", "GAC",
											"GAG", "GAT", "GCA", "GCC", "GCG", "GCT", "GGA", "GGC", "GGG",
											"GGT", "GTA", "GTC", "GTG", "GTT", "TAA", "TAC", "TAG", "TAT",
											"TCA", "TCC", "TCG", "TCT", "TGA", "TGC", "TGG", "TGT", "TTA",
											"TTC", "TTG", "TTT" };
	public static String[] aacidTable = new String[] { "Ala", "Cys", "Asp", "Glu", "Phe", "Gly", "His",
											"Ile", "Lys", "Leu", "Met", "Asn", "Pro", "Gln", "Arg",
											"Ser", "Thr", "Val", "Trp", "Tyr", "Stop" };
	public int[] remarks1 = new int[acodonTable.length], remarks2 = new int[aacidTable.length];
	public Float[] acodonTableFE = new Float[acodonTable.length], aacidTableFE = new Float[aacidTable.length];
	private static final long serialVersionUID = 1L;
	private static TrnaDatabase db = new TrnaDatabase();
	private static TrnaDatabase db2 = new TrnaDatabase();
	public String tempn,tempm,tempk,tempp,tempc,tempo,tempf,tempg;
	FileChooser fc;
	Species() {

	}

	Species(String name, String nickname, String domain, String kingdom, String phylum, String classs, String order, String family, String genus, ArrayList<Trna> trnaList, Species next) {
		this.name = name;
		this.nickname = nickname;
		this.trnaList = trnaList;
		this.numTrna = trnaList.size();

		this.domain = domain;
		this.kingdom = kingdom;
		this.phylum = phylum;
		this.classs = classs;
		this.order = order;
		this.family = family;
		this.genus = genus;

		this.next = next;
	}

	// creates a duplicate node from n
	Species(Species s) {
		this.name = s.name;
		this.nickname = s.nickname;
		this.numTrna = s.numTrna;
		this.trnaList = s.trnaList;

		this.domain = s.domain;
		this.kingdom = s.kingdom;
		this.phylum = s.phylum;
		this.classs = s.classs;
		this.order = s.order;
		this.family = s.family;
		this.genus = s.genus;

		this.next = s.next;
	}

	public int numTrna() {
		this.numTrna = trnaList.size();
		return trnaList.size();
	}

	public void userInput() {
		this.name = JOptionPane.showInputDialog("Enter Name");
		this.nickname = JOptionPane.showInputDialog("Enter Nickname");
		this.domain = JOptionPane.showInputDialog("Enter Domain");
		this.kingdom = JOptionPane.showInputDialog("Enter Kingdom");
		this.phylum = JOptionPane.showInputDialog("Enter Phylum");
		this.classs = JOptionPane.showInputDialog("Enter Class");
		this.order = JOptionPane.showInputDialog("Enter Order");
		this.family = JOptionPane.showInputDialog("Enter Family");
		this.genus = JOptionPane.showInputDialog("Enter Genus");
		this.trnaList = new ArrayList<Trna>();
		this.next = null;
	}
	public void deleteSpeciesForm(String[] arr,TrnaDatabase dbin){
	//public void deleteSpeciesForm(TrnaDatabase dbin){
		jf = new JFrame("Delete Species");
		db2 = new TrnaDatabase(dbin);
		JPanel jp = new JPanel();
		JButton jb = new JButton("Delete");
		JCheckBox check;
		int count;
		for(count=0;count<arr.length;count++){
			jcVal.add(arr[count]);
			check = new JCheckBox(arr[count]);
			jcb.add(check);
			jp.add(check);
		}
		jp.add(jb);
		jf.add(jp);
		jb.addActionListener(new ActionListener()	
		{
		public void actionPerformed(ActionEvent e)
		{	
		//Species spc = dbin.getByName(String name,TrnaDatabase dbin)
			int cnt = 0;
			ArrayList<Species> spec = new ArrayList<Species>();
			ArrayList<String> names = new ArrayList<String>();
			Species toDel = new Species();
			//Get Names of Nodes that were deleted
			//Convert to Species
			for(cnt = 0;cnt<jcb.size();cnt++){
				if (jcb.get(cnt).isSelected()) {
				   db2.delete(db2.getByName(jcVal.get(cnt)));
                }
			}
			try{
				db2.saveToFile("sample.dat");
			}catch(Exception ex){}
			jf.setVisible(false);
		}
		});
		jf.setSize(600,600);
		jf.setVisible(true);
		
	}
	public void assignValues(){
	try{
		//edit dis
		db.loadFromFile("sample.dat");
	}catch(Exception e){}
		Species spec = new Species();
		spec.name = tempn;
		spec.nickname = tempm;
		spec.domain = tempk;
		spec.kingdom = tempp;
		spec.phylum = tempc;
		spec.classs = tempo;
		spec.order = tempf;
		spec.family = tempg;
		spec.genus = tempg;
		spec.trnaList = new ArrayList<Trna>();
		spec.next = null;
		db.insert(spec);
		try{
		//Insert FileChooserHere
		fc = new FileChooser();
		String fname = fc.saveFileChooser();
		//p = new Parser("sample.dat");
		//	db = p.parseTrnaSorted();
		db.saveToFile(fname);
		} catch(Exception e){}
	}
	
	public void userInputForm(){
		Dimension d = new Dimension(150,150);
		jf = new JFrame("Add New Species");
		JPanel jp = new JPanel();
		JButton jb = new JButton("Submit");
		name1 = new JTextField("Name:");
		name1.setSize(d);
		known1 = new JTextField("Known As:");
		known1.setSize(d);
		domain1 = new JTextField("Domain:");
		domain1.setSize(d);
		kingdom1 = new JTextField("Kingdom:");
		kingdom1.setSize(d);
		phylum1 = new JTextField("Phylum:");
		phylum1.setSize(d);
		phylum1.add(new JLabel("Phylum:"));
		clss1 = new JTextField("Class:");
		clss1.setSize(d);
		clss1.add(new JLabel("Class:"));
		ordr1 = new JTextField("Order:");
		ordr1.setSize(d);
		family1 = new JTextField("Family:");
		family1.setSize(d);
		jp.add(new JLabel("Name:"));
		jp.add(name1);
		jp.add(new JLabel("Known As:"));
		jp.add(known1);
		jp.add(domain1);
		jp.add(kingdom1);
		jp.add(phylum1);
		jp.add(clss1);
		jp.add(ordr1);
		jp.add(family1);
		jp.add(jb);
		jf.add(jp);
		jf.setSize(450,450);
		jf.setVisible(true);
		jb.addActionListener(new ActionListener()	
		{
		public void actionPerformed(ActionEvent e)
		{	
			tempn = name1.getText().toString();
			tempm = known1.getText().toString();
			tempk = domain1.getText().toString();
			tempp = kingdom1.getText().toString();
			tempc = phylum1.getText().toString();
			tempo = clss1.getText().toString();
			tempf = ordr1.getText().toString();
			tempg = family1.getText().toString();
			jf.setVisible(false);
			assignValues();
			
		}
		});
		
		//return this;
		
	}
	public void addTrna(Trna t) {
		trnaList.add(t);
	}

	public String showTrnaList() {
		String temp = "";

		for (int i = 0; i < this.trnaList.size(); i++) {
			temp = temp + this.trnaList.get(i).toString() + "\n";
		}

		return temp;
	}

	public Object[][] toArray() {
		Object[][] data = new Object[this.trnaList.size()][4];

		for (int i = 0; i < this.trnaList.size(); i++) {
			data[i][0] = this.trnaList.get(i).name;
			data[i][1] = this.trnaList.get(i).acodon;
			data[i][2] = this.trnaList.get(i).aacid;
			data[i][3] = this.trnaList.get(i).fenergy;
		}

		return data;
	}

	public String toString() {
		return this.name + " (" + this.nickname + ")";
	}

	public void updateTables() {
		ArrayList<Trna> trnaList = new ArrayList<Trna>(this.trnaList), temp = new ArrayList<Trna>();
		Float avg;

		for (int i = 0; i < acodonTable.length; i++) {
			for (int j = 0; j < trnaList.size(); j++) {

				if (acodonTable[i].equals(trnaList.get(j).acodon)) {
					temp.add(trnaList.get(j));
				}
			}
			avg = 0f;
			if (temp.size() > 0) {

				for (int k = 0; k < temp.size(); k++) {
					avg = avg + temp.get(k).fenergy;
				}

				avg = avg / temp.size();
				this.remarks1[i] = temp.size();
				this.acodonTableFE[i] = avg;
			} else {
				this.acodonTableFE[i] = 0f;
				this.remarks1[i] = 0;
			}

			temp.clear();
		}

		for (int i = 0; i < aacidTable.length; i++) {
			for (int j = 0; j < trnaList.size(); j++) {

				if (aacidTable[i].equals(trnaList.get(j).aacid)) {
					temp.add(trnaList.get(j));
				}
			}
			avg = 0f;
			if (temp.size() > 0) {

				for (int k = 0; k < temp.size(); k++) {
					avg = avg + temp.get(k).fenergy;
				}

				avg = avg / temp.size();
				this.remarks2[i] = temp.size();
				this.aacidTableFE[i] = avg;
			} else {
				this.aacidTableFE[i] = 0f;
				this.remarks2[i] = 0;
			}

			temp.clear();
		}
	}

	public Object[][] acodonTableToArray() {
		Object[][] data = new Object[acodonTable.length][3];

		for (int i = 0; i < acodonTable.length; i++) {
			data[i][0] = acodonTable[i];
			data[i][1] = this.acodonTableFE[i];
			data[i][2] = this.remarks1[i];
		}

		return data;
	}

	public Object[][] aacidTableToArray() {
		Object[][] data = new Object[aacidTable.length][3];

		for (int i = 0; i < aacidTable.length; i++) {
			data[i][0] = aacidTable[i];
			data[i][1] = this.aacidTableFE[i];
			data[i][2] = this.remarks2[i];
		}

		return data;
	}

	public void initRemarks() {
		this.remarks1 = new int[64];
		this.remarks2 = new int[23];
	}
}