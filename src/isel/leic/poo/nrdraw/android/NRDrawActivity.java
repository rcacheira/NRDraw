package isel.leic.poo.nrdraw.android;

import isel.leic.poo.nrdraw.R;
import isel.leic.poo.nrdraw.android.filesystem.Loader;
import isel.leic.poo.nrdraw.android.filesystem.Saver;
import isel.leic.poo.nrdraw.android.view.DrawView;
import isel.leic.poo.nrdraw.model.Drawing;
import isel.leic.poo.nrdraw.model.Line;
import isel.leic.poo.nrdraw.model.Point;
import android.app.Activity;
import android.app.AlertDialog;
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
				save();
			}
			else if(v == btLoad){
				load();
			}
			else if(v == btClear){
				clear();
			}
		}
	}
	
	private class TouchBehaviour implements OnTouchListener{
		private Line l;
		
		private void createLine(Point p){
			drawing.add(l = new Line(p));
		}
		
		private void clearLine(){
			l = null;
		}
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					createLine(new Point(event.getX(0), event.getY(0)));
					drawView.invalidate();
					break;
				case MotionEvent.ACTION_MOVE:
					/**
					 * We choose to crate a new line when finger moves out of 
					 * drawView, and continue draw lines when finger moves in
					 */
					if(event.getX(0) < 0 || event.getY(0) < 0){
						clearLine();
					}
					else{
						if(l == null){
							createLine(new Point(event.getX(0), event.getY(0)));
						}
						else{
							l.add(new Point(event.getX(0), event.getY(0)));
						}
						drawView.invalidate();
					}
					break;
				default:
					break;
			}
			return true;
		}
	}
	
	private Button btSave, btLoad, btClear;
	private DrawView drawView;
	private ClickBehaviour clickBehaviour;
	private Drawing drawing;
	private AlertDialog.Builder builder;
	
	private void save(){
		builder.setTitle(R.string.dialog_title_save);
		Saver saver = new Saver(this, "drawing", drawing);
		try{
			saver.doOperation();
			builder.setMessage(R.string.message_save_ok);
		}
		catch(Exception e){
			builder.setMessage(R.string.message_save_n_ok);
		}
		builder.show();
	}
	
	private void load(){
		builder.setTitle(R.string.dialog_title_load);
		Loader loader = new Loader(this, "drawing", drawing);
		try{
			loader.doOperation();
			builder.setMessage(R.string.message_load_ok);
		}
		catch(Exception e){
			e.printStackTrace();
			builder.setMessage(R.string.message_load_n_ok);
		}
		builder.show();
		drawView.invalidate();
	}
	
	private void clear(){
		drawing.clear();
		drawView.invalidate();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nrdraw);
		builder = new AlertDialog.Builder(this);
		
		clickBehaviour = new ClickBehaviour();
		drawing = new Drawing();
		
		btSave = (Button)findViewById(R.id.btSave);
		btSave.setOnClickListener(clickBehaviour);
		
		btLoad = (Button)findViewById(R.id.btLoad);
		btLoad.setOnClickListener(clickBehaviour);
		
		btClear = (Button)findViewById(R.id.btClear);
		btClear.setOnClickListener(clickBehaviour);
		
		drawView = (DrawView)findViewById(R.id.drawView);
		drawView.setDraw(drawing);
		drawView.setOnTouchListener(new TouchBehaviour());
	}
	
}
