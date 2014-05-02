package isel.leic.poo.nrdraw.android;

import java.io.InvalidClassException;

import isel.leic.poo.nrdraw.R;
import isel.leic.poo.nrdraw.android.filesystem.AndroidLoader;
import isel.leic.poo.nrdraw.android.filesystem.AndroidSaver;
import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import isel.leic.poo.nrdraw.android.model.AndroidLine;
import isel.leic.poo.nrdraw.android.model.AndroidPoint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

/**
 * Class that extends from android class Activity.
 * 
 * It represents the main class of the android application NRDraw.
 * 
 * @author rcacheira & nreis
 *
 */
public class NRDrawActivity extends Activity {
	
	/**
	 * Implements a click on the android application.
	 * 
	 * @author rcacheira & nreis
	 *
	 */
	private class ClickBehaviour implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(v == btSave){
				actionSave();
			}
			else if(v == btLoad){
				actionLoad();
			}
			else if(v == btClear){
				actionClear();
			}
		}
	}
	
	/**
	 * Implements a continuous click on the android application, a touch.
	 * 
	 * @author rcacheira & nreis
	 *
	 */
	private class TouchBehaviour implements OnTouchListener{
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int idx = event.findPointerIndex(0);
			if(idx >= 0){
				switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						actionDown(event, idx);
						break;
					case MotionEvent.ACTION_MOVE:
						actionMove(event, idx);
						break;
					default:
						return false;
				}
				return true;
			}
			return false;
		}
	}
	/**
	 * 
	 */
	private static final String DRAWING_KEY = "NRDraw.Drawing";
	/**
	 * Three references to the buttons instantiated in XML file of the layout to ease programming.
	 */
	private Button btSave, btLoad, btClear;
	/**
	 * Reference to the custom view in XML file of the layout to ease programming.
	 */
	private NRDrawView drawView;
	/**
	 * Created to avoid the creation of new instances of the {@code ClickBehaviour}.
	 */
	private ClickBehaviour clickBehaviour;
	/**
	 * Reference to the drawing in an android application.
	 */
	private AndroidDrawing drawing;
	/**
	 * Line used to add points and verifications.
	 */
	private AndroidLine workingLine;
	
	/**
	 * Initiates an instance {@code AndroidSaver}.
	 */
	private void actionSave(){
		new AndroidSaver(this, "drawing", drawing).save();
	}

	/**
	 * Initiates an instance {@code AndroidLoader}.
	 */	
	private void actionLoad(){
		new AndroidLoader(this, "drawing", drawing).load(drawView);
	}
	
	/**
	 * Clears the drawing in drawView and redraws the drawing. 
	 */
	private void actionClear(){
		drawing.clear();
		drawView.invalidate();
	}
	
	/**
	 * Creates a new line indicating there is a finger on the screen
	 * and redraws the drawView. 
	 * 
	 * @param event	//TODO: ...
	 * @param pointerIndex	event pointer index.
	 */
	private void actionDown(MotionEvent event, int pointerIndex){
		createLine(new AndroidPoint(event.getX(pointerIndex), event.getY(pointerIndex)));
		drawView.invalidate();
	}
	
	/**
	 * Gets the x and y from {@code Event}.
	 * 
	 * Verifies if the action executed is correct.
	 * 
	 * Adds only a point, or a new line with a new point according to the verification made.
	 * 
	 * Redraws drawView.
	 * 
	 * @param event	//TODO: ...
	 * @param pointerIndex	event pointer index.
	 */
	private void actionMove(MotionEvent event, int pointerIndex){
		float x = event.getX(pointerIndex), y= event.getY(pointerIndex);
		if(workingLine != null && (x < 0 || y < 0)){
			workingLine = null;
		}
		else if(x >= 0 && y >= 0){
			if(workingLine == null){
				createLine(new AndroidPoint(x, y));
			}
			else{
				workingLine.add(new AndroidPoint(x, y));
			}
			drawView.invalidate();
		}
	}
	
	/**
	 * Creates a new line with a new point.
	 * 
	 * @param p firstPoint of the line
	 */
	private void createLine(AndroidPoint p){
		drawing.add(workingLine = new AndroidLine(p));
	}
	
	/**
	 * Saves the drawing in a file.
	 * 
	 * @param outState
	 */
	private void save(Bundle outState){
		outState.putSerializable(DRAWING_KEY, drawing);
	}
	
	/**
	 * Loads drawing if there is a file saved.
	 * 
	 * @param savedInstanceState
	 * @throws InvalidClassException if our class loaded isn't an instance of AndroidDrawing.
	 */
	private void load(Bundle savedInstanceState) throws InvalidClassException{
		Object obj = savedInstanceState.get(DRAWING_KEY);
		if(!(obj instanceof AndroidDrawing)){
			throw new InvalidClassException("Class loaded isn't an instance of AndroidDrawing");
		}
		drawing = (AndroidDrawing)obj;
	}
	
	/**	//TODO: Not necessary. Derived onCreate() has comment.
	 * Main.
	 * 
	 * Allows the rotation of the screen without loss of data.
	 * 
	 * Gets references to the buttons and drawView on XML file to ease programming.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nrdraw);
		
		clickBehaviour = new ClickBehaviour();
		
		try{
			if(savedInstanceState != null){
				load(savedInstanceState);
			} else {
				drawing = new AndroidDrawing();
			}
		}
		catch(InvalidClassException e){
			e.printStackTrace();
			drawing = new AndroidDrawing();
		}
		
		btSave = (Button)findViewById(R.id.btSave);
		btSave.setOnClickListener(clickBehaviour);
		
		btLoad = (Button)findViewById(R.id.btLoad);
		btLoad.setOnClickListener(clickBehaviour);
		
		btClear = (Button)findViewById(R.id.btClear);
		btClear.setOnClickListener(clickBehaviour);
		
		drawView = (NRDrawView)findViewById(R.id.drawView);
		drawView.setDrawing(drawing);
		drawView.setOnTouchListener(new TouchBehaviour());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		try{
			load(savedInstanceState);
		}
		catch(InvalidClassException e){
			e.printStackTrace();
			drawing = new AndroidDrawing();
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		save(outState);
	}
	
}
