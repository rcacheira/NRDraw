package isel.leic.poo.nrdraw.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Saver extends FileOperation{
	protected FileOutputStream fileOutputStream;
	
	public Saver(String fileName, Drawing drawing) {
		super(fileName, drawing);
	}
	
	@Override
	public void doOperation() throws IOException {
		openFile();
		PrintWriter p = new PrintWriter(fileOutputStream);
		p.print(drawing);
		p.flush();
		p.close();
	}

	protected void openFile() throws FileNotFoundException{
		fileOutputStream = new FileOutputStream(fileName, false);
	}
}
