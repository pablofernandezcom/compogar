import java.util.HashMap;
import java.util.Vector;

public class PairMaker {
	
	/* Putting random generated notes and duration into hashMap */
	
	
	private HashMap<Integer, Pair<Vector<String>, Vector<Integer>>> map = new HashMap<Integer, Pair<Vector<String>,Vector<Integer>>>();

	private Pair<Vector<String>, Vector<Integer>> makePair(
			Vector<String> notes, Vector<Integer> durations) {

		Pair<Vector<String>, Vector<Integer>> result = new Pair<Vector<String>, Vector<Integer>>(
				notes, durations);

		return result;
	}

	public void addToHashMap(int index, Vector<String> notes,
			Vector<Integer> duration) {
		map.put(index, makePair(notes, duration));
	}

	public Pair<Vector<String>, Vector<Integer>> getNoteAndDurationPari(int index) {
		return map.get(index);
	}

}
