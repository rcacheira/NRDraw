package isel.leic.poo.nrdraw.model;

/**
 * Class whose instance represents a Point
 * 
 * A point has a position, that is represented as a rectangular 
 * coordinates that must be positive values.
 * 
 * @author rcacheira & nreis
 *
 */
public class Point{
	/**
	 * The Point's position.
	 */
	private float x, y;
	
	/**
	 * Initiates an instance with the given coordinates.
	 * 
	 * @param x The horizontal coordinate value
	 * @param y The Vertical coordinate value
	 */
	public Point(float x, float y) {
		if(x<0 || y<0) 
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the Point's horizontal coordinate value
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
	public static boolean isObjectString(String s){
		return s.startsWith("Point:");
	}
	
	public static Point getFromString(String s){
		if(!isObjectString(s)) 
			throw new IllegalArgumentException();
		return new Point(
				getFloatValueFromString(s,"x"), getFloatValueFromString(s,"y"));
	}
	
	public static float getFloatValueFromString(String s, String var){
		int i0 = s.indexOf(var);
		if(i0<0)
			throw new IllegalArgumentException();
		int i1 = s.indexOf(",", i0);
//		if(i1 == -1){
//			i1 = s.indexOf("\n", i0);
//		}
		try{
			if(i1==-1)
				return Float.parseFloat(s.substring(i0+2));
			return Float.parseFloat(s.substring(i0+2, i1));
		}
		catch(Exception ex){
			throw new IllegalArgumentException();
		}
	}
	
}
