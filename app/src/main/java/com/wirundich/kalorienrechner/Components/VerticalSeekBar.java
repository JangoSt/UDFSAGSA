package com.wirundich.kalorienrechner.Components;

/**
 * Created by Matze on 12.03.2015.
*/
        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.drawable.Drawable;
        import android.util.AttributeSet;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.widget.SeekBar;

        import com.wirundich.kalorienrechner.R;

public class VerticalSeekBar extends SeekBar {
    public VerticalSeekBar(Context context) {
        super(context);
        setThumb(getResources().getDrawable(R.drawable.spinner));
        setThumbOffset(60);
    }
    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setThumb(getResources().getDrawable(R.drawable.spinner));
        setThumbOffset(60);
    }
    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setThumb(getResources().getDrawable(R.drawable.spinner));
        setThumbOffset(60);
    }
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
        setThumb(getResources().getDrawable(R.drawable.spinner));
        setThumbOffset(60);
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }
    protected void onDraw(Canvas c) {

        c.rotate(-90);
        c.translate(-getHeight(),0);
        super.onDraw(c);

        Paint paint = new Paint();

        paint.setColor(getResources().getColor(R.color.AppThemeColor));
        int yValue = (getHeight()-getThumbOffset())/getMax()*getProgress();
        Log.i("Canvas draw",c.getWidth()+" , "+c.getHeight()+" , "+getPaddingTop() +" , ");
        c.rotate(90);
        paint.setTextSize(40);
        c.drawText(this.getProgress()+"",0,-yValue,paint);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                int i=0;
                i=getMax() - (int) (getMax() * event.getY() / getHeight());
                setProgress(i);
               // Log.i("Progress",getProgress()+"");
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}