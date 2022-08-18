package com.example.praiveitcontacts;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Credits extends AppCompatActivity {

    private TextView textView;
    private Animation animation;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.credits);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("CREDITOS /n "+"Aaron 1 - NAME \n"+"Abner 2 - NAME \n"+"Denis 3 - NAME \n"+"Kelly 4 - NAME \n"+"Nelly 5 - NAME \n");

        textView.startAnimation(animation);
}
}