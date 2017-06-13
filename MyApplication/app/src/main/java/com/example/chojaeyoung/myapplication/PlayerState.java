package com.example.chojaeyoung.myapplication;

public class PlayerState {
    public static int money = 1000;
    public static int count = 0;
    public static int dotImageKinds = 0;
    public static int dotSwitch = 0;
    //public static boolean dotReset = false;

    public int StartDotKinds(){
        int i;
        i = (int)(Math.random()*4);
        return i;
    }

    public int DotKinds()
    {
        int i;
        i = (int)(Math.random()*3);
        return i;
    }

    public int FinalDotKinds()
    {
        int i;
        i = (int)(Math.random()*2);
        return i;
    }
}
