package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.R;
import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import isel.leic.poo.nrdraw.filesystem.Saver;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;

/**
 * Class whose instance represents an Android implementation of {@code Saver}.
 * 
 * @author rcacheira & nreis
 *
 */
public class AndroidSaver extends Saver{
	/**
	 * Activity used to save file.
	 */
	private Activity activity;
	
	/**
	 * Initiates an instance with the given Activity, View, fileName and Drawing.
	 * 
	 * @param activity Used to open file
	 * @param fileName File to save drawing
	 * @param drawing Drawing to save information on file
	 */
	public AndroidSaver(Activity activity, String fileName, AndroidDrawing drawing) {
		super(fileName, drawing);
		this.activity = activity;
	}
	
	/**
	 * Creates the file to be saved.
	 * 
	 * @throws FileNotFoundException when file was not created on fileSystem
	 */
	@Override
	protected void openFile() throws FileNotFoundException {
		fileOutputStream = activity.openFileOutput(fileName, 0);
	}
	
	/**
	 * Saves a drawing and shows an AlertDialog if some error occurs.
	 */
	public void save() {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setPositiveButton(R.string.button_ok,null);
		builder.setTitle(R.string.dialog_title_save);
		try{
			doOperation();
			builder.setMessage(R.string.message_save_ok);
		}
		catch(IOException e){
			e.printStackTrace();
			builder.setMessage(R.string.message_save_n_ok);
		}
		builder.show();
	}
	
}
