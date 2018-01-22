package com.wojon.gerry.snabbjob;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;

import com.wojon.gerry.snabbjob.Popup.Popup;

/**
 * Created by Gerry on 2017-12-31.
 */

public class NetHandler {
    private Activity currentActivity;
    public void setActivity(Activity activity){
        this.currentActivity = activity;

    }
    public void handlePacketLogin(PacketLogin packet){

        Tools.Log((packet.valid & 0b01) + " : " + ((packet.valid & 0b10) >> 1));
        if((packet.valid & 0b01) == Const.VALID_LOGIN) {
            if(((packet.valid & 0b10) >> 1) == Const.FIRST_LOGIN){
                Tools.changeActivity(currentActivity, NewUser.class, AnimationStyle.ACITIVTY_ANIMATE_SLIDEFADE);
            }else {
                Tools.Log("currentActivity: " + this.currentActivity);
                /*
                Intent intent = new Intent(currentActivity, MapsActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(currentActivity.getBaseContext(),R.anim.popupanim,R.anim.popupanim_close);
                currentActivity.startActivity(intent,options.toBundle());
                */
                Tools.changeActivity(currentActivity, MapsActivity.class, AnimationStyle.ACITIVTY_ANIMATE_SLIDEFADE);
            }
        }
        Popup.closePopup();
    }
}
