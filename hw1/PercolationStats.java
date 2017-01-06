import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	private double[] result;
	private double mean;
	private double stddev;
	private double total;
	private double trials;
	public PercolationStats(int n, int trials){
		if (n <= 0){
			throw new IllegalArgumentException(Integer.toString(n));
		}
		if (trials <= 0){
			throw new IllegalArgumentException(Integer.toString(trials));
		}
		result = new double[trials];
		this.total = n * n;
		for (int i = 0; i < trials; i++){
			boolean finished = false;
			Percolation test = new Percolation(n);
			int count = 0;
			while (!finished){
				int rol = StdRandom.uniform(1,n+1);
				int col = StdRandom.uniform(1,n+1);
				while (test.isOpen(rol,col)){
					rol = StdRandom.uniform(1,n+1);
					col = StdRandom.uniform(1,n+1);
				}
				test.open(rol,col);
				if (test.percolates()){
					finished = true;
				}
				count++;
			}
			result[i] = count / this.total;
		}
		this.trials = trials;
	}
	public double mean(){
		this.mean = StdStats.mean(result); 
		return this.mean;
	}
	public double stddev(){
		this.stddev = StdStats.stddev(result);
		return this.stddev;
	}
	public double confidenceLo(){
		return this.mean() - 1.96 * this.stddev() / java.lang.Math.sqrt(this.trials);
	}
	public double confidenceHi(){
		return this.mean() + 1.96 * this.stddev() / java.lang.Math.sqrt(this.trials);
	}
	public static void main(String[] args){
		PercolationStats test = new PercolationStats (Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		System.out.println("mean                   " + "= " + test.mean());
		System.out.println("stddev                 " + "= " + test.stddev());
		System.out.println("95% confidence interval" + "= " + test.confidenceLo() + ", "+ test.confidenceHi());
	}
}
	