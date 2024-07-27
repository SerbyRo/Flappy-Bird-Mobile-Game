package com.example.flappybird;

import android.graphics.Canvas;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
    SurfaceHolder surfaceHolder;
    boolean isRunning;
    long startTime;   // Flag to detect whether the tread is running or not
    long loopTime;  // Loop start time and loop duration
    long DELAY = 33;  // delay in miliseconds between screen refreshes
    public GameThread(SurfaceHolder surfaceHolder){
            this.surfaceHolder = surfaceHolder;
            isRunning = true;
    }

    @Override
    public void run(){
        // Looping until the boolean is false
        while(isRunning){
            startTime = SystemClock.uptimeMillis();

            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas != null){
                synchronized (surfaceHolder){
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().upgradeAndDrawBird(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            loopTime = SystemClock.uptimeMillis()-startTime;
            if (loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch (InterruptedException e){
                    Log.e("Interrupted","Interrupted while sleeping");
                }

            }
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setIsRunning(boolean state){
        isRunning = state;
    }
}
