import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
public class BruteCollinearPoints {
	private LineSegment[] seg;
    public BruteCollinearPoints(Point[] points){
	    if (points == null){
			throw new java.lang.NullPointerException();
	    }
		for (int i = 0; i < points.length; i++){
			if (points[i] == null){
				throw new java.lang.NullPointerException();
			}
			for (int l = i+1; l < points.length; l++){
				if (points[i].compareTo(points[l]) == 0){
					throw new java.lang.IllegalArgumentException();
				}
			}
		}
		ArrayList <LineSegment> lines = new ArrayList <LineSegment> ();
		Point[] copy = Arrays.copyOf(points,points.length);
		Arrays.sort(copy);
		for (int p = 0; p < copy.length - 3; p++) {
            for (int q = p + 1; q < copy.length - 2; q++) {
                for (int r = q + 1; r < copy.length - 1; r++) {
                    for (int s = r + 1; s < copy.length; s++) {
                        if (copy[p].slopeTo(copy[q]) == copy[p].slopeTo(copy[r]) && copy[p].slopeTo(copy[q]) == copy[p].slopeTo(copy[s])) {
                            lines.add(new LineSegment(copy[p], copy[s]));
                        }
                    }
                }
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
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}