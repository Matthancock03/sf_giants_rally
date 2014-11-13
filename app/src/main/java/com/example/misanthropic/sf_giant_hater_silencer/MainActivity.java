package com.example.misanthropic.sf_giant_hater_silencer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
public int picInt = 1;
public boolean playing = false;
public  MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void playMusic(){
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                mp = MediaPlayer.create(getApplicationContext(), R.drawable.dont_stop);
                if(!playing){
                    mp.start();
                mp.setLooping(true);
                    playing = true;
                }
                else{
                    mp.stop();
                    playing = false;
                }
            }
            };
        handler.post(runnable);
    }

    public void musicButtonClicked(View v){
        TextView txt = (TextView)findViewById(R.id.textView2);
        txt.setVisibility(View.INVISIBLE);

        final int[] repeatCount = {0};
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                picRotate();
                Log.d("MSG", "repeatCount is : " + repeatCount[0]);
                repeatCount[0]++;
                if(repeatCount[0] < 6) {
                    handler.postDelayed(this, 2000);
                }
            }
        };

        playMusic();
        handler.post(runnable);

    }

    public void onPause(){
        System.exit(0);
        if(playing) {
            mp.stop();
        }
    }

    public void onDestroy(){
        System.exit(0);
        if(playing) {
            mp.stop();
        }
    }


    public void onBackPressed(){
        if(playing) {
            mp.stop();
        }
        System.exit(0);
    }

    public void picRotate() {
        picInt++;
        ImageButton img = (ImageButton)findViewById(R.id.music_btn);
            String src = "sg_pic" + Integer.toString(picInt);
            int resID = getResources().getIdentifier(src, "drawable", getPackageName());
            img.setImageResource(resID);

        if(picInt>5){picInt=-1;}

    }

    public void picRotate(int num) {
        picInt = num;
        ImageButton img = (ImageButton)findViewById(R.id.music_btn);
        String src = "sg_pic" + Integer.toString(picInt);
        int resID = getResources().getIdentifier(src, "drawable", getPackageName());
        img.setImageResource(resID);

        if(picInt>4){picInt=0;}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
