package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;

/**
 * Class used to save the drawing in a file.
 * 
 * Extends from the class FileOperation.
 * 
 * @author rcacheira & nreis
 *
 */
public class Saver extends FileOperation{
	/**
	 * 
	 * @param activity
	 * @param fileName
	 * @param drawing
	 */
	public Saver(Activity activity, String fileName, Drawing drawing) {
		super(activity, fileName, drawing);
	}
	
	/**
	 * Calls method save()
	 */
	@Override
	public void doOperation() throws IOException {
		save();
	}
	
	/**
	 * Saves our drawing inside the file.
	 * 
	 * @throws IOException if no file was created
	 */
	private void save() throws IOException{
		PrintWriter p = new PrintWriter(activity.openFileOutput(fileName, 0));
		p.print(drawing);
		//p.flush();		Not necessary because method close() does it.
		p.close();
	}
	
}