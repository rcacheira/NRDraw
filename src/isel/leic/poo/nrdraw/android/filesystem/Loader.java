package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;

/**
 * Class used to load a file.
 * 
 * Extends from the class FileOperation.
 * 
 * @author rcacheira & nreis
 *
 */
public class Loader extends FileOperation{
	/**
	 * Used as an intermediary to load the lines and adding points in our drawing (treatLine).
	 */
	private Line lineLoading;
	
	/**
	 * 
	 * @param activity
	 * @param fileName
	 * @param drawing
	 */
	public Loader(Activity activity, String fileName, Drawing drawing) {
		super(activity, fileName, drawing);
	}
	/**
	 * Calls method load().
	 */
	@Override
	public void doOperation() throws IOException {
		load();
	}
	
	/**
	 * Loads the file stored.
	 * 
	 * @throws IOException if there is no file.
	 */
	private void load() throws IOException {
		Scanner s = new Scanner(activity.openFileInput(fileName));
		while(s.hasNext()){
			treatLine(s.next());
		}
		s.close();
	}
	
	/**
	 * Verifies what is being loaded and works accordingly.
	 * 
	 * @param line
	 */
	private void treatLine(String line){
		if(Drawing.isObjectString(line)){
			drawing.clear();
		}
		else if(Line.isObjectString(line)){
			lineLoading = new Line();
			drawing.add(lineLoading);
		}
		else if(Point.isObjectString(line)){
			lineLoading.add(Point.getFromString(line));
		}
		else{
			System.out.println("Unrecognized Line[" + line + "]");
		}
	}
	
}
