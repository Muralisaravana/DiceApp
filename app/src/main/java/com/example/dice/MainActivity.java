package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private AppCompatImageView aci_Dice;
    private Random random = new Random();
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aci_Dice = (AppCompatImageView)findViewById(R.id.aci_Dice);
        aci_Dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uruttu();
            }
        });
    }
    private void uruttu()
    {
        final Animation anim1= AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {
                play();
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                stop();
                int randomNumber = random.nextInt(6)+1;//gives rndm num b/w 1 and 6
                switch (randomNumber)
                {
                    case 1:
                        aci_Dice.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        aci_Dice.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        aci_Dice.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        aci_Dice.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        aci_Dice.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        aci_Dice.setImageResource(R.drawable.dice6);
                        break;
                    default:
                        aci_Dice.setImageResource(R.drawable.dice1);
                        break;

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        anim1.setAnimationListener(animationListener);
        aci_Dice.startAnimation(anim1);

    }

    private void play()
    {
       if (mediaPlayer==null)
       {
           mediaPlayer = MediaPlayer.create(this,R.raw.rolling);
           mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
               @Override
               public void onCompletion(MediaPlayer mediaPlayer) {
                   stopPlayer();
               }
           });
       }
       mediaPlayer.start();
    }
    private void stop()
    {
         stopPlayer();
    }
    private void stopPlayer()
    {
        if (mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}