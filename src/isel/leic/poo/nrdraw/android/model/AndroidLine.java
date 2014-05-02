package isel.leic.poo.nrdraw.android.model;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.Iterator;

import android.graphics.Canvas;
import android.graphics.Paint;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

/**
 * Implementation of Drawing in Android implements {@code Serializable}
 * to be saved on AndroidDrawing save by Activity method "onSaveInstanceState" 
 * 
 * @author rcacheira & nreis
 *
 */
public class AndroidLine extends Line implements AndroidDrawable, Serializable{
	/**
	 * Class Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initiates an instance
	 */
	public AndroidLine(){
		super();
	}
	
	/**
	 * Initiates an instance with the given firstPoint
	 * @param firsPoint
	 */
	public AndroidLine(AndroidPoint firstPoint){
		super(firstPoint);
	}
	
	/**
	 * Draws Line on given canvas using given brush
	 * 
	 * @throws IllegalArgumentException if null canvas or null brush
	 */
	public void draw(Canvas canvas, Paint brush) throws InvalidClassException{
		if(canvas == null || brush == null) throw new IllegalArgumentException();
		int nrOfPoints = getNrOfPoints();
		if(nrOfPoints == 1){
			if(!(getFirstPoint() instanceof AndroidPoint)){
				throw new InvalidClassException("Point isn't an instance of Android Point");
			}
			((AndroidPoint)getFirstPoint()).draw(canvas, brush);
		} else if(nrOfPoints > 1){
			Iterator<Point> it = getPoints().iterator();
			Point startPoint;
			Point stopPoint = it.next();
			while(it.hasNext()){
				startPoint = stopPoint;
				stopPoint = it.next();
				drawLineSeg(startPoint, stopPoint, canvas, brush);
			}
		}
	}
	
	private void drawLineSeg(Point startPoint, Point stopPoint, Canvas canvas, Paint brush){
		if(startPoint == null || stopPoint == null || canvas == null || brush == null)
			throw new IllegalArgumentException();
		canvas.drawLine(startPoint.getX(), startPoint.getY(), 
				stopPoint.getX(), stopPoint.getY(), brush);
	}

}
