package com.wojon.gerry.snabbjob.Popup;

import com.wojon.gerry.snabbjob.R;

/**
 * Created by Gerry on 2018-01-02.
 */

public class PopupStyle {
    public float screenWidth,screenHeight;
    public int layout;
    protected PopupStyle(float screenWidth, float screenHeight, int layout){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.layout = layout;
    }
    public static final PopupStyle LOADING_POPUP = new PopupStyle(1,0.4f, R.layout.popupactivity);
    public static final PopupStyle NOTIFICATION_POPUP = new PopupStyle(0.8f,0.5f, R.layout.popupactivity);
}
