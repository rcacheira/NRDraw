package isel.leic.poo.nrdraw.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class whose instance represents a Drawing Saver
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class Saver extends FileOperation{
	/**
	 * OutputStream to manipulate the Drawing
	 */
	protected FileOutputStream fileOutputStream;
	
	/**
	 * Initiates an instance of Saver with the given fileName and Drawing.
	 * 
	 * @param fileName Name of the file.
	 * @param drawing Drawing on which the action is taken.
	 */
	public Saver(String fileName, Drawing drawing) {
		super(fileName, drawing);
	}
	
	/**
	 * Saves Drawing given on constructor,
	 * to file with the name passed on the constructor.
	 */
	@Override
	public void doOperation() throws IOException {
		openFile();
		PrintWriter p = new PrintWriter(fileOutputStream);
		p.print(drawing);
		p.close();
	}

	/**
	 * Opens the {@code FileOutputStream} present in this class.
	 * 
	 * @throws FileNotFoundException if file cannot be opened for writing
	 */
	protected void openFile() throws FileNotFoundException{
		fileOutputStream = new FileOutputStream(fileName, false);
	}
}
