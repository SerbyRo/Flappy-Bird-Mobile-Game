package com.example.flappybird;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;

    public GameView(Context context) {
        super(context);
        initView();
    }


        void initView(){
            SurfaceHolder holder = getHolder();
            holder.addCallback(this);
            setFocusable(true);
            gameThread = new GameThread(holder);
        }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            AppConstants.getGameEngine().gameState = 1;
            AppConstants.getGameEngine().bird.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
        }

        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (!gameThread.isRunning()){
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        }else{
            gameThread.start();
        }

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (gameThread.isRunning()) {
            gameThread.setIsRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }
    }

}

