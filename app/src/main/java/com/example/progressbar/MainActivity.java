package com.example.progressbar;

import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private Button btnShow;
    private ProgressBar progressBar;
    private int counter = 0;
    private TextView txtView;
    private TextView txtLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtView = (TextView) findViewById(R.id.txtView);
        btnShow = (Button) findViewById(R.id.btnShow);
        txtLoading = (TextView) findViewById(R.id.txtLoading);

        txtLoading.setVisibility(View.VISIBLE);
        txtLoading.setText("Initial state.");
        txtLoading.setTextColor(Color.parseColor("#bdbdbd"));

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                counter = 0;
                counter = progressBar.getProgress();
                txtLoading.setText("Loading ...");
                txtLoading.setTextColor(Color.parseColor("#002100"));

                new Thread(new Runnable(){
                    public void run(){
                        while (counter < 100){
                            counter ++;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(counter);
                                    txtView.setText(counter + "/" + progressBar.getMax());

                                    if (counter >= 100){
                                        txtLoading.setText("Finished ...");
                                        txtLoading.setTextColor(Color.parseColor("#6e0000"));
                                        btnShow.setEnabled(false);
                                    }
                                }
                            });
                            try {
                                Thread.sleep(100);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }

                        }

                    }
                }).start();
            }
        });
    }
}