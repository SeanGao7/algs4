import edu.princeton.cs.algs4.MinPQ;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Solver {
    private Iterable<Board> solution;
    private int moves = 0;
	private boolean solvable = false;
	
	private class Node{
		public Node previous;
		public Board board;
		public int moves;
		public Node(Node previous, Board board, int moves){
			this.previous = previous;
			this.board = board;
			this.moves = moves;
		}
	}
	
    public Solver(Board initial){
		MinPQ<Node> Q = new MinPQ<Node>(new boardComparator());
		MinPQ<Node> twin_Q = new MinPQ<Node>(new boardComparator());
		Q.insert(new Node(null,initial,0));
		twin_Q.insert(new Node(null,initial.twin(),0));
		Node last = null;
		while (true){
			Node search = Q.delMin();
			Node twin_search = twin_Q.delMin();
			if (search.board.isGoal()){
				last = search;
				this.solvable = true;
				break;
			}
			if (twin_search.board.isGoal()){
				this.moves = -1;
				return;
			}
			for (Board x: search.board.neighbors()){
				if (search.previous != null){
					if (!x.equals(search.previous.board)){
						Q.insert(new Node(search,x,search.moves+1));
					}
				}
				else {
					Q.insert(new Node(search,x,search.moves+1));
				}
			}
			for (Board x: twin_search.board.neighbors()){
				if (twin_search.previous != null){
					if (!x.equals(twin_search.previous.board)){
						twin_Q.insert(new Node(twin_search,x,twin_search.moves+1));
					}
				}
				else {
					twin_Q.insert(new Node(twin_search,x,twin_search.moves+1));
				}
			}
		}
		this.moves = last.moves;
		
		Board[] sol = new Board[moves+1];
		int i = moves;
		while(last != null){
			sol[i--] = last.board;
			last = last.previous;
		}
		solution = new ArrayList<Board>(Arrays.asList(sol)); 
        
    }
    public boolean isSolvable(){
		return solvable;
    }
	
    public int moves(){
		return moves;
    }                     
    public Iterable<Board> solution(){
		return solution;
    }      
	
    public static void main(String[] args){
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

    private class boardComparator implements Comparator<Node>{
        public int compare(Node b1, Node b2){
            return b1.board.manhattan() + b1.moves - b2.board.manhattan() - b2.moves;
        }
    }
}