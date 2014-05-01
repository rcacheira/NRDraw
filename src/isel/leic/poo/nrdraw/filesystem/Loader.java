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
 * @author rcacheira
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
	
	@Override
	public void doOperation() throws IOException {
		openFile();
		Scanner s = new Scanner(fileInputStream);
		while(s.hasNext()){
			treatLine(s.next());
		}
		s.close();
	}

	protected void treatLine(String s){
		if(Drawing.isObjectString(s)){
			drawing.clear();
		}
		else if(Line.isObjectString(s)){
			lineLoading = createLine();
			drawing.add(lineLoading);
		}
		else if(AndroidPoint.isObjectString(s)){
			lineLoading.add(createPoint(parseFloatValue(s, "x"),
					parseFloatValue(s, "y")));
		}
		else{
			System.out.println("Unrecognized Line[" + s + "]");
		}
	}
	
	protected abstract Line createLine();
	
	protected abstract Point createPoint(float x, float y);
	
	protected void openFile() throws FileNotFoundException{
		fileInputStream = new FileInputStream(fileName);
	}
	
}
