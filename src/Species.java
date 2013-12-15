import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Species implements Serializable {
	public String name, nickname, kingdom, family, phylum;
	private int numTrna;
	public ArrayList<Trna> trnaList;
	Species next;
	
	private static final long serialVersionUID = 1L;

	// taxonomic heirarchy

	Species() {

	}

	// creates a duplicate node from n
	Species(Species s) {
		this.name = s.name;
		this.nickname = s.nickname;
		this.numTrna = s.numTrna;
		this.trnaList = s.trnaList;
		this.kingdom = s.kingdom;
		this.family = s.family;
		this.phylum = s.phylum;
		this.next = s.next;
	}

	public int numberOfTrnas() {
		return numTrna;
	}

	public void userInput() {
		this.name = JOptionPane.showInputDialog("Enter Name");
		this.nickname = JOptionPane.showInputDialog("Enter Nickname");
		this.kingdom = JOptionPane.showInputDialog("Enter Kingdom");
		this.family = JOptionPane.showInputDialog("Enter Family");
		this.phylum = JOptionPane.showInputDialog("Enter Phylum");
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