package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.IOException;

import android.app.Activity;

/**
 * Abstract class that works with the files,
 * ie. the saved files. 
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class FileOperation {
	/**
	 * Used to know the name of the file used
	 */
	protected String fileName;
	/**
	 * Used in function of the android application.
	 * Necessary due to the use of save and load on an android application.
	 */
	protected Drawing drawing;
	/**
	 * Used in function of the android application.
	 * Necessary due to the use of save and load on an android application.
	 */
	protected Activity activity;
	
	/**
	 * Initiates a file operation.
	 * 
	 * @param activity
	 * @param fileName	name of the created file
	 * @param drawing
	 */
	public FileOperation(Activity activity, String fileName, Drawing drawing) {
		this.activity = activity;
		this.fileName = fileName;
		this.drawing = drawing;
	}
	
	public abstract void doOperation() throws IOException;
}
