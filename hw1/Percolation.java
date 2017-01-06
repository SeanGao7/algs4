import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
public class Percolation{
	private WeightedQuickUnionUF grid;
	private WeightedQuickUnionUF grid_full;
	private int n;
	private int[][] openess;
	private int opennum = 0;
	public Percolation(int n){
		if (n <= 0){
			throw new IllegalArgumentException(Integer.toString(n));
		}
		grid = new WeightedQuickUnionUF(2+n*n);
		grid_full = new WeightedQuickUnionUF(1+n*n);
		for (int i = 1; i <=n; i++){
			grid_full.union(0,i);
			grid.union(0,i);
			grid.union(n*n+1,n*n+1-i);
		}
		openess = new int[n][n];
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n ; j++){
				openess[i][j] = 0;
			}
		}
		this.n = n;
	}
	public void open(int row, int col){
		int index = (row-1) * n + col;
		if (row < 1 || col < 1 || row > n || col > n){
			throw new IndexOutOfBoundsException(Integer.toString(row));
		}
		else if (this.isOpen(row, col)){
			return;
		}
		else{
			if (col > 1 && this.isOpen(row , col -1)){
				grid.union(index, (row - 1)*n + col -1);
				grid_full.union(index, (row - 1)*n + col -1);
			}
			if (row > 1 && this.isOpen(row - 1 , col)){
				grid.union(index, (row - 2)*n + col);
				grid_full.union(index, (row - 2)*n + col);
			}
			if (row < n && this.isOpen(row + 1, col)){
				grid.union(index, (row)*n + col);
				grid_full.union(index, (row)*n + col);
			}
			if (col < n && this.isOpen(row , col + 1)){
				grid.union(index, (row -1)* n + col + 1);
				grid_full.union(index, (row -1)* n + col + 1);
			}
			openess[row - 1][col - 1] = 1;
			opennum ++;
		}
	}
	public boolean isOpen(int row, int col){
		if (row < 1 || col < 1 || row > n || col > n){
			throw new IndexOutOfBoundsException(Integer.toString(row));
		}
		return openess[row - 1][col - 1] == 1;
	}
	public boolean isFull(int row, int col){
		int index = (row-1) * n + col;
		if (row < 1 || col < 1 || row > n || col > n){
			throw new IndexOutOfBoundsException(Integer.toString(row));
		}
		return grid_full.connected(0,index) && openess[row -1][col - 1] == 1;
	}
	
	public boolean percolates(){
		if (this.n == 1){
			return openess[0][0] == 1;
		}
		return grid.connected(0,n*n+1);
	}
	public int numberOfOpenSites(){
		return opennum;
	}
	public static void main(String[] args){
		boolean finished = false;
		Percolation test = new Percolation(20);
		int count = 0;
		while (!finished){
			int rol = StdRandom.uniform(1,21);
			int col = StdRandom.uniform(1,21);
			while (test.isOpen(rol,col)){
				rol = StdRandom.uniform(1,21);
				col = StdRandom.uniform(1,21);
			}
			test.open(rol,col);
			if (test.percolates()){
				finished = true;
			}
			count++;
		}
		System.out.println(count/400.0);
	}
}