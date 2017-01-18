import java.util.*;
import edu.princeton.cs.algs4.*;
public class FastCollinearPoints {
	private LineSegment[] seg;
	private ArrayList<checked> Checked = new ArrayList<checked>();
	
    public FastCollinearPoints(Point[] points){
		if (points == null){
			throw new java.lang.NullPointerException();
	    }
		for (int i = 0; i < points.length; i++){
			if (points[i] == null){
				throw new java.lang.NullPointerException();
			}
			for (int l = i+1; l < points.length; l++){
				if (points[i].compareTo(points[l])== 0){
					throw new java.lang.IllegalArgumentException();
				}
			}
		}
		
		ArrayList <LineSegment> lines = new ArrayList <LineSegment> ();
		Point[] copy = Arrays.copyOf(points,points.length);
		ArrayList <Point> temp = new ArrayList <Point>();
		
		for (int i = 0; i < points.length; i++){
			Arrays.sort(copy,points[i].slopeOrder());
			for(int j = 1; j < copy.length; j++){
				int k = j;
				while(k < copy.length && copy[k].slopeTo(points[i]) == copy[j].slopeTo(points[i])){
					temp.add(copy[k]);
					k++;
				}
				k--;
				if (temp.size() >= 3){
					temp.add(points[i]);
					Collections.sort(temp);
					if (!isDuplicate(temp.get(0),temp.get(temp.size()-1))){
						lines.add(new LineSegment(temp.get(0),temp.get(temp.size()-1)));
						this.Checked.add(new checked (temp.get(0),temp.get(temp.size()-1)));
					}
				}
				temp.clear();
				j = k;
			}
		}
		seg = lines.toArray(new LineSegment[lines.size()]);
		
    }
    public int numberOfSegments(){
		return seg.length;
	}
	public LineSegment[] segments(){
		LineSegment[] copy = Arrays.copyOf(seg,seg.length);
		return copy;
	}
	private boolean isDuplicate(Point endpoint, Point startpoint){
		for (checked item: this.Checked){
			if (item.endpoint.compareTo(endpoint) == 0 && item.startpoint.compareTo(startpoint) == 0){
				return true;
			}
		}
		return false;
	}
	private class checked{
		public Point endpoint;
		public Point startpoint;
		public checked(Point endpoint, Point startpoint){
			this.endpoint = endpoint;
			this.startpoint = startpoint;
		}
	}
	public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}