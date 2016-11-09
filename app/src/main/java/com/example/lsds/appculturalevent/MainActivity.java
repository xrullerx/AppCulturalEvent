package com.example.lsds.appculturalevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnStart;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (ImageButton)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        //Создаем таймер обратного отсчета на 3 секунд с шагом отсчета
        //в 1 секунду (задаем значения в миллисекундах):
        timer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {};
            public void onFinish() {
                Start();
            }
        }.start();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnStart:
                Start();
                break;
            default:
                break;
        }

    }
    private void Start() {
        if (timer != null)
            timer.cancel();
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }
}
