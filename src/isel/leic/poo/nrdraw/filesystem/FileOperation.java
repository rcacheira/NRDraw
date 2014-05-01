package isel.leic.poo.nrdraw.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileOperation {
	protected String fileName;
	protected Drawing drawing;
	
	public FileOperation(String fileName, Drawing drawing) {
		this.fileName = fileName;
		this.drawing = drawing;
	}
	
	protected abstract void openFile() throws FileNotFoundException;
	
	public abstract void doOperation() throws IOException;
	
	protected static final float getFloatValueFromString(String s, String var){
		int i0 = s.indexOf(var);
		if(i0<0)
			throw new IllegalArgumentException();
		int i1 = s.indexOf(",", i0);
		try{
			if(i1==-1)
				return Float.parseFloat(s.substring(i0+2));
			return Float.parseFloat(s.substring(i0+2, i1));
		}
		catch(Exception ex){
			throw new IllegalArgumentException();
		}
	}
}
