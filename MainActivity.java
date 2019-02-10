package com.example.hk.silentmode;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioManager;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.TimePicker;
import android.provider.MediaStore.Audio;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {

   /* public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }*/
    ToggleButton tglSil;
    TextView time;
    Button btn1;
    Button btn2;
    long sub = 0;
    TimePicker simpleTimePicker;
    AudioManager am;
    TimeZone timeZone = TimeZone.getTimeZone("Asia/Jerusalem");
    Calendar c= Calendar.getInstance(timeZone);
    int hourX=c.get(Calendar.HOUR_OF_DAY);
    int minuteX=c.get(Calendar.MINUTE);
    Calendar c2 = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        time = (TextView) findViewById(R.id.time);
        tglSil=(ToggleButton)findViewById(R.id.tglSil);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        simpleTimePicker.setIs24HourView(true);
        simpleTimePicker.setCurrentHour(hourX);
        simpleTimePicker.setCurrentMinute(minuteX);


        time.setText(String.format("%d:%d", hourX, minuteX));
        tglSil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   am.setRingerMode(0);
                } else {
                   am.setRingerMode(2);
                }
            }
        });
        simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
        {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
            {

                Toast.makeText(getApplicationContext(), hourOfDay + " " + minute, Toast.LENGTH_SHORT).show();
                time.setText("Time is :: " + hourOfDay + " : " + minute);

            }
        });

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(30000,1000)
                {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Toast toast = null;
                        Toast.makeText(MainActivity.this, "Time's Up!", Toast.LENGTH_SHORT).show();
                        tglSil.setChecked(false);


                    }
                }.start();
                TickThread t=new TickThread(3000);
                t.run();
                Toast toast = null;
                //Toast.makeText(MainActivity.this, "Time's Up!", Toast.LENGTH_SHORT).show();

            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }

}

