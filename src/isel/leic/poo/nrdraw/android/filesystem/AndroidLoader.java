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

public class AndroidLoader extends Loader{
	private Activity activity;
	private View view;
	
	public AndroidLoader(Activity activity, View view, String fileName, AndroidDrawing drawing) {
		super(fileName, drawing);
		this.activity = activity;
		this.view = view;
	}

	public void load(){
		try{
			doOperation();
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
	
	@Override
	public void doOperation() throws IOException {
		super.doOperation();
		view.invalidate();
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
