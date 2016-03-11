package com.test.playaudio;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button musicplay;
	private Button musicpause;
	private Button musicstop;
	private MediaPlayer mediaPlay = new MediaPlayer();
	
	private Button videoplay;
	private Button videopause;
	private Button videoreplay;
	private VideoView videoView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        musicplay = (Button)findViewById(R.id.mucis_play);
        musicpause = (Button)findViewById(R.id.music_pause);
        musicstop = (Button)findViewById(R.id.music_stop);
        musicplay.setOnClickListener(this);
        musicpause.setOnClickListener(this);
        musicstop.setOnClickListener(this);
        initMediaPlayer();
        
        videoplay = (Button)findViewById(R.id.video_play);
        videopause = (Button)findViewById(R.id.video_pause);
        videoreplay = (Button)findViewById(R.id.video_replay);
        videoView = (VideoView)findViewById(R.id.video_view);
        videoplay.setOnClickListener(this);
        videopause.setOnClickListener(this);
        videoreplay.setOnClickListener(this);
        initVideoPath();
    }


    private void initVideoPath()
	{
    	File file = new File(Environment.getExternalStorageDirectory(),"west.mp4");
    	videoView.setVideoPath(file.getPath());
	}


	private void initMediaPlayer()
	{
    	try
    	{
    		File file = new File(Environment.getExternalStorageDirectory(),"We Are The Champions.mp3");
    		mediaPlay.setDataSource(file.getPath());
    		mediaPlay.prepare();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
    
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    		case R.id.mucis_play:
    			if(!mediaPlay.isPlaying())
    			{
    				mediaPlay.start();
    			}
    			break;
    		case R.id.music_pause:
    			if(mediaPlay.isPlaying())
    			{
    				mediaPlay.pause();
    			}
    			break;
    		case R.id.music_stop:
    			if(mediaPlay.isPlaying());
    			{
    				mediaPlay.reset();
    				initMediaPlayer();
    			}
    			break;
    			
    		case R.id.video_play:
    			if(!videoView.isPlaying())
    			{
    				videoView.start();
    			}
    			break;
    		case R.id.video_pause:
    			if(videoView.isPlaying())
    			{
    				videoView.pause();
    			}
    			break;
    		case R.id.video_replay:
    			if(videoView.isPlaying())
    			{
    				videoView.resume();
    			}
    			break;
    		default:
    			break;
    	}
    }

    protected void onDestroy()
    {
    	super.onDestroy();
    	if(mediaPlay!=null)
    	{
    		mediaPlay.stop();
    		mediaPlay.release();
    	}
    	if(videoView!=null)
    	{
    		videoView.suspend();
    	}
    }
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.exit:
				finish();
		}
		return true;
	}
    
}
