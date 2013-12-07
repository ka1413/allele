
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Species implements Serializable{
	public String name, nickname;
	private int numTrna;
	public ArrayList<Trna> trnaList = new ArrayList<Trna>();
	Species next;
	//taxonomic heirarchy

	Species() {

	}

	//creates a duplicate node from n	
	Species(Species s) {
		this.name = s.name;
		this.nickname = s.nickname;
		this.numTrna = s.numTrna;
		this.trnaList = s.trnaList;
		
		this.next = s.next;
	}

	public int numberOfTrnas(){
		return numTrna;
	}

	public void userInput() {
		this.name = JOptionPane.showInputDialog("Enter Name");
		this.nickname = JOptionPane.showInputDialog("Enter Nickname");
		this.trnaList = null;
		this.next = null;
	}
	
	public String toString(){
		return this.name + " (" + this.nickname + ")";
	}
}