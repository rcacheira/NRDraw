package isel.leic.poo.nrdraw.android;

import isel.leic.poo.nrdraw.R;
import isel.leic.poo.nrdraw.android.filesystem.AndroidLoader;
import isel.leic.poo.nrdraw.android.filesystem.AndroidSaver;
import isel.leic.poo.nrdraw.android.model.AndroidDrawing;
import isel.leic.poo.nrdraw.android.model.AndroidLine;
import isel.leic.poo.nrdraw.android.model.AndroidPoint;
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
		private AndroidLine l;
		
		private void createLine(AndroidPoint p){
			drawing.add(l = new AndroidLine(p));
		}
		
		private void clearLine(){
			l = null;
		}
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					createLine(new AndroidPoint(event.getX(0), event.getY(0)));
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
							createLine(new AndroidPoint(event.getX(0), event.getY(0)));
						}
						else{
							l.add(new AndroidPoint(event.getX(0), event.getY(0)));
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
	private NRDrawView drawView;
	private ClickBehaviour clickBehaviour;
	private AndroidDrawing drawing;
	private AlertDialog.Builder builder;
	
	private void save(){
		builder.setTitle(R.string.dialog_title_save);
		AndroidSaver saver = new AndroidSaver(this, "drawing", drawing);
		try{
			saver.doOperation();
			builder.setMessage(R.string.message_save_ok);
		}
		catch(Exception e){
			e.printStackTrace();
			builder.setMessage(R.string.message_save_n_ok);
		}
		builder.show();
	}
	
	private void load(){
		builder.setTitle(R.string.dialog_title_load);
		AndroidLoader loader = new AndroidLoader(this, "drawing", drawing);
		try{
			loader.doOperation();
			builder.setMessage(R.string.message_load_ok);
			drawView.setDraw(drawing);
			drawView.invalidate();
		}
		catch(Exception e){
			e.printStackTrace();
			builder.setMessage(R.string.message_load_n_ok);
		}
		builder.show();
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
		drawing = new AndroidDrawing();
		
		btSave = (Button)findViewById(R.id.btSave);
		btSave.setOnClickListener(clickBehaviour);
		
		btLoad = (Button)findViewById(R.id.btLoad);
		btLoad.setOnClickListener(clickBehaviour);
		
		btClear = (Button)findViewById(R.id.btClear);
		btClear.setOnClickListener(clickBehaviour);
		
		drawView = (NRDrawView)findViewById(R.id.drawView);
		drawView.setDraw(drawing);
		drawView.setOnTouchListener(new TouchBehaviour());
	}
	
}
