package com.example.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.CountDownLatch;

//This class is used to increase the superheroes attack, defense and to heal them.
public class TrainingActivity extends AppCompatActivity {

    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private LinearLayout linearLayout;
    private int i;
    private TextView countdownText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        countdownText = findViewById(R.id.txtCountdown);
        linearLayout = findViewById(R.id.llRadioButtonContainer);
        countdownText.setVisibility(View.GONE);
        radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        RadioGroup.LayoutParams layoutParams;

        for (i = 0; i < Storage.getInstance().getSize(); i++) {
            radioButton = new RadioButton(this);
            radioButton.setText(Storage.getInstance().getSuperheroById(i).getName() + " (" + Storage.getInstance().getSuperheroById(i).getType() + ")");
            radioButton.setPadding(30, 0, 0, 0);

            radioButton.setId(i);

            layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, 40, 0, 0);
            radioGroup.addView(radioButton, layoutParams);
        }

        linearLayout.addView(radioGroup);
    }

    public void startCountdown(int seconds, TextView countdownText, Runnable onFinish) {
        CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdownText.setVisibility(View.VISIBLE);
                int seconds = (int) (millisUntilFinished / 1000);
                countdownText.setText("Aikaa jäljellä: " + seconds + "s");
            }

            public void onFinish() {
                countdownText.setText("Toiminto suoritettu!");
                countdownText.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        countdownText.setVisibility(View.GONE);
                    }
                }, 1000);
                onFinish.run();
            }
        };
        countDownTimer.start();
    }


    public void selectAct(View view) {
        RadioGroup rgTraining = findViewById(R.id.rgTraining);
        switch (rgTraining.getCheckedRadioButtonId()) {
            case R.id.rbTrainAttack:
                startCountdown(5, countdownText, new Runnable() {
                    @Override
                    public void run() {
                        Storage.getInstance().getSuperheroById(radioGroup.getCheckedRadioButtonId()).trainAttack();
                    }
                });
                break;
            case R.id.rbTrainDefense:
                startCountdown(5, countdownText, new Runnable() {
                    @Override
                    public void run() {
                        Storage.getInstance().getSuperheroById(radioGroup.getCheckedRadioButtonId()).trainDefense();
                    }
                });
                break;
            case R.id.rbHospital:
                startCountdown(5, countdownText, new Runnable() {
                    @Override
                    public void run() {
                        Storage.getInstance().getSuperheroById(radioGroup.getCheckedRadioButtonId()).sendToHospital();
                    }
                });
                break;
        }
    }
}
