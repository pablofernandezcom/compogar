import java.util.Random;
import java.util.Vector;


public class Duration {
	private static final int TOTAL = 5;
	final int duration[] = { 2, 4, 8, 16, 32};
	
	Vector<Integer> getRandomeDuration() {
		float currentSum = 0;
		Vector<Integer> resultDuration = new Vector<Integer>();

		Random r = new Random();

		while (currentSum != 0.5) {
			int i = r.nextInt(TOTAL);
			if (1 / (float) duration[i] > (0.5 - currentSum)) {
				continue;
			} else {
				resultDuration.add(duration[i]);

				currentSum += 1 / ((float) duration[i]);
			}
		}

		return resultDuration;
	}
	
	public int getFinalDurationSize(){
		if (finalDurations == null){
			prepare();
		}
		return finalDurations.size();		
	}
	
	private Vector<Vector<Integer>> finalDurations = null;
	
	public Vector<Vector<Integer>> getFinalDuration(){
		if (finalDurations == null){
			prepare();
		}
		
		return finalDurations;
	}
	
	private void prepare(){

		Vector<Vector<Integer>> finalDuration = new Vector<Vector<Integer>>();
		
		for(int i = 0; i < 500; i++){                                      // Initial Population Size
			Vector<Integer> randomDuration = getRandomeDuration();
			int sz = randomDuration.size();
			
			finalDuration.add(new Vector<Integer>());
			
			for(int j= 0; j < sz; j ++){
				finalDuration.elementAt(i).add(randomDuration.elementAt(j));
			}
		}
		finalDurations = finalDuration;	
	
	}
	
}
