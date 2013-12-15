import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Species implements Serializable {
	public String name, nickname, kingdom, phylum, classs, order, family, genus;
	private int numTrna;
	public ArrayList<Trna> trnaList;
	Species next;
	
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

	public int numberOfTrnas() {
		return numTrna;
	}

	public void userInput() {
		this.name = JOptionPane.showInputDialog("Enter Name");
		this.nickname = JOptionPane.showInputDialog("Enter Nickname");
		this.kingdom = JOptionPane.showInputDialog("Enter Kingdom");
		this.phylum = JOptionPane.showInputDialog("Enter Phylum");
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

	public String toString() {
		return this.name + " (" + this.nickname + ")";
	}
}