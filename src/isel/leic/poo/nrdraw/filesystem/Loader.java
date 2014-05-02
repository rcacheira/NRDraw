package isel.leic.poo.nrdraw.filesystem;

import isel.leic.poo.nrdraw.android.model.AndroidPoint;
import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class whose instance represents a Drawing Loader
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class Loader extends FileOperation{
	/**
	 * InputStream used to manipulate the file
	 */
	protected FileInputStream fileInputStream;
	/**
	 * Instance of line that represent the line being loaded
	 */
	private Line lineLoading;
	
	/**
	 * Initiates an instance of Loader with the given fileName and Drawing
	 * 
	 * @param fileName File on which the action is taken
	 * @param drawing Drawing on which the action is taken
	 */
	public Loader(String fileName, Drawing drawing) {
		super(fileName, drawing);
		this.fileInputStream = null;
	}
	
	/**
	 * Load Drawing on file with name given on constructor, to drawing given on 
	 * constructor
	 * 
	 * @throws IOException
	 */
	@Override
	public void doOperation() throws IOException {
		openFile();
		Scanner s = new Scanner(fileInputStream);
		while(s.hasNext()){
			parseLine(s.next());
		}
		s.close();
	}

	/**
	 * Parses a line and do the work necessary for process line
	 * 
	 * @param line Line to be parsed
	 */
	protected void parseLine(String line){
		if(Drawing.isObjectString(line)){
			drawing.clear();
		}
		else if(Line.isObjectString(line)){
			lineLoading = createLine();
			drawing.add(lineLoading);
		}
		else if(AndroidPoint.isObjectString(line)){
			lineLoading.add(createPoint(parseFloatValue(line, "x"),
					parseFloatValue(line, "y")));
		}
		else{
			System.out.println("Unrecognized Line[" + line + "]");
		}
	}
	
	/**
	 * Creates a new line
	 * 
	 * @return New Line
	 */
	protected abstract Line createLine();
	
	/**
	 * Creates a Point
	 * 
	 * @param x Horizontal coordinate value
	 * @param y Vertical coordinate value
	 * @return New Point with the given coordinates
	 */
	protected abstract Point createPoint(float x, float y);
	
	/**
	 * Opens the {@code FileInputStream} present in this class
	 * 
	 * @throws FileNotFoundException when file does not exists on fileSystem
	 */
	protected void openFile() throws FileNotFoundException{
		fileInputStream = new FileInputStream(fileName);
	}
	
}
