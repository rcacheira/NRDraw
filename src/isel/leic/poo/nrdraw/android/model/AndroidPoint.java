package isel.leic.poo.nrdraw.android.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import isel.leic.poo.nrdraw.model.Point;

public class AndroidPoint extends Point implements Drawable{

	public AndroidPoint(float x, float y) {
		super(x, y);
	}

	public void draw(Canvas canvas, Paint brush){
		if(canvas == null || brush == null) throw new IllegalArgumentException();
		canvas.drawPoint(getX(), getY(), brush);
	}
	
}
