package isel.leic.poo.nrdraw.android.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;

public class Loader extends FileOperation{

	private Line lineLoading;
	
	public Loader(Activity activity, String fileName, Drawing drawing) {
		super(activity, fileName, drawing);
	}
	
	@Override
	public void doOperation() throws IOException {
		load();
	}

	private void load() throws IOException {
		Scanner s = new Scanner(activity.openFileInput(fileName));
		while(s.hasNext()){
			treatLine(s.next());
		}
		s.close();
	}
	
	private void treatLine(String line){
		if(Drawing.isObjectString(line)){
			drawing.clear();
		}
		else if(Line.isObjectString(line)){
			lineLoading = new Line();
			drawing.add(lineLoading);
		}
		else if(Point.isObjectString(line)){
			lineLoading.add(Point.getFromString(line));
		}
		else{
			System.out.println("Unrecognized Line[" + line + "]");
		}
	}
	
}
