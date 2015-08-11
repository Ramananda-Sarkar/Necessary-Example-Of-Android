package com.example.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnTouchListener {

	OurView ourView;
	Bitmap bitmap;
	float x, y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ourView = new OurView(this);
		ourView.setOnTouchListener(this);
		bitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.images);
		x = y = 0;
		setContentView(ourView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	class OurView extends SurfaceView implements Runnable {

		Thread t = null;
		SurfaceHolder holder;
		boolean isItOk = false;

		public OurView(Context context) {
			super(context);
			holder = getHolder();
		}

		@Override
		public void run() {
			while (isItOk) {
				if (!holder.getSurface().isValid()) {
					continue;
				}

				Canvas c = holder.lockCanvas();
				c.drawARGB(255, 150, 150, 10);
				c.drawBitmap(bitmap, x - (bitmap.getWidth() / 2),
						y - (bitmap.getHeight() / 2), null);
				holder.unlockCanvasAndPost(c);
			}
		}

		public void pause() {
			isItOk = false;
			while (true) {
				try {
					t.join();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			t = null;
		}

		public void resume() {
		}
	}
}