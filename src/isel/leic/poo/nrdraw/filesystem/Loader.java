package isel.leic.poo.nrdraw.filesystem;

import isel.leic.poo.nrdraw.android.model.AndroidPoint;
import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class whose instance represents a Drawing Loader.
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class Loader extends FileOperation{
	/**
	 * InputStream used to manipulate the file.
	 */
	protected FileInputStream fileInputStream;
	/**
	 * Instance of line that represents the line being loaded.
	 */
	private Line lineLoading;
	
	/**
	 * Initiates an instance of Loader with the given fileName and Drawing.
	 * 
	 * @param fileName File on which the action is taken
	 * @param drawing Drawing on which the action is taken
	 */
	public Loader(String fileName, Drawing drawing) {
		super(fileName, drawing);
		this.fileInputStream = null;
	}
	
	/**
	 * Load Drawing on file with name given on constructor,
	 * to drawing given on constructor.
	 * 
	 * @throws IOException if no such file exists.
	 */
	@Override
	public void doOperation() throws IOException {
		openFile();
		Scanner s = new Scanner(fileInputStream);
		while(s.hasNext()){
			parseFileLine(s.next());
		}
		s.close();
	}

	/**
	 * Parses a line and does the work necessary to process the line.
	 * 
	 * @param line Line to be parsed
	 */
	protected void parseFileLine(String line){
		if(Drawing.isADrawingString(line)){
			drawing.clear();
		}
		else if(Line.isALineString(line)){
			lineLoading = createLine();
			drawing.add(lineLoading);
		}
		else if(AndroidPoint.isAPointString(line)){
			lineLoading.add(createPoint(parseFloatVarFromString(line, "x"),
					parseFloatVarFromString(line, "y")));
		}
		else{
			System.out.println("Unrecognized Line[" + line + "]");
		}
	}
	
	/**
	 * Creates a new line.
	 * 
	 * @return New Line
	 */
	protected abstract Line createLine();
	
	/**
	 * Creates a Point.
	 * 
	 * @param x Horizontal coordinate value
	 * @param y Vertical coordinate value
	 * @return New Point with the given coordinates
	 */
	protected abstract Point createPoint(float x, float y);
	
}
