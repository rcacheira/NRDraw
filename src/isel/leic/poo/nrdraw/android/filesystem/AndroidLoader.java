package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import isel.leic.poo.nrdraw.android.model.AndroidLine;
import isel.leic.poo.nrdraw.android.model.AndroidPoint;
import isel.leic.poo.nrdraw.filesystem.Loader;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;

public class AndroidLoader extends Loader{
	private Activity activity;
	public AndroidLoader(Activity activity, String fileName, AndroidDrawing drawing) {
		super(fileName, drawing);
		this.activity = activity;
	}

	@Override
	public void doOperation() throws IOException {
		super.doOperation();
	}
	
	@Override
	protected void openFile() throws FileNotFoundException {
		fileInputStream = activity.openFileInput(fileName);
	}

	@Override
	protected AndroidLine createLine() {
		return new AndroidLine();
	}

	@Override
	protected AndroidPoint createPoint(float x, float y) {
		return new AndroidPoint(x, y);
	}
	
}
