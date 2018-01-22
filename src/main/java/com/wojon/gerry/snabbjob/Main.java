package com.wojon.gerry.snabbjob;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.wojon.gerry.snabbjob.Activities.LoginActivity;
import com.wojon.gerry.snabbjob.Activities.RegisterActivity;
import com.wojon.gerry.snabbjob.Popup.Popup;
import com.wojon.gerry.snabbjob.Popup.PopupStyle;

import android.widget.*;

public class Main extends Activity {
    public static Client client;

    private MapsActivity map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(Const.SCREEN_SIZE);
        setContentView(R.layout.activity_main);
        new Connection().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this);
        final Button loginButton = (Button)findViewById(R.id.Login);
        final Button registerButton = (Button)findViewById(R.id.signup);
        final EditText pWord = null;
        final EditText uName = null;
        //loginButton.setWidth(uName.getWidth());
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Main.this, LoginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(R.anim.activity_slide_in_left,R.anim.activity_slide_out_right);
                /*
                PacketData data = new PacketData(PacketHandler.PacketID.LOGIN);
                data.addString(uName.getText().toString());
                data.addString(pWord.getText().toString());
                sendData(data);
                ValueAnimator anim = ValueAnimator.ofInt(loginButton.getMeasuredWidth(), loginButton.getMeasuredHeight());
                Popup.showPopup(Main.this, PopupStyle.LOADING_POPUP);
                */
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Main.this, RegisterActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(R.anim.activity_slide_in_right,R.anim.activity_slide_out_left);
            }
        });
        //Tools.Log(client.wew() + "");
        //new SendData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, PacketHandler.PacketID.LOGIN);
        //Intent intent = new Intent(this, Maps.class);
        //intent.putExtra("wew",5);
        //this.startActivity(intent);
    }
    @Override
    protected void onRestart() {
        super.onRestart();

        client.netHandler.setActivity(this);
    }
    private void sendData(PacketData data){
        new SendData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data);
    }
    public class Connection extends AsyncTask<Activity, Packet, Client>{
        @Override
        protected Client doInBackground(Activity... params) {
            client = new Client(new Client.MessageHandler(){

                @Override
                public void onMessageReceive(Packet packet) {
                    publishProgress(packet);
                }
            });
            client.netHandler.setActivity(params[0]);
            client.init();
            return null;
        }

        @Override
        protected void onProgressUpdate(Packet... values) {
            super.onProgressUpdate(values);
            for (int i = 0; i < values.length; i++){
                values[i].executePacket(client.netHandler);
            }

        }
    }
    public class SendData extends AsyncTask<PacketData, Packet, Client>{


        @Override
        protected Client doInBackground(PacketData... params) {
            for(int i = 0; i < params.length; i++) {
                client.sendPacket(params[i]);
            }
            return null;
        }
    }
}
