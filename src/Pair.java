public class Pair<T1, T2>{
	private T1 a;
	private T2 b;
	public Pair(T1 a, T2 b){
		this.a = a;
		this.b = b;
	}
	
	
	public T1 getFirst() { 
		return a; }

	public T2 getSecond() {
		return b;
	}
	
	@Override
	public String toString(){
		return "Notes: " + a + "\n\nDurations: " + b;
	}
}