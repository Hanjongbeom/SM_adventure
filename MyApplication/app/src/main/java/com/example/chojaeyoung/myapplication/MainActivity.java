package com.example.chojaeyoung.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import static com.example.chojaeyoung.myapplication.R.*;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer backgroundMusic; //배경음악
    private MediaPlayer startGame;
    private BackPressCloseHandler backPressCloseHandler; //뒤로가기 누를때 종료 // BackPressCloseHandler참고

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        backgroundMusic = MediaPlayer.create(this, R.raw.scene6); //배경음악
        backgroundMusic.start();
        startGame = MediaPlayer.create(this, R.raw.open5); //시작음악
        backPressCloseHandler = new BackPressCloseHandler(this);

        final ImageButton start = (ImageButton)findViewById(id.startButton); //LO/main ' 시작버튼

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //시작버튼 눌릴때
                backgroundMusic.stop(); //배경음악 멈춤
                startGame.start(); //기본음악 시작
                Intent intent_act = new Intent(getApplicationContext(),GameActivity.class); // game activity로 액티비티 전환
                startActivity(intent_act);
                finish();
            }
        });

    }

    @Override
    protected void onStop(){ //음악 종료
        super.onStop();
        backgroundMusic.stop();
    }

    public void onBackPressed() { backPressCloseHandler.onBackPressed(); } //한번클릭 (경고) / 두번클릭(종료)
}
