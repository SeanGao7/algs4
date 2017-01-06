import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	private class Node{
		private Item item;
		private Node next;
		private Node previous;
	}
	private Node first;
	private Node last;
	private int size;
	public Deque(){
		first = null;
		last = null;
		size = 0;
	}                           
	public boolean isEmpty(){
		return size == 0;
	}                
	public int size(){
		return size;
	}                        
	public void addFirst(Item item){
		if (item == null){
			throw new java.lang.NullPointerException();
		}
		Node temp = new Node();
		temp.item = item;
		if (first != null){
			first.previous = temp;
		}
		temp.next = first;
		temp.previous = null;
		this.first = temp;
		size++;
		if (size == 1){
			this.last = temp;
		}
	}          
	public void addLast(Item item){
		if (item == null){
			throw new java.lang.NullPointerException();
		}
		Node temp = new Node();
		temp.item = item;
		if (last != null){
			last.next = temp;
		}
		temp.next = null;
		temp.previous = last;
		this.last = temp;
		size++;
		if (size == 1){
			this.first = temp;
		}
	}           
	public Item removeFirst(){
		if (isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		Node temp = first;
		first = first.next;
		if (first != null){
			first.previous = null;
		}
		size--;
		if (size == 0){
			last = null;
		}
		return temp.item;
	}                
	public Item removeLast(){
		if (isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		Node temp = last;
		last = last.previous;
		if (last != null){
			last.next = null;
		}
		size--;
		if (size == 0){
			first = null;
		}
		return temp.item;
	}
	private class DequeIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current != null;
		}
		public Item next(){
			if (current == null){
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}
	}
	public Iterator<Item> iterator(){
		return new DequeIterator();
	}         
	public static void main(String[] args){
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		deque.addFirst(3);
		deque.addFirst(5);
		deque.addLast(2);
		deque.addLast(4);
		deque.addLast(6);
		for (int i : deque){
			System.out.println(i);
		}
		System.out.println("-");
		deque.removeFirst();
		deque.removeLast();
		for (int i : deque){
			System.out.println(i);
		}
		System.out.println("-");
		deque.removeFirst();
		deque.removeLast();
		for (int i : deque){
			System.out.println(i);
		}
		System.out.println("-");
		deque.removeFirst();
		deque.removeLast();
		for (int i : deque){
			System.out.println(i);
		}
		System.out.println("-");
	}   
}