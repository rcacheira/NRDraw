package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.filesystem.Loader;
import isel.leic.poo.nrdraw.model.Drawing;

import java.io.FileNotFoundException;

import android.app.Activity;

public class AndroidLoader extends Loader{
	private Activity activity;
	
	public AndroidLoader(Activity activity, String fileName, Drawing drawing) {
		super(fileName, drawing);
		this.activity = activity;
	}

	@Override
	public void openFile() throws FileNotFoundException {
		fileInputStream = activity.openFileInput(fileName);
	}
	
}
