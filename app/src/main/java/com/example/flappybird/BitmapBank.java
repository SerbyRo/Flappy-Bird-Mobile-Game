package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background;
    Bitmap[] bird;
    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = scaleImage(background);
        bird = new Bitmap[4];
        bird[0] = BitmapFactory.decodeResource(res, R.drawable.frame1);
        bird[1] = BitmapFactory.decodeResource(res, R.drawable.frame2);
        bird[2] = BitmapFactory.decodeResource(res, R.drawable.frame3);
        bird[3] = BitmapFactory.decodeResource(res, R.drawable.frame4);
    }
    public Bitmap getBackground(){
        return background;
    }

    public int getBackgroundWidth(){
        return background.getWidth();
    }

    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth()/getBackgroundHeight();

        int backgroundScaleWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap,backgroundScaleWidth, AppConstants.SCREEN_HEIGHT, false);
    }

    public Bitmap getBird(int frame) {
        return bird[frame];
    }

    public int getBirdWidth(){
        return bird[0].getWidth();
    }

    public int getBirdHeight(){
        return bird[0].getHeight();
    }
}
