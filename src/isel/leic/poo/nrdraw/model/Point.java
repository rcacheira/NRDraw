package isel.leic.poo.nrdraw.model;

/**
 * Class whose instance represents a Point.
 * 
 * Point has a position, that is represented as an x and y 
 * coordinates that must be positive values.
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class Point{
	/**
	 * The Point's position.
	 */
	private float x, y;
	
	/**
	 * Initiates an instance with the given coordinates.
	 * 
	 * @param x The horizontal coordinate value
	 * @param y The Vertical coordinate value
	 * @throws IllegalArgumentException when any of the coordinates are negative
	 */
	public Point(float x, float y) {
		if(x<0 || y<0) 
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the Point's horizontal coordinate value.
	 * 
	 * @return The instance horizontal value
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Gets the Point's vertical coordinate value
	 * 
	 * @return The instance vertical value
	 */
	public float getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "Point:x=" + x + ",y=" + y;
	}
	
	/**
	 * Verifies if String represents a Point
	 * 
	 * @param s String to verify
	 * @return {@code true} in case of String represents a Point, {@code false} otherwise
	 */
	public static boolean isAPointString(String s){
		return s.startsWith("Point:");
	}
	
}
