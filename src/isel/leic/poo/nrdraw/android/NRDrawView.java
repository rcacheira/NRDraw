package isel.leic.poo.nrdraw.android;

import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class NRDrawView extends View {
	
	private Paint brush;
	private AndroidDrawing drawing;
	
	public NRDrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		brush = new Paint();
		//TODO change that
		brush.setStrokeWidth(5);
		drawing = null;
	}
	
	public void setDraw(AndroidDrawing d){
		drawing = d;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawing.draw(canvas, brush);
	}
}
