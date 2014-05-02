package isel.leic.poo.nrdraw.android.model;

import java.io.Serializable;

import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Implementation of Drawing in Android needs to implement {@code Serializable} to be saved on Activity method "onSaveInstanceState"
 * 
 * @author Nuno
 *
 */
public class AndroidDrawing extends Drawing implements AndroidDrawable, Serializable{
	/**
	 * Class serial version
	 */
	private static final long serialVersionUID = 1L;
	
	public void draw(Canvas canvas, Paint brush){
		if(brush == null || canvas  == null) throw new IllegalArgumentException();
		if(getNrOfLines() > 0){
			for (Line l : getLines()) {
				((AndroidLine)l).draw(canvas, brush);
			}
		}
	}
	
}
