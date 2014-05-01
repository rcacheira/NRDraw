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
	
	public abstract void openFile() throws FileNotFoundException;
	
	public abstract void doOperation() throws IOException;
}
