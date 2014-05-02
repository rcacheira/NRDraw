package isel.leic.poo.nrdraw.android.model;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Paint;
import isel.leic.poo.nrdraw.model.Point;

/**
 * Implementation of Drawing in Android needs to implement {@code Serializable} to be saved on AndroidDrawing/AndroidLine save by Activity method "onSaveInstanceState" 
 * 
 * @author Nuno
 *
 */
public class AndroidPoint extends Point implements AndroidDrawable, Serializable{
	/**
	 * Class Serial Version
	 */
	private static final long serialVersionUID = 1L;

	public AndroidPoint(float x, float y) {
		super(x, y);
	}

	public void draw(Canvas canvas, Paint brush){
		if(canvas == null || brush == null) throw new IllegalArgumentException();
		canvas.drawPoint(getX(), getY(), brush);
	}
	
}
