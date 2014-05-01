package isel.leic.poo.nrdraw.filesystem;

import isel.leic.poo.nrdraw.android.model.AndroidPoint;
import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class Loader extends FileOperation{
	protected FileInputStream fileInputStream;
	private Line lineLoading;
	
	public Loader(String fileName, Drawing drawing) {
		super(fileName, drawing);
		this.fileInputStream = null;
	}
	
	@Override
	public void doOperation() throws IOException {
		openFile();
		Scanner s = new Scanner(fileInputStream);
		while(s.hasNext()){
			treatLine(s.next());
		}
		s.close();
	}

	protected void treatLine(String s){
		if(Drawing.isObjectString(s)){
			drawing.clear();
		}
		else if(Line.isObjectString(s)){
			lineLoading = createLine();
			drawing.add(lineLoading);
		}
		else if(AndroidPoint.isObjectString(s)){
			lineLoading.add(createPoint(getFloatValueFromString(s, "x"),
					getFloatValueFromString(s, "y")));
		}
		else{
			System.out.println("Unrecognized Line[" + s + "]");
		}
	}
	
	protected abstract Line createLine();
	
	protected abstract Point createPoint(float x, float y);
	
	@Override
	protected abstract void openFile() throws FileNotFoundException;
	
}
