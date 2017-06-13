package com.example.chojaeyoung.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class ShopActivity extends AppCompatActivity{

    // public static boolean item = false;
//    private PlayerState playerState;
    private Toast toast;
    private Activity activity = this;
    //    private int money;
//    private int count;
    private GameActivity gameActivity;
    private  BackPressCloseHandler backPressCloseHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final Button btn_Re = (Button)findViewById(R.id.returnBt);// LO/shop' 리턴버튼
        final ImageView item_1 = (ImageView)findViewById(R.id.item_CS); // LO/shop' 커서 애정도
        final ImageView item_2 = (ImageView)findViewById(R.id.item_Bb); // LO/shop' 블루베리 애정도
        backPressCloseHandler = new BackPressCloseHandler(this); // backPressCloseHandler
        gameActivity = new GameActivity();
//        playerState = new PlayerState();
//        money = playerState.money;
//        count = playerState.count;

        btn_Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itMain = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(itMain);
//                playerState.money = money;
//                playerState.count = count;
                SharedPreferences playerData = getSharedPreferences("Data", MODE_PRIVATE);
                SharedPreferences.Editor editor = playerData.edit();
                editor = playerData.edit();
                editor.putInt("MONEY", gameActivity.playerState.money);
                editor.putInt("LOVE", gameActivity.playerState.count);
                editor.putBoolean("ITEM_1", Item.item1);
                editor.commit();
                finish();
            }
        });

        item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //커서 애정도 클릭시

                if(Item.item1 != true) {
                    if (gameActivity.playerState.money >= 200) {
                        gameActivity.playerState.money = gameActivity.playerState.money - 200;
                        Item.item1 = true;
                        (Toast.makeText(activity, "아이템을 구매했습니다.", Toast.LENGTH_SHORT)).show();
                    } else {
                        showGuide();
                    }
                }else
                {
                    (Toast.makeText(activity, "이미 구매한 상품입니다.", Toast.LENGTH_SHORT)).show();
                }
            }
        });

        item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //블루베리 애정도 클릭시
                if(gameActivity.playerState.money >= 200) {
                    gameActivity.playerState.money = gameActivity.playerState.money - 200;
//                    Item.item2++;
                    gameActivity.playerState.count = gameActivity.playerState.count + 50;
                    (Toast.makeText(activity, "아이템을 구매했습니다.", Toast.LENGTH_SHORT)).show();
                } else {
                    showGuide();
                }
            }
        });

    }


    public  void  showGuide() //금액이 모지랄때
    {
        toast = Toast.makeText(activity, "\'소지금\'이 부족합니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }
}
