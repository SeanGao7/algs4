import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Permutation {
	public static void main(String[] args){
		RandomizedQueue<String> Q = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()){
			Q.enqueue(StdIn.readString());
		}
		for (int i = 0; i < Integer.parseInt(args[0]); i++){
			StdOut.println(Q.dequeue());
		}
	}
}