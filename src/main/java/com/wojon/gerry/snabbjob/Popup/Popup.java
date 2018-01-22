package com.wojon.gerry.snabbjob.Popup;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.wojon.gerry.snabbjob.Client;
import com.wojon.gerry.snabbjob.Const;
import com.wojon.gerry.snabbjob.R;

/**
 * Created by Gerry on 2018-01-01.
 */

public class Popup {
    protected static PopupWindow window;
    public static void showPopup(Activity activity, PopupStyle style){
        LayoutInflater inflater = (LayoutInflater)
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(style.layout, null);
        //popupView.startAnimation(AnimationUtils.loadAnimation(activity, R.style.PopupAnimation));
        window = new PopupWindow(popupView, (int)(Const.SCREEN_SIZE.widthPixels * style.screenWidth), (int)(Const.SCREEN_SIZE.heightPixels * style.screenHeight), true);
        window.setAnimationStyle(R.style.PopupAnimation);
        window.showAtLocation(activity.findViewById(R.id.mainLayout), Gravity.CENTER,0,0);
    }
    public static void closePopup(){
        window.dismiss();
    }
}
