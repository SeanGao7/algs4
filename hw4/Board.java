import java.lang.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Board {
	private int[][] tiles;
	private int N; 
	
    public Board(int[][] blocks){
		N = blocks.length;
		tiles = new int[N][N];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				tiles[i][j] = blocks[i][j];
			}
		}
	}
	
    public int dimension(){
		return N;
	}
	
    public int hamming(){
		int ham = 0;
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (tiles[i][j] != i * N + j +1 && tiles[i][j] != 0){
					ham++;
				}
			}
		}
		return ham;
	}
	
    public int manhattan(){
		int maha = 0;
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				if (tiles[i][j] != 0){
					maha += Math.abs((tiles[i][j] - 1) / N - i);
					maha += Math.abs((tiles[i][j] - 1) % N - j);
				}
			}
		}
		return maha;
	}
	
    public boolean isGoal(){
		return this.hamming() == 0;
	}
	
    public Board twin(){
		if (N == 1){
			return new Board(this.tiles);
		}
		else {
		int[][] temp = copy(this.tiles);
		if (temp[0][0] == 0 || temp[0][1] == 0){
				this.swap(temp,1,0,1,1);
				return new Board(temp);
			}
			this.swap(temp,0,0,0,1);
			return new Board(temp);
		}
	}
	
	private int[][] copy(int[][]A){
		int[][] B = new int[A.length][A[0].length];
		for (int i = 0; i < A.length; i++){
			B[i] = Arrays.copyOf(A[i],N);
		}
		return B;
	}
	
	private void swap(int[][] A, int i1,int j1, int i2, int j2){
		int swap = A[i1][j1];
		A[i1][j1] = A[i2][j2];
		A[i2][j2] = swap;
	}
	
    public boolean equals(Object y){
		if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
		Board x = (Board) y;
		if (this.N != x.N) return false;
		for (int i = 0; i < N; i++){
			if (!Arrays.equals(x.tiles[i],this.tiles[i])){
				return false;
			}
		}
		return true;
	}
	
    public Iterable<Board> neighbors(){
		ArrayList<Board> neighbor = new ArrayList<Board>();
		int i0 = 0;
		int j0 = 0;
		outerloop:
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				i0 = i;
				j0 = j;
				if (tiles[i][j]==0){
					break outerloop;
				}
			}
		}
		int[][] temp = copy(this.tiles);
		if (i0 > 0){
			swap(temp,i0,j0,i0-1,j0);
			neighbor.add(new Board(temp));
			swap(temp,i0-1,j0,i0,j0);
		}
		if (i0 + 1 <= N - 1 ){
			swap(temp,i0,j0,i0+1,j0);
			neighbor.add(new Board(temp));
			swap(temp,i0+1,j0,i0,j0);
		}
		if (j0 > 0){
			swap(temp,i0,j0,i0,j0-1);
			neighbor.add(new Board(temp));
			swap(temp,i0,j0-1,i0,j0);
		}
		if (j0 + 1 <= N - 1){
			swap(temp,i0,j0,i0,j0+1);
			neighbor.add(new Board(temp));
			swap(temp,i0,j0+1,i0,j0);
		}
		return neighbor;
	}
	
    public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++){
				s.append(tiles[i][j]);
				s.append(" ");
			}
			s.append("\n");
		}
		return s.toString();
	}

    public static void main(String[] args){
		
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		int[][] blocksB = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++){
				blocks[i][j] = in.readInt();
				blocksB[i][j] = blocks[i][j];
			}
		Board initial = new Board(blocks);
		Board initialB = new Board(blocksB);
		System.out.println(initial.toString());
		for (Board x : initial.neighbors()){
			System.out.println(x.toString());
		}
		System.out.println(initial.toString());
		System.out.println(initial.twin().toString());
		System.out.println(initial.toString());
		System.out.println(initial.equals(initialB));
		System.out.println(initial.toString());
		
	}
}