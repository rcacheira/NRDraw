package isel.leic.poo.nrdraw.model;

import java.util.LinkedList;

/**
 * Class whose instance represents a Line.
 * 
 * Line is composed by a set of points.
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class Line {
	/**
	 * The Line's set of points.
	 */
	private LinkedList<Point> points;
	
	/**
	 * Initiates an instance without points.
	 */
	public Line(){
		points = new LinkedList<Point>();
	}
	
	/**
	 * Initiates an instance with the given first point.
	 * 
	 * @param firstPoint
	 */
	public Line(Point firstPoint){
		points = new LinkedList<Point>();
		points.add(firstPoint);
	}
	
	/**
	 * Adds a point to Line.
	 * 
	 * @param p Point to add
	 */
	public void add(Point p) {
		points.add(p);
	}
	
	/**
	 * Gets the Line number of Points.
	 * 
	 * @return the instance number of Points
	 */
	public int getNrOfPoints(){
		return points.size();
	}
	
	/**
	 * Gets the Line first point.
	 * 
	 * It's useful when line has only one point.
	 * 
	 * @return the instance list first point
	 */
	public Point getFirstPoint(){
		return points.getFirst();
	}
	
	/**
	 * Gets the Line set of points.
	 * 
	 * @return the instance iterable list of points
	 */
	public Iterable<Point> getPoints(){
		return points;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Line:\n");
		for (Point point : points) {
			sb.append(point);
			sb.append('\n');
		}
		return sb.toString();
	}
	
	/**
	 * Verifies if String represents a Line.
	 * 
	 * @param s String to verify
	 * @return {@code true} in case of String represents a Line, {@code false} otherwise
	 */
	public static boolean isObjectString(String s){
		return s.startsWith("Line:");
	}
}
