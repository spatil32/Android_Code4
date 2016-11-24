package com.spatil32.a20367073_homework4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private data members
    private SeekBar imageSeekBar;
    private Button startSlideButton;
    private TextView seekbarProgress;

    public static final int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSeekBar = (SeekBar) findViewById(R.id.imageSeekBar);
        startSlideButton = (Button)findViewById(R.id.startSlideShow);
        seekbarProgress = (TextView)findViewById(R.id.seekbarProgress);

        //OnSeekbarChangeListener for seekbar
        imageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarProgress.setText("Timer = " + Integer.toString(seekBar.getProgress()));
                Toast.makeText(getApplicationContext(),"Seekbar Progress Changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"Started Seeking Seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"Stopped Seeking Seekbar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //This function is executed when second activity sends back response to first activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            String message = data.getStringExtra("message");
            this.seekbarProgress.setText(message);
        }
        else
            this.seekbarProgress.setText("!?");
    }

    //This function is executed when start slideshow button is pressed on first activity
    public void StartSlideShow(View view)
    {
        //pass the seekbar progress to second activity using Intent
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("seekbarValue", imageSeekBar.getProgress());
        this.startActivityForResult(intent, MY_REQUEST_CODE);
    }
}
