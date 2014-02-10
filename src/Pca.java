import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Pca {
	private static TrnaDatabase db;
	
	public Pca(TrnaDatabase gdb) {
		db = gdb;
	}
	
	public void exportMatrixAcodonLevelSpecies() throws IOException{
		FileWriter file = new FileWriter("pca_species.csv");
		BufferedWriter bf = new BufferedWriter(file);
		int i=0;
		
		bf.write(",");
		for(i=0; i<Species.acodonTable.length - 1; i++){
			bf.write(Species.acodonTable[i] + ",");
		}
		bf.write(Species.acodonTable[i] + "\n");
		
		Species spec = new Species();
		
		spec = db.listIterator();
		while(spec != null){
			bf.write(spec.name + ",");
			spec.updateTables();
			for(i=0; i<spec.acodonTableFE.length - 1; i++){
				bf.write(spec.acodonTableFE[i] + ",");
			}
			bf.write(spec.acodonTableFE[i] + "\n");
			
			spec = db.listIterator();
		}
		
		bf.close();
	}
	
	public void exportMatrixAcodonLevelFamily() throws IOException{
		FileWriter file = new FileWriter("pca_family.csv");
		BufferedWriter bf = new BufferedWriter(file);
		int i=0;
		
		bf.write(",");
		for(i=0; i<Species.acodonTable.length - 1; i++){
			bf.write(Species.acodonTable[i] + ",");
		}
		bf.write(Species.acodonTable[i] + "\n");
		
		Species spec = new Species();
		ArrayList<String> existingFamily = new ArrayList<String>();
		
		spec = db.listIterator();
		while(spec != null){
			if(existingFamily.contains(spec.genus)){
				
			} else {
				existingFamily.add(spec.genus);
			}
			
			spec = db.listIterator();
		}
		
		for(i = 0; i<existingFamily.size(); i++){
			spec = db.listIterator();
			while(spec != null){
				//if(existingFamily.get(i).equals(spec.genus)){
					bf.write(spec.name + ",");
					spec.updateTables();
					for(i=0; i<spec.acodonTableFE.length - 1; i++){
						bf.write(spec.acodonTableFE[i] + ",");
					}
					bf.write(spec.acodonTableFE[i] + "\n");
				//}
					spec = db.listIterator();
			}
		}
		
		bf.close();
	}
	
	public static void main(String args[]) throws Exception {
		System.out.println("hello world");
		
		TrnaDatabase gdb = new TrnaDatabase();
		gdb.loadFromFile("sample.dat");
		
		Pca pca = new Pca(gdb);
		
		pca.exportMatrixAcodonLevelFamily();
	}
}