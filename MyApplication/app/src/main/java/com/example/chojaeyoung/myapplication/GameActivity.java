package com.example.chojaeyoung.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    static public int one=0;
    static public int two=0;
    static public int three=0;
    static public int four=0;
    static public int five=0;
    static public int six=0;
    static public int seven=0;
    static public int eight=0;
    static public int nine=0;
    static public int ten=0;
    static public int eleven=0;
    static public int twelve=0;

    //private int count;
    public int dotImageKinds;
    public int imageSwitch=0;
    public int eggcheck=0;
    private TextView textLove; //애정도
    private TextView textMoney; //소지금
    private RelativeLayout bg; //레이아웃
    // private RelativeLayout sellBg;
    private static ImageView img; //그림
    private SharedPreferences playerData; // 어플 꺼져도 값 저장(삭제전까지)
    public SharedPreferences.Editor editor;
    private MediaPlayer backgroundMusic; //배경음악
    private MediaPlayer dotClickMusic; //클릭시 음악
    private MediaPlayer dotGrowth; //음악.../(?)
    private Handler mhandler = new Handler(); //핸들러
    private Handler handler = new Handler();
    private Runnable mMytesk = new Runnable() {
        @Override
        public void run() {
            sellPopUp(); //sellpopup activity?
            editor = playerData.edit();
            editor.putInt("DOT_KINDS", 0);
            if(Item.item1 == true) {
                handler.postDelayed(new IncHandler(5, 1000, handler), 100); //delay
            }
            bg.setClickable(true);
        }
    };

    private class IncHandler implements Runnable {
        private int incAmount;
        private long time;
        private boolean isRunning = true;
        private Handler self;
        public IncHandler(int incAmount, long time, Handler self) {
            this.incAmount = incAmount;
            this.time = time;
            this.self = self;
        }
        public void run() {
            //count = playerState.count;
            playerState.count = playerState.count + incAmount;
            textLove.setText(String.valueOf((playerState.count)));
            try {
                imageSetting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isRunning) {
                self.postDelayed(this, time);
            }
        }

        public void stop() { isRunning = false; }
    }


    private  BackPressCloseHandler backPressCloseHandler;
    //private  BackPressCloseHandler hello;
    public PlayerState playerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        backPressCloseHandler = new BackPressCloseHandler(this); // 뒤로가기
        playerState = new PlayerState();
        backgroundMusic = MediaPlayer.create(this, R.raw.scene5); //소리
        backgroundMusic.setLooping(true);
        backgroundMusic.start(); //음악

        playerData = getSharedPreferences("Data", MODE_PRIVATE);
        bg = (RelativeLayout)findViewById(R.id.main);
        //sellBg = (RelativeLayout)findViewById(R.id.main);
        textLove = (TextView)findViewById(R.id.loveText);
        textMoney = (TextView)findViewById(R.id.moneyText);
        playerState.money = playerData.getInt("MONEY", playerState.money);
        playerState.count = playerData.getInt("LOVE", playerState.count);
        playerState.dotSwitch = playerData.getInt("DOT_KINDS", playerState.dotSwitch);
        //Item.item1 = playerData.getBoolean("ITEM_1", false);
        img = (ImageView) findViewById(R.id.Image_char);
        dotImageKinds = playerState.dotImageKinds;
        imageSwitch = playerState.dotSwitch;
        final ImageView shopBt = (ImageView)findViewById(R.id.goShopButton);
        ImageView pocketDex = (ImageView)findViewById(R.id.goPocketDexButton);
        dotClickMusic = MediaPlayer.create(this, R.raw.cursor2);
        dotGrowth = MediaPlayer.create(this, R.raw.item);


        ShopActivity sh = new ShopActivity();
        //count = playerState.count;
//        boolean item_on_off = Item.item1;

        if(Item.item1 == true) {
            handler.postDelayed(new IncHandler(5, 1000, handler), 100);
        }
        /*if(Item.item2 > 0)
        {
            while(Item.item2 != 0)
            {
                p
            }
        }*/

        try {
            imageSetting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int money = playerState.money;
        textMoney.setText(""+playerState.money);
        textLove.setText(""+playerState.count);

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerState.count++;
                //System.out.println(count);
                textLove.setText(""+playerState.count);
                textMoney.setText(""+playerState.money);
                //System.out.println( playerState.count + "/" + playerState.money + "/" + dotImageKinds + "/" + imageSwitch );
                dotClickMusic.start(); //음악
                try {
                    imageSetting();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        shopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShop = new Intent(getApplicationContext(), ShopActivity.class);
                // playerState.count = count;
                backgroundMusic.stop();
                playerState.dotImageKinds = dotImageKinds;
                //playerState.dotSwitch = imageSwitch;
                startActivity(intentShop);
                finish();
            }
        });

        pocketDex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pocketdex = new Intent(getApplicationContext(), PocketDexActivity.class);
                // playerState.count = count;
                backgroundMusic.stop();
                playerState.dotImageKinds = dotImageKinds;
                //playerState.dotSwitch = imageSwitch;
                startActivity(pocketdex);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        textLove = (TextView)findViewById(R.id.loveText);
        textMoney = (TextView)findViewById(R.id.moneyText);
        textMoney.setText(""+playerState.money);
        textLove.setText(""+playerState.count);
        dotImageKinds = playerState.dotImageKinds;
        imageSwitch = playerState.dotSwitch;
        try {
            imageSetting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop(){ // 게임 종료시 저장해야하는 값 입력
        super.onStop();
        backgroundMusic.stop();
       // playerState.dotSwitch = imageSwitch;
        playerState.dotSwitch = eggcheck;
        playerData = getSharedPreferences("Data", MODE_PRIVATE);
        handler.removeMessages(0);
        editor = playerData.edit();
        editor.putInt("MONEY",playerState.money);
        editor.putInt("LOVE", playerState.count);
        editor.putInt("DOT_KINDS", playerState.dotSwitch);
        editor.putBoolean("ITEM_1", Item.item1);
        editor.commit();
    }

    public void imageSetting() throws InterruptedException {

        PlayerState playerState = new PlayerState();

        int switchN = imageSwitch;
        switch (switchN) {
            case 0:
                img.setImageResource(R.drawable.egg_start);
                break;
            case 1:
                img.setImageResource(R.drawable.egg_green);
                break;
            case 2:
                img.setImageResource(R.drawable.egg_purple);
                break;
            case 3:
                img.setImageResource(R.drawable.egg_red);
                break;
            case 4:
                img.setImageResource(R.drawable.egg_yellow);
                break;
            default:
                break;
        }
        /*System.err.println(playerState.count);
        if (dotImageKinds == 0 && playerState.count == 100) {
            if (eggcheck == 1) {
                int n = playerState.DotKinds(); //Playerstate' i = (int)(Math.random()*2) 값
                switch (0) { //초록색 3가지
                    case 0:
                        System.err.println("case0에 있음");
                        img.setImageResource(R.drawable.g1_j);
                        System.err.println("1초 전");
                        handler.postDelayed(new IncHandler(5, 1000, handler), 2000); //IncHandler(int incAmount, long time, Handler self)
                        System.err.println("1초 후"); // handler.postDelayed(new IncHandler(5, 1000, handler), 100);
                        dotImageKinds = 1; // 출력 값
                        playerState.dotImageKinds = dotImageKinds; //팝업창
                        System.err.println("case0의 팝업창 출력에 있음");
                        dotGrowth.start(); //소리
                        bg.setClickable(false); //화면터치
                        mhandler.postDelayed(mMytesk, 1000);

                        break;
                    case 1:
                        img.setImageResource(R.drawable.g2_e);
                        dotImageKinds = 2;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 2:
                        img.setImageResource(R.drawable.g3_m);
                        dotImageKinds = 3;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    default:
                        finish();
                        break;

                }
            } else if (eggcheck == 2) //1단계가 young_dot_1_4일때
            {
                int n = playerState.DotKinds();
                switch (n) { //보라색 3가지
                    case 0:
                        img.setImageResource(R.drawable.p1_m);
                        dotImageKinds = 4;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.p2_t);
                        dotImageKinds = 5;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 2:
                        img.setImageResource(R.drawable.p3_p);
                        dotImageKinds = 6;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    default:
                        finish();
                        break;

                }
            } else if (eggcheck == 3)
            {
                int n = playerState.FinalDotKinds();
                switch (n) { //빨강색 2가지
                    case 0:
                        img.setImageResource(R.drawable.r1_n);
                        dotImageKinds = 7;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.r2_c);
                        dotImageKinds = 8;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    default:
                        finish();
                        break;

                }
            }
            else if (eggcheck == 4) //1단계가 young_dot_3_sm 일때
            {
                int n = playerState.FinalDotKinds();
                switch (n) {
                    case 0:
                        img.setImageResource(R.drawable.y1_p);
                        dotImageKinds = 9;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.y2_t);
                        dotImageKinds = 10;
                        playerState.dotImageKinds = dotImageKinds;
                        dotGrowth.start();
                        bg.setClickable(false);
                        mhandler.postDelayed(mMytesk, 1000);
                        break;
                    default:
                        finish();
                        break;

                }
            }
        }
        */
        if (imageSwitch == 0 && playerState.count >= 20) { // 초기화면에서 두번째로 바뀔떄 / 10번 이상이면
            int n = playerState.StartDotKinds();
            switch (n) {
                case 0:
                    img.setImageResource(R.drawable.egg_green);
                    imageSwitch = 1;
                    break;
                case 1:
                    img.setImageResource(R.drawable.egg_purple);
                    imageSwitch = 2;
                    break;
                case 2:
                    img.setImageResource(R.drawable.egg_red);
                    imageSwitch = 3;
                    break;
                case 3:
                    img.setImageResource(R.drawable.egg_yellow);
                    imageSwitch = 4;
                    break;
                default:
                    finish();
                    break;
            }
        }
        if (imageSwitch > 0 && imageSwitch <= 4 && playerState.count >= 230) { // 두번쨰에서 세번째로 바뀔떄 / 250번 이상이면
            int i = playerState.count;
            int n = playerState.DotKinds();
            if (imageSwitch == 1) {
                eggcheck=1;
                if (i % 2 == 1) {
                    img.setImageResource(R.drawable.egg_green1);
                } else {
                    img.setImageResource(R.drawable.egg_green2);
                }
                if(playerState.count>=300){
                    if(n==0){
                        img.setImageResource(R.drawable.g1_j);
                        dotImageKinds = 1; // 출력 값
                        one++;
                       // hello.member(n);
                    }
                    else if(n==1){
                        img.setImageResource(R.drawable.g2_e);
                        dotImageKinds = 2; // 출력 값
                        two ++;
                    }
                    else if(n==2){
                        img.setImageResource(R.drawable.g3_m);
                        dotImageKinds = 3; // 출력 값
                        three ++;
                    }
                    playerState.dotImageKinds = dotImageKinds; //팝업창
                    //System.err.println("case0의 팝업창 출력에 있음");
                    dotGrowth.start(); //소리
                    bg.setClickable(false); //화면터치
                    mhandler.postDelayed(mMytesk, 1000);
                    playerState.count =0;
                    imageSwitch=0;
                }

            } else if (imageSwitch == 2) { //보라알 스위치
                eggcheck=2;
                if (i % 2 == 1) {
                    img.setImageResource(R.drawable.egg_purple1);

                } else {
                    img.setImageResource(R.drawable.egg_purple2);
                }
                if(playerState.count>=400){
                    if(n==0){
                        img.setImageResource(R.drawable.p1_m);
                        dotImageKinds = 4; // 출력 값
                        four ++;
                    }
                    else if(n==1){
                        img.setImageResource(R.drawable.p2_t);
                        dotImageKinds = 5; // 출력 값
                        five++;
                    }
                    else if(n==2){
                        img.setImageResource(R.drawable.p3_p);
                        dotImageKinds = 6; // 출력 값
                        six++;
                    }
                    playerState.dotImageKinds = dotImageKinds; //팝업창
                    dotGrowth.start(); //소리
                    bg.setClickable(false); //화면터치
                    mhandler.postDelayed(mMytesk, 1000);
                    playerState.count =0;
                    imageSwitch=0;
                }
            } else if (imageSwitch == 3) { // 두번쨰에서 세번째로 바뀔떄
                eggcheck=3;
                if (i % 2 == 1) {
                    img.setImageResource(R.drawable.egg_red1);
                } else {
                    img.setImageResource(R.drawable.egg_red2);
                }
                if(playerState.count>=400){
                    if(n==0){
                        img.setImageResource(R.drawable.r1_n);
                        dotImageKinds = 7; // 출력 값
                        seven++;
                    }
                    else if(n==1){
                        img.setImageResource(R.drawable.r2_c);
                        dotImageKinds = 8; // 출력 값
                        eight ++;
                    }
                    else if(n==2){
                        img.setImageResource(R.drawable.r3_l);
                        dotImageKinds = 9; // 출력 값
                        nine ++;
                    }
                    playerState.dotImageKinds = dotImageKinds; //팝업창
                    dotGrowth.start(); //소리
                    bg.setClickable(false); //화면터치
                    mhandler.postDelayed(mMytesk, 1000);
                    playerState.count =0;
                    imageSwitch=0;
                }
            } else if (imageSwitch == 4) { // 두번쨰에서 세번째로 바뀔떄 / 250번 이상이면
                eggcheck=4;
                if (i % 2 == 1) {
                    img.setImageResource(R.drawable.egg_yellow1);
                } else {
                    img.setImageResource(R.drawable.egg_yellow2);

                }
                if(playerState.count>=300){
                    if(n==0){
                        img.setImageResource(R.drawable.y1_p);
                        dotImageKinds = 10; // 출력 값
                        ten ++;
                    }
                    else if(n==1){
                        img.setImageResource(R.drawable.y2_t);
                        dotImageKinds = 11; // 출력 값
                        eleven++;
                    }
                    else if(n==2){
                        img.setImageResource(R.drawable.y3_k);
                        dotImageKinds = 12; // 출력 값
                        twelve++;
                    }
                    playerState.dotImageKinds = dotImageKinds; //팝업창
                    dotGrowth.start(); //소리
                    bg.setClickable(false); //화면터치
                    mhandler.postDelayed(mMytesk, 1000);
                    playerState.count =0;
                    imageSwitch=0;

                }
            }
        }
    }

    public void sellPopUp()
    {
        /*sellBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPopup = new Intent(getApplicationContext(), SellPopupActivity.class);
                startActivity(intentPopup);
            }
        });*/
        Intent intentPopup = new Intent(getApplicationContext(), SellPopupActivity.class);
        handler.removeMessages(0);
        startActivity(intentPopup);
        //       finish();
    }

   /* public void Reset()
    {
        dotImageKinds = playerState.dotSwitch;
        imageSwitch = playerState.dotImageKinds;
        //playerState.dotSwitch = imageSwitch;
        //playerState.dotImageKinds = dotImageKinds;

        //System.out.println( playerState.count + "/" + playerState.money + "/" + dotImageKinds + "/" + imageSwitch );
        imageSetting();
    }*/


    public void onBackPressed() { backPressCloseHandler.onBackPressed(); }
}
