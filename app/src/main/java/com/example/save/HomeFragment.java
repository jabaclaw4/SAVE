package com.example.save;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HomeFragment extends Fragment {

    CircularSeekBar seekBar;
    TextView text;

    public void clickBtn() {
        CountDownTimer timer = new CountDownTimer((long) seekBar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               updateTextVIew(millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.i("Timer", "CountDown is finished");
                text.setText("0:00");

            }
        }.start();
    }



    public void updateTextVIew(float timeSeconds) {
        float minutes = (int) timeSeconds / 60;
        float seconds = (int) timeSeconds - (minutes * 60);
        int minutes_n = Math.round(minutes);
        int seconds_n = Math.round(seconds);

        text.setText(String.format("%02d:%02d", minutes_n, seconds_n));
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        seekBar = (CircularSeekBar) v.findViewById(R.id.timerSeekBar);
        text = (TextView) v.findViewById(R.id.viewTimer);

        seekBar.setMax(3600);
        seekBar.setProgress(10);

//        Button button = v.findViewById(R.id.click);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickBtn();
//            }
//        });





        seekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(@Nullable CircularSeekBar circularSeekBar, float v, boolean b) {
//                float minutes = (int) v / 60;
//                float seconds = (int) v - (minutes * 60);
//                int minutes_n = Math.round(minutes);
//                int seconds_n = Math.round(seconds);

//сделать фиксировванные значения времени - ?

//                text.setText(String.format("%02d:%02d", minutes_n, seconds_n));
                updateTextVIew(v);
            }

            @Override
            public void onStopTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

            }

            @Override
            public void onStartTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

            }
        });

        return v;
    }
}