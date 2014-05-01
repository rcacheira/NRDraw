package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import isel.leic.poo.nrdraw.filesystem.Saver;

import java.io.FileNotFoundException;

import android.app.Activity;

public class AndroidSaver extends Saver{
	private Activity activity;
	
	public AndroidSaver(Activity activity, String fileName, AndroidDrawing drawing) {
		super(fileName, drawing);
		this.activity = activity;
	}
	
	@Override
	protected void openFile() throws FileNotFoundException {
		fileOutputStream = activity.openFileOutput(fileName, 0);
	}
	
}
