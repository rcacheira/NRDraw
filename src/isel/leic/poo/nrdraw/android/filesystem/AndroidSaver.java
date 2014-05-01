package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.filesystem.Saver;
import isel.leic.poo.nrdraw.model.Drawing;

import java.io.FileNotFoundException;

import android.app.Activity;

public class AndroidSaver extends Saver{
	private Activity activity;
	
	public AndroidSaver(Activity activity, String fileName, Drawing drawing) {
		super(fileName,drawing);
		this.activity = activity;
	}

	@Override
	public void openFile() throws FileNotFoundException {
		fileOutputStream = activity.openFileOutput(fileName, 0);
	}
	
}
