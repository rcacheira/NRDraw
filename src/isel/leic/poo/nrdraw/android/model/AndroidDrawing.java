package isel.leic.poo.nrdraw.android.model;

import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import android.graphics.Canvas;
import android.graphics.Paint;

public class AndroidDrawing extends Drawing implements Drawable{

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
