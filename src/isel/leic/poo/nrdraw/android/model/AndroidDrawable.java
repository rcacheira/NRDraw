package isel.leic.poo.nrdraw.android.model;

import java.io.InvalidClassException;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Interface that makes a derived class able to be draw on an Android View
 * 
 * @author rcacheira & nreis
 *
 */
public interface AndroidDrawable {
	public void draw(Canvas canvas, Paint brush) throws InvalidClassException;
}
