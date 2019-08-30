package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Switch useless;
    private Button destruct;
    private Button lookBusy;
    private ConstraintLayout constraintLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        constraintLayout.setBackgroundColor(Color.rgb(255,0,0));
                        destruct.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        destruct.setText("BOOM");
                        finish();
                    }
                }.start();
            }
        });


    }

    private void wireWidgets() {
        useless = findViewById(R.id.switch_main_useless);
        destruct = findViewById(R.id.button_main_destruct);
        lookBusy = findViewById(R.id.button_main_busy);
        constraintLayout = findViewById(R.id.constraint_layout_main);
    }

}
