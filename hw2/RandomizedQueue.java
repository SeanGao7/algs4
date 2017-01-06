import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] s;
	private int N = 0;
	private int capacity = 1;
	public RandomizedQueue(){
		s = (Item[]) new Object [1];
	}                 
	public boolean isEmpty(){
		return N == 0;
	}                 
	public int size(){
		return N;
	}                        
	public void enqueue(Item item){
		if (item == null){
			throw new java.lang.NullPointerException();
		}
		s[N++] = item;
		if (capacity == N){
			capacity *= 2;
			Item[] temp = (Item[]) new Object [capacity];
			for (int i = 0; i < N; i++){
				temp[i] = s[i];
			}
			s = temp;
		}
	}
	public Item dequeue(){
		if (N == 0){
			throw new java.util.NoSuchElementException();
		}
		int p = StdRandom.uniform(0,N);
		Item temp = s[p];
		s[p] = s[--N];
		s[N] = null;
		if (N <= capacity/4.0){
			capacity /= 2;
			Item[] tempQ = (Item[]) new Object[capacity];
			for (int i = 0; i < N; i++){
				tempQ[i] = s[i];
			}
			s = tempQ;
		}
		return temp;
	}                    
	public Item sample(){
		if (N == 0){
			throw new java.util.NoSuchElementException();
		}
		return s[StdRandom.uniform(0,N)];
	}                     
	public Iterator<Item> iterator(){
		return new RandomizedQueueIterator();
	}
	private class RandomizedQueueIterator implements Iterator<Item>{
		private int[] order = new int[N];
		private int counter = 0;
		public RandomizedQueueIterator(){
			for (int i = 0; i < N; i++){
				order[i] = i;
			}
			StdRandom.shuffle(order);
		}
		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}
		public boolean hasNext(){
			return counter != N;
		}
		public Item next(){
			if (counter == N){
				throw new java.util.NoSuchElementException();
			}
			return s[order[counter++]];
		}
	}
	public static void main(String[] args){
		RandomizedQueue<Integer> Q = new RandomizedQueue<Integer>();
		for (int i = 0; i < 100; i++){
			Q.enqueue(i);
		}
		for (int i = 0; i < 100; i++){
			System.out.println(Q.dequeue());
		}
	}
}