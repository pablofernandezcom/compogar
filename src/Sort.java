import java.util.Random;
import java.util.Vector;



public class Sort {
	final String[] notes = {"a", "a#", "b", "c", "c#", "d", "d#", "e", "f", "f#", "g", "g#"};
	final int[][] noteFitness = {{30, 16, 22, 27, 24, 10, 8, 28, 26, 25, 21, 19},				// Fitness lists of Notes
								 {16, 30, 14, 5, 27, 4, 10, 17, 3, 26, 21},
								 {22, 14, 30, 20, 23, 27, 24, 29, 9, 15, 26, 25},
								 {27, 5, 20, 30, 16, 22, 7, 26, 28, 17, 29, 18},
								 {24, 27, 23, 16, 30, 14, 22, 25, 13, 28, 11, 29},
								 {10, 4, 27, 22, 14, 30, 16, 21, 12, 6, 28, 17},
								 {8, 10, 24, 7, 22, 16, 30, 19, 1, 12, 13, 28},
								 {28, 17, 29, 26, 25, 21, 19, 30, 19, 21, 25, 26},
								 {26, 3, 9, 28, 13, 12, 1, 19, 30, 16, 22, 7},
								 {25, 26, 15, 17, 28, 6, 12, 21, 16, 30, 14, 22},
								 {27, 2, 26, 19, 11, 28, 13, 25, 22, 14, 30, 16},
								 {19, 21, 25, 18, 29, 17, 28, 26, 7, 22, 16, 30}};
	final String[][] scale = {{"a", "b", "c#", "d", "e", "f#", "g#"},							// List of scale notes for chords
							  {"a", "b", "c", "d", "e", "f", "g"},
							  {"a#", "c", "d", "d#", "f", "g", "a"},
							  {"a#", "c", "c#", "d#", "f", "f#", "g#"},
							  {"b", "c#", "d#", "e", "f#", "g#", "a#"},
							  {"b", "c#", "d", "e", "f#", "g", "a"},
							  {"c", "d", "e", "f", "g", "a", "b"},
							  {"c", "d", "d#", "f", "g", "g#", "a#"},
							  {"c#", "d#", "f", "f#", "g#", "a#", "c"},
							  {"c#", "d#", "e", "f#", "g#", "a", "b"},
							  {"d", "e", "f#", "g", "a", "b", "c#"},
							  {"d", "e", "f", "g","a", "a#", "c"},
							  {"d#", "f", "g", "g#", "a#", "c", "d"},
							  {"d#", "f", "f#", "g#", "a#", "b", "c#"},
							  {"e", "f#", "g#", "a", "b", "c#", "d#"},
							  {"e", "f#", "g", "a", "b", "c", "d"},
							  {"f", "g", "a", "a#", "c", "d", "e"},
							  {"f", "g", "g#", "a#", "c", "c#", "d#"},
							  {"f#", "g#", "a#", "b", "c#", "d#", "f"},
							  {"f#", "g#", "a", "b", "c#", "d", "e"},
							  {"g", "a", "b", "c", "d", "e", "f#"},
							  {"g", "a", "a#", "c", "d", "d#", "f"},
							  {"g#", "a#", "c", "c#", "d#", "f", "g"},
							  {"g#", "a#", "b", "c#", "d#", "e", "f#"}};
	Vector<String> finalNotes = new Vector<String>();
	
	Vector<String> getNotes(String chord, int x, int size){
		int[] array = new int[12];
		int[] array1 = new int[8];
		Vector<Integer> noteIndex = new Vector<Integer>();
		Vector<String> resNote = new Vector<String>();
		Random r = new Random();

		
		for(int i = 0; i <noteFitness[x].length; i ++){
			array[i] = noteFitness[x][i];
		}
		
		
		mergeSort(array, 0, array.length - 1);
		
		
		/* getting sorted fitness list of 8 element */
		
		for(int i = 0; i < array1.length; i++){
			array1[i] = array[4 + i];
		}
		
		
		for(int i = 0; i < array1.length; i++){
			
			for(int j = 0; j < noteFitness[x].length; j++){
				if(array1[i] == noteFitness[x][j]){
					noteIndex.add(j);
				}
			}
		}
		
		

		
		/* getting notes using noteFitness */
		
		for(int i = 0; i < noteIndex.size(); i++){
			resNote.add(notes[noteIndex.elementAt(i)]);
		}
		

		
		// get the notes
		
		finalNotes = new Vector<String>();
		
		for(int i = 0; i < size;){
			int var  = r.nextInt(resNote.size());
			String str = resNote.elementAt(var);
			int y = 2*x+1;
			int z = 2*x;

			
			if(chord.length() == 2 && chord.charAt(1) == 'm' && check(str, y)){
				finalNotes.add(str);
			}
			
			if(chord.length() == 3 && chord.charAt(1) == '#' && check(str, y)){
				finalNotes.add(str);
			}
			
			if(chord.length() == 1  && check(str, z)){
				finalNotes.add(str);
			}
			
			if(chord.length() == 2 && chord.charAt(1) == '#' && check(str, z)){
				finalNotes.add(str);
			}
			
			i = finalNotes.size();
		}
			
		return finalNotes;
	}
	
	
	private boolean check(String str, int i) {
		for(int j = 0; j < 7; j++){
			if(scale[i][j].equalsIgnoreCase(str)){
				return true;
			}
		}
		
		return false;
	}

	
	private void mergeSort(int[] tempArray2, int i, int j) {
		int low = i; 
		int high = j;
		
		if(low >= high){
			return;
		}
		
		int middle = (low + high) / 2;
		
		mergeSort(tempArray2, low, middle);
		mergeSort(tempArray2, middle + 1, high);
		
		int endLow = middle;
		int startHigh = middle + 1;
		
		while(low <= endLow && startHigh <= high){
			
			if(tempArray2[low] < tempArray2[startHigh]){
				low++;
			}
			else{
				int temp = tempArray2[startHigh];
				
				for(int k = startHigh - 1; k >= low; k--){
					tempArray2[k + 1] = tempArray2[k];
				}
				
				tempArray2[low] = temp;
				low++;
				startHigh++;
				endLow++;
			}
		}
		
	}
}
