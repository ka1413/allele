import java.io.*;

public class TrnaDatabase {
	/*
	 * Node head; Node rover = new Node(); Node rover2 = new Node(); Node i =
	 * new Node(); Node nodeCopy; boolean lastNode = false;
	 */

	private Species head, rover = new Species(), rover2 = new Species(),
			i = new Species(), nodeCopy;
	private boolean lastNode = false;
	File databaseFile;

	public TrnaDatabase() {

	}

	public TrnaDatabase(TrnaDatabase db) {
		if (db.head == null) {
			this.head = null;
		} else {
			this.head = db.head;
		}
	}

	// inserts a new node to the database
	public void insert(Species newNode) {
		rover = new Species();

		if (head == null) {
			head = new Species();
			head = newNode;

		} else {
			rover = head;
			while (rover.next != null)
				rover = rover.next;
			rover.next = newNode;
		}

		i = head;
	}

	// deletes an old node (returns true if successful)
	public boolean delete(Species nodeToDelete) {
		if (head == null) {
			return false;
		} else {
			if (head.name.equals(nodeToDelete.name)) {
				head = head.next;

				i = head;
				return true;
			} else {
				rover = head;

				while (rover.next != null) {
					if (rover.next.name.equals(nodeToDelete.name)) {
						rover.next = rover.next.next;

						i = head;
						return true;
					}
					rover = rover.next;
				}
				return false;
			}
		}
	}

	// replaces a node on the list (returns true if successful)
	// with your nodes being different, there is no way to
	// create a generic edit method
	public boolean replace(Species nodeToReplace, Species newNode) {
		if (head == null) {
			return false;
		} else {
			rover = head;

			if (head.name.equals(nodeToReplace.name)) {
				rover = head.next;
				head = newNode;
				head.next = rover;

				i = head;
				return true;
			} else {
				while (rover.next != null) {
					if (rover.next.name.equals(nodeToReplace.name)) {
						rover2 = rover.next.next;
						rover.next = newNode;
						// rover.next.head = myNewNode.head;
						rover.next.next = rover2;

						i = head;
						return true;
					}
					rover = rover.next;
				}

				return false;
			}
		}
	}

	// searches for a node, returns an object reference to the
	// node in the list if that node is found (null otherwise)
	public Species search(Species nodeToSearch) {
		if (head == null) {
			return null;
		} else {
			if (head.equals(nodeToSearch)) {
				nodeCopy = new Species(head);
				return nodeCopy;
			} else {
				rover = head;

				while (rover.next != null) {
					if (rover.next.equals(nodeToSearch)) {
						nodeCopy = new Species(rover.next);
						return nodeCopy;
					}
					rover = rover.next;
				}

				return null;
			}
		}
	}

	// saves entire list to file (returns true if successful)
	public boolean saveToFile(String filename) throws Exception {
		databaseFile = new File(filename);

		if (head == null)
			return false;
		else if (databaseFile.exists()) {
			if (databaseFile.delete()) {
				if (databaseFile.createNewFile()) {
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream(databaseFile));
					out.writeObject(head);
					out.close();
					return true;
				} else
					return false;
			} else
				return false;
		} else {
			if (databaseFile.createNewFile()) {
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(databaseFile));
				out.writeObject(head);
				out.close();
				return true;
			} else
				return false;
		}
	}

	// loads a list from file (returns true if successful)
	public boolean loadFromFile(String filename) throws Exception {
		databaseFile = new File(filename);

		if (databaseFile.exists()) {
			if (databaseFile.canRead()) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(databaseFile));
				Species o = (Species) in.readObject();

				try {
					o = (Species) in.readObject();
				} catch (EOFException e) {
					// System.out.println("End of file reached!");
				}

				head = new Species(o);
				in.close();

				i = head;
				return true;
			} else
				return false;
		} else {
			return false;
		}
	}

	// iterates through the list.
	// on the first call to this method, returns the start of the list
	// next calls to this method returns the next item on the list
	// when it reaches the end, return null.
	// the next call returns the start of list again, and so on.
	// (returns null if list is empty)
	// if the list is modified, go back to start of list
	public Species listIterator() {
		if (head == null) {
			return null;
		} else if (lastNode) {
			lastNode = false;
			return null;
		} else {
			if (i == null)
				i = head;
			nodeCopy = new Species(i);
			i = i.next;
			if (i == null)
				lastNode = true;

			return nodeCopy;
		}
	}

	// Two databases are equal if and
	// only if they contain exactly the same entries
	// (equal nodes being defined by their respective equals method)
	/*
	 * public boolean equals(TrnaDatabase db){ System.out.println("1"); int a=0,
	 * b=0; Node temp1 = new Node(), temp2 = new Node();
	 * 
	 * temp1 = this.head; temp2 = db.head;
	 * 
	 * if(temp1 == null && temp2 == null) return true;
	 * 
	 * while(temp1 != null || temp2 != null){ if(temp1 != null){ temp1 =
	 * temp1.next; a++; }
	 * 
	 * if(temp2 != null){ temp2 = temp2.next; b++; }
	 * 
	 * System.out.println("2"); }
	 * 
	 * if(a == b){ temp1 = this.head; temp2 = db.head; System.out.println("3");
	 * while(temp1 != null){ if(temp1.equals(temp2)){ temp1 = temp1.next; temp2
	 * = db.head; } else temp2 = temp2.next; System.out.println("4"); if(temp2
	 * == null) break; }
	 * 
	 * System.out.println(temp1); System.out.println(temp2);
	 * 
	 * if(temp1 == null) return true; else return false; } else return false; }
	 */

	public int length() {
		int counter = 0;

		rover = head;

		while (rover != null) {
			counter++;
			rover = rover.next;
		}

		return counter;
	}

	public String[] databaseToNameArray() {
		int i = 0;
		String[] s;
		Species n = new Species();

		if (head == null) {
			s = null;
		} else {
			s = new String[this.length()];

			n = listIterator();

			while (n != null) {
				s[i] = n.name;
				i++;
				n = listIterator();
			}
		}

		return s;
	}

	public Species getSelectedNode(int n) {
		if (n == 0) {
			return head;
		} else {
			rover = head;
			int i = 0;
			while(rover != null) {
				if (i == n) {
					break;
				} else {
					i++;
				}
				if(rover.next == null){
					break;
				}
				else{
					rover = rover.next;
				}
			}
			return rover;
		}
	}
	
	public Species getByName(String s) {
		if (s == null) {
			return null;
		} else {
			rover = head;

			while (rover.next != null) {
				if (s == rover.name) {
					break;
				}

				rover = rover.next;
			}

			return rover;
		}
	}
	
	public Object[][] toArray(){
		Object[][] data = new Object[this.length()][9];
		Species s;
		for(int i=0; i<this.length(); i++){
			s = this.listIterator();
			
			data[i][0] = s.name;
			data[i][1] = s.nickname;
			data[i][2] = s.kingdom;
			data[i][3] = s.phylum;
			data[i][4] = s.classs;
			data[i][5] = s.order;
			data[i][6] = s.family;
			data[i][7] = s.genus;
			data[i][8] = s.numTrna();
		}
		
		return data;
	}
}