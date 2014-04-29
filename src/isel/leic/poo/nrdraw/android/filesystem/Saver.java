package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;

public class Saver extends FileOperation{

	public Saver(Activity activity, String fileName, Drawing drawing) {
		super(activity, fileName, drawing);
	}
	
	@Override
	public void doOperation() throws IOException {
		save();
	}

	private void save() throws IOException{
		PrintWriter p = new PrintWriter(activity.openFileOutput(fileName, 0));
		p.print(drawing);
		p.flush();
		p.close();
	}
	
}
