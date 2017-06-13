package com.example.chojaeyoung.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.*;
import android.widget.*;



public class SellPopupActivity extends Activity  {

    private int n;
    private GameActivity gameActivity;
    //  private PlayerState pS;
    // private TextView love;
    // private TextView money;
    private static ImageView img; //그림

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_popup);

        final Button sellBtn = (Button) findViewById(R.id.sellBtn);
        final TextView sellGuide = (TextView) findViewById(R.id.sellText); //LO/popup' sell 버튼
        final RelativeLayout exit = (RelativeLayout)findViewById(R.id.layout_popup);
        img = (ImageView) findViewById(R.id.Image_char);
        gameActivity = new GameActivity();
        // love = (TextView) gameActivity.textLove.findViewById(R.id.loveText);
        //  money = (TextView) gameActivity.textMoney.findViewById(R.id.moneyText);

        // pS = new PlayerState();

        n = gameActivity.playerState.dotImageKinds; //랜덤값

        sellBtn.setClickable(true);
        switch (n) {
            case 1:
                sellGuide.setText("우와!\n초록알에서 잠만보가 나왔습니다!\n 보너스 금액 +100");
                break;
            case 2:
                sellGuide.setText("우와!\n초록알에서 이브이가 나왔습니다!\n 보너스 금액 +800");
                break;
            case 3:
                sellGuide.setText("우왕!\n초록알에서 마린이 나왔습니다!\n 보너스 금액 +300");
                break;
            case 4:
                sellGuide.setText("와우!\n보라알에서 메타몽이 나왔습니다!\n 보너스 금액 +700");
                break;
            case 5:
                sellGuide.setText("오우!\n보라알에서 따라큐가 나왔습니다!\n 보너스 금액 +1000");
                break;
            case 6:
                sellGuide.setText("대단해요!\n보라알에서 팬텀이 나왔습니다!\n 보너스 금액 +1500");
                break;
            case 7:
                sellGuide.setText("신기해요!\n빨간알에서 냐오불이 나왔습니다!\n 보너스 금액 +100");
                break;
            case 8:
                sellGuide.setText("즐거워요!\n빨간알에서 찌리리공이 나왔습니다!\n 대박!! 보너스 금액 +3000");
                break;
            case 9:
                sellGuide.setText("와우!\n빨간알에서 리자몽이 나왔습니다!\n 보너스 금액 +500");
                break;
            case 10:
                sellGuide.setText("행복해요!\n노란알에서 삐카츄가 나왔습니다!\n 보너스 금액 +200");
                break;
            case 11:
                sellGuide.setText("귀여워요!\n노란알에서 토케피가 나왔습니다!\n 보너스 금액 +500");
                break;
            case 12:
                sellGuide.setText("왕왕!\n노란알에서 고라파덕이 나왔습니다!\n 보너스 금액 +200");
                break;
            default:
                System.out.println("팝업 에러");
                break;

        }


        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // special 값
                sellBtn.setClickable(false);
                switch (n) {
                    case 1:
                        gameActivity.playerState.money = gameActivity.playerState.money + 100;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 2:
                        gameActivity.playerState.money = gameActivity.playerState.money + 800;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 3:
                        gameActivity.playerState.money = gameActivity.playerState.money + 300;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 4:
                        gameActivity.playerState.money = gameActivity.playerState.money + 700;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 5:
                        gameActivity.playerState.money = gameActivity.playerState.money + 1000;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 6:
                        gameActivity.playerState.money = gameActivity.playerState.money + 1500;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 7:
                        gameActivity.playerState.money = gameActivity.playerState.money + 100;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 8:
                        gameActivity.playerState.money = gameActivity.playerState.money + 3000; //special 보너스
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 9:
                        gameActivity.playerState.money = gameActivity.playerState.money + 500;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 10:
                        gameActivity.playerState.money = gameActivity.playerState.money + 200;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 11:
                        gameActivity.playerState.money = gameActivity.playerState.money + 500;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    case 12:
                        gameActivity.playerState.money = gameActivity.playerState.money + 200;
                        gameActivity.playerState.count = 0;
                        gameActivity.playerState.dotImageKinds = 0;
                        gameActivity.playerState.dotSwitch = 0;
                        // gameActivity.Reset();
                        break;
                    default:
                        System.out.println("판매 에러");
                        break;
                }
                //               Intent itMain = new Intent(getApplicationContext(), GameActivity.class);
                //                startActivity(itMain);
                finish();
            }
        });
    }
}
