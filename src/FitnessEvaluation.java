import java.util.Vector;


public class FitnessEvaluation {
	final String[][] scale = {{"a", "b", "c#", "d", "e", "f#", "g#"},			// Scale notes for specific chords
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
	
	final String[] chords = {"A", "Am", "A#", "A#m", "B", "Bm", "C", "Cm", "C#", "C#m", "D", "Dm", "D#",
							 "D#m", "E", "Em", "F", "Fm", "F#", "F#m", "G", "Gm", "G#", "G#m"};
	
	private Vector<String> notes = new Vector<String>();
	private Notes n = new Notes();
	private double y;
	private double z;
	private double w;
	private double x;
	private int index1;
	private int index2;
	private int index;
	
	/* Preparing fitness Value*/
	
	public double goingEvaluation(Vector<String> notes2, int i){
		y = 0;
		z =0;
		w = 0;
		x = 0;
		notes = notes2;
		index1 = 0;
		index2 = 0;
		index = i % 4;
		String str = n.getChord(index);
		String str1 = n.getChord(0);
		
		

		
		for(int j = 0; j < chords.length; j++){
			if(chords[j].equalsIgnoreCase(str1)){
				index1 = j;
			}
			
			if(chords[j].equalsIgnoreCase(str)){
				index2 = j;
			}
		}
		

		y = checkArPeggio(notes, index2);						// checking method call for erPeggio
		
		z = checkScaleNotes(notes, index, index2);				// checking method call for Scale Notes
		
		w = checkCommonNotes(notes, index, index1);				// checking method call for Common Notes
		
		x = checkRest(notes);									// checking method call for Rest Notes
		
		
		System.out.println();
		System.out.print("Chord:"+ str +"\n");
		System.out.print("ArpeggioValue:"+ y +"\n");
		System.out.print("ScaleValue:"+ z +"\n");
		System.out.print("CommonValue:"+ w +"\n");
		System.out.print("Rest:"+ x +"\n");
		System.out.println();

		return (x + y + z + w);
	}

	

	private double checkRest(Vector<String> notes2) {
		int x = 0;
		for(int i = 0; i < notes2.size(); i++){
			if(notes2.elementAt(i) == "r"){
				x++;
			}
		}
		
		if(x == 1){
			return 1.0;
		}
		if(x == 2){
			return 0.75;
		}
		if(x == 3){
			return 0.4;
		}
		if(x >= 4){
			return 0.2; 
		}
		return 0.5;
	}
	
	
	

	private double checkCommonNotes(Vector<String> notes2, int index, int index1) {
		double x = 0;
		double y = 0;
		double z = 0;
		for(int i = 0; i < notes2.size(); i++){			
			if(notes.elementAt(i) == "r"){
				z++;
			}
			for(int j = 0; j < 7; j++){
			
				if(scale[index1][j].equals(notes2.elementAt(i)) == true){
					x++;
				}
			}
		}

		y = x / ((double)notes2.size() - z);
		return y;
	}

	
	
	
	private double checkScaleNotes(Vector<String> notes2, int index, int index2) {
		double x = 0;
		double y = 0;
		int z = 0;
		for(int i = 0; i < notes2.size(); i++){
			if(notes2.elementAt(i) == "r"){
				z++;
			}
			
			for(int j = 0; j < 7; j++){
				if(scale[index2][j].equalsIgnoreCase(notes2.elementAt(i)) == true){
					x++;
				}
			}
		}
		
		y = x / (notes2.size()-z);
		return y;
	}
	
	
	

	private double checkArPeggio(Vector<String> notes2, int index2) {
		double x = 0;
		double y = 0;
		for(int j = 0; j < 5; j += 2){

			if(scale[index2][j].equalsIgnoreCase(notes2.elementAt(0))){
				x = 1;
			}
			if(notes2.size() > 1 && scale[index2][j].equalsIgnoreCase(notes2.elementAt(notes2.size()-1))){
				y = 1;
			}
		}
		
		return x + y;
	}

}
