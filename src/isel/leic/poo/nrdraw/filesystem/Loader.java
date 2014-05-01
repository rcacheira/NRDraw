package isel.leic.poo.nrdraw.filesystem;

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
		lineLoading = null;
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

	@Override
	public abstract void openFile() throws FileNotFoundException;
	
}
