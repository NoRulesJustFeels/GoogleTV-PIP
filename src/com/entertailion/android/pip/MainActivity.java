package com.entertailion.android.pip;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.widget.VideoView;

/**
 * Example app for embedding live TV in a Google TV app
 * 
 * @author leon_nicholls
 *
 */
public class MainActivity extends Activity implements
		MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener,
		MediaPlayer.OnPreparedListener {
	private static String LOG_TAG = "MainActivity";
	private VideoView videoView;
	private Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		startVideo();
	}
	
	/**
	 * Start the live TV PIP video stream
	 */
	private void startVideo() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				videoView = (VideoView) findViewById(R.id.pipview);
				videoView.setVideoURI(Uri.parse("hdmi://localhost?port=2"));
				videoView.setOnCompletionListener(MainActivity.this);
				videoView.setOnErrorListener(MainActivity.this);
				videoView.setOnPreparedListener(MainActivity.this);
				videoView.start();
			}
		}, 2000); // let the live TV app release its resources before playing PIP video
	}

	@Override
	protected void onPause() {
		videoView.stopPlayback();
		super.onPause();

		doFinish();
	}
	
	/**
	 * Close the app
	 */
	private void doFinish() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				MainActivity.this.finish();
			}
		}, 10);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.d(LOG_TAG, "onPrepared");

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.d(LOG_TAG, "onCompletion");

	}

	/** 
	 * Callback for video playback errors; exit the app on errors
	 * 
	 * @see android.media.MediaPlayer.OnErrorListener#onError(android.media.MediaPlayer, int, int)
	 */
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Log.d(LOG_TAG, "onError: " + what + ", " + extra);
		doFinish();
		return false;
	}
}
