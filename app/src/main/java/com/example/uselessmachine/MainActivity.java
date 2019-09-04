package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Switch useless;
    private Button destruct;
    private Button lookBusy;
    private ProgressBar progressBar;
    private TextView textView;
    private int progress = 0;
    private Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        textView.setVisibility(View.GONE);
//        progressBar.setVisibility(View.GONE);
        wireWidgets();
        setListeners();
    }


    private void setListeners() {
        useless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {
                        if(!useless.isChecked()) {
                            cancel();
                        }
                    }

                    @Override
                    public void onFinish() {
                        useless.setChecked(false);
                    }
                }.start();

//                if (isChecked) {
//                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        destruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        destruct.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        destruct.setText("BOOM");
                        finish();
                    }
                }.start();
            }
        });

        lookBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destruct.setVisibility(View.GONE);
                useless.setVisibility(View.GONE);
                lookBusy.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                progress = 0;
                new CountDownTimer(10100, 100) {

                    public void onTick(long millisUntilFinished) {
                        progress += 1;
                        progressBar.setProgress(progress);
                        textView.setText(progress+"/"+progressBar.getMax());
                    }

                    public void onFinish() {
                        destruct.setVisibility(View.VISIBLE);
                        useless.setVisibility(View.VISIBLE);
                        lookBusy.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                    }
                }.start();

            }
//            useless.setVisibility(View.GONE);
//            destruct.setVisibility((View.GONE));
//            lookBusy.setVisibility(View.GONE);
        });



    }

    private void wireWidgets() {
        useless = findViewById(R.id.switch_main_useless);
        destruct = findViewById(R.id.button_main_destruct);
        lookBusy = findViewById(R.id.button_main_busy);
        progressBar = findViewById(R.id.progressBar_main_progress);
        textView = findViewById(R.id.textView_main_loadingText);
    }

}
