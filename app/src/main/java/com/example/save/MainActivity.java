package com.example.save;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.save.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//уведомления, где показано сколько осталось
//история посаженных деревьев(статистика)
//при выборе в алертдиалоге двух кнопок и нажатии основной - начало
//смена роста деревья с течением времени
//фиксированные значения для таймера на сикбаре


//к защите: движение язычка относительно времени на текствью и сбрасывание таймера при открытии другого приложения

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FloatingActionButton button_sheet;
    DrawerLayout sheet;

    MaterialButton main_plant;
    CountDownTimer countDownTimer;
    Boolean counterIsActive;
    HomeFragment homeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        button_sheet = findViewById(R.id.button_plant);
        button_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBottom();
            }
        });

        homeFragment = new HomeFragment();
        replaceFragmentNavigation(homeFragment);
        binding.buttonNavMenu.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                replaceFragmentNavigation(homeFragment);
            } else if (itemId == R.id.nav_history) {
                replaceFragmentNavigation(new HistoryFragment());
            }
            return true;
        });


    }

    private void showDialogBottom() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        Button button_main_plant = dialog.findViewById(R.id.button_main_plant);
        button_main_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.clickBtn();
                dialog.hide();
            }
        });
    }

    private void replaceFragmentNavigation(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout,fragment);
        fragmentTransaction.commit();

    }



}