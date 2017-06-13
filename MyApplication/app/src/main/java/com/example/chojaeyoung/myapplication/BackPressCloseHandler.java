package com.example.chojaeyoung.myapplication;


import android.app.Activity;
import android.provider.Settings;
import  android.widget.Toast;

public class BackPressCloseHandler {

    private  long backKeyPressedTime = 0;
    private Toast toast; //팝업창

    private  Activity activity;
    private  Activity activity1;

    public  BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public  void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) { //뒤로버튼 한번누를떄 showGuide 로 이동
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) //연속해서 두번누를 경우 종료
        {
            activity.finish(); //프로그램 종료
            toast.cancel();
        }
    }
    public int n;
    public int Dotmonster()
    {
        int i;
        i = (int)(Math.random()*3);
        return i;
    }

    public  void  showGuide() //토스트 메세지 출력(팝업창)
    {
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

    /*public void member(int n){
        switch(n){
            case 0:
                Toast toast1 = Toast.makeText(activity1, "잠만보 뽑기 성공!!!!", toast.LENGTH_SHORT);
                toast1.show();
            case 1:
                Toast toast2 = Toast.makeText(activity1, "잠만보 뽑기 성공!!!!", toast.LENGTH_SHORT);
                toast2.show();
            case 2:
                Toast toast3 = Toast.makeText(activity1, "잠만보 뽑기 성공!!!!", toast.LENGTH_SHORT);
                toast3.show();
            case 3:
                Toast toast4 = Toast.makeText(activity1, "잠만보 뽑기 성공!!!!", toast.LENGTH_SHORT);
                toast4.show();
            case 4:
                Toast toast5 = Toast.makeText(activity1, "잠만보 뽑기 성공!!!!", toast.LENGTH_SHORT);
                toast5.show();
            case 5:
                Toast toast6 = Toast.makeText(activity1, "잠만보 뽑기 성공!!!!", toast.LENGTH_SHORT);
                toast6.show();
            case 6:
                Toast toast7 = Toast.makeText(activity1, "잠만보 뽑기 성공!!!!", toast.LENGTH_SHORT);
                toast7.show();

        }
    }*/
}
