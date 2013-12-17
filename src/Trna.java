import java.io.Serializable;


public class Trna implements Serializable {
	public String name, acodon, isotype, sequence;
	public Float fenergy;
	
	private static final long serialVersionUID = 1L;
	
	public Trna(){
		
	}
	
	public Trna(String name, String acodon, String isotype, String sequence, Float fenergy){
		this.name = name;
		this.acodon = acodon;
		this.isotype = isotype;
		this.sequence = sequence;
		this.fenergy = fenergy;
	}
	
	public Trna(Trna t){
		this.name = t.name;
		this.acodon = t.acodon;
		this.isotype = t.isotype;
		this.sequence = t.sequence;
		this.fenergy = t.fenergy;
	}
	
	public String toString(){
		
		return this.name + " " + this.acodon + " " + this.isotype + " " + this.fenergy.toString() + " " + this.sequence;
	}
}