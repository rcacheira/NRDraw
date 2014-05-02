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

public class NRDrawActivity extends Activity {
	
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
	
	private class TouchBehaviour implements OnTouchListener{
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					actionDown(event);
					break;
				case MotionEvent.ACTION_MOVE:
					actionUp(event);
					break;
				default:
					return false;
			}
			return true;
		}
	}
	
	private static final String DRAWING_KEY = "NRDraw.Drawing";
	private Button btSave, btLoad, btClear;
	private NRDrawView drawView;
	private ClickBehaviour clickBehaviour;
	private AndroidDrawing drawing;
	private AndroidLine workingLine;
	
	private void actionSave(){
		new AndroidSaver(this, "drawing", drawing).save();
	}
	
	private void actionLoad(){
		new AndroidLoader(this, drawView, "drawing", drawing).load();
	}
	
	private void actionClear(){
		drawing.clear();
		drawView.invalidate();
	}
	
	private void actionDown(MotionEvent event){
		createLine(new AndroidPoint(event.getX(0), event.getY(0)));
		drawView.invalidate();
	}
	
	private void actionUp(MotionEvent event){
		if(workingLine != null && (event.getX(0) < 0 || event.getY(0) < 0)){
			workingLine = null;
		}
		else{
			if(workingLine == null){
				createLine(new AndroidPoint(event.getX(0), event.getY(0)));
			}
			else{
				workingLine.add(new AndroidPoint(event.getX(0), event.getY(0)));
			}
			drawView.invalidate();
		}
	}
	
	private void createLine(AndroidPoint p){
		drawing.add(workingLine = new AndroidLine(p));
	}
	
	private void save(Bundle outState){
		outState.putSerializable(DRAWING_KEY, drawing);
	}
	
	private void load(Bundle savedInstanceState) throws InvalidClassException{
		Object obj = savedInstanceState.get(DRAWING_KEY);
		if(!(obj instanceof AndroidDrawing)){
			throw new InvalidClassException("Class loaded isn't an instance of AndroidDrawing");
		}
		drawing = (AndroidDrawing)obj;
	}
	
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
