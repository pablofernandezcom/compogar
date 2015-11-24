import java.util.Vector;

public class Notes {
	
	private int tempIndex = 0;

	final String[] notes = { "a", "a#", "b", "c", "c#", "d", "d#", "e", "f",
							"f#", "g", "g#" };
	final int[][] noteFitness = {{ 30, 16, 22, 27, 24, 10, 8, 28, 26, 25, 21, 19 },
								 { 16, 30, 14, 5, 27, 4, 10, 17, 3, 26, 21 },
								 { 22, 14, 30, 20, 23, 27, 24, 29, 9, 15, 26, 25 },
								 { 27, 5, 20, 30, 16, 22, 7, 26, 28, 17, 29, 18 },
								 { 24, 27, 23, 16, 30, 14, 22, 25, 13, 28, 11, 29 },
								 { 10, 4, 27, 22, 14, 30, 16, 21, 12, 6, 28, 17 },
								 { 8, 10, 24, 7, 22, 16, 30, 19, 1, 12, 13, 28 },
								 { 28, 17, 29, 26, 25, 21, 19, 30, 19, 21, 25, 26 },
								 { 26, 3, 9, 28, 13, 12, 1, 19, 30, 16, 22, 7 },
								 { 25, 26, 15, 17, 28, 6, 12, 21, 16, 30, 14, 22 },
								 { 27, 2, 26, 19, 11, 28, 13, 25, 22, 14, 30, 16 },
								 { 19, 21, 25, 18, 29, 17, 28, 26, 7, 22, 16, 30 } };
	
	private final String[] chordProgression = { "Em", "Bm", "C", "D" };
	
	private int counter = 0;
	
	private Duration myduration = new Duration();
	private Sort mysort = new Sort();
	private PairMaker pairMaker;

	public Notes() {
		pairMaker = new PairMaker();
	}
	
	public String getChord(int i){
		return chordProgression[i];
	}

	public Pair<Vector<String>, Vector<Integer>> getPairs(int index) {
		return pairMaker.getNoteAndDurationPari(index);
	}
	
	
	/* Getting random generated notes for population*/
	
	Vector<String> getNotes(int sz, Vector<Integer> durations) {
		int size = sz;

		Vector<String> resultNote = new Vector<String>();

		if (counter > 3) {
			counter = 0;
		}

		String chord = chordProgression[counter];

		System.out.print("Chord: " + chord);
		System.out.println();
		System.out.print("Notes for " + chord + " is: ");

		if (chord.length() >= 2 && chord.charAt(0) == 'A'
				&& chord.charAt(1) == '#') {
			resultNote = mysort.getNotes(chord, 1, size);
		} 
		else if (chord.charAt(0) == 'A') {
			resultNote = mysort.getNotes(chord, 0, size);
		} 
		else if (chord.charAt(0) == 'B') {
			resultNote = mysort.getNotes(chord, 2, size);
		} 
		else if (chord.length() >= 2 && chord.charAt(0) == 'C'
				&& chord.charAt(1) == '#') {
			resultNote = mysort.getNotes(chord, 4, size);
		}
		else if (chord.charAt(0) == 'C') {
			resultNote = mysort.getNotes(chord, 3, size);
		}
		else if (chord.length() >= 2 && chord.charAt(0) == 'D'
				&& chord.charAt(1) == '#') {
			resultNote = mysort.getNotes(chord, 6, size);
		} 
		else if (chord.charAt(0) == 'D') {
			resultNote = mysort.getNotes(chord, 5, size);
		} 
		else if (chord.charAt(0) == 'E') {
			resultNote = mysort.getNotes(chord, 7, size);
		} 
		else if (chord.length() >= 2 && chord.charAt(0) == 'F'
				&& chord.charAt(1) == '#') {
			resultNote = mysort.getNotes(chord, 9, size);
		} 
		else if (chord.charAt(0) == 'F') {
			resultNote = mysort.getNotes(chord, 8, size);
		} 
		else if (chord.length() >= 2 && chord.charAt(0) == 'G'
				&& chord.charAt(1) == '#') {
			resultNote = mysort.getNotes(chord, 11, size);
		} 
		else if (chord.charAt(0) == 'G') {
			resultNote = mysort.getNotes(chord, 10, size);
		}


		assert(resultNote != null);
		assert(durations != null);
		
		pairMaker.addToHashMap(tempIndex++, resultNote, durations);

		counter++;

		System.out.println();

		return resultNote;
	}
	
	int getTotalPairs(){							
		return tempIndex;
	}
	
	// Making Pair
	
	public void addNewPair(Vector<Integer> durations, Vector<String> resultNote){		
		pairMaker.addToHashMap(tempIndex++, resultNote, durations);
	}
	
	
	/* Getting Final duration & notes for population*/

	Vector<Vector<String>> getFinalNotes() {
		Vector<Vector<String>> finalNotes = new Vector<Vector<String>>();

		for (int i = 0; i < myduration.getFinalDurationSize(); i++) {
			int size = myduration.getFinalDuration().elementAt(i).size();

			Vector<String> randomNotes = getNotes(size, myduration
					.getFinalDuration().elementAt(i));
			int sz = randomNotes.size();

			finalNotes.add(new Vector<String>());

			for (int j = 0; j < sz; j++) {
				finalNotes.elementAt(i).add(randomNotes.elementAt(j));
			}
		}
		return finalNotes;
	}
}
