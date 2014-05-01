package isel.leic.poo.nrdraw.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Class whose instance represents a drawing.
 * 
<<<<<<< HEAD
 * A Drawing is represented by a set of lines.
=======
 * A Drawing contains a set of lines
>>>>>>> Ricardo
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class Drawing implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The Drawing's set of lines.
	 */
	private List<Line> lines;
	
	/**
	 * Initiates an instance without lines.
	 */
	public Drawing() {
		lines = new LinkedList<Line>();
	}
	
	/**
	 * Adds a line to Drawing.
	 * 
	 * @param l Line to add
	 */
	public void add(Line l){
		lines.add(l);
	}
	
	/**
	 * Get's the Drawing number of lines.
	 * 
	 * @return the instance number of lines
	 */
	public int getNrOfLines(){
		return lines.size();
	}

	/**
	 * Get's the Drawing set of lines.
	 * 
	 * @return the instance iterable list of lines 
	 */
	public Iterable<Line> getLines(){
		return lines;
	}
	
	/**
	 * Clears the Drawing Lines.
	 */
	public void clear(){
		lines.clear();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Drawing:\n");
		for(Line line: lines){
			sb.append(line);
			sb.append('\n');
		}
		return sb.toString();
	}
	
	/**
	 * Verifies if String represents a Drawing.
	 * 
	 * @param s String to verify
	 * @return {@code true} in case of String represents a Drawing, {@code false} otherwise
	 */
	public static boolean isObjectString(String s){
		return s.startsWith("Drawing:");
	}
}
