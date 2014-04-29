package isel.leic.poo.nrdraw.android.view;

import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;

import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.*;

public class DrawView extends View {
	
	private Paint brush;
	private Drawing drawing;
	
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		brush = new Paint();
		//TODO change that
		brush.setStrokeWidth(5);
		drawing = null;
	}
	
	public void setDraw(Drawing d){
		drawing = d;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		draw(drawing, canvas);
	}
	
	private void draw(Drawing d, Canvas canvas){
		if(d == null || canvas  == null) throw new IllegalArgumentException();
		if(d.getNrOfLines() > 0){
			for (Line l : d.getLines()) {
				draw(l, canvas);
			}
		}
	}
	
	private void draw(Line l, Canvas canvas){
		if(l == null || canvas == null) throw new IllegalArgumentException();
		int nrOfPoints = l.getNrOfPoints();
		if(nrOfPoints == 1){
			draw(l.getFirstPoint(), canvas);
		} else if(nrOfPoints > 1){
			Iterator<Point> it = l.getPoints().iterator();
			Point startPoint;
			Point stopPoint = it.next();
			while(it.hasNext()){
				startPoint = stopPoint;
				stopPoint = it.next();
				drawLineSeg(startPoint, stopPoint, canvas);
			}
		}
	}
	
	private void draw(Point p, Canvas canvas){
		if(p == null || canvas == null) throw new IllegalArgumentException();
		canvas.drawPoint(p.getX(), p.getY(), brush);
	}
	
	private void drawLineSeg(Point startPoint, Point stopPoint, Canvas canvas){
		if(startPoint == null || stopPoint == null || canvas == null)
			throw new IllegalArgumentException();
		canvas.drawLine(startPoint.getX(), startPoint.getY(), 
				stopPoint.getX(), stopPoint.getY(), brush);
	}
}
