package com.wojon.gerry.snabbjob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Gerry on 2018-01-02.
 */

public abstract class ActivityLayout extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.client.netHandler.setActivity(this);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Main.client.netHandler.setActivity(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
