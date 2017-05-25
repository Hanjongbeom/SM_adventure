package com.example.dayto.sm_adventure;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    Handler handler = new Handler () {
        @Override
        public void handleMessage(Message msg) {
            finish();
        }
    };

    handler.sendEmptyMessageDelayed(0, 3000);

}
}

