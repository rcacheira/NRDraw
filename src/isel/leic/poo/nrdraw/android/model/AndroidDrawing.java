package isel.leic.poo.nrdraw.android.model;

import java.io.InvalidClassException;
import java.io.Serializable;

import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Implementation of Drawing in Android implements {@code Serializable} 
 * to be saved on Activity method {@code onSaveInstanceState}.
 * 
 * @author rcacheira & nreis
 *
 */
public class AndroidDrawing extends Drawing implements AndroidDrawable, Serializable{
	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Draws Drawing on given canvas using given brush.
	 * 
	 * @throws IllegalArgumentException if there is no canvas or brush
	 * @throws InvalidClassException if any line existent on list isn't an 
	 * instance of AndroidLine
	 */
	public void draw(Canvas canvas, Paint brush) throws InvalidClassException{
		if(brush == null || canvas  == null) throw new IllegalArgumentException();
		if(getNrOfLines() > 0){
			for (Line l : getLines()) {
				if(!(l instanceof AndroidLine)){
					throw new InvalidClassException("Line isn't an instance of Android Line");
				}
				((AndroidLine)l).draw(canvas, brush);
			}
		}
	}
	
}
