import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Pca {
	private static TrnaDatabase db;
	
	public Pca(TrnaDatabase gdb) {
		db = gdb;
	}
	
	public void exportMatrixAcodonLevelSpecies() throws IOException{
		FileWriter file = new FileWriter("pca_species.csv");
		BufferedWriter bf = new BufferedWriter(file);
		int i=0, j=0;
		
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
			for(j=0; j<spec.acodonTableFE.length - 1; j++){
				if(spec.acodonTableFE[j] != 0){
					bf.write(spec.acodonTableFE[j] + ",");
				} else {
					bf.write(",");
				}
			}
			bf.write(spec.acodonTableFE[i] + "\n");
			
			spec = db.listIterator();
		}
		
		bf.close();
	}
	
	public void exportMatrixAcodonLevelFamily() throws IOException{
		FileWriter file = new FileWriter("pca_family.csv");
		BufferedWriter bf = new BufferedWriter(file);
		int i=0, j=0;
		
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
		
		Collections.sort(existingFamily, String.CASE_INSENSITIVE_ORDER);
		
		for(i = 0; i<existingFamily.size(); i++){
			spec = db.listIterator();
			while(spec != null){
				if (existingFamily.get(i).equals(spec.genus)) {
					bf.write(spec.name + ",");
					spec.updateTables();
					for (j = 0; j < spec.acodonTableFE.length - 1; j++) {
						if(spec.acodonTableFE[j] != 0){
							bf.write(spec.acodonTableFE[j] + ",");
						} else {
							bf.write(",");
						}
					}
					bf.write(spec.acodonTableFE[j] + "\n");
				}
				spec = db.listIterator();
			}
		}
		
		bf.close();
	}
	
	public void exportMatrixAcodonLevelPhylum() throws IOException{
		FileWriter file = new FileWriter("pca_phylum.csv");
		BufferedWriter bf = new BufferedWriter(file);
		int i=0, j=0;
		
		bf.write(",");
		for(i=0; i<Species.acodonTable.length - 1; i++){
			bf.write(Species.acodonTable[i] + ",");
		}
		bf.write(Species.acodonTable[i] + "\n");
		
		Species spec = new Species();
		ArrayList<String> existingFamily = new ArrayList<String>();
		
		spec = db.listIterator();
		while(spec != null){
			if(existingFamily.contains(spec.classs)){
				
			} else {
				existingFamily.add(spec.classs);
			}
			
			spec = db.listIterator();
		}
		
		Collections.sort(existingFamily, String.CASE_INSENSITIVE_ORDER);
		
		Iterator<String> test = existingFamily.iterator();
		
		while(test.hasNext()){
			System.out.println(test.next());
		}
		
		for(i = 0; i<existingFamily.size(); i++){
			spec = db.listIterator();
			while(spec != null){
				if (existingFamily.get(i).equals(spec.classs)) {
					bf.write(spec.name + ",");
					spec.updateTables();
					for (j = 0; j < spec.acodonTableFE.length - 1; j++) {
						if(spec.acodonTableFE[j] != 0){
							bf.write(spec.acodonTableFE[j] + ",");
						} else {
							bf.write(",");
						}
					}
					bf.write(spec.acodonTableFE[j] + "\n");
				}
				spec = db.listIterator();
			}
		}
		
		bf.close();
	}
	
	public void exportMatrixAcodonLevelKingdom() throws IOException{
		FileWriter file = new FileWriter("pca_kingdom.csv");
		BufferedWriter bf = new BufferedWriter(file);
		int i=0, j=0;
		
		bf.write(",");
		for(i=0; i<Species.acodonTable.length - 1; i++){
			bf.write(Species.acodonTable[i] + ",");
		}
		bf.write(Species.acodonTable[i] + "\n");
		
		Species spec = new Species();
		ArrayList<String> existingFamily = new ArrayList<String>();
		
		spec = db.listIterator();
		while(spec != null){
			if(existingFamily.contains(spec.phylum)){
				
			} else {
				existingFamily.add(spec.phylum);
			}
			
			spec = db.listIterator();
		}
		
		Collections.sort(existingFamily, String.CASE_INSENSITIVE_ORDER);
		
		Iterator<String> test = existingFamily.iterator();
		
		while(test.hasNext()){
			System.out.println(test.next());
		}
		
		for(i = 0; i<existingFamily.size(); i++){
			spec = db.listIterator();
			while(spec != null){
				if (existingFamily.get(i).equals(spec.phylum)) {
					bf.write(spec.name + ",");
					spec.updateTables();
					for (j = 0; j < spec.acodonTableFE.length - 1; j++) {
						if(spec.acodonTableFE[j] != 0){
							bf.write(spec.acodonTableFE[j] + ",");
						} else {
							bf.write(",");
						}
					}
					bf.write(spec.acodonTableFE[j] + "\n");
				}
				spec = db.listIterator();
			}
		}
		
		bf.close();
	}
	
	public void exportMatrixAacidLevelKingdom() throws IOException{
		FileWriter file = new FileWriter("pca_kingdom_aacid.csv");
		BufferedWriter bf = new BufferedWriter(file);
		int i=0, j=0;
		
		bf.write(",");
		for(i=0; i<Species.aacidTable.length - 1; i++){
			bf.write(Species.aacidTable[i] + ",");
		}
		bf.write(Species.aacidTable[i] + "\n");
		
		Species spec = new Species();
		ArrayList<String> existingFamily = new ArrayList<String>();
		
		spec = db.listIterator();
		while(spec != null){
			if(existingFamily.contains(spec.phylum)){
				
			} else {
				existingFamily.add(spec.phylum);
			}
			
			spec = db.listIterator();
		}
		
		Collections.sort(existingFamily, String.CASE_INSENSITIVE_ORDER);
		
		Iterator<String> test = existingFamily.iterator();
		
		while(test.hasNext()){
			System.out.println(test.next());
		}
		
		for(i = 0; i<existingFamily.size(); i++){
			spec = db.listIterator();
			while(spec != null){
				if (existingFamily.get(i).equals(spec.phylum)) {
					bf.write(spec.name + ",");
					spec.updateTables();
					for (j = 0; j < spec.aacidTableFE.length - 1; j++) {
						if(spec.aacidTableFE[j] != 0){
							bf.write(spec.aacidTableFE[j] + ",");
						} else {
							bf.write(",");
						}
					}
					bf.write(spec.aacidTableFE[j] + "\n");
				}
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
		
		pca.exportMatrixAacidLevelKingdom();
	}
}