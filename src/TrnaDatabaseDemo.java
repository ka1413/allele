
import javax.swing.*;

class TrnaDatabaseDemo {
    public static void main(String args[]) throws Exception {
		TrnaDatabase db = new TrnaDatabase();
		Species n, m;
		boolean isSuccessful;
		
		String homeMenu[] = {"Basic Testing Demo", "File Handling", "Close"};
		while (true) {
			int choice = JOptionPane.showOptionDialog(
			null, "What do you want to do?", "TrnaDatabase",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.INFORMATION_MESSAGE,
			null, homeMenu, homeMenu[0]);

			switch(choice){
				case 0:
					String choices1[] = {"Insert", "Delete", "Replace", "Search", "Show List", "Back"};
					while (true) {
						int choice1 = JOptionPane.showOptionDialog(
						null, "What do you want to do?", "Basic Testing Demo",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null, choices1, choices1[0]);

						switch(choice1){
							case 0: n = new Species();
								n.userInput();
								db.insert(n);
								break;
							case 1: n = new Species();
								n.userInput();
								isSuccessful = db.delete(n);
								
								if(isSuccessful) JOptionPane.showMessageDialog(null, "Done");
								else JOptionPane.showMessageDialog(null, "Error occured");
								break;
							case 2: n = new Species(); m = new Species();
								JOptionPane.showMessageDialog(null, "Enter attributes of node to be edited");
								n.userInput();
								JOptionPane.showMessageDialog(null, "Enter new attributes");
								m.userInput();
								isSuccessful = db.replace(n, m);
								
								if(isSuccessful) JOptionPane.showMessageDialog(null, "Done");
								else JOptionPane.showMessageDialog(null, "Error occured");
								break;
							case 3: n = new Species(); m = new Species();
								n.userInput();
								m = db.search(n);
								
								if(m != null) JOptionPane.showMessageDialog(null, "Node exists\n" + m);
								else JOptionPane.showMessageDialog(null, "Error occured");
								break;
							case 4: showList(db); break;
						}
						
						if(choice1 == 5) break;
					}break;
					
				case 1: 
					String choices3[] = {"Save", "Load", "Back"};
					while (true) {
						int choice3 = JOptionPane.showOptionDialog(
						null, "What do you want to do?", "File Handling",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null, choices3, choices3[0]);

						switch(choice3){
							case 0: 
								isSuccessful = db.saveToFile("sample.dat");
								
								if(isSuccessful) {JOptionPane.showMessageDialog(null, "Done saving"); choice3 = 2;}
								else JOptionPane.showMessageDialog(null, "Error occured in saving");
								break;
							case 1: 
								isSuccessful = db.loadFromFile("sample.dat");
								
								if(isSuccessful) {JOptionPane.showMessageDialog(null, "Done loading"); choice3 = 2;}
								else JOptionPane.showMessageDialog(null, "Error occured in loading");
								break;
						}
						
						if(choice3 == 2) break;
					}break;
				}
			if(choice == 2) break;
		}
    }
	
	static void showList(TrnaDatabase db){
		Species n = db.listIterator();
		System.out.println("List:\n");
		
		while(n != null){
			System.out.println(n.toString() + "\n");
			n = db.listIterator();
		}
	}
}