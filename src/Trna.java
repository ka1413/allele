import java.io.Serializable;


public class Trna implements Serializable {
	public String name, acodon, aacid, sequence;
	public Float fenergy;
	
	private static final long serialVersionUID = 1L;
	
	public Trna(){
		
	}
	
	public Trna(String name, String acodon, String aacid, String sequence, Float fenergy){
		this.name = name;
		this.acodon = acodon;
		this.aacid = aacid;
		this.sequence = sequence;
		this.fenergy = fenergy;
	}
	
	public Trna(Trna t){
		this.name = t.name;
		this.acodon = t.acodon;
		this.aacid = t.aacid;
		this.sequence = t.sequence;
		this.fenergy = t.fenergy;
	}
	
	public String toString(){
		
		return this.name + " " + this.acodon + " " + this.aacid + " " + this.fenergy.toString() + " " + this.sequence;
	}
}