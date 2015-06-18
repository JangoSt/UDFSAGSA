package com.wirundich.kalorienrechner.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by mawi7191 on 18.06.2015.
 */
public class Indicator extends FrameLayout {
    int progress = 40;
    Paint mPaint;
    float progressToFloat;
    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        setWillNotDraw(false);
        setStyle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());
        canvas.drawRect(0,0, getWidth(),getHeight(),mPaint);
    }
    public void setStyle(){
        if(progress<50 ){
            progressToFloat = (float) (progress/50.0);

            mPaint.setColor(Color.argb(255, (int) (255*(progressToFloat)), 255, 120));
        }
        else{
            progressToFloat = (float) ((50-(progress-50))/50.0);
            Log.d(this.getClass().getSimpleName(),"Progress to float = "+progressToFloat);
            mPaint.setColor(Color.argb(255,   255, (int) (255*(progressToFloat)),120));
        }

    }
    public void setProgress(int progress){
        this.progress = progress;
        setStyle();
        Indicator.this.invalidate();
    }
    public int getProgress(){
        return progress;
    }
}
