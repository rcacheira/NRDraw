package isel.leic.poo.nrdraw.android.model;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Paint;
import isel.leic.poo.nrdraw.model.Point;

/**
 * Implementation of Drawing in Android implement {@code Serializable} 
 * to be saved on AndroidDrawing/AndroidLine save by Activity method 
 * {@code onSaveInstanceState}
 * 
 * @author rcacheira & nreis
 *
 */
public class AndroidPoint extends Point implements AndroidDrawable, Serializable{
	/**
	 * Class Serial Version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initiates an instance with the given coordinates.
	 * 
	 * @param x The horizontal coordinate value
	 * @param y The vertical coordinate value
	 */
	public AndroidPoint(float x, float y) {
		super(x, y);
	}

	/**
	 * Draws point on the given canvas using the given brush.
	 * 
	 * @throws IllegalArgumentException if there is no canvas or brush
	 */
	public void draw(Canvas canvas, Paint brush){
		if(canvas == null || brush == null) throw new IllegalArgumentException();
		canvas.drawPoint(getX(), getY(), brush);
	}
	
}
