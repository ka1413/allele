import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Species implements Serializable {
	public String name, nickname, kingdom, phylum, classs, order, family, genus;
	public int numTrna;
	public Float[] acodonTableFE = new Float[64], aacidTableFE = new Float[23];
	public int[] remarks1 = new int[64], remarks2 = new int[23];
	public ArrayList<Trna> trnaList;
	public Species next;
	public static String[] acodonTable = new String[] { "AAA", "AAC", "AAG",
			"AAT", "ACA", "ACC", "ACG", "ACT", "AGA", "AGC", "AGG", "AGT",
			"ATA", "ATC", "ATG", "ATT", "CAA", "CAC", "CAG", "CAT", "CCA",
			"CCC", "CCG", "CCT", "CGA", "CGC", "CGG", "CGT", "CTA", "CTC",
			"CTG", "CTT", "GAA", "GAC", "GAG", "GAT", "GCA", "GCC", "GCG",
			"GCT", "GGA", "GGC", "GGG", "GGT", "GTA", "GTC", "GTG", "GTT",
			"TAA", "TAC", "TAG", "TAT", "TCA", "TCC", "TCG", "TCT", "TGA",
			"TGC", "TGG", "TGT", "TTA", "TTC", "TTG", "TTT" };
	public static String[] aacidTable = new String[] { "Ala", "Cys", "Asp",
			"Glu", "Phe", "Gly", "His", "Ile", "Lys", "Leu", "Met", "Asn",
			"Pyl", "Pro", "Gln", "Arg", "Ser", "Thr", "Sec", "Val", "Trp",
			"Tyr", "SeC(e)" };

	private static final long serialVersionUID = 1L;

	Species() {

	}
	
	Species(String name, String nickname, String kingdom, String phylum,
			String classs, String order, String family, String genus,
			ArrayList<Trna> trnaList, Species next) {
		this.name = name;
		this.nickname = nickname;
		this.trnaList = trnaList;
		this.numTrna = trnaList.size();

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
		
		this.kingdom = s.kingdom;
		this.phylum = s.phylum;
		this.classs = s.classs;
		this.order = s.order;
		this.family = s.family;
		this.genus = s.genus;
		
		this.next = s.next;
	}

	public int numTrna() {
		return trnaList.size();
	}

	public void userInput() {
		this.name = JOptionPane.showInputDialog("Enter Name");
		this.nickname = JOptionPane.showInputDialog("Enter Nickname");
		this.kingdom = JOptionPane.showInputDialog("Enter Kingdom");
		this.phylum = JOptionPane.showInputDialog("Enter Phylum");
		this.classs = JOptionPane.showInputDialog("Enter Class");
		this.order = JOptionPane.showInputDialog("Enter Order");
		this.family = JOptionPane.showInputDialog("Enter Family");
		this.genus = JOptionPane.showInputDialog("Enter Genus");
		this.trnaList = new ArrayList<Trna>();
		this.next = null;
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
		
		for(int i=0; i<this.trnaList.size(); i++){
			data[i][0] = this.trnaList.get(i).name;
			data[i][1] = this.trnaList.get(i).acodon;
			data[i][2] = this.trnaList.get(i).isotype;
			data[i][3] = this.trnaList.get(i).fenergy;
		}
		
		return data;
	}

	public String toString() {
		return this.name + " (" + this.nickname + ")";
	}
	
	public void updateTables(){
		ArrayList<Trna> trnaList = new ArrayList<Trna>(this.trnaList), temp = new ArrayList<Trna>();
		Float avg;
		
		for(int i=0; i<acodonTable.length; i++){
			for(int j=0; j<trnaList.size(); j++){
				
				if(acodonTable[i].equals(trnaList.get(j).acodon)){
					temp.add(trnaList.get(j));
				}
			}
			avg = 0f;
			if(temp.size() > 0){
				
				for(int k=0; k<temp.size(); k++){
					avg = avg + temp.get(k).fenergy;
				}
				
				avg = avg / temp.size();
				//this.remarks1[i] = temp.size();
				this.acodonTableFE[i] = avg;
			} else {
				this.acodonTableFE[i] = null;
			}
			
			temp.clear();
		}
		
		for(int i=0; i<aacidTable.length; i++){
			for(int j=0; j<trnaList.size(); j++){
				
				if(aacidTable[i].equals(trnaList.get(j).isotype)){
					temp.add(trnaList.get(j));
				}
			}
			avg = 0f;
			if(temp.size() > 0){
				
				for(int k=0; k<temp.size(); k++){
					avg = avg + temp.get(k).fenergy;
				}
				
				avg = avg / temp.size();
				//this.remarks2[i] = temp.size();
				this.aacidTableFE[i] = avg;
			} else {
				this.aacidTableFE[i] = null;
			}
			
			temp.clear();
		}
	}
	
	public Object[][] acodonTableToArray() {
		Object[][] data = new Object[acodonTable.length][3];
		
		for(int i=0; i<acodonTable.length; i++){
			data[i][0] = acodonTable[i];
			data[i][1] = this.acodonTableFE[i];
			//data[i][2] = this.remarks1[i];
		}
		
		return data;
	}
	
	public Object[][] aacidTableToArray() {
		Object[][] data = new Object[aacidTable.length][3];
		
		for(int i=0; i<aacidTable.length; i++){
			data[i][0] = aacidTable[i];
			data[i][1] = this.aacidTableFE[i];
			//data[i][2] = this.remarks2[i];
		}
		
		return data;
	}
	public void initRemarks(){
		this.remarks1 = new int[64]; this.remarks2 = new int[23];
	}
}