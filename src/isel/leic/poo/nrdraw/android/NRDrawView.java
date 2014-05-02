package isel.leic.poo.nrdraw.android;

import java.io.InvalidClassException;

import isel.leic.poo.nrdraw.android.model.AndroidDrawable;
import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * Class whose instance represents the View where drawing is possible.
 * 
 * @author rcacheira & nreis
 *
 */
public class NRDrawView extends View {
	/**
	 * Used to draw.
	 */
	private Paint brush;
	/**
	 * Drawing of the android application.
	 */
	private AndroidDrawing drawing;
	
	/**
	 * Constructor that is called when inflating a view from XML.
	 * 
	 * This is called when a view is being constructed from an XML file,
	 * supplying attributes that were specified in the XML file.
	 * 
	 * This version uses a default style of 0,
	 * so the only attribute values applied are those in the Context's Theme
	 * and the given AttributeSet.
	 * 
	 * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param attrs The attributes of the XML tag that is inflating the view. 
	 */
	public NRDrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		brush = new Paint();
		//TODO change that
		brush.setStrokeWidth(5);
		drawing = null;
	}
	
	/**
	 * Changes the instance drawing.
	 * 
	 * @param androidDrawing new reference to drawing.
	 */
	public void setDrawing(AndroidDrawing androidDrawing){
		drawing = androidDrawing;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		try{
			drawObject(drawing, canvas);
		}
		catch(InvalidClassException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws on {@code Canvas}.
	 * 
	 * @param canvas Where it is possible to draw.
	 * @param brush Used to draw.
	 * @throws InvalidClassException //TODO: ...
	 */
	protected void drawObject(AndroidDrawable androidDrawable, Canvas canvas) throws InvalidClassException{
		androidDrawable.draw(canvas, brush);
	}
}
