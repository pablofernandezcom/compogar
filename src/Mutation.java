import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Vector;


public class Mutation {
	final String[] noteSet = {"c", "c#", "d", "d#", "e", "f", "f#", "g", "g#", "a", "a#", "b", "r"};
	private static final int MUTATION = 7;
	
	public Vector<String> goMutaion(Vector<String> notes) {
		Random r = new Random();
		int i = r.nextInt(MUTATION);
		
		Vector<String> string = new Vector<String>();
		
		
		/* Starting Mutation*/
		
		switch (i) {
			case 0:											// None Function
				string = notes;
				break;
			
			case 1:											// Reverse Function
				for(int j = notes.size(); j > 0; j--){
					string.add(notes.elementAt(j-1));
				}
				break;
			
			case 2:											//Rotate Function
				
				int x = notes.size() / 2;
				
				for(int j = x; j < notes.size(); j++){
					string.add(notes.elementAt(j));
				}
				
				for(int k = 0; k < x; k++){
					string.add(notes.elementAt(k));
				}
				
				break;
				
			case 3:											// Invert Function
				
				for(int j = 0; j < notes.size(); j++){
					
					for(int k =0 ; k < 13; k++){
						if(noteSet[k].equalsIgnoreCase(notes.elementAt(j))){
							string.add(noteSet[12 - k]);
						}
					}
				}
				
				break;
				
			case 4:											// Ascending Function
				
				Vector<Integer> index = new Vector<Integer>();
				
				for(int j = 0; j < notes.size(); j++){
					for(int k = 0; k < noteSet.length; k++){
						if(noteSet[k].equalsIgnoreCase(notes.elementAt(j))){
							index.add(k);
						}
					}
				}
				
				Collections.sort(index);
				
				for(int l = 0; l < notes.size(); l++){
					string.add(noteSet[index.elementAt(l)]);
				}
				
				break;
				
			case 5:											// Descending Function
				
				Vector<Integer> index1 = new Vector<Integer>();
				
				for(int j = 0; j < notes.size(); j++){
					for(int k = 0; k < noteSet.length; k++){
						if(noteSet[k].equalsIgnoreCase(notes.elementAt(j))){
							index1.add(k);
						}
					}
				}
				
				Comparator comparator = Collections.reverseOrder();
				
				Collections.sort(index1, comparator);
				
				for(int l = 0; l < notes.size(); l++){
					string.add(noteSet[index1.elementAt(l)]);
				}
				
				break;
				
			case 6:											// Transpose Function
				
				for(int j = 0; j < notes.size(); j++){
					int count = 0;
					for(int k = 0; k < 13; k++){
						if(k + 3 < 13){
							if(noteSet[k].equalsIgnoreCase(notes.elementAt(j))){
								string.add(noteSet[k+3]);
							}
						}
						else{
							count++;
							if(noteSet[k].equalsIgnoreCase(notes.elementAt(j))){
									string.add(noteSet[count-1]);
							}
						}
					}
				}
		}
		
		
		return string;
	}
	
}
