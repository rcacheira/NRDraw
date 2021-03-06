package isel.leic.poo.nrdraw.android.model;

import java.util.Iterator;

import android.graphics.Canvas;
import android.graphics.Paint;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

public class AndroidLine extends Line implements AndroidDrawable{
	
	public AndroidLine(){
		super();
	}
	
	public AndroidLine(AndroidPoint firsPoint){
		super(firsPoint);
	}
	
	public void draw(Canvas canvas, Paint brush){
		if(canvas == null || brush == null) throw new IllegalArgumentException();
		int nrOfPoints = getNrOfPoints();
		if(nrOfPoints == 1){
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
