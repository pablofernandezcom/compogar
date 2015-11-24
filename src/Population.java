import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;


public class Population {
	
	public static void main(String[] args) throws IOException{
		Notes n = new Notes();	
		FitnessEvaluation fitness = new FitnessEvaluation();
		Mutation mymutaion = new Mutation();
		
		/* Initiating population */

		n.getFinalNotes();
		
		/* Assign fitness value in the population*/
		
		Vector<String> notes = new Vector<String>();
		Vector<Integer> duration = new Vector<Integer>();		
		Vector<Double> fitnessValue = new Vector<Double>();
		int tot = n.getTotalPairs();
		
		for(int i = 0; i < tot; i++){
			
			Pair<Vector<String>, Vector<Integer>> pair = n.getPairs(i);
			Vector<String> mutant = new Vector<String>();
			
			notes = pair.getFirst();
			duration = pair.getSecond();
			
			double x = fitness.goingEvaluation(notes, i);		// counting fitness for the population
			
			fitnessValue.add(x);

			System.out.print("For "+ i);
			System.out.print("\n"+fitnessValue.elementAt(i));
			System.out.println();
		}
		
		
		
		/* Starting Mutation */
		
		
		
		Vector<String> mutant = new Vector<String>();
		int start = 0;
		int end = n.getTotalPairs();
		int stopCriteria = 0;
		
		while(stopCriteria < 100){										//stop criteria for the GA
			
			Vector<String> notes1 = new Vector<String>();

			Vector<Integer> duration1 = new Vector<Integer>();
			
//			
			System.out.println("\n*****"+start+"\n #####" + end);
			
			for(int i = start; i < end; i++){
				Pair<Vector<String>, Vector<Integer>> pair1 = n.getPairs(i);
				mutant = null;
				
				notes1 = pair1.getFirst();
				duration1 = pair1.getSecond();
				mutant = mymutaion.goMutaion(notes1);           // Running mutation
			
				
				n.addNewPair(duration1, mutant);                // Evaluate Population
				
				
				double y = fitness.goingEvaluation(mutant, i);
				fitnessValue.add(y);			                 // Measuring Fitness 
			}
			
			start = end;
			end = n.getTotalPairs();
			stopCriteria++;
		}
		
		
		
		/* Getting best fitted  Note Set*/
		
		int sz =  n.getTotalPairs();;
		
		Double max = 0.0;
		int maxIndex = 0;
		Double max1 = 0.0;
		int maxIndex1 = 0;
		Double max2 = 0.0; 
		int maxIndex2 = 0;
		Double max3 = 0.0;
		int maxIndex3 = 0;
		
		for(int i = 0; i < sz; i++){
			if(i % 4 == 0 && max < fitnessValue.elementAt(i)){
				max = fitnessValue.elementAt(i);
				maxIndex = i;
			}
			else if(i % 4 == 1 && max1 < fitnessValue.elementAt(i)){
				max1 = fitnessValue.elementAt(i);
				maxIndex1 = i;
			}
			else if(i % 4 == 2 && max2 < fitnessValue.elementAt(i)){
				max2 = fitnessValue.elementAt(i);
				maxIndex2 = i;
			}
			else if(i % 4 == 3 && max3 < fitnessValue.elementAt(i)){
				max3 = fitnessValue.elementAt(i);
				maxIndex3 = i;
			}
		}
		
		
		
		
		
		/* Output total population*/
		
		
		PrintWriter printWriter = new PrintWriter(new FileWriter ("D:\\outputfile.txt"));
		for (int i =0 ; i < sz; i++){
			
			int chordIndex = i % 4;
			String chord = n.getChord(chordIndex);
			printWriter.println(i +":");
			printWriter.println("\nCHORD:"+chord+"\n");
			Pair<Vector<String>, Vector<Integer>> pair1 = n.getPairs(i);
			printWriter.println(pair1);
			printWriter.println("\nFitness value: "+ fitnessValue.elementAt(i)+" ");
			printWriter.println();
			printWriter.println("1: "+maxIndex+" \n2: "+maxIndex1+" \n3: "+maxIndex2+" \n4: "+maxIndex3);
			
		}
		printWriter.close();
		
		
		/* Output best population*/
		
		PrintWriter printWriter1 = new PrintWriter(new FileWriter ("D:\\finalResult.txt"));
		
		String chord = n.getChord(maxIndex % 4);
		printWriter1.println("\nCHORD:"+chord+"\n");
		Pair<Vector<String>, Vector<Integer>> pair1 = n.getPairs(maxIndex);
		printWriter1.println(pair1);
		printWriter1.println("\nFitness value: "+ fitnessValue.elementAt(maxIndex)+" ");
		printWriter1.println();
		
		String chord1 = n.getChord(maxIndex1 % 4);
		printWriter1.println("\nCHORD:"+chord1+"\n");
		Pair<Vector<String>, Vector<Integer>> pair2 = n.getPairs(maxIndex1);
		printWriter1.println(pair2);
		printWriter1.println("\nFitness value: "+ fitnessValue.elementAt(maxIndex1)+" ");
		printWriter1.println();
		
		String chord2 = n.getChord(maxIndex2 % 4);
		printWriter1.println("\nCHORD:"+chord2+"\n");
		Pair<Vector<String>, Vector<Integer>> pair3 = n.getPairs(maxIndex2);
		printWriter1.println(pair3);
		printWriter1.println("\nFitness value: "+ fitnessValue.elementAt(maxIndex2)+" ");
		printWriter1.println();
		
		String chord3 = n.getChord(maxIndex3 % 4);
		printWriter1.println("\nCHORD:"+chord3+"\n");
		Pair<Vector<String>, Vector<Integer>> pair4 = n.getPairs(maxIndex3);
		printWriter1.println(pair4);
		printWriter1.println("\nFitness value: "+ fitnessValue.elementAt(maxIndex3)+" ");
		printWriter1.println();
		
		printWriter1.close();
	}
}
