package com.wojon.gerry.snabbjob;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Gerry on 2017-12-20.
 */

public class Tools {


    public static void Log(String msg){
        Log.i("MSG",msg);
    }

    public static void changeActivity(Activity currentActivity, Class newActivity, AnimationStyle animation){
        Intent intent = new Intent(currentActivity, newActivity);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(currentActivity.getBaseContext(),animation.getAnimIn(),animation.getAnimOut());
        currentActivity.startActivity(intent,options.toBundle());
    }
}
enum AnimationStyle{
    ACITIVTY_ANIMATE_SLIDEFADE(R.anim.popupanim,R.anim.popupanim_close);
    //public static final int[] ACITIVTY_ANIMATE_SLIDEFADE = new int[]{R.anim.popupanim,R.anim.popupanim_close};
    private int[] animations = new int[2];
    AnimationStyle(int animIn,int animOut){
        animations[0] = animIn;
        animations[1] = animOut;
    }
    public int getAnimIn(){
        return animations[0];
    }
    public int getAnimOut(){
        return animations[1];
    }
}
