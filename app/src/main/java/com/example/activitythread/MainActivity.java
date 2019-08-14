package com.example.activitythread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Handler threadHandler;
    private Button openButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = findViewById(R.id.open_btn);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleRunnable runnable= new ExampleRunnable(1000);
                new Thread(runnable).start();
            }
        });
    }


    private class ExampleRunnable implements Runnable {
        int time;

        ExampleRunnable(int time) {
            this.time = time;
        }

        @Override
        public void run() {
             threadHandler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                }
            });
        }
    }
}
