package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FlashActivity extends AppCompatActivity {

    Animation animation1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        ImageView image_splash = (ImageView) findViewById(R.id.image_splash);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movingroundobject);
        image_splash.startAnimation(animation1);


        new Handler().postDelayed(new Runnable() { // from class: com.uvapay.activities.SplashScreenActivity.1
            @Override
            public void run() {
                FlashActivity.this.startActivity(new Intent(FlashActivity.this,MainActivity.class));
                FlashActivity.this.finish();
            }
        }, 3500L);

    }
}