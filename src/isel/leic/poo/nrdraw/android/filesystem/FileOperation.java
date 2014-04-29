package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.IOException;

import android.app.Activity;

public abstract class FileOperation {
	protected String fileName;
	protected Drawing drawing;
	protected Activity activity;
	
	public FileOperation(Activity activity, String fileName, Drawing drawing) {
		this.activity = activity;
		this.fileName = fileName;
		this.drawing = drawing;
	}
	
	public abstract void doOperation() throws IOException;
}
