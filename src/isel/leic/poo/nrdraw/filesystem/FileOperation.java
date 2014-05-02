package isel.leic.poo.nrdraw.filesystem;

import isel.leic.poo.nrdraw.model.Drawing;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Abstract class whose instance represents a FileOperation.
 * 
 * @author rcacheira & nreis
 *
 */
public abstract class FileOperation {
	/**
	 * File on which the action is taken.
	 */
	protected String fileName;
	/**
	 * Drawing on which the action is taken.
	 */
	protected Drawing drawing;
	
	/**
	 * Initiates an instance of File Operation with the given fileName and Drawing.
	 * 
	 * @param fileName File on which the action is taken
	 * @param drawing Drawing on which the action is taken
	 */
	public FileOperation(String fileName, Drawing drawing) {
		this.fileName = fileName;
		this.drawing = drawing;
	}
	
	/**
	 * Does the file operation.
	 * 
	 * @throws IOException
	 */
	protected abstract void doOperation() throws IOException;
	
	/**
	 * Opens the {@code FileOutputStream} present in this class.
	 * 
	 * @throws FileNotFoundException if file cannot be opened for writing
	 */
	protected abstract void openFile() throws FileNotFoundException;
	
	/**
	 * Parses a value on a String using our own notation schema
	 * 
	 * @param s String to be parsed
	 * @param varName Variable to find on String
	 * @return a value parsed
	 * @throws IllegalArgumentException if varName isn't found on String s
	 */
	protected static final String parseVarFromString(String s, String varName){
		int i0 = s.indexOf(varName);
		if(i0<0)
			throw new IllegalArgumentException();
		int i1 = s.indexOf(",", i0);
		if(i1==-1)
			return s.substring(i0+2);
		return s.substring(i0+2, i1);
	}
	
	/**
	 * Parses a float value on a String using our own notation schema
	 * 
	 * @param s String to be parsed
	 * @param varName Variable to find on String
	 * @return a value parse
	 * @throws IllegalArgumentException if varName isn't found on String s
	 * @throws NumberFormatException if parsed value for String isn't a float
	 */
	protected static final float parseFloatVarFromString(String s, String varName){
		return Float.parseFloat(parseVarFromString(s,varName));
	}
}
