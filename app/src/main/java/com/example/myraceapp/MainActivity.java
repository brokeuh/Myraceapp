package com.example.myraceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myraceapp.util.ProgressHandler;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ProgressBar pb;
    private Button btn;
    private ProgressHandler mHandler;
    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startThread();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.tv_result);
        pb = findViewById(R.id.pb_progress);
        btn = findViewById(R.id.btn_start);

        mHandler = new ProgressHandler(pb, tv);
        btn.setOnClickListener(startListener);

    }

    private  void  startThread(){
        Thread bgThread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = new Message();
                    msg.arg1 = i;

                    mHandler.sendMessage(msg);

                }

            }
        });

        bgThread.start();
    }
}
