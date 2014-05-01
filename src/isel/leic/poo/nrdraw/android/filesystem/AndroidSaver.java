package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.R;
import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import isel.leic.poo.nrdraw.filesystem.Saver;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;

public class AndroidSaver extends Saver{
	private Activity activity;
	
	public AndroidSaver(Activity activity, String fileName, AndroidDrawing drawing) {
		super(fileName, drawing);
		this.activity = activity;
	}
	
	public void save() {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(R.string.dialog_title_save);
		try{
			super.doOperation();
			builder.setMessage(R.string.message_save_ok);
		}
		catch(IOException e){
			e.printStackTrace();
			builder.setMessage(R.string.message_save_n_ok);
		}
		builder.show();
	}
	
	@Override
	protected void openFile() throws FileNotFoundException {
		fileOutputStream = activity.openFileOutput(fileName, 0);
	}
	
}
