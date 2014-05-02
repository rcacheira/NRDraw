package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.R;
import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import isel.leic.poo.nrdraw.android.model.AndroidLine;
import isel.leic.poo.nrdraw.android.model.AndroidPoint;
import isel.leic.poo.nrdraw.filesystem.Loader;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

/**
 * Class whose instance represents a an Adroid implmentation of Loader
 * 
 * @author rcacheira & nreis
 *
 */
public class AndroidLoader extends Loader{
	/**
	 * Activity used to open file
	 */
	private Activity activity;
	/**
	 * View needs be updated after a good load operation 
	 */
	private View view;
	
	/**
	 * Initiates an instance with the given Activity, View, fileName and Drawing
	 * 
	 * @param activity Used to open file
	 * @param view That needs to be updated
	 * @param fileName File to load drawing
	 * @param drawing Drawing to load information saved on file
	 */
	public AndroidLoader(Activity activity, View view, String fileName, AndroidDrawing drawing) {
		super(fileName, drawing);
		this.activity = activity;
		this.view = view;
	}

	/**
	 * Loads a drawing and show an AlertDialog if some error occurs
	 */
	public void load(){
		try{
			doOperation();
			view.invalidate();
		}
		catch(IOException e){
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setPositiveButton(R.string.button_ok,null);
			builder.setTitle(R.string.dialog_title_load);
			builder.setMessage(R.string.message_load_n_ok);
			builder.show();
		}
	}
	
	/**
	 * Opens the file to be loaded
	 * 
	 * @throws FileNotFoundException when file does not exists on fileSystem
	 */
	@Override
	protected void openFile() throws FileNotFoundException {
		fileInputStream = activity.openFileInput(fileName);
	}

	/**
	 * Creates a new AndroidLine
	 * 
	 * @return the new AndroidLine
	 */
	@Override
	protected AndroidLine createLine() {
		return new AndroidLine();
	}

	/**
	 * Creates a new AndroidPoint with the given coordinates
	 * 
	 * @param x The horizontal coordinate value
	 * @param y The vertical coordinate value
	 * @return created AndroidPoint
	 */
	@Override
	protected AndroidPoint createPoint(float x, float y) {
		return new AndroidPoint(x, y);
	}
	
}
