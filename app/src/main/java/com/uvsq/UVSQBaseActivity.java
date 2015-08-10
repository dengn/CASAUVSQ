package com.uvsq;

import android.app.Activity;
import android.os.Bundle;

import de.greenrobot.event.EventBus;

/**
 * Created by etudiant on 06/08/2015.
 */
public abstract class UVSQBaseActivity extends Activity {

    UVSQMessageListener uvsqMessageListener;


    private String message="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setUvsqMessageListener(UVSQMessageListener uvsqMessageListener){
        this.uvsqMessageListener = uvsqMessageListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(MessageEvent event) {

        message = event.messsage;
        uvsqMessageListener.onMessageReceived(message);
    }
}
